package com.tuodao.bp.task.sdk.listener;

import com.google.common.base.Preconditions;
import com.tuodao.bp.task.sdk.driver.PostEvent;
import com.tuodao.bp.task.sdk.driver.TaskEventBus;
import com.tuodao.bp.task.sdk.event.TaskEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.jms.Queue;
import java.util.Map;


/**
 * author hechuan
 * <p>
 * created on 2017/9/15
 * <p>
 * since V1.0.0
 */
@Component
public class TaskTimerListener {
    // 日志
    private static final Logger logger = LoggerFactory.getLogger(TaskTimerListener.class);

    @Autowired
    private TaskEventBus eventBus;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Resource(name = "taskTimerReceiveQueue")
    private Queue taskTimerReceiveQueue;

    // @JmsListener(destination = MqContstant.TASK_TIMER_SEND_QUEUE)
    public void comsumer(Map<String,String> map){
        logger.info("消息定时消费开始，消息主体 : [{}]",map);

        String taskId = map.get("taskId");
        String taskQueue = map.get("taskQueue");

        Preconditions.checkNotNull(taskId);
        Preconditions.checkNotNull(taskQueue);

        TaskEvent event = new TaskEvent(taskQueue);
        PostEvent postEvent = PostEvent.create().setTaskId(taskQueue).setEvent(event);

        eventBus.post(postEvent);

        if(CollectionUtils.isEmpty(event.getResultList())){
            logger.info("消息定时消费结束,开始回调，taskId : [{}],taskQueue：[{}]",taskId,taskQueue);

            jmsMessagingTemplate.convertAndSend(taskTimerReceiveQueue,taskId);

            logger.info("消息定时消费结束,结束回调，taskId : [{}],taskQueue：[{}]",taskId,taskQueue);
        }else {
            //HE CHUAN TODO SEND EMAIL
        }


    }
}
