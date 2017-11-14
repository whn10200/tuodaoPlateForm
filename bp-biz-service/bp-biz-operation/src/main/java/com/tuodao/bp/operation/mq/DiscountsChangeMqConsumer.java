package com.tuodao.bp.operation.mq;

import com.tuodao.bp.activemq.constant.MqContstant;
import com.tuodao.bp.model.mq.OpDiscountsChangeMqInfo;
import com.tuodao.bp.operation.constant.OperationBizConstant;
import com.tuodao.bp.operation.persistence.mapper.basic.OpUserDiscountMapper;
import com.tuodao.bp.operation.persistence.mapper.basic.OpUserOperationDataMapper;
import com.tuodao.bp.operation.persistence.model.basic.OpUserDiscount;
import com.tuodao.bp.operation.persistence.model.basic.OpUserOperationData;
import com.tuodao.bp.result.exception.BizFeignException;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.jms.Queue;
import java.util.Objects;

/**
 * @description: 优惠券使用MQ消费
 * @author: mif
 * @date: 2017/10/12
 * @time: 09:49
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class DiscountsChangeMqConsumer {
    private static Logger logger = LoggerFactory.getLogger(DiscountsChangeMqConsumer.class);

    @Resource
    private OpUserDiscountMapper opUserDiscountMapper;

    @Resource
    private OpUserOperationDataMapper opUserOperationDataMapper;


    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Resource(name = "opDiscountAsyncMqQueue")
    private Queue opDiscountAsyncMqQueue;

    @JmsListener(destination = MqContstant.OP_DISCOUNT_CHANGE_MQ_QUEUE)
    @Transactional(rollbackFor = BizFeignException.class)
    public void consumer(ActiveMQObjectMessage message, OpDiscountsChangeMqInfo opDiscountsChangeMqInfo) {
        logger.info("优惠券使用状态变更MQ消费,opDiscountsChangeMqInfo={}", opDiscountsChangeMqInfo);

        final int disStatus = opDiscountsChangeMqInfo.getDisStaus();
        final String userId = opDiscountsChangeMqInfo.getUserId();

        OpUserDiscount opUserDiscount = opUserDiscountMapper.selectByPrimaryKey(opDiscountsChangeMqInfo.getDisId());
        if (null == opUserDiscount || !Objects.equals(opUserDiscount.getUserId(), userId)) {
            logger.error("优惠券不存在,消费失败");
            callBack(opDiscountsChangeMqInfo, false, "优惠券不存在,消费失败");
            return;
        }

        if (Objects.equals(opDiscountsChangeMqInfo.getDisStaus(), opUserDiscount.getDisStatus())) {
            logger.warn("优惠券状态相等，无需修改");
            callBack(opDiscountsChangeMqInfo, true, "优惠券状态相等，无需修改");
            return;
        }
        try {
            opUserDiscount.setDisStatus(opDiscountsChangeMqInfo.getDisStaus());
            opUserDiscountMapper.updateByPrimaryKey(opUserDiscount);


            // 更新用户运营数据
            int count = (disStatus == OperationBizConstant.DISCOUNT_STATUS_USABLE) ? 1 : -1;
            // 类型(1:抵用券,2:加息券,3:免费提现次数)
            int discountType = opUserDiscount.getDiscountType();
            OpUserOperationData userOperationData = opUserOperationDataMapper.selectByPrimaryKey(userId);
            if (discountType == OperationBizConstant.DISCOUNT_TYPE_VOUCHER) {
                //抵用券
                userOperationData.setUsableVoucher(userOperationData.getUsableVoucher() + count);
                opUserOperationDataMapper.updateByPrimaryKey(userOperationData);
            } else if (discountType == OperationBizConstant.DISCOUNT_TYPE_COUPON) {
                // 加息券
                userOperationData.setUsablePateCoupon(userOperationData.getUsablePateCoupon() + count);
                opUserOperationDataMapper.updateByPrimaryKey(userOperationData);
            } else {
                logger.error("优惠券类型为提现次数，无需修改");
            }
            logger.info("优惠券使用状态变更MQ消费结束,opDiscountsChangeMqInfo={}", opDiscountsChangeMqInfo);
            callBack(opDiscountsChangeMqInfo, true, "优惠券状态修改，消费成功");
        } catch (Exception e) {
            logger.error("优惠券使用状态变更MQ消费异常,msg:[{}]", e.getMessage(), e);
            if (message.getRedeliveryCounter() == 5) {
                callBack(opDiscountsChangeMqInfo, false, "优惠券使用状态变更MQ消费异常");
            } else {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 回调
     *
     * @param opDiscountsChangeMqInfo 消息体内容
     * @param success                 成功-true,失败-false
     * @param remark                  备注
     */
    private void callBack(OpDiscountsChangeMqInfo opDiscountsChangeMqInfo, boolean success, String remark) {
        opDiscountsChangeMqInfo.setSuccess(success);
        opDiscountsChangeMqInfo.setRemark(remark);
        jmsMessagingTemplate.convertAndSend(opDiscountAsyncMqQueue, opDiscountsChangeMqInfo);
    }
}
