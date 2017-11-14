package com.tuodao.bp.traningcenter.service.impl;

import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.model.business.traningcenter.input.*;
import com.tuodao.bp.model.business.traningcenter.input.OwnTransferInput;
import com.tuodao.bp.result.error.TransactError;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.traningcenter.db.mapper.basic.BorrowChoicenessTenderMapper;
import com.tuodao.bp.traningcenter.db.mapper.basic.BorrowCollectionMapper;
import com.tuodao.bp.traningcenter.db.mapper.basic.BorrowTenderMapper;
import com.tuodao.bp.traningcenter.db.model.basic.AccountLog;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowChoicenessTender;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowCollection;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowTender;
import com.tuodao.bp.model.enums.AccountLogType;
import com.tuodao.bp.traningcenter.service.AccountLogService;
import com.tuodao.bp.traningcenter.service.BorrowChicenessService;
import com.tuodao.bp.traningcenter.service.BorrowChicenessTenderService;
import com.tuodao.bp.traningcenter.service.BorrowTenderService;
import com.tuodao.bp.traningcenter.until.*;
import com.tuodao.bp.traningcenter.until.DateUtils;
import com.tuodao.bp.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: wuzf
 * @date: 2017/9/12 0012.
 * @time: 16:23
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Service
@Transactional
public class BorrorChicenessServiceImpl implements BorrowChicenessService{
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(BorrowChicenessTenderServiceImpl.class);

    @Resource
    private BorrowCollectionMapper borrowCollectionMapper;
    @Resource
    private BorrowTenderMapper borrowTenderMapper;
    @Resource
    private AccountLogService accountLogService;
    @Resource
    private BorrowChoicenessTenderMapper borrowChoicenessTenderMapper;
    @Resource
    private BorrowChicenessTenderService borrowChicenessTenderService;

    @Override
    public void doOwnPlanTransfer(OwnTransferInput ownTransferInput)
    {
        logger.info("choiceness doAfterPlanTransfer ,input = {}", ownTransferInput);
        // 如下代码是针对于转让人的
        logger.info("choiceness doAfterPlanTransfer for_ower:{}", ownTransferInput.getTenderId());
        //先查询出未还款的记录
        List<BorrowCollection> borrowCollectionBoList=borrowCollectionMapper.selectByTenderIdAndNORecover(ownTransferInput.getTenderId());
        if(borrowCollectionBoList==null)
        {
            throw new BizFeignException(TransactError.RECOVER_LIST_NOT_FOUND);
        }
        //该笔投资的代收本金 代收利息
        BigDecimal preAllCapital=new BigDecimal(0);
        BigDecimal preAllIntrest=new BigDecimal(0);
        //更新转让人回款记录 7为已转让
        for(int i=0;i<borrowCollectionBoList.size();i++)
        {
            BorrowCollection borrowCollection = borrowCollectionBoList.get(i);
            borrowCollection.setStatus(7);
            borrowCollection.setCapital(borrowCollection.getPreCapital());
            borrowCollectionMapper.updateByPrimaryKeySelective(borrowCollection);
            preAllCapital=preAllCapital.add(borrowCollection.getPreCapital());
            if(borrowCollection.getIsShow()==1)
            {
                preAllIntrest=preAllIntrest.add(borrowCollection.getPreInterest());
            }

        }
        //更新转让人该笔转让的投资修改为已转让
        BorrowTender borrowTender =borrowTenderMapper.selectByPrimaryKey(ownTransferInput.getTenderId());
        if(borrowTender==null)
        {
            throw new BizFeignException(TransactError.TENDER_NOT_FOUND);
        }
        borrowTender.setStatus(6);
        borrowTenderMapper.updateByPrimaryKey(borrowTender);
        //转让人只收到本金 更改资金记录以及自身资金
        AccountLog accountLog = new AccountLog();
        accountLog.setUserId(ownTransferInput.getUserId());
        accountLog.setType(AccountLogType.PLAN_DEDUCT_DEVREVERIFY.getCode());
        accountLog.setAccount(ownTransferInput.getAccount());
        accountLog.setTotal(new BigDecimal(0));
        accountLog.setCashFrost(new BigDecimal(0));
        accountLog.setTenderFrost(new BigDecimal(0));
        accountLog.setBalance(ownTransferInput.getAccount());
        accountLog.setRecharge(new BigDecimal(0));
        accountLog.setAwaitInterest(preAllIntrest.multiply(new BigDecimal(-1)));
        accountLog.setAwaitCapital(preAllCapital.multiply(new BigDecimal(-1)));
        accountLog.setFromAccount(ownTransferInput.getTenderId() + "");
        accountLog.setToAccount(ownTransferInput.getUserId());
        accountLog.setToRemarks("从转让标" + ownTransferInput.getTenderId() + "获取转让本金");
        accountLog.setRemarks("从转让标" + ownTransferInput.getBorrowName() + "获取转让本金");
        accountLog.setIsShow(1);
        accountLog.setIntrestAccount(new BigDecimal(0));
        accountLog.setFeeAccount(new BigDecimal(0));
        accountLog.setChangeType(0);
        accountLogService.updateAccountAndLogPro(accountLog);
        //根据tenderid查询对应的加入记录  修改回款表中信息
        updateColletion(ownTransferInput.getTenderId(),ownTransferInput.getAccount());

    }

