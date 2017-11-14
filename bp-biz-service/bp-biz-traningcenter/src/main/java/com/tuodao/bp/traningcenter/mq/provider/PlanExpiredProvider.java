package com.tuodao.bp.traningcenter.mq.provider;

import com.tuodao.bp.activemq.constant.DepositoryMqConstant;
import com.tuodao.bp.model.mq.PlatformTransferMqInfo;
import com.tuodao.bp.model.mq.TenderBankCancelRequestMqInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Queue;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 理财计划到期生产者
 * @author: wuzf
 * @date: 2017/11/02
 * @time: 14:39
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class PlanExpiredProvider {


    private static final Logger logger = LoggerFactory.getLogger(PlanExpiredProvider.class);

    @Resource 
    JmsMessagingTemplate jmsMessagingTemplate;
    @Resource(name = "deInPlatformTransfer")
    private Queue inPlatformTransfer;


    /**
     * 平台转个人
     * @param platformTransferRequest
     */
    public void sendToPlatformTransfer(PlatformTransferMqInfo platformTransferRequest, String headerValue) {
        Map<String, Object> header = new HashMap<>(1);
        header.put(DepositoryMqConstant.DEPOSITORY_HEAD_KEY, headerValue);
        jmsMessagingTemplate.convertAndSend(inPlatformTransfer, platformTransferRequest, header);
        logger.info("send message to platform transfer:{}", platformTransferRequest.toString());
    }



    /**
     * 撤销投标
     */
    public void tenderCancelRequest(TenderBankCancelRequestMqInfo tenderBankCancelRequestMqInfo,String headerValue){
        Map<String, Object> header = new HashMap<>(1);
        header.put(DepositoryMqConstant.DEPOSITORY_HEAD_KEY, headerValue);
        jmsMessagingTemplate.convertAndSend(inPlatformTransfer, tenderBankCancelRequestMqInfo, header);
        logger.info("send message to platform transfer:{}", tenderBankCancelRequestMqInfo.toString());
    }


}
