package com.tuodao.bp.operation.service.impl;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import com.google.common.collect.Range;
import com.tuodao.bp.model.business.operation.input.InverstmentInput;
import com.tuodao.bp.model.business.operation.input.InverstmentQueryInput;
import com.tuodao.bp.model.business.operation.output.InverstmentOutput;
import com.tuodao.bp.model.business.operation.output.InverstmentQueryOutput;
import com.tuodao.bp.model.constant.PublicConstant;
import com.tuodao.bp.operation.constant.OperationBizConstant;
import com.tuodao.bp.operation.constant.OperationRespExceptionConstant;
import com.tuodao.bp.operation.persistence.mapper.basic.*;
import com.tuodao.bp.operation.persistence.model.basic.*;
import com.tuodao.bp.operation.service.IInverstmentService;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * 抽奖service
 * author hechuan
 * <p>
 * created on 2017/9/28
 * <p>
 * since V1.0.0
 */
@Service
public class InverstmentServiceImpl implements IInverstmentService {

    // 日志
    private static final Logger logger = LoggerFactory.getLogger(InverstmentServiceImpl.class);

    @Autowired
    private OpScoreDrawPrizeMapper opScoreDrawPrizeMapper;

    @Autowired
    private OpUserDrawPrizeResultMapper opUserDrawPrizeResultMapper;

    @Autowired
    private OpUserScoreMapper opUserScoreMapper;

    @Autowired
    private OpUserScoreDetailMapper opUserScoreDetailMapper;

    @Autowired
    private OpUserOperationDataMapper opUserOperationDataMapper;

    @Autowired
    private OpUserDiscountMapper opUserDiscountMapper;

    /**
     *
     * 查询抽奖列表
     *
     * @param input
     * @return
     */
    @Override
    public List<InverstmentQueryOutput> getInverstQueryList(InverstmentQueryInput input) {
        OpScoreDrawPrizeExample example = new OpScoreDrawPrizeExample();
        example.createCriteria().andDrawTypeEqualTo(input.getInverstType()).andIsDelEqualTo(PublicConstant.DEL_NO);
        List<OpScoreDrawPrize> queryList = opScoreDrawPrizeMapper.selectByExample(example);
        List<InverstmentQueryOutput> resultList = queryList.stream().map(prize ->{
                InverstmentQueryOutput out = new InverstmentQueryOutput();
                BeanUtils.copyProperties(prize,out);
                return out;
        }).collect(Collectors.toList());
        logger.info("查询抽奖列表 resultList.size = [{}],DrawType:[{}],用户ID：[{}]",resultList.size(),input.getInverstType(),input.getUserId());
        return resultList;
    }

    /**
     * 获取抽奖结果
     * 修改用户积分总数，修改用户积分明细数量
     * 保存用户奖品关系
     *
     * @param input
     * @return
     */
    @Transactional
    @Override
    public InverstmentOutput getInverstResult(InverstmentInput input) {
        OpScoreDrawPrizeExample example = new OpScoreDrawPrizeExample();
        example.createCriteria().andDrawTypeEqualTo(input.getInverstType()).andIsDelEqualTo(PublicConstant.DEL_NO);
        List<OpScoreDrawPrize> queryList = opScoreDrawPrizeMapper.selectByExample(example);
        // 排序-从小到大
        queryList.sort((a,b) ->a.getWinRate().compareTo(b.getWinRate()));
        logger.info("查询结果列表排序，从小到大，queryList.size:[{}]",queryList.size());

        // 组装数据
        Map<Range<BigDecimal>,InverstmentOutput> map = collectMap(queryList);
        logger.info("获取范围及返回组装，map:[{}]",map);

        // 抽奖
        InverstmentOutput out = getInverstmentOutput(map);
        logger.info("计算抽奖返回结果.out:[{}]",out);

        // 更新用户积分总数
        updateUserScore(input,out);

        // 保存积分明细
        saveScoreDetail(input,out);

        // 更新用户运营数据
        updateUserOperationData(input,out);

        // 保存用户优惠券
        saveUserDiscount(input,out);

        // 保存用户奖品
        saveUserPrize(input, out);
        logger.info("保存用户抽奖结果入库");

        return out;
    }

