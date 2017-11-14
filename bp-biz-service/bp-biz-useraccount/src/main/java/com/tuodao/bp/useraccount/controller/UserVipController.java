package com.tuodao.bp.useraccount.controller;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.useraccount.output.UserVipInfoOutput;
import com.tuodao.bp.useraccount.service.IUserVipInfoService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description: 用户VIP信息
 * @author: mif
 * @date: 2017/10/23
 * @time: 15:28
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@RequestMapping(value = "ua/userVip")
@RestController
public class UserVipController {

    @Resource
    private IUserVipInfoService userVipInfoService;

    /**
     * 查询用户VIP信息
     *
     * @param basePojo 基础类
     * @return Vip信息
     */
    @RequestMapping(value = "getUserVipInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserVipInfoOutput getUserVip(BasePojo basePojo) {
        return userVipInfoService.selectByUserId(basePojo.getUserId());
    }
}
