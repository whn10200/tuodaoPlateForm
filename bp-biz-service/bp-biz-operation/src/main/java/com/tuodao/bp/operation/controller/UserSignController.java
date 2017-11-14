package com.tuodao.bp.operation.controller;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.operation.output.CheckUserSignOutput;
import com.tuodao.bp.operation.service.IUserSignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 服务端 - 用户签到Controller
 * User: zkai
 * Date: 2017/9/25
 * Time: 14:38
 * Copyright:拓道金服 Copyright (c) 2017
 */
@RestController
public class UserSignController {
    // 日志
    private static final Logger logger = LoggerFactory.getLogger(UserSignController.class);

    @Autowired
    private IUserSignService userSignService;


    /**
     * 用户签到
     *
     * @param input
     */
    @RequestMapping(value = "/userSign", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public int userSign(BasePojo input) {
        logger.info("用户签到input：[{}]", input);
        return userSignService.userSign(input);
    }

    /**
     * 用户是否签到
     *
     * @param input
     */
    @RequestMapping(value = "/checkSign", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public CheckUserSignOutput checkSign(BasePojo input) {
        logger.info("校验用户签到input：[{}]", input);
        return userSignService.checkSign(input);
    }
}
