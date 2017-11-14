package com.tuodao.bp.traningcenter.mq.consumer;

import com.tuodao.bp.activemq.constant.MqContstant;
import com.tuodao.bp.cache.basic.traningcenter.ReturnsCache;
import com.tuodao.bp.model.ReturnCacheInfo;
import com.tuodao.bp.model.business.traningcenter.input.PlanNomalInput;
import com.tuodao.bp.model.business.traningcenter.input.PlanNomalListInput;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.traningcenter.constant.TraningCenterExceptionConstant;
import com.tuodao.bp.traningcenter.db.model.basic.AccountStatus;
import com.tuodao.bp.traningcenter.mq.provider.ProducerMq;
import com.tuodao.bp.traningcenter.service.AccountStatusService;
import com.tuodao.bp.traningcenter.service.BorrowChicenessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * @description:理财计划下普通标的复审的mq
 * @author: wuzf
 * @date: 2017/10/10 0027.
 * @time: 11:12
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class ReverifyOriginalMq {

    private static final Logger logger = LoggerFactory.getLogger(ReverifyOriginalMq.class);

    @Resource
    private BorrowChicenessService borrowChicenessService;
    @Resource
    private AccountStatusService accountStatusService;
    @Resource
    ReturnsCache returnsCache;
    @Resource
    private ProducerMq producerMQ;


    /**
     * 理财计划下普通标的复审消费者
     * @param
     */
    @JmsListener(destination= MqContstant.TRANING_REVERIFY_ORGINAL_QUEUE)
    public void ReverifyOriginal(PlanNomalListInput inputs) {
        try
        {
            borrowChicenessService.reverifyPlanNomal(inputs);
            if(inputs.getKey()!=null)
            {
                ReturnCacheInfo returnCacheInfo = new ReturnCacheInfo();
                returnCacheInfo.setStatus(1);
                returnCacheInfo.setKey(inputs.getKey());
                returnsCache.putReturnInfo(returnCacheInfo);
            }
            producerMQ.creatproductUpdateMqInfo(inputs.getInputs().get(0).getLastProductId(), "");
        }
        catch (Exception e)
        {
            if(inputs.getKey()!=null) {
                ReturnCacheInfo returnCacheInfo = new ReturnCacheInfo();
                returnCacheInfo.setStatus(2);
                returnCacheInfo.setKey(inputs.getKey());
                returnCacheInfo.setCode(TraningCenterExceptionConstant.REVERIFY_PLAN_FARIL);
                returnCacheInfo.setContent("复审失败");
                returnsCache.putReturnInfo(returnCacheInfo);
            }
            throw new BizFeignException(TraningCenterExceptionConstant.REVERIFY_PLAN_FARIL);
        }
        finally {
            List<AccountStatus> list = new ArrayList<>();
            List<PlanNomalInput> planNomalInputList = inputs.getInputs();
            for(int i=0;i<planNomalInputList.size();i++)
            {
                AccountStatus accountStatus = new AccountStatus();
                accountStatus.setProductId(planNomalInputList.get(i).getProductId());
                accountStatus.setLastProductId(planNomalInputList.get(i).getLastProductId());
                accountStatus.setType(0);
                list.add(accountStatus);
            }
           accountStatusService.updateAccountStatus(list, 5);
        }
    }

}