    public void updateColletion(Integer tenderId,BigDecimal account)
    {
        BorrowTender borrowTender = borrowTenderMapper.selectByPrimaryKey(tenderId);
        BorrowChoicenessTender borrowChoicenessTender = borrowChoicenessTenderMapper.selectByPrimaryKey(borrowTender.getChoicenessTenderId());
        //查询回款表中的tenderid
        BorrowCollection borrowCollection =borrowCollectionMapper.getChoicenessTenderCollection(
                borrowChoicenessTender.getBeforeTenderId() == null ? borrowChoicenessTender.getId() : borrowChoicenessTender.getBeforeTenderId(), borrowChoicenessTender.getBorrowId());
        borrowCollection.setCapital(borrowCollection.getCapital().add(account));

        if(borrowCollection.getPreCapital().compareTo(borrowCollection.getCapital())==0)
        {
            borrowCollection.setStatus(1);
        }
        borrowCollectionMapper.updateByPrimaryKeySelective(borrowCollection);
    }


    public void doTenderPlanTransferAfter(PlanDevelopListInput inputs,PlanNomalInput planNomalInput)
    {
        logger.info("choiceness doTenderPlanTransferAfter for_tender:{}", inputs.getTenderId());
        List<PlanNomalInput> list=inputs.getList();
        Date repayTime = inputs.getLastRepayTime();
        //查询过去了几天
        int days= (int)DateUtil.diffDay(repayTime,new Date());
        //加息劵利率
        double vouchApr = planNomalInput.getVouchApr().doubleValue();
        //总利率
        double AllApr = planNomalInput.getApr().doubleValue() + planNomalInput.getAwardScale().doubleValue() + vouchApr;
        //回款计划
        List<BorrowPlan> planList = BorrowUtils.getPlan(planNomalInput.getRaymentType(), new BDC(planNomalInput.getAccount()).toDouble(), AllApr, inputs.getPeriod(), vouchApr, planNomalInput.getAwardScale().doubleValue()
                , planNomalInput.getApr().doubleValue());
        BigDecimal tenderPreCapital = new BigDecimal(0);

        BigDecimal tenderPreIntest = new BigDecimal(0);
        //所有平台奖励的钱
        BigDecimal tenderPlatformInterest = new BigDecimal(0);
        //所有加息劵的钱
        BigDecimal tenderVoucherCouponMoney = new BigDecimal(0);
        int start = inputs.getPeriods()-inputs.getPeriod()+1;
        for (int z = 0; z < planList.size(); z++) {
            BorrowPlan borrowPlan = planList.get(z);
            BorrowCollection borrowCollection = new BorrowCollection();
            borrowCollection.setUserId(planNomalInput.getUserId());
            borrowCollection.setTenderId(planNomalInput.getTenderId());
            borrowCollection.setStatus(0);
            borrowCollection.setPeriod(start + z);
            borrowCollection.setPeriods(inputs.getPeriods());
//            Long repayTime = planNomalInput.getRepayTime();
            if (planNomalInput.getRaymentType() == 2) {
                borrowCollection.setPreCollectionTime(DateUtil.addDays(repayTime, planNomalInput.getLeftPeriod()));
            } else {
                borrowCollection.setPreCollectionTime(DateUtil.addMonths(repayTime, z + 1));
            }
            borrowCollection.setPreCollectionMonth(DateUtil.formatMin(borrowCollection.getPreCollectionTime()));

            borrowCollection.setPreCapital(new BDC2(borrowPlan.getCapital()).toS0Bd());
            Boolean flag = false;
            //如果是按天
            if (planNomalInput.getRaymentType() == 2) {
                flag = DateUtil.diffDay(borrowCollection.getPreCollectionTime(), planNomalInput.getRepayLastTime()) > 0 ? false : true;

            } else {
                flag = DateUtil.diffDay(borrowCollection.getPreCollectionTime(), planNomalInput.getRepayLastTime()) > 0 ? false : true;
            }

            //是否需要展示收益
            if (flag == false) {
                borrowCollection.setIsShow(1);
                if(z==0)
                {
                    borrowCollection.setPreInterest(new BDC2(borrowPlan.getInterest()).toS0Bd()
                            .multiply(new BigDecimal(30 - days).subtract(new BigDecimal(30))));
                }
                else
                {
                    borrowCollection.setPreInterest(new BDC2(borrowPlan.getInterest()).toS0Bd());
                }
                borrowCollection.setSimulatedInterest(new BDC2(borrowPlan.getInterest()).toS0Bd());
                borrowCollection.setCouponAccount(new BDC2(borrowPlan.getCouponVoucherAccount()).toS0Bd());
                borrowCollection.setPlatformAccount(new BDC2(borrowPlan.getAwardAccount()).toS0Bd());
                tenderPreCapital = tenderPreCapital.add(borrowCollection.getCapital());
                tenderPreIntest = tenderPreIntest.add(borrowCollection.getPreInterest());
                tenderPlatformInterest = tenderPlatformInterest.add(borrowCollection.getPlatformAccount());
                tenderVoucherCouponMoney = tenderVoucherCouponMoney.add(borrowCollection.getCouponAccount());
            } else {
                borrowCollection.setIsShow(0);
                borrowCollection.setPreInterest(new BigDecimal(0));
                borrowCollection.setSimulatedInterest(new BDC2(borrowPlan.getInterest()).toS0Bd());
                borrowCollection.setCouponAccount(new BigDecimal(0));
                borrowCollection.setPlatformAccount(new BigDecimal(0));
            }
            borrowCollection.setCapital(new BigDecimal(0));
            borrowCollection.setTenderNid(planNomalInput.getId());
            borrowCollection.setInterest(new BigDecimal(0));
            borrowCollection.setBorrowId(planNomalInput.getLastProductId());
            borrowCollectionMapper.insert(borrowCollection);
        }
        BorrowTender borrowTender = borrowTenderMapper.selectByPrimaryKey(planNomalInput.getTenderId());
        borrowTender.setPlatformInterest(tenderPlatformInterest);
        borrowTender.setVoucherCouponMoney(tenderVoucherCouponMoney);
        borrowTender.setStatus(2);
        borrowTenderMapper.updateByPrimaryKey(borrowTender);
        //投资人添加资金记录以及修改资金
        AccountLog tenderAccountLog = new AccountLog();
        tenderAccountLog.setUserId(planNomalInput.getUserId());
        tenderAccountLog.setType(AccountLogType.PLAN_DEDUCT_ORIREVERIFY.getCode());
        tenderAccountLog.setAccount(planNomalInput.getAccount());
        tenderAccountLog.setTotal(new BigDecimal(0));
        tenderAccountLog.setCashFrost(new BigDecimal(0));
        tenderAccountLog.setTenderFrost(planNomalInput.getAccount().multiply(new BigDecimal(-1)));
        tenderAccountLog.setBalance(new BigDecimal(0));
        tenderAccountLog.setRecharge(new BigDecimal(0));
        tenderAccountLog.setAwaitInterest(tenderPreCapital);
        tenderAccountLog.setAwaitCapital(tenderPreIntest);
        tenderAccountLog.setFromAccount(planNomalInput.getUserId());
        tenderAccountLog.setToAccount(planNomalInput.getUserId());
        tenderAccountLog.setToRemarks("标的复审");
        tenderAccountLog.setIsShow(1);
        tenderAccountLog.setIntrestAccount(new BigDecimal(0));
        tenderAccountLog.setFeeAccount(new BigDecimal(0));
        tenderAccountLog.setChangeType(0);
        accountLogService.updateAccountAndLogPro(tenderAccountLog);
    }



