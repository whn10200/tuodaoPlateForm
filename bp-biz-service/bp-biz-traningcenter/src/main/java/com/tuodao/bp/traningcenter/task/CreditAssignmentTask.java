package com.tuodao.bp.traningcenter.task;

import com.tuodao.bp.activemq.constant.MqContstant;
import com.tuodao.bp.traningcenter.service.CreditAssignmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author tookbra
 * @date 2017/10/23
 * @description
 */
@Component
public class CreditAssignmentTask{


    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(CreditAssignmentTask.class);

    @Autowired
    CreditAssignmentService creditAssignmentService;


    /**
     * 满标复审
     * 每半小时执行
     * @param map
     * @return
     */
    @JmsListener(destination = MqContstant.TASK_TIMER_SEND_QUEUE, selector = "taskQueue='TRANSFER_FULL_REVIEW'")
    public void execute(Map<String,Object> map) {
        logger.info("定时任务满标复审开始-每半小时执行：map={}",map);
        creditAssignmentService.fullReview();
        logger.info("定时任务满标复审结束-每半小时执行：map={}",map);
    }

    /**
     * 解冻任务
     * 每半小时执行
     * @param map
     * @return
     */
    @JmsListener(destination = MqContstant.TASK_TIMER_SEND_QUEUE, selector = "taskQueue='TRANSFER_UNFREEZE'")
    public void execute1(Map<String,Object> map) {
        logger.info("begin to load transfer unfreeze task");
        creditAssignmentService.unFreeze();
        logger.info("end to load transfer unfreeze task");
    }

    /**
     * 债转到期回滚
     * @param map
     */
    @JmsListener(destination = MqContstant.TASK_TIMER_SEND_QUEUE, selector = "taskQueue='TRANSFER_MATURITY'")
    public void execute2(Map<String, Object> map) {
        logger.info("begin to load transfer transfer maturity task");
        creditAssignmentService.revokedTransfer();
        logger.info("end to load transfer transfer maturity task");
    }
}
