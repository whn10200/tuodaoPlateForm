package com.tuodao.bp.operation.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.PagePojo;
import com.tuodao.bp.model.business.operation.input.ScoreExchangeInput;
import com.tuodao.bp.model.business.operation.output.MyScoreOutput;
import com.tuodao.bp.model.business.operation.output.ScoreDetailOutput;
import com.tuodao.bp.model.business.operation.output.ScoreMallOutput;
import com.tuodao.bp.model.constant.PublicConstant;
import com.tuodao.bp.model.constant.operation.ScoreExchangeConstant;
import com.tuodao.bp.operation.constant.OperationBizConstant;
import com.tuodao.bp.operation.constant.OperationRespExceptionConstant;
import com.tuodao.bp.operation.persistence.mapper.basic.*;
import com.tuodao.bp.operation.persistence.model.basic.*;
import com.tuodao.bp.operation.service.IScoreMallService;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * author hechuan
 * <p>
 * created on 2017/9/26
 * <p>
 * since V1.0.0
 */
@Service
public class ScoreMallServiceImpl implements IScoreMallService {

    // 日志
    private static final Logger logger = LoggerFactory.getLogger(ScoreMallServiceImpl.class);

    @Autowired
    private OpScoreMallMapper opScoreMallMapper;

    @Autowired
    private OpUserScoreMapper opUserScoreMapper;

    @Autowired
    private OpUserScoreDetailMapper opUserScoreDetailMapper;

    @Autowired
    private OpUserDiscountMapper opUserDiscountMapper;

    @Autowired
    private OpUserOperationDataMapper opUserOperationDataMapper;

    /**
     * @see IScoreMallService#getScoreMallPaged(PagePojo)
     * @param input 分页信息
     * @return
     */
    @Override
    public PageInfo<ScoreMallOutput> getScoreMallPaged(PagePojo input) {
        OpScoreMallExample example = new OpScoreMallExample();

        PageHelper.startPage(input.getCurrentPage(),input.getPageSize());
        List<OpScoreMall> queryList = opScoreMallMapper.selectByExample(example);

        logger.info("积分商城-兑换专区-queryList.size : [{}]",queryList.size());

        // 转换
        List<ScoreMallOutput> resultList = queryList.stream().map(mall -> {
            ScoreMallOutput out = new ScoreMallOutput();
            BeanUtils.copyProperties(mall, out);
            return out;
        }).collect(Collectors.toList());

        // 返回
        PageInfo<ScoreMallOutput> result = new PageInfo<>(resultList);
        Page<OpScoreMall> page = (Page<OpScoreMall>)queryList;
        result.setTotal(page.getTotal());
        result.setPages(page.getPages());
        return result;
    }

    /**
     * @see IScoreMallService#getMyScoreStatistic(BasePojo)
     * @param input 用户信息
     * @return
     */
    @Override
    public MyScoreOutput getMyScoreStatistic(BasePojo input) {
        OpUserScoreExample example = new OpUserScoreExample();
        OpUserScoreExample.Criteria cc = example.createCriteria();
        cc.andUserIdEqualTo(input.getUserId())
                .andUserMobileEqualTo(input.getMobile())
                .andIsDelEqualTo(PublicConstant.DEL_NO);
        List<OpUserScore> queryList = opUserScoreMapper.selectByExample(example);

        // 如果没有用户相关积分，则设置默认全为0
        if(CollectionUtils.isEmpty(queryList)){
            logger.info("没有相关用户积分，一切为默认值 为 0");
            return MyScoreOutput.create().setScopeExpireYear(0).setScoreCurrent(0).setScoreTotal(0).setScoreUsed(0);
        }
        // 如果有，则直接copy
        MyScoreOutput out = MyScoreOutput.create();
        OpUserScore opUserScore = queryList.get(0);
        BeanUtils.copyProperties(opUserScore,out);
        return out;
    }