    public void doPlayAccountTransferAfter(PlanDevelopListInput inputs,PlanNomalInput planNomalInput)
    {
        logger.info("choiceness doTenderPlanTransferAfter for_tender:{}", inputs.getTenderId());
        List<PlanNomalInput> list=inputs.getList();
        Date repayTime = inputs.getLastRepayTime();
        //查询过去了几天
        int days= (int)DateUtil.diffDay(repayTime,new Date());
        //加息劵利率
        double vouchApr = planNomalInput.getVouchApr().doubleValue();
        //总利率
        double AllApr = planNomalInput.getApr().doubleValue() + planNomalInput.getAwardScale().doubleValue() + vouchApr;
        //回款计划
        List<BorrowPlan> planList = BorrowUtils.getPlan(planNomalInput.getRaymentType(), new BDC(planNomalInput.getAccount()).toDouble(), AllApr, inputs.getPeriod(), vouchApr, planNomalInput.getAwardScale().doubleValue()
                , planNomalInput.getApr().doubleValue());
        BigDecimal tenderPreCapital = new BigDecimal(0);

        BigDecimal tenderPreIntest = new BigDecimal(0);
        //所有平台奖励的钱
        BigDecimal tenderPlatformInterest = new BigDecimal(0);
        //所有加息劵的钱
        BigDecimal tenderVoucherCouponMoney = new BigDecimal(0);
        int start = inputs.getPeriods()-inputs.getPeriod()+1;
        for (int z = 0; z < planList.size(); z++) {
            BorrowPlan borrowPlan = planList.get(z);
            BorrowCollection borrowCollection = new BorrowCollection();
            borrowCollection.setUserId(planNomalInput.getUserId());
            borrowCollection.setTenderId(planNomalInput.getTenderId());
            borrowCollection.setStatus(0);
            borrowCollection.setPeriod(start + z);
            borrowCollection.setPeriods(inputs.getPeriods());
//            Long repayTime = planNomalInput.getRepayTime();
            if (planNomalInput.getRaymentType() == 2) {
                borrowCollection.setPreCollectionTime(DateUtil.addDays(repayTime, planNomalInput.getLeftPeriod()));
            } else {
                borrowCollection.setPreCollectionTime(DateUtil.addMonths(repayTime, z+1));
            }
            borrowCollection.setPreCollectionMonth(DateUtil.formatMin(borrowCollection.getPreCollectionTime()));

            borrowCollection.setPreCapital(new BDC2(borrowPlan.getCapital()).toS0Bd());
            if(z==0)
            {
                borrowCollection.setPreInterest(new BDC2(borrowPlan.getInterest()).toS0Bd()
                        .multiply(new BigDecimal(30 - days).subtract(new BigDecimal(30))));
            }
            else
            {
                borrowCollection.setPreInterest(new BDC2(borrowPlan.getInterest()).toS0Bd());
            }
            borrowCollection.setIsShow(1);

            tenderPreCapital = tenderPreCapital.add(borrowCollection.getCapital());
            tenderPreIntest = tenderPreIntest.add(borrowCollection.getPreInterest());
            tenderPlatformInterest = tenderPlatformInterest.add(borrowCollection.getPlatformAccount());
            tenderVoucherCouponMoney = tenderVoucherCouponMoney.add(borrowCollection.getCouponAccount());

            borrowCollection.setCapital(new BigDecimal(0));
            borrowCollection.setTenderNid(planNomalInput.getId());
            borrowCollection.setInterest(new BigDecimal(0));
            borrowCollection.setBorrowId(planNomalInput.getLastProductId());
            borrowCollectionMapper.insert(borrowCollection);
        }
        BorrowTender borrowTender = borrowTenderMapper.selectByPrimaryKey(planNomalInput.getTenderId());
        borrowTender.setPlatformInterest(tenderPlatformInterest);
        borrowTender.setVoucherCouponMoney(tenderVoucherCouponMoney);
        borrowTender.setStatus(2);
        borrowTenderMapper.updateByPrimaryKey(borrowTender);
        //投资人添加资金记录以及修改资金
        AccountLog tenderAccountLog = new AccountLog();
        tenderAccountLog.setUserId(planNomalInput.getUserId());
        tenderAccountLog.setType(AccountLogType.PLAN_DEDUCT_ORIREVERIFY.getCode());
        tenderAccountLog.setAccount(planNomalInput.getAccount());
        tenderAccountLog.setTotal(new BigDecimal(0));
        tenderAccountLog.setCashFrost(new BigDecimal(0));
        tenderAccountLog.setTenderFrost(planNomalInput.getAccount().multiply(new BigDecimal(-1)));
        tenderAccountLog.setBalance(new BigDecimal(0));
        tenderAccountLog.setRecharge(new BigDecimal(0));
        tenderAccountLog.setAwaitInterest(tenderPreCapital);
        tenderAccountLog.setAwaitCapital(tenderPreIntest);
        tenderAccountLog.setFromAccount(planNomalInput.getUserId());
        tenderAccountLog.setToAccount(planNomalInput.getUserId());
        tenderAccountLog.setToRemarks("标的复审");
        tenderAccountLog.setIsShow(1);
        tenderAccountLog.setIntrestAccount(new BigDecimal(0));
        tenderAccountLog.setFeeAccount(new BigDecimal(0));
        tenderAccountLog.setChangeType(0);
        accountLogService.updateAccountAndLogPro(tenderAccountLog);
    }



