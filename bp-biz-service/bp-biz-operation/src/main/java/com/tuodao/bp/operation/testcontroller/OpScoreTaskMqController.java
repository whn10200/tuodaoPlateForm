package com.tuodao.bp.operation.testcontroller;

import com.tuodao.bp.model.mq.OpScoreTaskMqInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.jms.Queue;

/**
 * Description: 用户积分同步
 * User: zkai
 * Date: 2017/9/14
 * Time: 14:31
 * Copyright:拓道金服 Copyright (c) 2017
 */
@RestController
@RequestMapping("/demo")
public class OpScoreTaskMqController {
    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    @Resource(name = "opScoreTaskQueue")
    private Queue opScoreTaskQueue;

    /**
     * 用户积分同步
     * @param input
     */
    @RequestMapping(value="/opScoreTaskMq",method= RequestMethod.POST)
    @ResponseBody
    public void opScoreTaskMq(OpScoreTaskMqInput input) {
        jmsTemplate.convertAndSend(opScoreTaskQueue, input);
    }

}
