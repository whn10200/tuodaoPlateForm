package com.tuodao.bp.traningcenter.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


import com.tuodao.bp.model.business.traningcenter.output.BorrowTenderOutput;
import com.tuodao.bp.model.constant.traningcenter.CashConstant;
import com.tuodao.bp.model.facade.traningcenter.input.AccountLogParam;
import com.tuodao.bp.model.facade.traningcenter.output.AppAccountLogVo;
import com.tuodao.bp.traningcenter.db.model.basic.*;
import com.tuodao.bp.model.mq.AccountDealMqInfo;
import com.tuodao.bp.result.error.TransactError;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.traningcenter.mq.provider.ProducerMq;
import com.tuodao.bp.utils.BigDecimalUtils;
import com.tuodao.bp.cache.constant.RedisConstans;
import com.tuodao.bp.model.business.traningcenter.input.AccountLogInput;
import com.tuodao.bp.model.constant.traningcenter.TenderConstant;
import com.tuodao.bp.model.facade.traningcenter.output.AccountLogVo;
import com.tuodao.bp.traningcenter.db.mapper.basic.AccountLogMapper;
import com.tuodao.bp.traningcenter.db.mapper.basic.AccountMapper;
import com.tuodao.bp.model.enums.AccountLogType;
import com.tuodao.bp.traningcenter.service.AccountLogService;
import com.tuodao.bp.utils.DateUtil;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @description:
 * @author: wuzf
 * @date: 2017/9/11 0011.
 * @time: 16:31
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class AccountLogServiceImpl implements AccountLogService {

    private static final Logger logger = LoggerFactory.getLogger(AccountLogServiceImpl.class);

    /**
     * 线程等待时间 10s
     */
    private static final int WAIT_TIME = 10;

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountLogMapper accountLogMapper;

    @Autowired
    private ProducerMq producerMQ;

    @Autowired
    RedissonClient redissonClient;

    /**
     * 存管更新用户的资金 AccountLog的资金相关属性 默认均不为空 取消空值判断
     * @param accountLog
     * @param
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateAccountAndLogPro(AccountLog accountLog) {
        RLock fairLock = redissonClient.getFairLock(RedisConstans.ACCOUNT_LOG_PREFIX + accountLog.getUserId());

        try {//加锁 防止其他更新资金日志情况
            if(fairLock.tryLock(WAIT_TIME, TimeUnit.SECONDS)){
                logger.info("account log before :{}", accountLog);
                //不做非空判断,前置已通过校验
                Account account = accountMapper.getByUserId(accountLog.getUserId());
                //提现冻结
                accountLog.setCashFrost(account.getCashFrost().add(accountLog.getCashFrost()));
                account.setCashFrost(accountLog.getCashFrost());
                //投标冻结
                accountLog.setTenderFrost(account.getTenderFrost().add(accountLog.getTenderFrost()));
                account.setTenderFrost(accountLog.getTenderFrost());
                //可提现金额
                accountLog.setBalance(account.getBalance().add(accountLog.getBalance()));
                account.setBalance(accountLog.getBalance());
                //充值金额
                accountLog.setRecharge(account.getRecharge().add(accountLog.getRecharge()));
                account.setRecharge(accountLog.getRecharge());
                //总待收利息
                accountLog.setAwaitInterest(account.getAwaitInterest().add(accountLog.getAwaitInterest()));
                account.setAwaitInterest(accountLog.getAwaitInterest());
                //总待收本金
                accountLog.setAwaitCapital(account.getAwaitCapital().add(accountLog.getAwaitCapital()));
                account.setAwaitCapital(accountLog.getAwaitCapital());
                //累计收益
                account.setTotalEarnings(account.getTotalEarnings().add(accountLog.getIntrestAccount()));
                //可免费提现金额 该字段只在提现,投标或者撤销投标,投标失败等可能出现失败的业务里面设置值(包含债权投标,精选计划投标)
                account.setBalanceCash(account.getBalanceCash().add(accountLog.getBalanceCash()));
                setBalanceCash(account,accountLog);
                //提现成功
                if(accountLog.getType() == AccountLogType.CASH_SUCCESS.getCode()){
                    account.setTotalWithdraw(account.getTotalWithdraw().add(accountLog.getAccount()));
                }
                //充值成功
                if(accountLog.getType() == AccountLogType.RECHARGE.getCode()){
                    account.setTotalRecharge(account.getTotalRecharge().add(accountLog.getAccount()));
                }
                //推广投资,回款奖励
                if(accountLog.getType() == AccountLogType.INVITE_TENDER_AWARD.getCode() ||
                        accountLog.getType() == AccountLogType.INVITE_COLLECTION_AWARD.getCode()){
                    account.setReturnAmount(account.getReturnAmount().add(accountLog.getAccount()));
                }
                if(accountLog.getType() == AccountLogType.PLAN_TENDER_FROST.getCode()){
                    account.setCouponsAmount(account.getCouponsAmount().add(accountLog.getIntrestAccount()));
                }
                BigDecimal total = account.getTotal();
                account.setTotal(account.getCashFrost()
                        .add(account.getTenderFrost())
                        .add(account.getBalance())
                        .add(account.getAwaitCapital())
                        .add(account.getAwaitInterest()));
                BigDecimal subTotal= account.getTotal().subtract(total);
                accountLog.setTotal(account.getTotal());
                accountLog.setAddTime(new Date());
                verifyAccount(account);
                accountLogMapper.insertSelective(accountLog);
                accountMapper.updateByPrimaryKeySelective(account);
                doByType(account, accountLog.getType(), subTotal);
                logger.info("account log end :{}", accountLog.toString());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            fairLock.unlock();
        }
    }

    /**
     * 计算免费提现额度 注意 该处不包含 "投标或者撤销投标,投标失败时设置值(债权投标,精选计划投标)"
     * @param account
     * @param accountLog
     */
    private void setBalanceCash(Account account, AccountLog accountLog) {
        //增加的免费提现额度的部分包含:邀请好友投资奖励,邀请好友回款奖励,债券回收本金,债券回收利息,债券奖励手续费, 精选计划奖励利息
        boolean addBalanceCash = accountLog.getType() == AccountLogType.INVITE_TENDER_AWARD.getCode()
                || accountLog.getType() == AccountLogType.INVITE_COLLECTION_AWARD.getCode()
                || accountLog.getType() == AccountLogType.TRANSFER_RECYCLE_PRINCIPAL.getCode()
                || accountLog.getType() == AccountLogType.TRANSFER_RECYCLE_INTEREST.getCode()
                || accountLog.getType() == AccountLogType.TRANSFER_AWARD_FEE.getCode()
                || accountLog.getType() == AccountLogType.PLAN_AWARD_INTEREST.getCode();
        //减少免费提现额度的部分包含:债券扣除手续费,精选计划扣除利息
        boolean subBalanceCash = accountLog.getType() == AccountLogType.TRANSFER_FEE.getCode()
                || accountLog.getType() == AccountLogType.PLAN_DEDUCT_INTEREST.getCode();
        if(addBalanceCash){
            //原始+操作金额
            account.setBalanceCash(account.getBalanceCash().add(accountLog.getAccount()));
        }
        if(subBalanceCash){
            account.setBalanceCash(account.getBalanceCash().subtract(accountLog.getAccount()));
        }
    }

    /**
     * 校验账户的合法性 如果所有资金账户均不能为负数
     * @param account
     */
    private void verifyAccount(Account account){
        if(account.getBalance().doubleValue() < 0){
            logger.error("资金账户可用金额错误:{}",account.getBalance().doubleValue());
            throw new BizFeignException(TransactError.ACCOUNT_BALANCE_MINUS_ERROR);
        }
        if(account.getRecharge().doubleValue() < 0){
            logger.error("资金账户充值金额错误:{}",account.getRecharge().doubleValue());
            throw new BizFeignException(TransactError.ACCOUNT_RECHARGE_ERROR);
        }
        if(account.getTenderFrost().doubleValue() < 0){
            logger.error("资金账户投标金额错误:{}",account.getTenderFrost().doubleValue());
            throw new BizFeignException(TransactError.ACCOUNT_TENDER_ERROR);
        }
        if(account.getCashFrost().doubleValue() < 0 ){
            logger.error("资金账户提现金额错误:{}",account.getCashFrost().doubleValue());
            throw new BizFeignException(TransactError.ACCOUNT_CASH_ERROR);
        }
        if(account.getAwaitCapital().doubleValue() < 0){
            logger.error("资金账户待收本金错误:{}",account.getAwaitCapital().doubleValue());
            throw new BizFeignException(TransactError.ACCOUNT_AWAIT_CAPITAL_ERROR);
        }
        if(account.getAwaitInterest().doubleValue() < 0){
            logger.error("资金账户待收利息错误:{}",account.getAwaitInterest().doubleValue());
            throw new BizFeignException(TransactError.ACCOUNT_AWAIT_INTEREST_ERROR);
        }
        if(account.getBalanceCash().doubleValue() < 0){
            logger.error("资金账户可提现金额错误:{}",account.getBalanceCash().doubleValue());
            throw new BizFeignException(TransactError.ACCOUNT_BALANCE_CASH_ERROR);
        }
        //累计收益等由于均为+操作,如果有负数均为程序错误
    }



    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void batchUpdate(List<AccountLog> accountLogList) {
        accountLogList.forEach(accountLog -> this.updateAccountAndLogPro(accountLog));
    }


    /**
     * 判断各种
     * account在数据库中默认零 该处不进行非空判断
     * 发送mq到用户中心接受（生产者）
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public void doByType(Account account, Integer accountLogType, BigDecimal subTotal){

        AccountDealMqInfo accountDealMqInfo = new AccountDealMqInfo();
        accountDealMqInfo.setUserId(account.getUserId());
        accountDealMqInfo.setType(AccountLogType.getNewMsg(accountLogType));
        accountDealMqInfo.setTotalFund(subTotal);
        accountDealMqInfo.setTotalEarnings(account.getTotalEarnings());
        accountDealMqInfo.setDueInFund(account.getAwaitCapital().add(account.getAwaitInterest()));
        accountDealMqInfo.setDueInPrincipal(account.getAwaitCapital());
        accountDealMqInfo.setDueInInterest(account.getAwaitInterest());
        accountDealMqInfo.setUsableFund(account.getBalance());
        accountDealMqInfo.setFreezeFund(account.getCashFrost().add(account.getTenderFrost()));
        accountDealMqInfo.setCanWithdrawFund(account.getBalance().subtract(account.getRecharge()));
        accountDealMqInfo.setReturnAmount(account.getReturnAmount());

        switch (AccountLogType.values()[accountLogType]) {
            case PLAN_AWARD_INTEREST:
                accountDealMqInfo.setInvestmentTimes(1);//TODO 累计投资次数没有
                break;
            case TENDER_SUCCESS:
                accountDealMqInfo.setInvestmentTimes(1);
                break;
            case RECHARGE:
                accountDealMqInfo.setTotalRecharge(account.getTotalRecharge());
                break;
            default:
                break;
        }

        producerMQ.sendAccountDealMqInfo(accountDealMqInfo);
    }

    public void recharge(Account account, String logType, BigDecimal subTotal) {
        AccountDealMqInfo accountDealMqInfo = new AccountDealMqInfo();
        accountDealMqInfo.setUserId(account.getUserId());
        accountDealMqInfo.setType(logType);
        accountDealMqInfo.setTotalFund(subTotal);
    }

    public void planTenderFrost(Account account, Integer accountLogType, BigDecimal subTotal)
    {
        BigDecimal first = new BigDecimal(0);
        AccountDealMqInfo accountDealMqInfo = new AccountDealMqInfo(null,null,first,first,first,first,first,first,first,first,first,first,
                null,first,first);
        accountDealMqInfo.setType(AccountLogType.getNewMsg(accountLogType));
        accountDealMqInfo.setUserId(account.getUserId());

        accountDealMqInfo.setTotalEarnings(account.getTotalEarnings());
        accountDealMqInfo.setDueInFund(account.getAwaitCapital().add(account.getAwaitInterest()));
        accountDealMqInfo.setDueInPrincipal(account.getAwaitCapital());
        accountDealMqInfo.setDueInInterest(account.getAwaitInterest());
        accountDealMqInfo.setUsableFund(account.getBalance());
        accountDealMqInfo.setFreezeFund(account.getCashFrost().add(account.getTenderFrost()));
        accountDealMqInfo.setCanWithdrawFund(account.getBalance().subtract(account.getRecharge()));
        accountDealMqInfo.setInvestmentTimes(1);//TODO 累计投资次数没有
        accountDealMqInfo.setReturnAmount(account.getReturnAmount());
        accountDealMqInfo.setTotalFund(subTotal);
        producerMQ.sendAccountDealMqInfo(accountDealMqInfo);
    }


    /**
     * 提现冻结资金记录
     * @param cash 提现申请信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertAccountCashApplyLog(AccountCash cash) {
        AccountLog log = new AccountLog();
        log.setUserId(cash.getUserId());
        //发生金额
        log.setAccount(cash.getAccount());
        log.setType(AccountLogType.CASH_FROST.getCode());
        /*
         *是否对投资人显示该条自己记录: 0:不显示 1:显示
         */
        log.setIsShow(TenderConstant.LOG_SHOW);
        log.setOrderNo(cash.getOrderNo());
        //冻结金额增加
        log.setCashFrost(cash.getAccount());
        //可用余额减少
        log.setBalance(BigDecimalUtils.negation(cash.getAccount()));
        log.setChangeType(AccountLogType.CASH_FROST.getType());
        log.setRemarks(cash.getRemarks());
        //可免费提现金额减少
        log.setBalanceCash(BigDecimalUtils.negation(cash.getFeeAccount()));
        //累计提现不需要额外填写
        updateAccountAndLogPro(log);
    }

    /**
     * 提现成功 增加资金记录
     * @param cash
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertAccountCashSuccessLog(AccountCash cash,String remarks) {
        AccountLog log = new AccountLog();
        log.setUserId(cash.getUserId());
        //提现成功默认发生金额实际提现金额
        log.setAccount(cash.getRealAccount());

        log.setType(AccountLogType.CASH_SUCCESS.getCode());
        //解冻
        log.setCashFrost(BigDecimalUtils.negation(cash.getRealAccount()));
        log.setChangeType(AccountLogType.CASH_SUCCESS.getType());
        log.setRemarks(remarks);

        log.setIsShow(TenderConstant.LOG_SHOW);
        log.setOrderNo(cash.getOrderNo());

        updateAccountAndLogPro(log);
    }

    @Override
    public void insertAccountCashFeeLog(AccountCash cash, String remarks) {
        AccountLog log = new AccountLog();
        log.setUserId(cash.getUserId());
        //提现成功默认发生金额实际提现金额
        log.setAccount(cash.getFee());

        log.setType(AccountLogType.CASH_SUCCESS_FEE.getCode());
        //解冻
        log.setCashFrost(BigDecimalUtils.negation(cash.getFee()));
        log.setChangeType(AccountLogType.CASH_SUCCESS_FEE.getType());
        log.setRemarks(remarks);

        log.setIsShow(TenderConstant.LOG_SHOW);
        log.setOrderNo(cash.getOrderNo());
        updateAccountAndLogPro(log);
    }

    @Override
    public void insertAccountCashFailLog(AccountCash cash) {
        AccountLog log = new AccountLog();
        log.setUserId(cash.getUserId());
        //提现成功默认发生金额为提现金额
        log.setAccount(cash.getAccount());

        log.setType(AccountLogType.CASH_FAIL.getCode());
        //提现金额减少
        log.setCashFrost(BigDecimalUtils.negation(cash.getAccount()));
        //可用余额增加
        log.setBalance(cash.getAccount());
        log.setChangeType(AccountLogType.CASH_FAIL.getType());
        log.setRemarks("提现失败解冻");
        //将免费提现金额增加
        log.setBalanceCash(cash.getFeeAccount());

        log.setIsShow(TenderConstant.LOG_SHOW);
        log.setOrderNo(cash.getOrderNo());

        updateAccountAndLogPro(log);
    }

    /**
     * 投标冻结 资金记录
     * @param tender 投标信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertBorrowTenderFrostLog(BorrowTender tender,String remarks) {

        AccountLog log = new AccountLog();
        //可用余额取反
        log.setBalance(BigDecimalUtils.negation(tender.getAccount()));
        log.setUserId(tender.getUserId());
        //操作金额=实际投标金额
        log.setAccount(tender.getAccount());
        log.setIsShow(TenderConstant.LOG_SHOW);
        log.setOrderNo(tender.getOrderId());
        log.setRemarks(remarks);
        log.setType(AccountLogType.TENDER_FROST.getCode());
        log.setChangeType(AccountLogType.TENDER_FROST.getType());
        log.setTenderFrost(tender.getAccount());

        log.setBalanceCash(BigDecimalUtils.negation(tender.getFeeAccount()));

        updateAccountAndLogPro(log);
    }



    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public PageInfo<AccountLogVo> getUserAccountLogByPage(AccountLogInput input) {
        PageHelper.startPage(input.getCurrentPage(), input.getPageSize());
        List<AccountLog> logList = accountLogMapper.getList(input);

        PageInfo<AccountLog> pageInfo = new PageInfo<>(logList);

        List<AccountLogVo> list = new ArrayList<>();
        if(pageInfo.getList() != null && pageInfo.getList().size() > 0){
            AccountLogVo vo;
            for(AccountLog accountLog : pageInfo.getList()){
                vo = new AccountLogVo();
                vo.setAccount(BigDecimalUtils.centToYuanFormat(accountLog.getAccount().doubleValue()));
                vo.setBalance(BigDecimalUtils.centToYuanFormat(accountLog.getBalance().doubleValue()));
                vo.setTransactionTime(DateUtil.formatLong(accountLog.getAddTime()));
                vo.setRemarks(accountLog.getRemarks());
                vo.setType(accountLog.getType());
                list.add(vo);
            }
        }
        PageInfo<AccountLogVo> result = new PageInfo<>();
        result.setList(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public PageInfo<AppAccountLogVo> getAppUserAccountLogByPage(AccountLogParam param) {
        PageHelper.startPage(param.getCurrentPage(), param.getPageSize());
        AccountLogInput logInput = new AccountLogInput();
        logInput.setUserId(param.getUserId());
        logInput.setType(param.getType());
        List<AccountLog> logList = accountLogMapper.getList(logInput);
        PageInfo<AccountLog> pageInfo = new PageInfo<>(logList);

        List<AppAccountLogVo> list = new ArrayList<>();
        if(pageInfo.getList() != null && pageInfo.getList().size() > 0){
            AppAccountLogVo vo;
            for(AccountLog accountLog : pageInfo.getList()){
                vo = new AppAccountLogVo();
                vo.setAccount(BigDecimalUtils.centToYuanFormat(accountLog.getAccount().doubleValue()));
                vo.setAddTime(DateUtil.formatLong(accountLog.getAddTime()));
                list.add(vo);
            }
        }

        PageInfo<AppAccountLogVo> result = new PageInfo<>();
        result.setList(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertBorrowCollectionLog(BorrowCollection collection) {
        AccountLog log = new AccountLog();
        log.setUserId(collection.getUserId());
        log.setIsShow(TenderConstant.LOG_SHOW);

        //操作金额=实际还款本金
        log.setAccount(collection.getCapital().add(collection.getInterest()));
        //可用余额增加
        log.setBalance(log.getAccount());
        //待收本金减少
        log.setAwaitCapital(BigDecimalUtils.negation(collection.getCapital()));

        //收益变化
        log.setIntrestAccount(collection.getInterest());

        if(collection.getStatus() == TenderConstant.COLLECTION_STATUS_2){
            log.setRemarks("标的提前回款");
            log.setType(AccountLogType.BORROW_ADVANCE_COLLECTION.getCode());
            log.setChangeType(AccountLogType.BORROW_ADVANCE_COLLECTION.getType());
            //提前还款时由于剩余期数的利息不会付,因此直接将代收利息清除,取预计还款利息
            log.setAwaitInterest(BigDecimalUtils.negation(collection.getPreInterest()));
        }else{
            log.setRemarks("标的回款");
            log.setType(AccountLogType.BORROW_COLLECTION.getCode());
            log.setChangeType(AccountLogType.BORROW_COLLECTION.getType());
            //代收利息减少
            log.setAwaitInterest(BigDecimalUtils.negation(collection.getInterest()));
        }
        updateAccountAndLogPro(log);
    }


    /**
     * 借款人还款,投资人批量回款资金记录 无法进行数据库层的批量操作 只能单个调用
     * @param list
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertBorrowCollectionLogBatch(List<BorrowCollection> list) {
        for (BorrowCollection collection : list){
            insertBorrowCollectionLog(collection);
        }
    }

    /**
     * 满标复审通过,待收增加资金日志
     * @param list
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertTenderSuccessLog(List<BorrowTenderOutput> list,String remarks) {
        AccountLog log;
        for(BorrowTenderOutput tender : list){
            log = new AccountLog();
            log.setUserId(tender.getUserId());
            log.setIsShow(TenderConstant.LOG_SHOW);
            log.setType(AccountLogType.TENDER_SUCCESS.getCode());
            log.setChangeType(AccountLogType.TENDER_SUCCESS.getType());
            //操作金额=投资本金+预计回款总利息
            log.setAccount(tender.getAccountInterest().add(tender.getPreAccount()));
            //投标冻结减少
            log.setTenderFrost(BigDecimalUtils.negation(tender.getAccount()));
            //待收本金增加
            log.setAwaitCapital(tender.getPreAccount());
            //代收利息增加
            log.setAwaitInterest(tender.getAccountInterest());
            log.setRemarks(remarks);
            updateAccountAndLogPro(log);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertTenderCancelLog(List<BorrowTender> list,String remarks) {
        AccountLog log;
        for(BorrowTender tender : list){
            log = new AccountLog();
            log.setUserId(tender.getUserId());
            log.setIsShow(TenderConstant.LOG_SHOW);
            log.setRemarks(remarks);
            log.setType(AccountLogType.TENDER_CANCEL.getCode());
            log.setChangeType(AccountLogType.TENDER_CANCEL.getType());
            //操作金额=实际投标金额
            log.setAccount(tender.getAccount());
            //投标冻结= -投标金额
            log.setTenderFrost(BigDecimalUtils.negation(tender.getAccount()));
            //可用余额增加
            log.setBalance(tender.getAccount());

            log.setBalanceCash(tender.getFeeAccount());

            updateAccountAndLogPro(log);
        }
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void insertTenderFailLog(BorrowTender tender, AccountLogType accountLogType) {
        AccountLog log = new AccountLog();
        log.setUserId(tender.getUserId());
        log.setIsShow(TenderConstant.LOG_SHOW);
        //投标冻结减少
        log.setTenderFrost(BigDecimalUtils.negation(tender.getAccount()));
        log.setType(accountLogType.getCode());
        log.setChangeType(accountLogType.getType());
        //可用余额增加
        log.setBalance(tender.getAccount());
        log.setRemarks("投标失败,解冻资金");
        log.setAccount(tender.getAccount());
        log.setBalanceCash(tender.getFeeAccount());

        updateAccountAndLogPro(log);
    }


    /**
     * 理财计划复审增加预计收益
     * @param
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertPlanReverifyLog(BigDecimal addAwaitInterest,String userId,String id)
    {
        AccountLog log = new AccountLog();
        log.setAccount(addAwaitInterest);
        log.setUserId(userId);
        log.setType(AccountLogType.PLAN_DEDUCT_REVERIFY.getCode());
        log.setAwaitInterest(addAwaitInterest);
        log.setFromAccount(id);
        log.setToRemarks(AccountLogType.PLAN_DEDUCT_REVERIFY.getMsg());
        log.setIsShow(TenderConstant.LOG_SHOW);
        log.setChangeType(AccountLogType.PLAN_DEDUCT_REVERIFY.getType());
        updateAccountAndLogPro(log);
    }

    /**
     * 理财计划复审增加预计本金
     * @param
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertPlanReverifyCapitalLog(BigDecimal addAwaitCapital,String userId,String id)
    {
        AccountLog log = new AccountLog();
        log.setAccount(addAwaitCapital);
        log.setUserId(userId);
        log.setType(AccountLogType.PLAN_DEDUCT_REVERIFY.getCode());
        log.setAwaitCapital(addAwaitCapital);
        log.setFromAccount(id);
        log.setToRemarks(AccountLogType.PLAN_DEDUCT_REVERIFY.getMsg());
        log.setIsShow(TenderConstant.LOG_SHOW);
        log.setChangeType(AccountLogType.PLAN_DEDUCT_REVERIFY.getType());
        updateAccountAndLogPro(log);
    }

    /**
     * 理财计划下撤销投标减少冻结 增加余额
     * @param
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void withDrawTender(BigDecimal amount,String userId)
    {
        AccountLog log = new AccountLog();
        log.setAccount(amount);
        log.setUserId(userId);
        log.setType(AccountLogType.PLAN_DEDUCT_BALANCE.getCode());
        log.setBalance(amount);
        log.setTenderFrost(amount.multiply(new BigDecimal(-1)));
        log.setFromAccount(userId);
        log.setToRemarks(AccountLogType.PLAN_DEDUCT_BALANCE.getMsg());
        log.setIsShow(TenderConstant.LOG_SHOW);
        log.setChangeType(AccountLogType.PLAN_DEDUCT_BALANCE.getType());
        updateAccountAndLogPro(log);
    }

    /**
     * 解冻理财计划(未匹配资金）
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean thawingPlanFunds(BorrowChoicenessTender tender) {

        AccountLog accountLog = new AccountLog();

        BigDecimal bankAmount = tender.getPreAccount().subtract(tender.getAccount());

        accountLog.setUserId(tender.getUserId());
        accountLog.setType(AccountLogType.PLAN_TENDER_UNFREEZE.getCode());
        accountLog.setAccount(tender.getPreAccount());
        accountLog.setTotal(new BigDecimal(0));
        accountLog.setCashFrost(new BigDecimal(0));
        accountLog.setTenderFrost(BigDecimalUtils.negation(bankAmount));
        accountLog.setBalance(bankAmount);
        accountLog.setRecharge(new BigDecimal(0));
        accountLog.setAwaitInterest(new BigDecimal(0));
        accountLog.setAwaitCapital(new BigDecimal(0));
        accountLog.setFromAccount(tender.getUserId());
        accountLog.setToAccount(tender.getUserId());
        accountLog.setToRemarks(AccountLogType.PLAN_TENDER_UNFREEZE.getMsg());
        accountLog.setIsShow(1);
        accountLog.setIntrestAccount(new BigDecimal(0));
        accountLog.setFeeAccount(new BigDecimal(0));
        accountLog.setChangeType(AccountLogType.PLAN_TENDER_UNFREEZE.getType());
        updateAccountAndLogPro(accountLog);
        return true;
    }


}
