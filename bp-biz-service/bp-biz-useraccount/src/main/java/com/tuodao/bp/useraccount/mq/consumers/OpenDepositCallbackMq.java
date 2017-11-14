package com.tuodao.bp.useraccount.mq.consumers;

import com.tuodao.bp.activemq.constant.DepositoryMqConstant;
import com.tuodao.bp.model.business.useraccount.input.OpenDepositCallbackInput;
import com.tuodao.bp.useraccount.service.IUserDepositService;
import com.tuodao.bp.useraccount.service.UserBaseService;
import org.apache.activemq.command.ActiveMQMapMessage;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import java.util.HashMap;


/**
 * Description:
 * User: zkai
 * Date: 2017/10/24
 * Time: 10:07
 * Copyright:拓道金服 Copyright (c) 2017
 */
@Component
public class OpenDepositCallbackMq extends UserBaseService {
    private static final Logger logger = LoggerFactory.getLogger(OpenDepositCallbackMq.class);

    @Resource
    private IUserDepositService userDepositService;

    @JmsListener(destination = DepositoryMqConstant.DE_OUT_USER_REGIST_4_ELE,selector = DepositoryMqConstant.DE_SELECTOR_USER_REGIST_4_ELE)
    public void consumer(ActiveMQMapMessage message, HashMap<String, String> map) {
        OpenDepositCallbackInput openDepositCallbackInput = new OpenDepositCallbackInput();
        openDepositCallbackInput.fromHashMap(map);
        logger.info("开通开通存管回调， queue de_out_user_regist_4_ele ,OpenDepositCallbackInput={}", openDepositCallbackInput.toString());
        userDepositService.openDepositCallback(openDepositCallbackInput);
        logger.info("开通开通存管回调结束");

        // TODO 消息推送
    }
}
