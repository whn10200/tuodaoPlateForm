package com.tuodao.bp.traningcenter.mq;

import com.tuodao.bp.activemq.constant.DepositoryMqConstant;
import com.tuodao.bp.activemq.constant.MqContstant;
import com.tuodao.bp.model.constant.traningcenter.TenderConstant;
import com.tuodao.bp.model.mq.OpDiscountsChangeMqInfo;
import com.tuodao.bp.model.mq.ProductTenderMqInfo;
import com.tuodao.bp.model.mq.TenderBankRequestMqInfo;
import com.tuodao.bp.model.mq.TenderBankStatusRequestMqInfo;
import com.tuodao.bp.traningcenter.mq.provider.ProducerMq;
import com.tuodao.bp.traningcenter.service.BorrowTenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


/**
 * @description: 投标 队列消费者
 * @author: 王艳兵
 * @date: 2017/10/12
 * @time: 14:23
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class TenderMq {

    private static final Logger LOGGER = LoggerFactory.getLogger(TenderMq.class);

    @Autowired
    private BorrowTenderService borrowTenderService;

    @Autowired
    private ProducerMq producerMq;

    /**
     * 更新优惠券成功后回调 执行投标信息
     * @param info
     */
    @JmsListener(destination = MqContstant.OP_DISCOUNT_ASYNC_MQ_QUEUE)
    public void updateVoucherStatusResponse(OpDiscountsChangeMqInfo info){
        LOGGER.debug("更新优惠券成功后的回调,入参:{}",info);
        if(info.isSuccess()){
            if(info.getExecutor() != null){
                producerMq.commonTender(info.getExecutor());
            }else{
                LOGGER.debug("更新优惠券异步回调没有查询到投标信息,不做处理");
            }
        }else{
            borrowTenderService.putTenderResult(info.getExecutor().getTenderKey(),info.getRemark(), TenderConstant.TENDER_RESULT_FAIL);
        }
    }


    /**
     * MQ 投标消费接口
     * @param info 消息队列参数
     */
    @JmsListener(destination= MqContstant.PRODUCT_TENDER_QUEUE)
    public void tender(ProductTenderMqInfo info){
        LOGGER.debug("投标队列开始消费,入参ProductTenderMqInfo:{}",info);
        if(info.getBusinessType() == 0){
            borrowTenderService.tenderConsumer(info.getExecutor());
        }
    }

    /**
     * 投标后银行成功或失败异步回调
     * @param info
     */
    @JmsListener(destination = DepositoryMqConstant.DE_OUT_BIDDING_BID,selector = DepositoryMqConstant.TENDER_IN_VALUE_SELECTOR)
    public void bankAsyncResponse(TenderBankRequestMqInfo info){
        LOGGER.debug("投标请求银行异步回调结果,入参:",info);
        if(info.isSuccess()){
            borrowTenderService.tenderSuccessTask(info.getOrderId(),info.getTenderKey());
        }else{
            borrowTenderService.tenderFailTask(info.getOrderId(),"投标失败,银行请求错误",info.getTenderKey());
        }
    }




}
