package com.tuodao.bp.traningcenter.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tuodao.bp.cache.basic.operation.ScoreTaskCache;
import com.tuodao.bp.cache.basic.useraccount.UserAccountCache;
import com.tuodao.bp.model.OpScoreTaskCacheInfo;
import com.tuodao.bp.model.PagePojo;
import com.tuodao.bp.model.business.traningcenter.output.AutoTenderOutput;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.model.constant.PublicConstant;
import com.tuodao.bp.model.constant.operation.UserDiscountConstant;
import com.tuodao.bp.model.constant.traningcenter.TenderConstant;
import com.tuodao.bp.model.facade.traningcenter.input.AutoTenderParam;
import com.tuodao.bp.model.facade.traningcenter.output.AutoTenderListVo;
import com.tuodao.bp.model.facade.traningcenter.output.AutoTenderVo;
import com.tuodao.bp.result.error.TransactError;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.traningcenter.db.mapper.basic.AutoTenderLogMapper;
import com.tuodao.bp.traningcenter.db.mapper.basic.AutoTenderMapper;
import com.tuodao.bp.traningcenter.db.model.basic.AutoTender;
import com.tuodao.bp.traningcenter.db.model.basic.AutoTenderLog;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowTender;
import com.tuodao.bp.traningcenter.mq.provider.ProducerMq;
import com.tuodao.bp.traningcenter.service.AutoTenderService;
import com.tuodao.bp.utils.BigDecimalUtils;
import com.tuodao.bp.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/9/26
 * @time: 14:35
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Service("autoTenderService")
public class AutoTenderServiceImpl  implements AutoTenderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AutoTenderServiceImpl.class);

    @Autowired
    private AutoTenderMapper autoTenderMapper;

    @Autowired
    private AutoTenderLogMapper autoTenderLogMapper;

    @Autowired
    private ScoreTaskCache scoreTaskCache;

    @Autowired
    private ProducerMq producerMq;

    @Autowired
    private UserAccountCache userAccountCache;

    /**
     * 更新或新增自动投标设置: 先删除旧的设置信息,同时在新增自动投标设置信息及备份自动投标设置信息即autoTenderLog
     * @param param
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveAutoTender(AutoTenderParam param) {
        AutoTender tender = new AutoTender();
        BeanUtils.copyProperties(param,tender);
        tender.setMaxAccount(BigDecimal.valueOf(BigDecimalUtils.yuanToCent(param.getMaxAccount())));
        tender.setMinAccount(BigDecimal.valueOf(BigDecimalUtils.yuanToCent(param.getMinAccount())));


        insertAutoTender(tender);

        insertAutoTenderLog(tender);

        scoreAward(param.getUserId());
    }

    /**
     * 自动投标首次设置 奖励积分
     * @param userId
     */
    private void scoreAward(String userId) {
        long count = autoTenderLogMapper.getCountAutoTenderLog(userId);
        if(count == 1){
            OpScoreTaskCacheInfo scoreTaskInfo = scoreTaskCache.getScoreTaskInfo(PublicConstant.TASK_ID_FIRST_SET_AUTO_INVEST);
            UserAccountInfo userAccoutInfo = userAccountCache.getUserAccoutInfo(userId);
            if(scoreTaskInfo != null && userAccoutInfo != null){
                producerMq.updateUserScore(PublicConstant.TASK_ID_FIRST_SET_AUTO_INVEST,userId,userAccoutInfo.getMobile(),null,scoreTaskInfo.getScore());
            }
        }
    }



    @Override
    public AutoTenderVo getUserAutoTender(String userId) {
        AutoTender tender = autoTenderMapper.getByUserId(userId);
        long total = getTotalAutoTender();
        if(tender != null){
            AutoTenderVo vo = new AutoTenderVo();
            BeanUtils.copyProperties(tender,vo);
            long ranking = autoTenderMapper.getTotalByWeight(tender.getWeight());
            //设置用户在总排名的位置
            vo.setRanking(ranking);
            vo.setTotalAutoTender(total);
            vo.setStatus(TenderConstant.AUTO_TENDER_OPEN);
            return vo;
        }
        return new AutoTenderVo(TenderConstant.AUTO_TENDER_CLOSE,total,total);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void closeAutoTender(String userId) {
        AutoTender tender = autoTenderMapper.getByUserId(userId);
        if(tender == null){
            throw new BizFeignException(TransactError.AUTO_TENDER_CLOSE_ERROR);
        }
        AutoTenderLog log = new AutoTenderLog();
        BeanUtils.copyProperties(tender,log,"id");
        log.setAutoTenderId(tender.getId());
        log.setAddTime(new Date());
        
        autoTenderLogMapper.insertSelective(log);

        autoTenderMapper.deleteByPrimaryKey(tender.getId());
    }

    @Override
    public long getTotalAutoTender() {
        return autoTenderMapper.getTotalByWeight(null);
    }

    @Override
    public List<AutoTenderOutput> getList(int limit) {
        List<AutoTender> tenderList = autoTenderMapper.getList(limit);
        if(tenderList != null && tenderList.size() > 0){
            List<AutoTenderOutput> list = new ArrayList<>();
            AutoTenderOutput output;
            //将数据库查询的参数转换为出参参数
            for(AutoTender tender :tenderList){
                output = new AutoTenderOutput();
                BeanUtils.copyProperties(tender,output);
                list.add(output);
            }
            return list;
        }
        return null;
    }

    /**
     * 删除原始自动投标信息,添加新自动投标设置信息
     * @param tender
     */
    private void insertAutoTender(AutoTender tender){
        autoTenderMapper.deleteByUserId(tender.getUserId());
        long weight = getMaxWeight();
        tender.setWeight(weight + 1);
        tender.setAddTime(new Date());
        autoTenderMapper.insertSelective(tender);
    }


    /**
     * 备份自动投标设置日志
     * @param autoTender   自动投标设置信息
     */
    @Override
    public void insertAutoTenderLog(AutoTender autoTender){
        AutoTenderLog log = new AutoTenderLog();
        BeanUtils.copyProperties(autoTender,log,"id");
        log.setAddTime(new Date());
        log.setAutoTenderId(autoTender.getId());
        autoTenderLogMapper.insertSelective(log);
    }


    private long getMaxWeight(){
        return autoTenderMapper.getMaxWeight();
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addAutoTenderSuccess(BorrowTender borrowTender) {
        AutoTender tender = autoTenderMapper.getByUserId(borrowTender.getUserId());
        if(tender == null){
            LOGGER.warn("没找到自动投标设置信息,用户可能在投标过程中已经关闭或删除自动投标设置信息");
            return;
        }
        AutoTender autoTender = new AutoTender();
        BeanUtils.copyProperties(tender,autoTender,"id");
        insertAutoTender(autoTender);

        AutoTenderLog log = autoTenderLogMapper.getByAutoTenderId(borrowTender.getAutoTenderId());

        if(log == null){
            LOGGER.warn("没找到自动投标设置日志信息,数据同步时出错没有保存");
        }else{
            //该处直接更新添加时间为当前时间作为投标时间 注意如果有拆分投标则以最后一次投标为主
            log.setAddTime(new Date());
            log.setType(TenderConstant.AUTO_TENDER_TYPE);
            //投标金额依次累加
            log.setTenderMoney(borrowTender.getPreAccount().add(log.getTenderMoney()));
            /**
             * 只有在使用加息券或者抵用券时 才会更新自动投标拆分投资时 第一次满足使用券需求 第二次不满足 覆盖的问题
             */
            if (log.getVoucherType() == 0){
                log.setVoucherType(getVoucherType(borrowTender));
            }
            if(log.getVoucherMoney().doubleValue() == 0){
                log.setVoucherMoney(borrowTender.getVoucherMoney());
            }
            autoTenderLogMapper.updateByPrimaryKeySelective(log);
        }
    }

    /**
     * 判断当次投标使用的券类型 0:不使用 1:使用抵用券 2:使用加息券
     * @param tender
     * @return
     */
    @Override
    public int getVoucherType(BorrowTender tender){
        if(tender.getVoucherId() != null && tender.getVoucherId() > 0){
            return UserDiscountConstant.VOUCHER_TYPE;
        }
        if(tender.getVoucherCouponId() != null && tender.getVoucherCouponId() > 0){
            return UserDiscountConstant.COUPON_TYPE;
        }
        return UserDiscountConstant.DEFAULT_TYPE;
    }

    @Override
    public PageInfo<AutoTenderListVo> getAutoTenderListByPage(PagePojo pagePojo) {

        PageHelper.startPage(pagePojo.getCurrentPage(),pagePojo.getPageSize());

        List<AutoTenderLog> list = autoTenderLogMapper.getList(pagePojo.getUserId());
        PageInfo<AutoTenderLog> pageInfo = new PageInfo<>(list);

        List<AutoTenderListVo> voList = new ArrayList<>();
        if(pageInfo.getList() != null && pageInfo.getList().size() > 0){
            for(AutoTenderLog log : pageInfo.getList()){
                voList.add(transfer(log));
            }
        }
        PageInfo<AutoTenderListVo> result = new PageInfo<>();
        result.setTotal(pageInfo.getTotal());
        result.setList(voList);
        return result;
    }

    @Override
    public AutoTenderListVo getAutoTenderDetail(String userId, Integer autoTenderId) {
        AutoTenderLog log = autoTenderLogMapper.getByUserIdAndAutoTenderId(userId, autoTenderId);

        return transfer(log);
    }

    private AutoTenderListVo transfer(AutoTenderLog log){
        AutoTenderListVo vo = new AutoTenderListVo();
        BeanUtils.copyProperties(log,vo);
        //分转元
        vo.setTenderMoney(BigDecimalUtils.centToYuanFormat(log.getTenderMoney().doubleValue()));
        vo.setVoucherMoney(log.getVoucherMoney().doubleValue());
        vo.setAddTime(DateUtil.formatLong(log.getAddTime()));
        return vo;
    }
}