    public void doTenderPlanTransferBefore(PlanDevelopListInput inputs, PlanNomalInput planNomalInput)
    {
        //相当于我不用生成回款记录
    }


    //理财计划下普通标的的生成回款计划 以及修改投资人资金记录 修改投资表 返回还款计划
    @Override
    public void reverifyPlanNomal(PlanNomalListInput inputs)
    {
        List<PlanNomalInput> list=inputs.getInputs();

        for(int i=0;i<list.size();i++) {
            //每笔投资
            PlanNomalInput planNomalInput = list.get(i);
            //加息劵利率
            double vouchApr = planNomalInput.getVouchApr().doubleValue();
            //总利率
            double AllApr = planNomalInput.getApr().doubleValue() + planNomalInput.getAwardScale().doubleValue() + vouchApr;
           //回款计划
            List<BorrowPlan> planList = BorrowUtils.getPlan(planNomalInput.getRaymentType(),  new BDC(planNomalInput.getAccount()).toDouble(),AllApr, inputs.getBorrowPeriod(), vouchApr, planNomalInput.getAwardScale().doubleValue()
                    , planNomalInput.getApr().doubleValue());

            BigDecimal tenderPreCapital = new BigDecimal(0);

            BigDecimal tenderPreIntest = new BigDecimal(0);
            //所有平台奖励的钱
            BigDecimal tenderPlatformInterest = new BigDecimal(0);
            //所有加息劵的钱
            BigDecimal tenderVoucherCouponMoney = new BigDecimal(0);

            for (int z = 0; z < planList.size(); z++) {
                BorrowPlan borrowPlan = planList.get(z);
                BorrowCollection borrowCollection = new BorrowCollection();
                borrowCollection.setUserId(planNomalInput.getUserId());
                borrowCollection.setTenderId(planNomalInput.getTenderId());
                borrowCollection.setStatus(0);
                borrowCollection.setPeriod(planNomalInput.getStartPeriod() + z);
                borrowCollection.setPeriods(planNomalInput.getStartPeriod() + planNomalInput.getLeftPeriod() - 1);
                Long repayTime = planNomalInput.getRepayTime();
                if(planNomalInput.getRaymentType()==3) {
                    borrowCollection.setPreCollectionTime(DateUtils.getDate(DateUtils.addNewDays(repayTime / 1000, planNomalInput.getLeftPeriod())));
                }
                else
                {
                    borrowCollection.setPreCollectionTime(DateUtils.getDate(DateUtils.addNewMonths(repayTime / 1000, z + 1)));
                }
                borrowCollection.setPreCollectionMonth(DateUtil.formatMin(borrowCollection.getPreCollectionTime()));

                borrowCollection.setPreCapital(new BDC2(borrowPlan.getCapital()).toS0Bd());
                Boolean flag =false;
                //如果是按天
                if(planNomalInput.getRaymentType()==3)
                {
                    flag = DateUtil.diffDay(borrowCollection.getPreCollectionTime(),planNomalInput.getRepayLastTime())>0?false:true;

                }
                else
                {
                    flag = DateUtil.diffDay(borrowCollection.getPreCollectionTime(),planNomalInput.getRepayLastTime())>0?false:true;
                }
                if(planNomalInput.getRepayLastTime()==null)
                {
                    borrowCollection.setIsShow(0);
                    borrowCollection.setPreInterest(new BigDecimal(0));
                    borrowCollection.setSimulatedInterest(new BDC2(borrowPlan.getInterest()).toS0Bd());
                    borrowCollection.setCouponAccount(new BDC2(borrowPlan.getCouponVoucherAccount()).toS0Bd());
                    borrowCollection.setPlatformAccount(new BDC2(borrowPlan.getAwardAccount()).toS0Bd());
                }
                else
                {
                    //是否需要展示收益
                    if (flag == false) {
                        borrowCollection.setIsShow(1);
                        borrowCollection.setPreInterest(new BDC2(borrowPlan.getInterest()).toS0Bd());
                        borrowCollection.setSimulatedInterest(new BDC2(borrowPlan.getInterest()).toS0Bd());
                        borrowCollection.setCouponAccount(new BDC2(borrowPlan.getCouponVoucherAccount()).toS0Bd());
                        borrowCollection.setPlatformAccount(new BDC2(borrowPlan.getAwardAccount()).toS0Bd());
                        tenderPreCapital = tenderPreCapital.add(borrowCollection.getCapital());
                        tenderPreIntest = tenderPreIntest.add(borrowCollection.getPreInterest());
                        tenderPlatformInterest = tenderPlatformInterest.add(borrowCollection.getPlatformAccount());
                        tenderVoucherCouponMoney = tenderVoucherCouponMoney.add(borrowCollection.getCouponAccount());
                    } else {
                        borrowCollection.setIsShow(0);
                        borrowCollection.setPreInterest(new BigDecimal(0));
                        borrowCollection.setSimulatedInterest(new BDC2(borrowPlan.getInterest()).toS0Bd());
                        borrowCollection.setCouponAccount(new BigDecimal(0));
                        borrowCollection.setPlatformAccount(new BigDecimal(0));
                    }
                }
                borrowCollection.setCapital(new BigDecimal(0));
                borrowCollection.setTenderNid(planNomalInput.getId());
                borrowCollection.setInterest(new BigDecimal(0));
                borrowCollection.setBorrowId(planNomalInput.getLastProductId());
                borrowCollectionMapper.insert(borrowCollection);
            }
            BorrowTender borrowTender = borrowTenderMapper.selectByPrimaryKey(planNomalInput.getTenderId());
            borrowTender.setPlatformInterest(tenderPlatformInterest);
            borrowTender.setVoucherCouponMoney(tenderVoucherCouponMoney);
            borrowTender.setStatus(2);
            borrowTenderMapper.updateByPrimaryKey(borrowTender);
            //投资人添加资金记录以及修改资金
            AccountLog tenderAccountLog = new AccountLog();
            tenderAccountLog.setUserId(planNomalInput.getUserId());
            tenderAccountLog.setType(AccountLogType.PLAN_TENDER_FROST.getCode());
            tenderAccountLog.setAccount(planNomalInput.getAccount());
            tenderAccountLog.setTotal(new BigDecimal(0));
            tenderAccountLog.setCashFrost(new BigDecimal(0));
            tenderAccountLog.setTenderFrost(planNomalInput.getAccount().multiply(new BigDecimal(-1)));
            tenderAccountLog.setBalance(new BigDecimal(0));
            tenderAccountLog.setRecharge(new BigDecimal(0));
            tenderAccountLog.setAwaitInterest(tenderPreCapital);
            tenderAccountLog.setAwaitCapital(tenderPreIntest);
            tenderAccountLog.setFromAccount(planNomalInput.getUserId());
            tenderAccountLog.setToAccount(planNomalInput.getUserId());
            tenderAccountLog.setToRemarks("标的复审");
            tenderAccountLog.setIsShow(1);
            tenderAccountLog.setIntrestAccount(new BigDecimal(0));
            tenderAccountLog.setFeeAccount(new BigDecimal(0));
            tenderAccountLog.setChangeType(0);
            accountLogService.updateAccountAndLogPro(tenderAccountLog);
        }
    }


