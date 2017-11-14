package com.tuodao.bp.useraccount.mq.consumers;

import com.tuodao.bp.model.mq.AccountDealMqInfo;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.useraccount.persistence.mapper.basic.AccountFinanceMapper;
import com.tuodao.bp.useraccount.persistence.mapper.basic.AccountFundsMapper;
import com.tuodao.bp.useraccount.persistence.model.basic.AccountFinance;
import com.tuodao.bp.useraccount.persistence.model.basic.AccountFunds;
import com.tuodao.bp.useraccount.service.UserBaseService;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import static com.tuodao.bp.activemq.constant.MqContstant.ACCOUNT_DEAL_FINANCE_QUEUE;

/**
 * @description: 账户中心-交易中心资金队列消费者
 * @author: mif
 * @date: 2017/9/14
 * @time: 16:51
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class AccountDealMqConsumer extends UserBaseService {

    private static Logger logger = LoggerFactory.getLogger(AccountDealMqConsumer.class);

    @Resource
    private AccountFinanceMapper accountFinanceMapper;

    @Resource
    private AccountFundsMapper accountFundsMapper;


    @JmsListener(destination = ACCOUNT_DEAL_FINANCE_QUEUE)
    @Transactional(rollbackFor = BizFeignException.class)
    public void consumer(ActiveMQObjectMessage message, AccountDealMqInfo accountDealMqInfo) {
        logger.info("用户-交易中心资金队列消费者， queue ACCOUNT_DEAL_FINANCE_QUEUE ,accountDealMqInfo={}", accountDealMqInfo);

        AccountFinance accountFinance = accountFinanceMapper.selectByPrimaryKey(accountDealMqInfo.getUserId());

        //update 金融账户资金（有生产端定义是新增还是减少，新增为正数，减少为负数）
        AccountFinance temp = new AccountFinance();
        temp.setUserId(accountDealMqInfo.getUserId());

        temp.setTotalFund(null == accountDealMqInfo.getTotalFund() ? accountFinance.getTotalFund() : accountFinance.getTotalFund().add(accountDealMqInfo.getTotalFund()));
        temp.setTotalEarnings(null == accountDealMqInfo.getTotalEarnings() ? accountFinance.getTotalEarnings() : accountFinance.getTotalEarnings().add(accountDealMqInfo.getTotalEarnings()));
        temp.setDueInFund(null == accountDealMqInfo.getDueInFund() ? accountFinance.getDueInFund() : accountFinance.getDueInFund().add(accountDealMqInfo.getDueInFund()));
        temp.setUsableFund(null == accountDealMqInfo.getUsableFund() ? accountFinance.getUsableFund() : accountFinance.getUsableFund().add(accountDealMqInfo.getUsableFund()));
        temp.setDueInPrincipal(null == accountDealMqInfo.getDueInPrincipal() ? accountFinance.getDueInPrincipal() : accountFinance.getDueInPrincipal().add(accountDealMqInfo.getDueInPrincipal()));
        temp.setDueInInterest(null == accountDealMqInfo.getDueInInterest() ? accountFinance.getDueInInterest() : accountFinance.getDueInInterest().add(accountDealMqInfo.getDueInInterest()));
        temp.setFreezeFund(null == accountDealMqInfo.getFreezeFund() ? accountFinance.getFreezeFund() : accountFinance.getFreezeFund().add(accountDealMqInfo.getFreezeFund()));
        temp.setCanWithdrawFund(null == accountDealMqInfo.getCanWithdrawFund() ? accountFinance.getCanWithdrawFund() : accountFinance.getCanWithdrawFund().add(accountDealMqInfo.getCanWithdrawFund()));
        temp.setTotalRecharge(null == accountDealMqInfo.getTotalRecharge() ? accountFinance.getTotalRecharge() : accountFinance.getTotalRecharge().add(accountDealMqInfo.getTotalRecharge()));
        temp.setTotalWithdraw(null == accountDealMqInfo.getTotalWithdraw() ? accountFinance.getTotalWithdraw() : accountFinance.getTotalWithdraw().add(accountDealMqInfo.getTotalWithdraw()));
        temp.setInvestmentTimes(null == accountDealMqInfo.getInvestmentTimes() ? accountFinance.getInvestmentTimes() : accountFinance.getInvestmentTimes() + accountDealMqInfo.getInvestmentTimes());
        temp.setInvestmentAmount(null == accountDealMqInfo.getInvestmentAmount() ? accountFinance.getInvestmentAmount() : accountFinance.getInvestmentAmount().add(accountDealMqInfo.getInvestmentAmount()));
        temp.setReturnAmount(null == accountDealMqInfo.getReturnAmount() ? accountFinance.getReturnAmount() : accountFinance.getReturnAmount().add(accountDealMqInfo.getReturnAmount()));
        temp.setGmtModifier(accountDealMqInfo.getUserId());

        accountFinanceMapper.updateByPrimaryKeySelective(temp);

        //update 平台账户资金
        AccountFunds funds = accountFundsMapper.selectByPrimaryKey(temp.getUserId());
        funds.setTotalFund(null == accountDealMqInfo.getTotalFund() ? funds.getTotalFund() : funds.getTotalFund().add(accountDealMqInfo.getTotalFund()));
        funds.setFinanceFund(null == accountDealMqInfo.getTotalFund() ? funds.getFinanceFund() : funds.getFinanceFund().add(accountDealMqInfo.getTotalFund()));
        funds.setGmtModifier(accountDealMqInfo.getUserId());
        accountFundsMapper.updateByPrimaryKeySelective(funds);

    }
}
