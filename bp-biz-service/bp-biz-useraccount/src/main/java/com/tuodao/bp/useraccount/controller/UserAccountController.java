package com.tuodao.bp.useraccount.controller;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.useraccount.output.UserAccountInfoOutput;
import com.tuodao.bp.useraccount.service.IUserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description: 用户账户信息控制类
 * @author: mif
 * @date: 2017/9/5
 * @time: 16:49
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@RestController
@RequestMapping(value = "ua")
public class UserAccountController {

    @Resource
    private IUserService userService;

    /**
     * 获取用户账户基础信息
     *
     * @param basePojo
     * @return
     */
    @RequestMapping(value = "getUserAccountInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserAccountInfoOutput getUserAccountInfo(BasePojo basePojo) {
        return userService.getUserAccountInfo(basePojo.getUserId());
    }

}
