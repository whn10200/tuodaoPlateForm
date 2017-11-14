package com.tuodao.bp.operation.mq;

import com.alibaba.fastjson.JSON;
import com.tuodao.bp.activemq.constant.MqContstant;
import com.tuodao.bp.model.mq.OpScoreTaskMqInput;
import com.tuodao.bp.operation.service.IOpScoreTaskService;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Description: 运营中心积分mq消费者
 * User: zkai
 * Date: 2017/9/21
 * Time: 10:22
 * Copyright:拓道金服 Copyright (c) 2017
 */
@Component
public class OpScoreTaskMqConsumer {
    private static Logger logger = LoggerFactory.getLogger(OpScoreTaskMqConsumer.class);

    @Autowired
    private IOpScoreTaskService opScoreTaskService;

    /**
     * 新手任务完成，积分变更同步
     * @param opScoreTaskMqInput
     */
    @JmsListener(destination = MqContstant.OP_SCORE_TASK_QUEUE)
    public void userCompleteTask(ActiveMQObjectMessage message, OpScoreTaskMqInput opScoreTaskMqInput) {
        logger.info("任务完成，积分变更同步到运营中心 OpScoreTaskMqInput={}", JSON.toJSONString(opScoreTaskMqInput));
        opScoreTaskService.userCompleteTask(opScoreTaskMqInput);
        logger.info("任务完成，积分变更同步到运营中心完成");
    }
}
