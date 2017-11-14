package com.tuodao.bp.useraccount.mq.consumers;

import com.alibaba.fastjson.JSON;
import com.tuodao.bp.activemq.constant.MqContstant;
import com.tuodao.bp.model.mq.AccountIncomeDetailMqInfo;
import com.tuodao.bp.useraccount.service.IAccountIncomeDetailService;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Description: 累计收益明细 提供消息接收者，收到消息
 * User: zkai
 * Date: 2017/9/13
 * Time: 15:37
 * Copyright:拓道金服 Copyright (c) 2017
 */
@Component
public class AccountIncomeDetailMq {
    private static final Logger logger = LoggerFactory.getLogger(AccountIncomeDetailMq.class);

    @Autowired
    private IAccountIncomeDetailService accountIncomeDetailService;

    /**
     * 接受消息，添加账户累计收益
     * @param input
     */
    @JmsListener(destination= MqContstant.ACCOUNT_INCOME_DETAIL_QUEUE)
    public void addAccountIncomeDetail(ActiveMQObjectMessage message, AccountIncomeDetailMqInfo input) {
        logger.info("账户累计收益明细同步mq接受到的消息,AccountIncomeDetailMqInfo = {}", JSON.toJSONString(input));
        accountIncomeDetailService.addAccountIncomeDetail(input);
        logger.info("账户累计收益明细同步mq完成");
    }
}
