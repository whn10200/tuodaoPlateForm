package com.tuodao.bp.traningcenter.mq;

import com.alibaba.fastjson.JSON;
import com.tuodao.bp.activemq.constant.MqContstant;
import com.tuodao.bp.model.mq.AccountLogMqInfo;
import com.tuodao.bp.model.mq.AccountMqInfo;
import com.tuodao.bp.traningcenter.db.model.basic.AccountLog;
import com.tuodao.bp.traningcenter.service.AccountLogService;
import com.tuodao.bp.traningcenter.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @description:资金记录添加以及修改资金mq
 * @author: wuzf
 * @date: 2017/9/27 0027.
 * @time: 11:12
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class AccountLogMq {
    @Autowired
    private AccountLogService accountLogService;

    @Autowired
    private AccountService accountService;

    private static final Logger logger = LoggerFactory.getLogger(AccountLogMq.class);

    /**
     * 接受消息，资金记录添加以及修改资金
     * @param input
     */
    @JmsListener(destination= MqContstant.TRANING_ACCOUNT_LOG_QUEUE)
    public void addAccountIncomeDetail(AccountLogMqInfo input) {
        logger.info("资金记录添加以及修改资金mq接受到的消息,AccountIncomeDetailMqInfo = {}", JSON.toJSONString(input));
        AccountLog accountLog = new AccountLog();
        BeanUtils.copyProperties(input, accountLog);
        accountLogService.updateAccountAndLogPro(accountLog);
        logger.info("资金记录添加以及修改资金mq完成");
    }

    /**
     * 创建资金账户队列
     * @param info
     */
    @JmsListener(destination = MqContstant.TRANING_CREATE_ACCOUNT_QUEUE)
    public void createAccount(AccountMqInfo info){
        accountService.createUserAccount(info.getUserId());
    }
}
