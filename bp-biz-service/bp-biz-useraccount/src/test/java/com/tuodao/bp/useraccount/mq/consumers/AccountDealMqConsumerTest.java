package com.tuodao.bp.useraccount.mq.consumers;

import com.tuodao.bp.model.constant.PublicConstant;
import com.tuodao.bp.model.mq.AccountDealMqInfo;
import com.tuodao.bp.useraccount.UserAccountTestApi;
import org.junit.Test;
import org.springframework.jms.core.JmsMessagingTemplate;

import javax.annotation.Resource;
import javax.jms.Queue;
import java.math.BigDecimal;

/**
 * @description: ${Description}
 * @author: mif
 * @date: 2017/10/10
 * @time: 11:05
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class AccountDealMqConsumerTest extends UserAccountTestApi {

    @Resource(name = "accountDealFinanceQueue")
    private Queue accountDealFinanceQueue;

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    /**
     * 满标复审
     */
    @Test
    public void sendToQueueWithTheReview() {
        AccountDealMqInfo mqInfo = new AccountDealMqInfo();
        mqInfo.setUserId("15988888935-rhzuq6qwr84rdl4c3g8k");
        mqInfo.setType(PublicConstant.MQ_ACCOUNT_DEAL_TYPE_WITH_THE_REVIEW);
//        mqInfo.setIsFirstTender(true);
        mqInfo.setTotalFund(new BigDecimal("500.00"));
        mqInfo.setInvestmentAmount(new BigDecimal("200.00"));

        jmsMessagingTemplate.convertAndSend(accountDealFinanceQueue, mqInfo);
    }

    /**
     * 回款
     */
    @Test
    public void sendToQueueBackToArticle() {
        AccountDealMqInfo mqInfo = new AccountDealMqInfo();
        mqInfo.setUserId("15988888935-rhzuq6qwr84rdl4c3g8k");
        mqInfo.setType(PublicConstant.MQ_ACCOUNT_DEAL_TYPE_BACK_TO_ARTICLE);
        mqInfo.setTotalFund(new BigDecimal("1000.00"));
        mqInfo.setTotalEarnings(new BigDecimal("100.00"));
        mqInfo.setInvestmentAmount(new BigDecimal("300.00"));

        jmsMessagingTemplate.convertAndSend(accountDealFinanceQueue, mqInfo);
    }
}