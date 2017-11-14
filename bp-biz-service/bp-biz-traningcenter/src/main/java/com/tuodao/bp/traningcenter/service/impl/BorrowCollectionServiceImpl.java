package com.tuodao.bp.traningcenter.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.tuodao.bp.model.business.product.input.BorrowRepaymentInput;
import com.tuodao.bp.model.business.traningcenter.input.JustIdInput;
import com.tuodao.bp.model.business.traningcenter.input.UserBackMoneyInput;
import com.tuodao.bp.model.business.traningcenter.output.BackMoneyPlanOutput;
import com.tuodao.bp.model.business.traningcenter.output.CollectionCountOutput;
import com.tuodao.bp.model.business.traningcenter.output.RecoverListOutput;
import com.tuodao.bp.model.constant.traningcenter.TenderConstant;
import com.tuodao.bp.model.facade.traningcenter.input.CollectionParam;
import com.tuodao.bp.model.facade.traningcenter.output.CollectionDayVo;
import com.tuodao.bp.model.mq.TenderAccountMqInfo;
import com.tuodao.bp.model.traningcenter.input.BorrowCollectionInput;
import com.tuodao.bp.model.traningcenter.input.UserCollectionInput;
import com.tuodao.bp.result.error.TransactError;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.traningcenter.db.mapper.basic.BorrowCollectionMapper;
import com.tuodao.bp.traningcenter.db.mapper.basic.BorrowTenderMapper;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowCollection;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowCollectionCapital;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowTender;
import com.tuodao.bp.traningcenter.enums.CollectionStatus;
import com.tuodao.bp.traningcenter.mq.provider.ProducerMq;
import com.tuodao.bp.traningcenter.service.AccountLogService;
import com.tuodao.bp.traningcenter.service.BorrowChicenessTenderService;
import com.tuodao.bp.traningcenter.service.BorrowCollectionService;
import com.tuodao.bp.traningcenter.service.CreditAssignmentService;
import com.tuodao.bp.utils.BigDecimalUtils;
import com.tuodao.bp.utils.DateUtil;
import com.tuodao.bp.utils.TransferUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/9/12
 * @time: 20:50
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Service("borrowCollectionService")
@Transactional(rollbackFor = RuntimeException.class)
public class BorrowCollectionServiceImpl implements BorrowCollectionService {

    @Autowired
    private BorrowCollectionMapper borrowCollectionMapper;

    @Autowired
    private AccountLogService accountLogService;

    @Autowired
    private BorrowTenderMapper borrowTenderMapper;

    @Autowired
    private BorrowChicenessTenderService borrowChicenessTenderService;

    @Autowired
    private ProducerMq producerMq;

    @Autowired
    private CreditAssignmentService creditAssignmentService;

    private static final Logger logger = LoggerFactory.getLogger(BorrowCollectionServiceImpl.class);

    @Override
    public double getUserTotalCollection(String userId) {
        return borrowCollectionMapper.getTotalCollectionByUserId(userId).doubleValue();
    }

    @Override
    public PageInfo<BackMoneyPlanOutput> getRecoveredByTenderId(UserBackMoneyInput input) {
        PageHelper.startPage(input.getCurrentPage(), input.getPageSize());

        List<BorrowCollection> borrowCollectionList = borrowCollectionMapper.selectByTenderIdAndUserId(input.getUserId(), input.getTenderId(),1);

        List<BackMoneyPlanOutput> list = convertToOutPut(borrowCollectionList);
        PageInfo<BackMoneyPlanOutput> result = new PageInfo<>(list);
        Page<BorrowCollection> page = (Page<BorrowCollection>)borrowCollectionList;
        result.setTotal(page.getTotal());
        return result;
    }

    @Override
    public List<BackMoneyPlanOutput> getByTenderId(String userId, int tenderId) {
        List<BorrowCollection> borrowCollectionList = borrowCollectionMapper.selectByTenderIdAndUserId(userId,
                tenderId,1);

        List<BackMoneyPlanOutput> list = convertToOutPut(borrowCollectionList);
        return list;
    }

    private List<BackMoneyPlanOutput> convertToOutPut(List<BorrowCollection> borrowCollectionList) {
        List<BackMoneyPlanOutput> list = new ArrayList<>();
        borrowCollectionList.forEach(borrowCollection -> {
            BackMoneyPlanOutput backMoneyPlanOutput = new BackMoneyPlanOutput();
            TransferUtil.transfer(borrowCollection, backMoneyPlanOutput);
            backMoneyPlanOutput.setStatusMsg(CollectionStatus.getValue(borrowCollection.getStatus()).getMsg());
            list.add(backMoneyPlanOutput);
        });
        return list;
    }

    @Override
    public BorrowCollectionCapital getBackPlan(Integer tenderId) {
        return borrowCollectionMapper.selectBackByTenderId(tenderId);
    }

    @Override
    public BorrowCollectionCapital getUnBackPlan(Integer tenderId) {
        return borrowCollectionMapper.selectUnBackByTenderId(tenderId);
    }

