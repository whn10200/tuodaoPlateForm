package com.tuodao.bp.api.facade.controller.operation;

import com.alibaba.fastjson.JSON;
import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.client.operation.OpCooperationClient;
import com.tuodao.bp.api.facade.common.BaseController;
import com.tuodao.bp.model.annotation.AccessToken;
import com.tuodao.bp.model.business.operation.input.OpCooperationInput;
import com.tuodao.bp.model.enums.AccessType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 合作加盟管理聚合
 * Author: yinping
 * Date: 2017/9/22
 * Copyright:拓道金服 Copyright (c) 2017
 */
@RequestMapping("/router/op")
@RestController
public class FacadeOpCooperationController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(FacadeOpCooperationController.class);

    @Autowired
    private OpCooperationClient opCooperationClient;

    /**
     * 新增加盟管理
     * @param input
     * @return
     */
    @RequestMapping(value="/addCooperation",method= RequestMethod.POST)
    @AccessToken(checkAccess=true,access = {AccessType.APP,AccessType.PC})
    public RespResult addCooperation(OpCooperationInput input){
        logger.info("查找用户任务(新手任务,日常任务) input={}", JSON.toJSONString(input));
        opCooperationClient.addCooperation(input);
        return RespResult.create();
    }
}
