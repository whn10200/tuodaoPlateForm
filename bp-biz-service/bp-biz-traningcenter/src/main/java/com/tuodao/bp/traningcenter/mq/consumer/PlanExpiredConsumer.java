package com.tuodao.bp.traningcenter.mq.consumer;

import com.tuodao.bp.activemq.constant.DepositoryMqConstant;
import com.tuodao.bp.model.business.common.input.EmailInput;
import com.tuodao.bp.model.business.traningcenter.cache.TransferTenderInfo;
import com.tuodao.bp.model.business.traningcenter.cache.UnFreezeInfo;
import com.tuodao.bp.model.mq.PlatformTransferMqInfo;
import com.tuodao.bp.model.mq.TenderBankCancelRequestMqInfo;
import com.tuodao.bp.model.mq.TransferMqInfo;
import com.tuodao.bp.traningcenter.client.CommonClient;
import com.tuodao.bp.traningcenter.mq.provider.PlanExpiredProvider;
import com.tuodao.bp.traningcenter.service.impl.GenerateService;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Queue;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 理财计划到期生产者
 * @author: wuzf
 * @date: 2017/11/02
 * @time: 14:39
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class PlanExpiredConsumer {


    private static final Logger logger = LoggerFactory.getLogger(PlanExpiredConsumer.class);


    @Autowired
    CommonClient commonClient;

    @Autowired
    RedissonClient redissonClient;

    /**
     * 失败尝试次数
     */
    @Value("${depository.fail.trySec}")
    private int sec;

    @Value("${depository.fail.notifyUsers}")
    private String notifyUsers;

    @Autowired
    private PlanExpiredProvider planExpiredProvider;

    @Autowired
    private GenerateService generateService;

    /**
     * 平台转个人监听
     * @param platformTransferMqInfo
     */
    @JmsListener(destination = DepositoryMqConstant.DE_OUT_PLATFORM_TRANSFER,
            selector = DepositoryMqConstant.DE_IN_PLATFORM_TRANSFER_EXPIRED__SELECTOR)
    public void platformToPersonListener(PlatformTransferMqInfo platformTransferMqInfo) {
        if(!platformTransferMqInfo.isSuccess()) {
            logger.info("platform transfer is fail, info:{}", platformTransferMqInfo.toString());
            RAtomicLong num = redissonClient.getAtomicLong("platform_transfer_fee_"+platformTransferMqInfo.getUserId());
            num.getAndIncrement();
            if(num.get() == sec) {
                sendEmail("transfer debt failed", platformTransferMqInfo.toString());
                num.delete();
            } else {
                String orderNo = "fs_" + platformTransferMqInfo.getBorrowId() + "_transfer" + generateService.get() +"_transfer";
                platformTransferMqInfo.setOrderNo(orderNo);
                planExpiredProvider.sendToPlatformTransfer(platformTransferMqInfo,
                        DepositoryMqConstant.DE_IN_PLATFORM_TRANSFER_EXPIRED_VALUE);
            }
        }
    }



    /**
     * 撤销投标监听
     * @param tenderBankCancelRequestMqInfo
     */
    @JmsListener(destination = DepositoryMqConstant.DE_OUT_PLATFORM_TRANSFER,
            selector = DepositoryMqConstant.DE_IN_PLATFORM_TRANSFER_EXPIRED__SELECTOR)
    public void cancelTenderListener(TenderBankCancelRequestMqInfo tenderBankCancelRequestMqInfo) {
        if(!tenderBankCancelRequestMqInfo.isSuccess()) {
            logger.info("platform transfer is fail, info:{}", tenderBankCancelRequestMqInfo.toString());
            RAtomicLong num = redissonClient.getAtomicLong("cancel_tender_"+tenderBankCancelRequestMqInfo.getUserId());
            num.getAndIncrement();
            if(num.get() == sec) {
                sendEmail("transfer debt failed", tenderBankCancelRequestMqInfo.toString());
                num.delete();
            } else {
                planExpiredProvider.tenderCancelRequest(tenderBankCancelRequestMqInfo,
                        DepositoryMqConstant.DE_IN_PLATFORM_TRANSFER_EXPIRED_VALUE);
            }
        }
    }




    public void sendEmail(String subject, String content) {
        EmailInput input = new EmailInput();
        input.setSubject(subject);
        input.setContent(content);
        input.setReceivers(notifyUsers);
        commonClient.sendEmail(input);
    }

}
