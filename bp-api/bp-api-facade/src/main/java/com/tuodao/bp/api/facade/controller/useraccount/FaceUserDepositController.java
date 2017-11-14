package com.tuodao.bp.api.facade.controller.useraccount;

import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.client.useraccount.UserDepositFegin;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.annotation.AccessToken;
import com.tuodao.bp.model.business.useraccount.input.OpenDepositInput;
import com.tuodao.bp.model.business.useraccount.output.UserDepositOutput;
import com.tuodao.bp.model.enums.AccessType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 聚合 - 用户存管相关controller
 * User: zkai
 * Date: 2017/10/17
 * Time: 10:33
 * Copyright:拓道金服 Copyright (c) 2017
 */
@RequestMapping("/router/ua")
@RestController
public class FaceUserDepositController {
    private static final Logger logger = LoggerFactory.getLogger(FaceUserDepositController.class);

    @Autowired
    private UserDepositFegin userDepositFegin;

    /**
     * 用户开通存管
     *
     * @param input
     * @return
     */
    @RequestMapping(value = "/openDeposit", method = RequestMethod.POST)
    @AccessToken(checkAccess = true,access = {AccessType.PC, AccessType.APP})
    public RespResult<String> openDeposit(OpenDepositInput input) {
        logger.info("用户开通存管input={}",input.toString());
        userDepositFegin.openDeposit(input);
        return RespResult.<String>create();
    }

    /**
     * 用户存管信息
     *
     * @param basePojo 基础信息
     * @return 存管信息
     */
    @RequestMapping(value = "/getUserDepositInfo", method = RequestMethod.POST)
    @AccessToken(checkAccess=true,access = {AccessType.PC, AccessType.APP})
    public RespResult<UserDepositOutput> getUserDepositInfo(BasePojo basePojo) {
        logger.info("查询存管信息, basePojo={}", basePojo);
        return RespResult.<UserDepositOutput>create().setContent(userDepositFegin.getUserDepositInfo(basePojo));
    }
}