    private void saveUserPrize(InverstmentInput input, InverstmentOutput out) {
        Date now = new Date();
        OpUserDrawPrizeResult record = new OpUserDrawPrizeResult();
        record.setPrizeName(out.getPrizeName());
        record.setGmtCreate(now);
        record.setGmtCreator(input.getUserId());
        record.setGmtModify(now);
        record.setGmtModifier(input.getUserId());
        record.setHanppenTime(now);
        record.setUserId(input.getUserId());
        record.setUserMobile(input.getMobile());
        record.setIsDel(PublicConstant.DEL_NO);
        record.setConsumeScore(input.getScore());
        record.setStatus(Objects.equal(out.getPrizeType(),OperationBizConstant.PRIZE_TYPE_SHIWU) ? OperationBizConstant.PRIZE_RESULT_STATUS_WEIFAFANG : OperationBizConstant.PRIZE_RESULT_STATUS_YIFAFANG);
        opUserDrawPrizeResultMapper.insert(record);
    }


    /**
     * 保存用户，优惠券信息
     * @param input
     * @param out
     */
    private void saveUserDiscount(InverstmentInput input, InverstmentOutput out){

        // 抵用
        if (Objects.equal(out.getPrizeType(),OperationBizConstant.PRIZE_TYPE_DIYONG)
                // 加息
                || Objects.equal(out.getPrizeType(),OperationBizConstant.PRIZE_TYPE_JIAXI)){

            OpUserDiscount record = new OpUserDiscount();
            record.setUserId(input.getUserId());
            record.setUserMobile(input.getMobile());
            record.setDiscountTitle(out.getPrizeName());
            if(Objects.equal(out.getPrizeType(),OperationBizConstant.PRIZE_TYPE_DIYONG)){
                // 抵用
                record.setDiscountType(OperationBizConstant.DISCOUNT_TYPE_VOUCHER);
            }else{
                // 加息
                record.setDiscountType(OperationBizConstant.DISCOUNT_TYPE_COUPON);
            }
            Date now = new Date();
            record.setEffectDate(now);
            record.setEffectDay(OperationBizConstant.INVERSTMENT_DISCOUNT_EFFECT_DAY);
            record.setInvalidDate(TimeUtils.addDay(now,OperationBizConstant.INVERSTMENT_DISCOUNT_EFFECT_DAY));
            record.setSource(OperationBizConstant.INVERSTMENT_DISCOUNT_SOURCE);
            record.setMoneyLimit(out.getMoneyLimit());
            record.setDateLimit(out.getDateLimit());
            record.setDiscountAvailable(out.getPrizeValue().toString());
            record.setDisStatus(OperationBizConstant.DISCOUNT_STATUS_USABLE);
            record.setDisLock(OperationBizConstant.DISCOUNT_LOCK_1);
            record.setDiscountStyle(OperationBizConstant.DISCOUNT_STYLE_QIANTAI);
            record.setGmtCreator(PublicConstant.SYSTEM_CREATOR);
            record.setGmtModifier(PublicConstant.SYSTEM_MODIFIER);

            opUserDiscountMapper.insertSelective(record);
        }



    }

    /**
     * 组装数据
     * @param queryList
     * @return
     */
    private Map<Range<BigDecimal>,InverstmentOutput> collectMap(List<OpScoreDrawPrize> queryList) {
        Map<Range<BigDecimal>,InverstmentOutput> map = Maps.newHashMap();
        BigDecimal prev = null;
        InverstmentOutput out = null;
        for(OpScoreDrawPrize op : queryList){
            out = new InverstmentOutput();
            BeanUtils.copyProperties(op,out);
            BigDecimal value = calcValue(op.getWinRate());
            if(null == prev){
                map.put(Range.closedOpen(BigDecimal.ZERO, value),out);
                prev = value;
            }else{
                map.put(Range.closedOpen(prev,prev.add(value)),out);
                prev = prev.add(value);
            }
        }
        return map;
    }

