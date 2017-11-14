package com.tuodao.bp.api.facade.controller.operation;

import com.alibaba.fastjson.JSON;
import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.client.operation.UserSignClient;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.annotation.AccessToken;
import com.tuodao.bp.model.business.operation.output.CheckUserSignOutput;
import com.tuodao.bp.model.enums.AccessType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 聚合 - 用户签到
 * User: zkai
 * Date: 2017/9/26
 * Time: 10:05
 * Copyright:拓道金服 Copyright (c) 2017
 */
@RequestMapping("/router/op")
@RestController
public class FacadeUserSignController {
    private static final Logger logger = LoggerFactory.getLogger(FacadeUserSignController.class);

    @Autowired
    private UserSignClient userSignClient;

    /**
     * 用户签到
     * @param input
     * @return
     */
    @RequestMapping(value="/userSign",method= RequestMethod.POST)
    @AccessToken(checkAccess=true,access= {AccessType.PC, AccessType.APP})
    public RespResult<Integer> userSign(BasePojo input){
        logger.info("用户签到 input={}", JSON.toJSONString(input));
        int score = userSignClient.userSign(input);
        return RespResult.<Integer>create().setContent(score);
    }

    /**
     * 判断用户是否已签到
     * @param input
     * @return
     */
    @RequestMapping(value="/checkSign",method= RequestMethod.POST)
    @AccessToken(checkAccess=true,access= {AccessType.PC, AccessType.APP})
    public RespResult<CheckUserSignOutput> checkSign(BasePojo input){
        logger.info("判断用户是否已签到 input={}", JSON.toJSONString(input));
        CheckUserSignOutput output = userSignClient.checkSign(input);
        return RespResult.<CheckUserSignOutput>create().setContent(output);
    }
}