    /**
     * 该理财计划复审
     * @param reverifyPlanInput
     */
    @Override
    public void  planReverify(ReverifyPlanInput reverifyPlanInput)
    {
        List<PlanListInput> list = reverifyPlanInput.getList();
        for(int z =0;z<list.size();z++)
        {
            PlanListInput planListInput=list.get(z);
            if(planListInput.getType()==0)
            {
                //走普通标
                planOriReverify(planListInput);
            }
            else
            {
                List<BorrowCollection> collectionslist = borrowCollectionMapper.selectByTenderId(planListInput.getTenderId());
                if(collectionslist==null)
                {
                    PlanDevelopListInput inputs = new PlanDevelopListInput();
                    PlanNomalInput planNomalInput = new PlanNomalInput();
                    inputs.setPeriods(planListInput.getPeriods());
                    inputs.setPeriod(planListInput.getPeriod());
                    inputs.setLastRepayTime(planListInput.getLastRepayTime());
                    BeanUtils.copyProperties(planNomalInput, planListInput);
                    //走转让标
                    doTenderPlanTransferAfter(inputs,planNomalInput);
                }
            }
            if(z==list.size()-1)
            {
                insertLastCollection(planListInput);
            }
        }

        //复审后发放积分以及首投发送消息
        this.sendPoint(reverifyPlanInput.getProductOutput());

    }