    /**
     * 1-查询积分商城-比对相关输入数据是否合法
     * 2-减少用户积分明细，减少用户积分总数
     * 3-增加用户免费提现次数或加息券数量
     * 4-更新运营数据表记录
     * @see IScoreMallService#scoreExchange(ScoreExchangeInput)
     * @param input 兑换信息
     * @return
     */
    @Transactional
    @Override
    public boolean scoreExchange(ScoreExchangeInput input) {
        OpScoreMall opScoreMall = opScoreMallMapper.selectByPrimaryKey(input.getId());
        // 查询积分商城
        paramScoreMallCheck(input,opScoreMall);
        // 更新用户积分总数
        updateUserScore(input);
        // 保存积分明细
        saveScoreDetail(input, opScoreMall);
        // 保存用户优惠券或免费提现次数
        saveUserDiscount(input, opScoreMall);
        // 更新用户运营数据
        updateUserOperationData(input);

        logger.info("积分兑换成功..");
        return true;
    }

    /**
     * 我的积分-积分明细列表
     *
     * @param input 用户信息
     * @return 积分明细列表
     */
    @Override
    public PageInfo<ScoreDetailOutput> getMyScoreDetailPaged(PagePojo input) {
        OpUserScoreDetailExample example = new OpUserScoreDetailExample();
        example.createCriteria().andUserIdEqualTo(input.getUserId()).andIsDelEqualTo(PublicConstant.DEL_NO);

        // 分页查询
        PageHelper.startPage(input.getCurrentPage(),input.getPageSize());
        List<OpUserScoreDetail> queryList = opUserScoreDetailMapper.selectByExample(example);

        // 组装返回
        List<ScoreDetailOutput> resultList = queryList.stream().map(detail -> {
            ScoreDetailOutput out = new ScoreDetailOutput();
            BeanUtils.copyProperties(detail, out);
            return out;
        }).collect(Collectors.toList());
        PageInfo<ScoreDetailOutput> pageInfo = new PageInfo<>(resultList);
        Page<OpUserScoreDetail> page = (Page<OpUserScoreDetail>)queryList;
        pageInfo.setPages(page.getPages());
        pageInfo.setTotal(page.getTotal());

        logger.info("我的积分明细列表查询成功! pageInfo:[{}]",pageInfo);
        return pageInfo;
    }


    /**
     * 更新用户运营数据
     * @param input
     */
    private void updateUserOperationData(ScoreExchangeInput input) {
        OpUserOperationDataExample example = new OpUserOperationDataExample();
        example.createCriteria().andUserIdEqualTo(input.getUserId());
        List<OpUserOperationData> opUserOperationDataList = opUserOperationDataMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(opUserOperationDataList) || opUserOperationDataList.size() != 1){
            logger.info("用户积分[OP_USER_OPERATION_DATA]表数据有问题，应该有一条记录，但现在却不是,opUserOperationDataList.size :[{}]",opUserOperationDataList.size());
            throw new BizFeignException(OperationRespExceptionConstant.SCORE_EXCHANGE_USER_OPERATION_DATA_ERROR);
        }
        OpUserOperationData opUserOperationDataSelect = opUserOperationDataList.get(0);

        OpUserOperationData opUserOperationData = new OpUserOperationData();
        opUserOperationData.setUsableScores(opUserOperationDataSelect.getUsableScores() - input.getSumScore());
        // 免费提现次数
        if(ScoreExchangeConstant.TYPE_FREE == input.getType()){
            opUserOperationData.setFreeGetNumber(input.getNum());
        }
        // 加息券
        else{
            opUserOperationData.setUsablePateCoupon(input.getNum());
        }
        opUserOperationDataMapper.updateByExampleSelective(opUserOperationData,example);