    @Override
    public List<BorrowCollection> getCollection(Integer tenderId, Integer status) {
        return borrowCollectionMapper.selectByTenderIdAndStatus(tenderId, status);
    }

    @Override
    public List<BackMoneyPlanOutput> getTenderCollection(BorrowRepaymentInput repayment) {

        List<BorrowCollection> list = borrowCollectionMapper.getList(repayment.getBorrowId(),repayment.getPeriod(),repayment.getAdvance(),repayment.getUserId());

        List<BackMoneyPlanOutput> backMoneyPlanOutputList = TransferUtil.transferList(list, BackMoneyPlanOutput.class);

        return backMoneyPlanOutputList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertBorrowCollectionBatch(List<UserCollectionInput> list, Integer borrowId) {
        if (list != null && list.size() > 0){
            List<BorrowCollectionInput> result = Lists.newArrayList();
            list.forEach(userCollectionInput -> result.addAll(userCollectionInput.getCollectionList()));
            return borrowCollectionMapper.insertBatch(result,borrowId);
        }
        return 0;
    }

    @Override
    public Map<String, BigDecimal> getRealCollection(CollectionParam param) {
        return borrowCollectionMapper.getRealCollection(param);
    }

    @Override
    public Map<String, BigDecimal> getPreCollection(CollectionParam param) {
        return borrowCollectionMapper.getPreCollection(param);
    }

    @Override
    public PageInfo<BackMoneyPlanOutput> getCollectionCalendarByPage(CollectionParam param) {
        PageHelper.startPage(param.getCurrentPage(),param.getPageSize());
        List<BorrowCollection> list = borrowCollectionMapper.getByDay(param.getUserId(),param.getDay(),param.getType());
        PageInfo<BorrowCollection> pageInfo = new PageInfo<>(list);

        List<BackMoneyPlanOutput> backList = TransferUtil.transferList(pageInfo.getList(),BackMoneyPlanOutput.class);

        PageInfo<BackMoneyPlanOutput> result = new PageInfo<>();
        result.setList(backList);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public List<CollectionDayVo> getCollectionDays(String userId, String month) {

        List<BorrowCollection> list = borrowCollectionMapper.getCollectionDays(userId, month);

        //将查询到的list进行转换输出
        return FluentIterable.from(list).transform(input -> {
            CollectionDayVo day = new CollectionDayVo();
            //未回款
            if(input.getStatus() == TenderConstant.COLLECTION_STATUS_0){
                //将日期的天转换为数字 1-31
                day.setDay(Integer.parseInt(DateUtil.format(input.getPreCollectionTime(),"dd")));
            }else{
                day.setDay(Integer.parseInt(DateUtil.format(input.getCollectionTime(),"dd")));
            }
            day.setStatus(input.getStatus());
            return day;
        }).toList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void collection(BorrowRepaymentInput repayment) {
        logger.error("回款入参,标的ID:{},期数:{},是否为提前还款:{}",repayment.getBorrowId(),repayment.getPeriod(),repayment.getAdvance());

        List<BorrowCollection> list = borrowCollectionMapper.getList(repayment.getBorrowId(),repayment.getPeriod(),repayment.getAdvance(),null);

        if(list == null || list.size() == 0){
            logger.error("标的回款,未查询到回款列表,标的ID:{},期数:{},是否为提前还款:{}",repayment.getBorrowId(),repayment.getPeriod(),repayment.getAdvance());
            throw new BizFeignException(TransactError.RECOVER_LIST_NOT_FOUND);
        }

        //提前还款 本期利息照收 之后的只回款本金
        if(repayment.getAdvance()){
            advanceCollection(list,repayment.getPeriod());
        }else{
            normalCollection(list);
        }
        postCollection(list,repayment);
    }

    /**
     * 后置处理
     * 判断是否为精选计划回款 ,如果为提前还款且为精选计划回款 则需要将精选计划回款本金放入到资金池
     * 如果为精选计划最后一期回款仍需要将本金放入到资金池
     * @param list
     * @param repayment
     */
    private void postCollection(List<BorrowCollection> list, BorrowRepaymentInput repayment) {

        //存放需要更新其他服务的队列
        List<BorrowCollection> collections = new ArrayList<>();

        for(BorrowCollection collection : list){
            if(collection.getIsCapital() == TenderConstant.IS_CAPITAL_1){
                //只有本金不为零的时候才进行更新 减少不必要操作
                if(collection.getCapital().doubleValue() > 0 ){
                    //TODO
                    //borrowChicenessTenderService.updatePlanCollection(collection,collection.getChoicenessTenderId());
                }
            }else{
                collections.add(collection);
            }
        }

        if(repayment.getAdvance()){
            logger.debug("当前还款为提前还款,通知债权转让,标的ID:{}",repayment.getBorrowId());
            //creditAssignmentService.revokedTransfer(repayment.getBorrowId());//TODO
        }
        sendCollectionAccount(collections);
    }




    /**
     * 正常回款
     * @param list 待回款列表
     */
    private void normalCollection(List<BorrowCollection> list){
        boolean flag = false;
        for (BorrowCollection collection :list){
            collection.setInterest(collection.getPreInterest());
            collection.setCapital(collection.getPreCapital());
            collection.setStatus(TenderConstant.COLLECTION_STATUS_1);
            flag = (collection.getPeriod() == collection.getPeriods().intValue());
        }
        accountLogService.insertBorrowCollectionLogBatch(list);
        borrowCollectionMapper.updateBatchStatus(list);
        //还款为最后一期
        if(flag){
            List<Integer> tenderIds = FluentIterable.from(list).transform(input -> input.getTenderId()).toList();
            borrowTenderMapper.updateTenderStatusBatch2(tenderIds,TenderConstant.TENDER_COMPLETE,null,null);
        }
    }

    /**
     * 提前回款:
     * 将本期的本金+利息还给投资人 同时将剩余期数的本金还给投资人 注意本期本金+利息+剩余期本金一并更新到本期上 剩余期实际还款本金+利息均为0
     * @param list
     */
    private void advanceCollection(List<BorrowCollection> list,int period){
        logger.debug("执行提前还款逻辑,期数:{}",period);
        for(BorrowCollection collection : list){
            //如果回款期数为当期
            if(period == collection.getPeriod()){
                setCollection(collection,list);
                //提前还款 只有当前期写资金记录,剩余期数只更新状态
                accountLogService.insertBorrowCollectionLog(collection);
                borrowTenderMapper.updateTenderStatus(collection.getTenderId(),TenderConstant.TENDER_COMPLETE,"标的提前还款完成");
            }else{
                //没有涉及到回款 不写资金记录
                collection.setStatus(TenderConstant.COLLECTION_STATUS_2);
                collection.setCapital(BigDecimal.valueOf(0D));
                collection.setInterest(BigDecimal.valueOf(0D));
                collection.setPlatformAccount(BigDecimal.valueOf(0D));
                collection.setCouponAccount(BigDecimal.valueOf(0D));
            }
        }
        borrowCollectionMapper.updateBatchStatus(list);
    }

    /**
     * 计算并设置回款信息应还本金及利息等
     * @param collection 待设置的回款信息
     * @param list 回款信息列表
     */
    private void setCollection(BorrowCollection collection,List<BorrowCollection> list){
        //本期应还总利息(基础利息+平台利息+加息券利息)
        double interest = 0D;
        //本期应还本金
        double capital = 0D;
        //预计利息总利息 防止提前还款 代收利息依旧存在的问题
        double preInterest = 0D;
        //加息收益
        double couponInterest = 0D;
        //平台收益
        double platformInterest = 0D;
        for (BorrowCollection bc: list){
            if(collection.getPeriod().intValue() == bc.getPeriod().intValue()){
                interest = BigDecimalUtils.add(interest,bc.getPreInterest().doubleValue());
                couponInterest = BigDecimalUtils.add(couponInterest,bc.getCouponAccount().doubleValue());
                platformInterest = BigDecimalUtils.add(platformInterest,bc.getPlatformAccount().doubleValue());
            }
            preInterest = BigDecimalUtils.add(preInterest,bc.getPreInterest().doubleValue());
            //本金一直累加
            capital = BigDecimalUtils.add(capital,collection.getPreCapital().doubleValue());
        }
        collection.setPreInterest(BigDecimal.valueOf(preInterest));
        collection.setInterest(BigDecimal.valueOf(interest));
        collection.setCapital(BigDecimal.valueOf(capital));
        collection.setStatus(TenderConstant.COLLECTION_STATUS_2);
        collection.setCouponAccount(BigDecimal.valueOf(couponInterest));
        collection.setPlatformAccount(BigDecimal.valueOf(platformInterest));
    }



    @Override
    public void sendCollectionAccount(List<BorrowCollection> list) {
        if(list != null && list.size() > 0){
            list.forEach(collection -> {
                TenderAccountMqInfo info = new TenderAccountMqInfo();
                info.setUserId(collection.getUserId());
                //邀请奖励上级返现 基础利息+平台加息利息 (不包含加息券利息)
                info.setAwardInterest(collection.getInterest().subtract(collection.getCouponAccount()));
                info.setType(TenderConstant.ACCOUNT_MQ_TYPE_1);
                producerMq.updateUserAccount(info);
            });
        }
    }

    @Override
    public CollectionCountOutput getUserCollectionInterest(String userId) {
        return borrowCollectionMapper.getUserCollectionInterest(userId);
    }


    @Override
    public PageInfo<RecoverListOutput> selectRecoverListBychioId(JustIdInput justIdInput){
        logger.info("choiceness recover selectRecoverListBychioId start ,input = {}",justIdInput);
        PageHelper.startPage(justIdInput.getCurrentPage(), justIdInput.getPageSize());
        List<RecoverListOutput> recoverListOutputs = borrowCollectionMapper.selectRecoverListBychioId(justIdInput.getJustId());
        PageInfo<RecoverListOutput> pageInfo = new PageInfo<>(recoverListOutputs);
        Page<RecoverListOutput> page = (Page<RecoverListOutput>)recoverListOutputs;
        pageInfo.setTotal(page.getTotal());
        return pageInfo;
    }

}