    public void sendPoint(ProductOutput productOutput)
    {
        List<BorrowChoicenessTender> borrowTenderList = borrowChoicenessTenderMapper.selectOriginalListByBorrowId(productOutput.getId());
        for(int m=0;m<borrowTenderList.size();m++)
        {
            BorrowChoicenessTender borrowChoicenessTender = borrowTenderList.get(m);
            borrowChicenessTenderService.appTender(borrowChoicenessTender);
            borrowChicenessTenderService.under12MonthTender(borrowChoicenessTender, productOutput);
            borrowChicenessTenderService.above12MonthTender(borrowChoicenessTender, productOutput);
            borrowChicenessTenderService.dayTender(borrowChoicenessTender, productOutput);
            borrowChicenessTenderService.firstOrSecondTender(borrowChoicenessTender, productOutput);
            //判断其是不是第一次投资
            //判断其是不是第二次投资
        }
        BorrowChoicenessTender max = borrowChicenessTenderService.maxTender(productOutput);
        BorrowChoicenessTender last = borrowChicenessTenderService.lastTender(productOutput);
        borrowChicenessTenderService.maxAndLastTender(max, last, productOutput);
    }


    public void  planOriReverify(PlanListInput planListInput)
    {
        List<BorrowCollection> collectionslist = borrowCollectionMapper.selectByTenderId(planListInput.getTenderId());
        if(collectionslist!=null)
        {
            BigDecimal tenderPreCapital = new BigDecimal(0);

            BigDecimal tenderPreIntest = new BigDecimal(0);
            //所有平台奖励的钱
            BigDecimal tenderPlatformInterest = new BigDecimal(0);
            //所有加息劵的钱
            BigDecimal tenderVoucherCouponMoney = new BigDecimal(0);

            BigDecimal addAwaitInterest=  new BigDecimal(0);
            BigDecimal addAwaitCapital=  new BigDecimal(0);
            for (int j = 0; j < collectionslist.size(); j++)
            {
                BorrowCollection collection = collectionslist.get(j);
                int days= (int) DateUtil.diffDay(planListInput.getRepayLastTime(), collection.getPreCollectionTime());
                if(days>0)
                {
                    if(j==0)
                    {
                        //第一期的话
                        int day= (int) DateUtil.diffDay(collection.getPreCollectionTime(), new Date());
                        collection.setPreInterest(collection.getSimulatedInterest()
                                .multiply(new BigDecimal(30 - day).subtract(new BigDecimal(30))));
                        collection.setPlatformAccount(collection.getPlatformAccount().
                                multiply(new BigDecimal(30 - day).subtract(new BigDecimal(30))));
                        collection.setCouponAccount(collection.getCouponAccount().
                                multiply(new BigDecimal(30 - day).subtract(new BigDecimal(30))));
                        collection.setIsShow(1);
                    }
                    else
                    {
                        collection.setPreInterest(collection.getSimulatedInterest());
                        collection.setIsShow(1);
                    }
                    addAwaitInterest = addAwaitInterest.add(collection.getPreInterest());
                    addAwaitCapital = addAwaitCapital.add(collection.getPreCapital());

                    borrowCollectionMapper.updateByPrimaryKeySelective(collection);
                    tenderPreCapital = tenderPreCapital.add(collection.getCapital());
                    tenderPreIntest = tenderPreIntest.add(collection.getPreInterest());
                    tenderPlatformInterest = tenderPlatformInterest.add(collection.getPlatformAccount());
                    tenderVoucherCouponMoney = tenderVoucherCouponMoney.add(collection.getCouponAccount());
                }
                BorrowTender borrowTender = borrowTenderMapper.selectByPrimaryKey(planListInput.getTenderId());
                borrowTender.setPlatformInterest(tenderPlatformInterest);
                borrowTender.setVoucherCouponMoney(tenderVoucherCouponMoney);
                borrowTender.setStatus(2);
                borrowTenderMapper.updateByPrimaryKey(borrowTender);
                accountLogService.insertPlanReverifyLog(addAwaitInterest, planListInput.getUserId(), planListInput.getId().toString());
                accountLogService.insertPlanReverifyCapitalLog(addAwaitCapital, planListInput.getUserId(), planListInput.getId().toString());
            }
        }
    }


