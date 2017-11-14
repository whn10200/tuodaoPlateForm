package com.tuodao.bp.operation.mq;

import com.tuodao.bp.model.mq.OpDiscountsChangeMqInfo;
import com.tuodao.bp.operation.OperationTestApi;
import com.tuodao.bp.operation.constant.OperationBizConstant;
import org.junit.Test;
import org.springframework.jms.core.JmsMessagingTemplate;

import javax.annotation.Resource;
import javax.jms.Queue;

import static org.junit.Assert.*;

/**
 * @description: ${Description}
 * @author: mif
 * @date: 2017/10/12
 * @time: 11:21
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class DiscountsChangeMqConsumerTest extends OperationTestApi {

    @Resource(name = "opDiscountChangeMqQueue")
    private Queue opDiscountChangeMqQueue;

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Test
    public void consumer() throws Exception {
        OpDiscountsChangeMqInfo opDiscountsChangeMqInfo = new OpDiscountsChangeMqInfo();
        opDiscountsChangeMqInfo.setUserId("15988888925-caqdfgojsrcgna8oi8b8");
        opDiscountsChangeMqInfo.setDisId(50L);
        opDiscountsChangeMqInfo.setDisStaus(OperationBizConstant.DISCOUNT_STATUS_USED);

        jmsMessagingTemplate.convertAndSend(opDiscountChangeMqQueue,opDiscountsChangeMqInfo);
    }

}