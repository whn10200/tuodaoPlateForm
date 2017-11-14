package com.tuodao.bp.useraccount.controller;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.useraccount.input.ConsigneeInfoInput;
import com.tuodao.bp.model.business.useraccount.output.UserInfoDetailOutput;
import com.tuodao.bp.useraccount.service.IUserInfoDetailService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description: 用户详情
 * @author: mif
 * @date: 2017/10/12
 * @time: 14:43
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@RestController
@RequestMapping(value = "ua/userDetail")
public class UserInfoDetailController {

    @Resource
    private IUserInfoDetailService userInfoDetailService;

    /**
     * 用户详情
     *
     * @param basePojo 基础POJO
     * @return 用户详情
     */
    @RequestMapping(value = "getUserInfoDetail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserInfoDetailOutput getUserInfoDetail(BasePojo basePojo) {
        return userInfoDetailService.getUserInfoDetail(basePojo.getUserId());
    }

    /**
     * 修改收件人信息
     *
     * @param consigneeInfoInput 收件地址
     */
    @RequestMapping(value = "updateConsigneeInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateConsigneeInfo(ConsigneeInfoInput consigneeInfoInput) {
        userInfoDetailService.updateConsigneeInfo(consigneeInfoInput);
    }
}
