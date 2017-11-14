package com.tuodao.bp.operation.listener;

import com.tuodao.bp.activemq.constant.MqContstant;
import com.tuodao.bp.operation.service.IOpTaskPushService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Description: 运营中心新手任务定时任务
 * User: zkai
 * Date: 2017/9/21
 * Time: 14:24
 * Copyright:拓道金服 Copyright (c) 2017
 */
@Component
public class NoviceTaskPushBusiness {

    private static final Logger logger = LoggerFactory.getLogger(NoviceTaskPushBusiness.class);

    @Autowired
    private IOpTaskPushService opTaskPushService;

    @JmsListener(destination = MqContstant.TASK_TIMER_SEND_QUEUE,selector = "taskQueue='noviceTaskPushTimer'")
    public void execute(Map<String,Object> map) {
        logger.info("map ：{}",map);
        logger.info("do novice task push task......begin;");
        opTaskPushService.timedPush();
        logger.info("do novice task push task......end;");
    }
}
