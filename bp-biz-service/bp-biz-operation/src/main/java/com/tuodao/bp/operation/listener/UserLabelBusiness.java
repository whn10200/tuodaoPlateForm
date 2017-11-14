package com.tuodao.bp.operation.listener;

import com.tuodao.bp.activemq.constant.MqContstant;
import com.tuodao.bp.operation.service.IUserLabelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Description: 运营中心用户标签定时任务
 * User: zkai
 * Date: 2017/9/21
 * Time: 14:24
 * Copyright:拓道金服 Copyright (c) 2017
 */
@Component
public class UserLabelBusiness{

    private static final Logger logger = LoggerFactory.getLogger(UserLabelBusiness.class);

    @Autowired
    private IUserLabelService userLabelService;

    @JmsListener(destination = MqContstant.TASK_TIMER_SEND_QUEUE,selector = "taskQueue='userLabelTimer'")
    public void execute(Map<String,Object> map) {
        logger.info("map ：{}",map);
        logger.info("do user label task......begin;;");
        userLabelService.timeClockUpdateLable();
        logger.info("do user label task......end;;");
    }
}
