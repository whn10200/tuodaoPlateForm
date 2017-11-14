package com.tuodao.bp.useraccount.controller;

import com.tuodao.bp.model.business.useraccount.input.SAddUserFeedBack;
import com.tuodao.bp.useraccount.interceptor.UnableValidate;
import com.tuodao.bp.useraccount.service.IUserFeedBackService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description: 用户反馈相关controller
 * User: zkai
 * Date: 2017/9/12
 * Time: 15:44
 * Copyright:拓道金服 Copyright (c) 2017
 */
@RestController
@RequestMapping(value = "ua")
public class UserFeedbackController {
    @Resource
    private IUserFeedBackService userFeedBackService;

    /**
     * 添加用户反馈
     *
     * @param input
     */
    @RequestMapping(value = "addUserFeedBack", method = RequestMethod.POST,produces= MediaType.APPLICATION_JSON_VALUE)
    @UnableValidate
    public void addUserFeedBack(SAddUserFeedBack input) {
        userFeedBackService.addUserFeedBack(input);
    }

}