    /**
     * 获取抽奖结果
     * @param map
     * @return
     */
    private InverstmentOutput getInverstmentOutput(Map<Range<BigDecimal>,InverstmentOutput> map){
        Random random = new Random();
        int rant = random.nextInt(10000);
        BigDecimal mal = new BigDecimal(rant);
        for(Map.Entry<Range<BigDecimal>,InverstmentOutput> entry : map.entrySet()){
            if(entry.getKey().contains(mal)){
                return entry.getValue();
            }
        }
        return new InverstmentOutput();
    }

    private BigDecimal calcValue(BigDecimal v){
        return v.multiply(new BigDecimal(10000)).setScale(0);
    }


    /**
     * 更新用户积分总数
     * @param input
     * @param out
     */
    private void updateUserScore(InverstmentInput input,InverstmentOutput out) {
        OpUserScoreExample example = new OpUserScoreExample();
        example.createCriteria().andUserIdEqualTo(input.getUserId()).andIsDelEqualTo(PublicConstant.DEL_NO);
        List<OpUserScore> opUserScoreList = opUserScoreMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(opUserScoreList) || opUserScoreList.size() != 1){
            logger.info("用户积分抽奖-用户积分[OP_USER_SCORE]表数据有问题，应该有一条记录，但现在却不是,opUserScoreList.size :[{}]",opUserScoreList.size());
            throw new BizFeignException(OperationRespExceptionConstant.SCORE_EXCHANGE_USER_SCORE_ERROR);
        }
        OpUserScore opUserScoreSelect = opUserScoreList.get(0);


        if(opUserScoreSelect.getScoreCurrent() < input.getScore()){
            logger.info("用户积分抽奖-当前可用积分:[{}],小于抽奖所需积分:[{}]",opUserScoreSelect.getScoreCurrent(),input.getScore());
            throw new BizFeignException(OperationRespExceptionConstant.INVERST_USER_SCORE_LESS_THAN_CHANGE_ERROR);
        }

        OpUserScore record = new OpUserScore();
        record.setGmtModifier(input.getUserId());
        // 已使用积分增加
        record.setScoreUsed(opUserScoreSelect.getScoreUsed() + input.getScore());
        // 可使用积分减少
        record.setScoreCurrent(opUserScoreSelect.getScoreCurrent() - input.getScore());

        // 如果抽奖抽到的也是积分，又要加到可使用积分这里
        if(Objects.equal(out.getPrizeType(),OperationBizConstant.PRIZE_TYPE_JIFENG)){
            record.setScoreCurrent(record.getScoreCurrent() + out.getPrizeValue());
        }
        opUserScoreMapper.updateByExampleSelective(record,example);

