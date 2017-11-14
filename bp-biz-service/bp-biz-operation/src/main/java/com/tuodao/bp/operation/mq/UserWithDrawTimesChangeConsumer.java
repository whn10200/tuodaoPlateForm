package com.tuodao.bp.operation.mq;

import com.tuodao.bp.activemq.constant.MqContstant;
import com.tuodao.bp.model.mq.WithDrawTimesMqInfo;
import com.tuodao.bp.operation.service.IUserDiscountService;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;

/**
 * 改变用户免费提现次数
 * author hechuan
 * <p>
 * created on 2017/10/23
 * <p>
 * since V1.0.0
 */
public class UserWithDrawTimesChangeConsumer{

    // 日志
    private static final Logger logger = LoggerFactory.getLogger(UserWithDrawTimesChangeConsumer.class);

    @Autowired
    private IUserDiscountService userDiscountService;

    @JmsListener(destination = MqContstant.OP_WITH_DRAW_TIMES_CHANGE_MQ_QUEUE)
    public void withDrawTimesChangeConsumer(ActiveMQObjectMessage message, WithDrawTimesMqInfo withDrawTimesMqInfo) {
        logger.info("免费提现次数队列接收 开始：[{}]", withDrawTimesMqInfo);

        // 保存用户免费提现次数关系
        userDiscountService.consumerWithDrawTimes(withDrawTimesMqInfo);

        logger.info("免费提现次数队列消费成功：[{}]", withDrawTimesMqInfo);
    }
}
