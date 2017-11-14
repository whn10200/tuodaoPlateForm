package com.tuodao.bp.api.facade.controller.useraccount;

import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.client.useraccount.UserInfoDetailClient;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.annotation.AccessToken;
import com.tuodao.bp.model.business.useraccount.input.ConsigneeInfoInput;
import com.tuodao.bp.model.business.useraccount.output.UserInfoDetailOutput;
import com.tuodao.bp.model.enums.AccessType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description: 聚合层用户详情
 * @author: mif
 * @date: 2017/10/12
 * @time: 15:24
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@RestController
@RequestMapping("/router/ua")
public class FacadeUserDetailController {

    private static Logger logger = LoggerFactory.getLogger(FacadeUserDetailController.class);

    @Resource
    private UserInfoDetailClient userInfoDetailClient;

    /**
     * 用户详情
     *
     * @param basePojo 基础POJO
     * @return 用户详情
     */
    @RequestMapping(value = "getUserInfoDetail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @AccessToken(access = {AccessType.APP, AccessType.PC})
    public RespResult<UserInfoDetailOutput> getUserInfoDetail(BasePojo basePojo) {
        logger.info("获取用户详情，basePojo={}", basePojo);
        return RespResult.<UserInfoDetailOutput>create().setContent(userInfoDetailClient.getUserInfoDetail(basePojo));
    }

    /**
     * 修改收件人信息
     *
     * @param consigneeInfoInput 收件地址
     */
    @RequestMapping(value = "updateConsigneeInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @AccessToken(access = {AccessType.APP, AccessType.PC})
    public RespResult<String> updateConsigneeInfo(ConsigneeInfoInput consigneeInfoInput) {
        logger.info("修改收件人信息，consigneeInfoInput={}", consigneeInfoInput);
        userInfoDetailClient.updateConsigneeInfo(consigneeInfoInput);
        return RespResult.<String>create();
    }
}
