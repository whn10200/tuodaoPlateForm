package com.tuodao.bp.useraccount.testcontroller;

import com.tuodao.bp.model.mq.AccountIncomeDetailMqInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.jms.Queue;

/**
 * Description: 同步账户累计收益明细
 * User: zkai
 * Date: 2017/9/14
 * Time: 14:31
 * Copyright:拓道金服 Copyright (c) 2017
 */
@RestController
@RequestMapping("/demo")
public class AccountIncomeDetailController {
    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    @Resource(name = "accountIncomeDetailQueue")
    private Queue accountIncomeDetailQueue;

    /**
     * 添加账户累计收益明细
     * @param input
     */
    @RequestMapping(value="/addAccountIncomeDetail",method= RequestMethod.POST)
    @ResponseBody
    public void addAccountIncomeDetail(AccountIncomeDetailMqInfo input) {
        jmsTemplate.convertAndSend(accountIncomeDetailQueue, input);
    }

}
