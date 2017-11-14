package com.tuodao.bp.traningcenter.mq.provider;

import com.tuodao.bp.activemq.constant.DepositoryMqConstant;
import com.tuodao.bp.model.mq.FreezeMqInfo;
import com.tuodao.bp.model.mq.OpDiscountsChangeMqInfo;
import com.tuodao.bp.model.mq.PlatformTransferMqInfo;
import com.tuodao.bp.model.mq.TransferMqInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Queue;
import java.util.HashMap;
import java.util.Map;

/**
 * 理财计划债转 生产者
 * @author qingli.chen
 * @date 2017/10/20
 * @description
 */
@Component
public class PlanCreditAssignmentProvider {

    private static final Logger logger = LoggerFactory.getLogger(PlanCreditAssignmentProvider.class);

    @Resource
    JmsMessagingTemplate jmsMessagingTemplate;

    @Resource(name = "opDiscountChangeMqQueue")
    private Queue opDiscountChangeMqQueue;

    @Resource(name = "deInPlatformFundFreeze")
    private Queue inPlatformFundFreeze;

    @Resource(name = "deOutPlatformFundUnblock")
    private Queue inPlatformFundUnlock;

    @Resource(name = "deInBiddingTransferDebt")
    private Queue inBiddingTransferDebt;

    @Resource(name = "deInPlatformTransfer")
    private Queue inPlatformTransfer;


    /**
     * 转让
     * @param transferRequest
     */
    public void sendToTransfer(TransferMqInfo transferRequest, String headerValue) {
        Map<String, Object> header = new HashMap<>(1);
        header.put(DepositoryMqConstant.DEPOSITORY_HEAD_KEY,headerValue);
        jmsMessagingTemplate.convertAndSend(inBiddingTransferDebt, transferRequest, header);
        logger.info("send message to transfer:{}", transferRequest.toString());
    }


}
