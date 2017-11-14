package com.tuodao.bp.operation.mq;

import com.tuodao.bp.activemq.constant.MqContstant;
import com.tuodao.bp.model.mq.CouponGrantMqInfo;
import com.tuodao.bp.operation.service.IUserDiscountService;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 优惠券消息队列消费者
 * author hechuan
 * <p>
 * created on 2017/9/22
 * <p>
 * since V1.0.0
 */
@Component
public class UserDiscountsMQConsumer {

    // 日志
    private static final Logger logger = LoggerFactory.getLogger(UserDiscountsMQConsumer.class);

    @Autowired
    private IUserDiscountService userDiscountService;

    @JmsListener(destination = MqContstant.OP_COUPON_GRANT_QUEUE)
    public void couponGrantConsumer(ActiveMQObjectMessage message, CouponGrantMqInfo couponGrantMqInfo) {
        logger.info("优惠券消息队列接收 开始：[{}]", couponGrantMqInfo);

        // 保存用户优惠券关系
        userDiscountService.couponGrant(couponGrantMqInfo, true);

        logger.info("优惠券消息队列消费成功：[{}]", couponGrantMqInfo);
    }

}
