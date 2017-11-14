package com.tuodao.bp.traningcenter.controller;

import com.tuodao.bp.model.traningcenter.output.AccountOutput;
import com.tuodao.bp.traningcenter.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/9/12
 * @time: 20:39
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@RestController
public class AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    /**
     * 获取用户账户信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "/account/getUserAccount",consumes = MediaType.APPLICATION_JSON_VALUE)
    public AccountOutput getUserAccount(@RequestParam("userId")String userId){
        LOGGER.debug("获取用户账户信息,入参:{}",userId);
        return accountService.getUserAccount(userId);
    }


}