        logger.info("更新用户运营数据 - 成功");
    }

    /**
     * 保存用户优惠券或免费提现次数
     * @param input
     * @param opScoreMall
     */
    private void saveUserDiscount(ScoreExchangeInput input, OpScoreMall opScoreMall) {
        Date now = new Date();
        OpUserDiscount userDiscount = new OpUserDiscount();
        userDiscount.setGmtModifier(input.getUserId());
        userDiscount.setGmtCreator(input.getUserId());
        userDiscount.setUserMobile(input.getMobile());
        userDiscount.setUserId(input.getUserId());
        userDiscount.setEffectDate(now);
        userDiscount.setEffectDay(opScoreMall.getEffectDay());
        userDiscount.setInvalidDate(TimeUtils.addDay(now,opScoreMall.getEffectDay()));
        userDiscount.setSource("来自积分兑换");
        userDiscount.setDiscountType(input.getType());
        // 免费提现次数
        if(ScoreExchangeConstant.TYPE_FREE == input.getType()){
            userDiscount.setDiscountAvailable(String.valueOf(input.getNum()));
            userDiscount.setDiscountTitle(input.getNum()+"次免费提现次数");
        }
        // 加息券
        else{
            userDiscount.setDiscountAvailable(String.valueOf(opScoreMall.getNumValueView()));
            userDiscount.setDiscountTitle(opScoreMall.getNumValueView()+"加息券");
        }
        opUserDiscountMapper.insertSelective(userDiscount);

        logger.info("保存用户优惠券或免费提现次数 - 成功");
    }

    /**
     * 更新用户积分总数
     * @param input
     */
    private void updateUserScore(ScoreExchangeInput input) {
        OpUserScoreExample example = new OpUserScoreExample();
        example.createCriteria().andUserIdEqualTo(input.getUserId()).andIsDelEqualTo(PublicConstant.DEL_NO);
        List<OpUserScore> opUserScoreList = opUserScoreMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(opUserScoreList) || opUserScoreList.size() != 1){
            logger.info("用户积分[OP_USER_SCORE]表数据有问题，应该有一条记录，但现在却不是,opUserScoreList.size :[{}]",opUserScoreList.size());
            throw new BizFeignException(OperationRespExceptionConstant.SCORE_EXCHANGE_USER_SCORE_ERROR);
        }
        OpUserScore opUserScoreSelect = opUserScoreList.get(0);

        OpUserScore record = new OpUserScore();
        record.setGmtModifier(input.getUserId());
        // 已使用积分增加
        record.setScoreUsed(opUserScoreSelect.getScoreUsed() + input.getSumScore());
        // 可使用积分减少
        record.setScoreCurrent(opUserScoreSelect.getScoreCurrent() - input.getSumScore());

        if(opUserScoreSelect.getScoreCurrent() < input.getSumScore()){
            logger.info("用户积分当前可用积分:[{}],小于兑换所需积分:[{}]",opUserScoreSelect.getScoreCurrent(),input.getSumScore());
            throw new BizFeignException(OperationRespExceptionConstant.SCORE_EXCHANGE_USER_SCORE_LESS_THAN_CHANGE_ERROR);
        }
        opUserScoreMapper.updateByExampleSelective(record,example);

        logger.info("更新用户积分总数-成功");
    }


    /**
     * 保存积分明细
     * @param input
     * @param opScoreMall
     */
    private void saveScoreDetail(ScoreExchangeInput input, OpScoreMall opScoreMall) {
        OpUserScoreDetail detail = new OpUserScoreDetail();
        detail.setUserMobile(input.getMobile());
        detail.setUserId(input.getUserId());
        detail.setScore(-input.getSumScore());
        detail.setGmtCreator(input.getUserId());
        detail.setGmtModifier(input.getUserId());
        // 消耗
        detail.setType(OperationBizConstant.ACORE_TYPE_SUBTRACT);
        // 免费提现次数
        if(ScoreExchangeConstant.TYPE_FREE == input.getType()){
            detail.setSource("兑换"+input.getNum()+"次免费提现次数");
        }
        // 加息券
        else{
            detail.setSource("兑换"+input.getType()+"张"+opScoreMall.getNumValueView()+"加息券");
        }
        opUserScoreDetailMapper.insertSelective(detail);

        logger.info("保存积分明细-成功");
    }

    /**
     * 查询积分商城 参数检查
     * @param input
     * @param opScoreMall
     */
    private void paramScoreMallCheck(ScoreExchangeInput input,OpScoreMall opScoreMall) {
        Integer sumScore = input.getSumScore();
        int calcScore = opScoreMall.getNeedScore() * input.getNum();
        if(sumScore != calcScore){
            logger.info("查询积分商城 参数检查 完成---不通过，本该:[{}],传值:[{}]",calcScore,sumScore);
            throw new BizFeignException(OperationRespExceptionConstant.SCORE_EXCHANGE_SCORE_ERROR);
        }
        logger.info("查询积分商城 参数检查 完成---通过");
    }


}
