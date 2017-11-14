package com.tuodao.bp.useraccount.listener;

import com.tuodao.bp.activemq.constant.MqContstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * author hechuan
 * <p>
 * created on 2017/9/18
 * <p>
 * since V1.0.0
 */
@Component
public class UserAccoutTimerBusiness {

    private static final Logger logger = LoggerFactory.getLogger(UserAccoutTimerBusiness.class);

    @JmsListener(destination = MqContstant.TASK_TIMER_SEND_QUEUE,selector = "taskQueue='userAccountTimer'")
    public void execute(Map<String,Object> map) {
        logger.info("map ：{}",map);
        logger.info("do task......begin;;");
        logger.info("do task......end;;");
    }
}
