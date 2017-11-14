package com.tuodao.bp.product.mq.consumers;

import com.tuodao.bp.cache.basic.useraccount.UserAccountCache;
import com.tuodao.bp.model.business.traningcenter.output.SelectClaimListOutput;
import com.tuodao.bp.model.business.traningcenter.output.SelectClaimOutput;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.model.mq.ProductUpdateMqInfo;
import com.tuodao.bp.product.db.mapper.basic.ProductMapper;
import com.tuodao.bp.product.db.model.basic.Product;
import com.tuodao.bp.product.service.IProductService;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.tuodao.bp.activemq.constant.MqContstant.RELEASE_DEBTS_QUEUE;

/**
 * @description: 产品中心 更新产品队列消费者
 * @author: wuchengjie
 * @date: 2017/9/27
 * @time: 16:51
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class ReleaseDebtsMqConsumer {

    private static Logger logger = LoggerFactory.getLogger(ReleaseDebtsMqConsumer.class);

    @Autowired
    private IProductService productService;

    @Autowired
    private ProductMapper productMapper;

    /**
     * 释放理财计划债权队列
     * @param mqInfo
     */
    @JmsListener(destination = RELEASE_DEBTS_QUEUE)
    public void consumer(ActiveMQObjectMessage message, SelectClaimListOutput mqInfo) {
        logger.info("释放理财计划债权队列， queue RELEASE_DEBTS_QUEUE ,SelectClaimListOutput={}", mqInfo);
        Integer productId =  mqInfo.getProductId();
        Product product = productMapper.selectByPrimaryKey(productId);
        List<SelectClaimOutput> list = mqInfo.getList();

        productService.doFinancialProductsReleaseDebts(product,list);

    }


}