    public void insertLastCollection(PlanListInput planListInput)
    {
        List<BorrowChoicenessTender> tenderRecords = borrowChoicenessTenderMapper.selectOriginalListByBorrowId(planListInput.getId());
        for (int j=0;j<tenderRecords.size();j++)
        {
            BorrowCollection borrowCollection = new BorrowCollection();
            borrowCollection.setUserId(tenderRecords.get(j).getUserId());
            borrowCollection.setTenderId(tenderRecords.get(j).getId());
            borrowCollection.setStatus(0);
            borrowCollection.setPeriod(null);
            borrowCollection.setPeriods(null);
            //到期日
            borrowCollection.setPreCollectionTime(planListInput.getRepayLastTime());
            borrowCollection.setPreCapital(tenderRecords.get(j).getPreAccount());
            borrowCollection.setIsShow(1);
            borrowCollection.setPreInterest(new BigDecimal(0));
            borrowCollection.setCapital(new BigDecimal(0));
            borrowCollection.setInterest(new BigDecimal(0));
            borrowCollection.setTenderNid(tenderRecords.get(j).getBorrowId());
            borrowCollectionMapper.insert(borrowCollection);
        }
    }

    /**
     * 理财计划下转让标的复审
     * @param inputs
     */
    @Override
    public void reverifyDevelop(PlanDevelopListInput inputs)
    {
        OwnTransferInput ownTransferInput= new OwnTransferInput();
        ownTransferInput.setTenderId(inputs.getTenderId());
        ownTransferInput.setAccount(inputs.getAccount());
        ownTransferInput.setBorrowName(inputs.getBorrowName());
        ownTransferInput.setBorrowId(inputs.getBorrowId());
        ownTransferInput.setUserId(inputs.getUserId());
        this.doOwnPlanTransfer(ownTransferInput);
        List<PlanNomalInput> list =  inputs.getList();
        for(int n=0;n<list.size();n++)
        {
            PlanNomalInput planNomalInput = list.get(n);
            if(planNomalInput.getRepayLastTime()==null)
            {
                this.doTenderPlanTransferBefore(inputs,planNomalInput);
            }
            else
            {
                if(planNomalInput.getPlayAccount()==1)
                {
                    this.doPlayAccountTransferAfter(inputs,planNomalInput);
                }
                else
                {
                    this.doTenderPlanTransferAfter(inputs,planNomalInput);
                }
            }
        }

    }
}
