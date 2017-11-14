package com.tuodao.bp.traningcenter.mq.provider;

import com.tuodao.bp.model.mq.AccountLogMqInfo;
import com.tuodao.bp.model.enums.AccountLogType;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Queue;
import java.math.BigDecimal;

/**
 * @description:
 * @author: wuzf
 * @date: 2017/9/27 0027.
 * @time: 16:18
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class MqPlanReverify {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;


    @Resource(name = "traningccountLogQueue")
    private Queue traningccountLogQueue;

    /**
     * 发送消息到交易中心-理财激活复审修改代收利息
     *
     * @param addAwaitInterest 增加的代收利息
     */
    public void reverifyAccountLog(BigDecimal addAwaitInterest,String userId,String tenderId) {
        // 并且更改代收利息到资金记录中
        AccountLogMqInfo accountLog = new AccountLogMqInfo();
        accountLog.setUserId(userId);
        accountLog.setType(AccountLogType.PLAN_DEDUCT_REVERIFY.getCode());
        accountLog.setAccount(addAwaitInterest);
        accountLog.setTotal(new BigDecimal(0));
        accountLog.setCashFrost(new BigDecimal(0));
        accountLog.setTenderFrost(new BigDecimal(0));
        accountLog.setBalance(new BigDecimal(0));
        accountLog.setRecharge(new BigDecimal(0));
        accountLog.setAwaitInterest(addAwaitInterest);
        accountLog.setAwaitCapital(new BigDecimal(0));
        accountLog.setFromAccount(tenderId);
        accountLog.setToAccount(tenderId);
        accountLog.setToRemarks(AccountLogType.PLAN_DEDUCT_REVERIFY.getMsg());
        accountLog.setRemarks(AccountLogType.PLAN_DEDUCT_REVERIFY.getMsg());
        accountLog.setIsShow(1);
        accountLog.setIntrestAccount(new BigDecimal(0));
        accountLog.setFeeAccount(new BigDecimal(0));
        accountLog.setChangeType(AccountLogType.PLAN_DEDUCT_REVERIFY.getType());
        jmsMessagingTemplate.convertAndSend(traningccountLogQueue, accountLog);
    }
}
