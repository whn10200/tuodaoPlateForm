package com.tuodao.bp.traningcenter.controller;

import com.tuodao.bp.model.business.traningcenter.input.JustIdInput;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/10/17 0017.
 */
@RestController
@RequestMapping("/TraningCenter")
public class AccountStatusController {


    /**
     * （查询该理财计划的动作状态）
     * justIdInput 该理财计划的唯一id
     */
    @RequestMapping(value = "/plan/getPlanStatus", method = RequestMethod.POST)
    public void getPlanStatus(JustIdInput justIdInput) {
//		jmsMessagingTemplate.convertAndSend(traningReverifyPlanQueue, justIdInput);


    }

}
