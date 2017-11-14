package com.tuodao.bp.product.mq.consumers;

import com.tuodao.bp.cache.basic.useraccount.UserAccountCache;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.model.mq.ProductUpdateMqInfo;
import com.tuodao.bp.product.service.IProductService;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import static com.tuodao.bp.activemq.constant.MqContstant.PRODUCT_UPDATE_QUEUE;

/**
 * @description: 产品中心 更新产品队列消费者
 * @author: wuchengjie
 * @date: 2017/9/27
 * @time: 16:51
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class ProductUpdateMqConsumer {

    private static Logger logger = LoggerFactory.getLogger(ProductUpdateMqConsumer.class);

    @Autowired
    private IProductService productService;

    @Autowired
    private UserAccountCache userAccountCache;

    /**
     * 理财计划投资返回的消费
     * （在发标之前的更新没有加入到其中 是因为在发标之前是不太会有重复更新同一条数据出现的）
     * @param mqInfo
     */
    @JmsListener(destination = PRODUCT_UPDATE_QUEUE)
    public void consumer(ActiveMQObjectMessage message, ProductUpdateMqInfo mqInfo) {
        logger.info("产品中心投资队列消费者， queue PRODUCT_TENDER_RESPONSE_QUEUE ,tenderMqInfo={}", mqInfo);
        Integer operationId = mqInfo.getOperation();
        if(StringUtils.isBlank(mqInfo.getOperationUserName()) &&StringUtils.isNotBlank(mqInfo.getOperationUserId()) )
        {
            UserAccountInfo info =  userAccountCache.getUserAccoutInfo(mqInfo.getOperationUserId());
            if(info != null){
                mqInfo.setOperationUserName(info.getUserName());
            }
        }

        Integer result;
        switch (operationId){
            case 0:   //加入理财计划
                result = productService.joinPlan(mqInfo);
                logger.info("结果： {}",result);
                break;
            case 1:   //投标方法 不返回信息给他们
                result = productService.purchaseProductByMqInfo(mqInfo);
                logger.info("结果： {}",result);
                 break;
            case 2:    //大后台更新产品状态的信息 （发标 或者 撤标）
                productService.updateProductStatus(mqInfo);
                logger.info("结果：成功");
                break;
            case 3:    //对于散标的满标复审
                productService.reverifyBorrow(mqInfo);
                logger.info("结果：成功");
                break;
            case 4:    //撤回理财计划底层标的
                productService.withdrawPlanBorrow(mqInfo);
                logger.info("结果：成功");
                break;
            default: break;
        }

    }


}
