package com.tuodao.bp.traningcenter.mq.consumer;

import com.tuodao.bp.activemq.constant.MqContstant;
import com.tuodao.bp.model.business.traningcenter.input.TenderExecutor;
import com.tuodao.bp.traningcenter.service.DevelopTenderService;
import com.tuodao.bp.traningcenter.service.OrginalBorrowTenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @description: 理财计划下转让投标 队列消费者
 * @author: wuzf
 * @date: 2017/10/18
 * @time: 14:23
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class DevelopTenderMq {

    @Autowired
    private DevelopTenderService developTenderService;

    /**
     * MQ 投标消费接口
     * @param executor
     */
    @JmsListener(destination= MqContstant.TRANING_DEVELOP_TENDER_QUEUE)
    public void tender(TenderExecutor executor){
        developTenderService.tenderConsumer(executor);
    }
}
