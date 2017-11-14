package com.tuodao.bp.operation.mq;

import com.tuodao.bp.activemq.constant.MqContstant;
import com.tuodao.bp.model.constant.PublicConstant;
import com.tuodao.bp.model.mq.CouponGrantMqInfo;
import com.tuodao.bp.model.mq.OpVipUpMqInfo;
import com.tuodao.bp.operation.service.IUserDiscountService;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @description: 用户Vip升级消费者
 * @author: mif
 * @date: 2017/9/28
 * @time: 17:49
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class OpVipUpMqConsumer {

    private static Logger logger = LoggerFactory.getLogger(OpVipUpMqConsumer.class);

    @Resource
    private IUserDiscountService userDiscountService;


    @JmsListener(destination = MqContstant.OP_VIP_UP_MQ_QUEUE)
    public void consumer(ActiveMQObjectMessage message, OpVipUpMqInfo opVipUpMqInfo) {
        logger.info("用户VIP升级消费,opVipUpMqInfo={}", opVipUpMqInfo);

        String welfareActivityCode = PublicConstant.VIP_UP_MAP.get(opVipUpMqInfo.getVipLevel());
        //如果升级发放优惠券
        CouponGrantMqInfo couponGrantMqInfo = new CouponGrantMqInfo();
        couponGrantMqInfo.setUserId(opVipUpMqInfo.getUserId());
        couponGrantMqInfo.setMobile(opVipUpMqInfo.getMobile());
        couponGrantMqInfo.setWelfareActivityCode(welfareActivityCode);
        userDiscountService.couponGrant(couponGrantMqInfo, opVipUpMqInfo.getVipLevel() > opVipUpMqInfo.getMaxVipLevel());
    }

}
