package com.tuodao.bp.traningcenter.mq.consumer;

import com.tuodao.bp.activemq.constant.MqContstant;
import com.tuodao.bp.cache.basic.traningcenter.ReturnsCache;
import com.tuodao.bp.model.ReturnCacheInfo;
import com.tuodao.bp.model.business.traningcenter.input.ReverifyPlanInput;
import com.tuodao.bp.model.mq.OpDiscountsChangeMqInfo;
import com.tuodao.bp.model.mq.ProductUpdateMqInfo;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.traningcenter.constant.TraningCenterExceptionConstant;
import com.tuodao.bp.traningcenter.db.model.basic.AccountStatus;
import com.tuodao.bp.traningcenter.service.AccountStatusService;
import com.tuodao.bp.traningcenter.service.BorrowChicenessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:理财计划复审的mq
 * @author: wuzf
 * @date: 2017/10/10 0027.
 * @time: 11:12
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class ReverifyPlanMq {

    private static final Logger logger = LoggerFactory.getLogger(ReverifyPlanMq.class);

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Resource
    private BorrowChicenessService borrowChicenessService;
    @Resource
    private AccountStatusService accountStatusService;
    @Resource
    ReturnsCache returnsCache;

    /**
     * 理财计划复审的消费者
     * @param
     */
    @JmsListener(destination= MqContstant.TRANING_REVERIFY_PLAN_QUEUE)
    public void reverifyPlanMq(ReverifyPlanInput reverifyPlanInput) {
        try
        {
            borrowChicenessService.planReverify(reverifyPlanInput);
            if(reverifyPlanInput.getList().get(0).getKey()!=null)
            {
                //在缓存中存入正在处理中
                ReturnCacheInfo returnCacheInfo = new ReturnCacheInfo();
                returnCacheInfo.setStatus(1);
                returnCacheInfo.setKey(reverifyPlanInput.getList().get(0).getKey());
                returnsCache.putReturnInfo(returnCacheInfo);
            }
        }
        catch (Exception e)
        {
            if(reverifyPlanInput.getList().get(0).getKey()!=null) {
                ReturnCacheInfo returnCacheInfo = new ReturnCacheInfo();
                returnCacheInfo.setStatus(2);
                returnCacheInfo.setKey(reverifyPlanInput.getList().get(0).getKey());
                returnCacheInfo.setCode(TraningCenterExceptionConstant.REVERIFY_PLAN_FARIL);
                returnCacheInfo.setContent("复审失败");
                returnsCache.putReturnInfo(returnCacheInfo);
            }
            throw new BizFeignException(TraningCenterExceptionConstant.REVERIFY_PLAN_FARIL);
        }
        finally {
            List<AccountStatus> accountStatusList = new ArrayList<>();
            AccountStatus accountStatus = new AccountStatus();
            accountStatus.setProductId(reverifyPlanInput.getProductOutput().getId());
            accountStatusList.add(accountStatus);
            Boolean flag = accountStatusService.updateAccountStatus(accountStatusList, 4);
        }
    }

    /**
     * 给产品中心的mq（生产者）
     */
    public void creatproductUpdateMqInfo(Integer borrowId,BigDecimal preAccount)
    {
        ProductUpdateMqInfo productUpdateMqInfo = new ProductUpdateMqInfo();
        productUpdateMqInfo.setOperation(0);
        productUpdateMqInfo.setProductId(borrowId);
        productUpdateMqInfo.setTenderAmount(preAccount);
//        jmsMessagingTemplate.convertAndSend(productUpdateQueue, productUpdateMqInfo);
    }

    /**
     * 给运营中心的mq（生产者）
     */
    public void creatOpDiscountsChangeMqInfo(String userId,Integer disId)
    {
        OpDiscountsChangeMqInfo opDiscountsChangeMqInfo = new OpDiscountsChangeMqInfo();
        opDiscountsChangeMqInfo.setUserId(userId);
        opDiscountsChangeMqInfo.setDisId((long) disId);
        opDiscountsChangeMqInfo.setDisStaus(1);
//        jmsMessagingTemplate.convertAndSend(opDiscountChangeMqQueue, opDiscountsChangeMqInfo);

//        DemoOuput demoOuput = jmsMessagingTemplate.convertSendAndReceive(opDiscountChangeMqQueue,opDiscountsChangeMqInfo,DemoOuput.class);
    }

}