        logger.info("用户积分抽奖-更新用户积分总数-成功");
    }

    /**
     * 保存积分明细
     * @param input
     * @param out
     */
    private void saveScoreDetail(InverstmentInput input,InverstmentOutput out) {
        OpUserScoreDetail outDetail = new OpUserScoreDetail();
        outDetail.setUserMobile(input.getMobile());
        outDetail.setUserId(input.getUserId());
        outDetail.setScore(-input.getScore());
        outDetail.setGmtCreator(input.getUserId());
        outDetail.setGmtModifier(input.getUserId());
        // 消耗
        outDetail.setType(OperationBizConstant.ACORE_TYPE_SUBTRACT);
        outDetail.setSource("积分抽奖");
        opUserScoreDetailMapper.insertSelective(outDetail);

        if(Objects.equal(out.getPrizeType(),OperationBizConstant.PRIZE_TYPE_JIFENG)){
            // 如果抽到积分，也要记入积分明细
            OpUserScoreDetail inDetail = new OpUserScoreDetail();
            inDetail.setUserMobile(input.getMobile());
            inDetail.setUserId(input.getUserId());
            inDetail.setScore(out.getPrizeValue());
            inDetail.setGmtCreator(input.getUserId());
            inDetail.setGmtModifier(input.getUserId());
            // 收入
            inDetail.setType(OperationBizConstant.ACORE_TYPE_ADD);
            inDetail.setSource("积分抽奖");
            opUserScoreDetailMapper.insertSelective(inDetail);
        }

        logger.info("用户积分抽奖-保存积分明细-成功");
    }


    /**
     * 更新用户运营数据
     * @param input
     * @param out
     */
    private void updateUserOperationData(InverstmentInput input,InverstmentOutput out) {
        OpUserOperationDataExample example = new OpUserOperationDataExample();
        example.createCriteria().andUserIdEqualTo(input.getUserId());
        List<OpUserOperationData> opUserOperationDataList = opUserOperationDataMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(opUserOperationDataList) || opUserOperationDataList.size() != 1){
            logger.info("用户积分抽奖-用户积分[OP_USER_OPERATION_DATA]表数据有问题，应该有一条记录，但现在却不是,opUserOperationDataList.size :[{}]",opUserOperationDataList.size());
            throw new BizFeignException(OperationRespExceptionConstant.SCORE_EXCHANGE_USER_OPERATION_DATA_ERROR);
        }
        OpUserOperationData opUserOperationDataSelect = opUserOperationDataList.get(0);

        OpUserOperationData opUserOperationData = new OpUserOperationData();
        // 抽奖使用了积分
        opUserOperationData.setUsableScores(opUserOperationDataSelect.getUsableScores() - input.getScore());
        // 抽奖又抽到了积分
        if(Objects.equal(out.getPrizeType(),OperationBizConstant.PRIZE_TYPE_JIFENG)){
            opUserOperationData.setUsableScores(opUserOperationData.getUsableScores() + out.getPrizeValue());
        }
        opUserOperationDataMapper.updateByExampleSelective(opUserOperationData,example);

        logger.info("用户积分抽奖-更新用户运营数据 - 成功");
    }

   /* public static void main(String[] args) {
        List<OpScoreDrawPrize> queryList = Lists.newArrayList();
        OpScoreDrawPrize op1 = new OpScoreDrawPrize();
        op1.setPrizeName("op1");
        op1.setWinRate(BigDecimal.valueOf(6000L));
        queryList.add(op1);

        OpScoreDrawPrize op2 = new OpScoreDrawPrize();
        op2.setPrizeName("op2");
        op2.setWinRate(BigDecimal.valueOf(2000L));
        queryList.add(op2);

        OpScoreDrawPrize op3 = new OpScoreDrawPrize();
        op3.setPrizeName("op3");
        op3.setWinRate(BigDecimal.valueOf(1000L));
        queryList.add(op3);

        OpScoreDrawPrize op4 = new OpScoreDrawPrize();
        op4.setPrizeName("op4");
        op4.setWinRate(BigDecimal.valueOf(800L));
        queryList.add(op4);

        OpScoreDrawPrize op5 = new OpScoreDrawPrize();
        op5.setPrizeName("op5");
        op5.setWinRate(BigDecimal.valueOf(200L));
        queryList.add(op5);


        queryList.sort((a,b) ->a.getWinRate().compareTo(b.getWinRate()));

        for(OpScoreDrawPrize os : queryList){
            out.println(os.getPrizeName() + "," + os.getWinRate());
        }

        Map<Range<BigDecimal>,String> map = Maps.newHashMap();

        BigDecimal prev = null;
        for(OpScoreDrawPrize op : queryList){
            if(null == prev){
                map.put(Range.closedOpen(BigDecimal.ZERO,op.getWinRate()),op.getPrizeName());
                prev = op.getWinRate();
            }else{
                map.put(Range.closedOpen(prev,prev.add(op.getWinRate())),op.getPrizeName());
                prev = prev.add(op.getWinRate());
            }
        }


        System.out.println(map);

        HashMultiset<String> sets = HashMultiset.create();

        Random random = new Random();
        for(int i = 0 ; i < 10000 ; i++){
            int x = random.nextInt(10000);
            BigDecimal mal = new BigDecimal(x);

            map.entrySet().stream().filter(entry -> entry.getKey().contains(mal)).forEach(entry -> {
                sets.add(entry.getValue());
            });

        }


        Map<String, List<String>> hmMap = sets.stream().collect(Collectors.groupingBy(s -> s.toString()));
        out.println(hmMap.get("op1").size()); // 6000
        out.println(hmMap.get("op2").size()); // 2000
        out.println(hmMap.get("op3").size()); // 1000
        out.println(hmMap.get("op4").size()); // 800
        out.println(hmMap.get("op5").size()); // 200

    }*/



}
