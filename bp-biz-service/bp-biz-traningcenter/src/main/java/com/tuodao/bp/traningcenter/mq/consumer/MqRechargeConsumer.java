package com.tuodao.bp.traningcenter.mq.consumer;

import com.tuodao.bp.activemq.constant.DepositoryMqConstant;
import com.tuodao.bp.activemq.constant.MqContstant;
import com.tuodao.bp.model.mq.GatewayRechargeMqInfo;
import com.tuodao.bp.model.mq.QuickRechargeApplyMqInfo;
import com.tuodao.bp.model.mq.QuickRechargeConfirmMqInfo;
import com.tuodao.bp.traningcenter.enums.RechargeStatus;
import com.tuodao.bp.traningcenter.enums.depository.FastRechargeStatus;
import com.tuodao.bp.traningcenter.service.AccountRechargeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 充值队列 生产者
 * @author qingli.chen
 * @date 2017/10/16
 * @description
 */
@Component
public class MqRechargeConsumer {

    private static final Logger logger = LoggerFactory.getLogger(MqRechargeConsumer.class);


    @Autowired
    AccountRechargeService accountRechargeService;

    /**
     * 网关充值消费
     * @param info
     */
    @JmsListener(destination= DepositoryMqConstant.DE_OUT_RECHARGE_GATEWAY,
            selector = DepositoryMqConstant.DE_IN_RECHARGE_GATEWAY_SELECTOR)
    public void gateListener(GatewayRechargeMqInfo info){
        logger.info("网关充值消费:{}", info.toString());
        if(!info.isSuccess()) {
            accountRechargeService.update(info.getOrderNo(), RechargeStatus.FAILED.getValue());
        }
    }

    /**
     * 快捷充值发送短信消费
     * @param info
     */
    @JmsListener(destination= DepositoryMqConstant.DE_OUT_RECHARGE_QUICK_APPLY,
            selector = DepositoryMqConstant.DE_IN_RECHARGE_QUICK_APPLY_SELECTOR)
    public void sendSmsListener(QuickRechargeApplyMqInfo info){
        logger.info("快捷充值发送短信消费:{}", info.toString());
        if(!info.isSuccess()) {
            accountRechargeService.update(info.getOrderNo(), RechargeStatus.FAILED.getValue());
        }
    }

    /**
     * 快捷充值消费
     * @param info
     */
    @JmsListener(destination= DepositoryMqConstant.DE_OUT_RECHARGE_QUICK_CONFIRM,
            selector = DepositoryMqConstant.DE_IN_RECHARGE_QUICK_CONFIRM_SELECTOR)
    public void fastListener(QuickRechargeConfirmMqInfo info){
        logger.info("快捷充值消费:{}", info.toString());

        int status = 0;
        if(info.getOrderStatus() == FastRechargeStatus.APPLY.getValue()
                || info.getOrderStatus() == FastRechargeStatus.PROCESS.getValue()) {

        } else if(info.getOrderStatus() == FastRechargeStatus.FAILED.getValue()) {
            status = RechargeStatus.FAILED.getValue();
        } else if(info.getOrderStatus() == FastRechargeStatus.SUCCESS.getValue()) {
            status = RechargeStatus.SUCCESS.getValue();
        }

        accountRechargeService.update(info.getOrderNo(), status);
    }
}
