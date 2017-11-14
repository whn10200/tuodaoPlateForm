package com.tuodao.bp.traningcenter.controller;

import com.tuodao.bp.model.business.traningcenter.input.JustIdInput;
import com.tuodao.bp.model.business.traningcenter.input.PlanListInput;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.traningcenter.constant.TraningCenterExceptionConstant;
import com.tuodao.bp.traningcenter.db.mapper.basic.BorrowChoicenessTenderMapper;
import com.tuodao.bp.traningcenter.db.model.basic.AccountStatus;
import com.tuodao.bp.traningcenter.service.AccountStatusService;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.jms.Queue;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:理财计划到期的类
 * @author: wuzf
 * @date: 2017/11/2
 * @time: 20:40
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@RestController
public class PlanExpiredController {

    @Resource
    private AccountStatusService accountStatusService;
    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Resource(name = "traningExpiredQueue")
    private Queue traningExpiredQueue;

    /**
     * 理财计划到期的生产者
     */
    @RequestMapping(value = "/plan/planExpired", method = RequestMethod.POST)
    public void planExpired(JustIdInput justIdInput) {
        List<AccountStatus> list = new ArrayList<>();
        AccountStatus accountStatus = new AccountStatus();
        accountStatus.setType(2);
        accountStatus.setProductId(justIdInput.getJustId());
        list.add(accountStatus);
        Boolean flag = accountStatusService.updateAccountStatus(list, 6);
        if(flag==true)
        {
            jmsMessagingTemplate.convertAndSend(traningExpiredQueue, justIdInput);
        }
        else
        {
            throw new BizFeignException(TraningCenterExceptionConstant.REVERIFY_PLAN_FARIL);
        }
    }



}
