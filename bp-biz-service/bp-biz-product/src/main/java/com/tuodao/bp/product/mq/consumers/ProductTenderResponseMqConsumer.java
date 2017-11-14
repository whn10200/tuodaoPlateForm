package com.tuodao.bp.product.mq.consumers;

import com.tuodao.bp.model.mq.ProductTenderMqInfo;
import com.tuodao.bp.product.service.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import static com.tuodao.bp.activemq.constant.MqContstant.PRODUCT_TENDER_RESPONSE_QUEUE;

/**
 * @description: 产品中心-交易中心投资队列消费者
 * @author: wuchengjie
 * @date: 2017/9/27
 * @time: 16:51
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class ProductTenderResponseMqConsumer {

    private static Logger logger = LoggerFactory.getLogger(ProductTenderResponseMqConsumer.class);

    @Autowired
    private IProductService productService;

    /**
     * 理财计划投资返回的消费
     * @param tenderMqInfo
     */
    @JmsListener(destination = PRODUCT_TENDER_RESPONSE_QUEUE)
    public void consumer(ProductTenderMqInfo tenderMqInfo) {
        logger.info("交易中心-产品中心投资队列消费者， queue PRODUCT_TENDER_RESPONSE_QUEUE ,tenderMqInfo={}", tenderMqInfo);


        Integer result = productService.purchaseProductByMqInfo(tenderMqInfo);

        //剩余金额小于投资金额 那么就废弃此订单（当做重复订单处理）

    }


}
