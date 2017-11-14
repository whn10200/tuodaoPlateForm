package com.tuodao.bp.common.controller;

import com.alibaba.fastjson.JSON;
import com.tuodao.bp.common.service.PushService;
import com.tuodao.bp.model.business.common.input.PushInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description:
 * User: zkai
 * Date: 2017/8/17
 * Time: 15:10
 * Copyright:拓道金服 Copyright (c) 2017
 */
@RestController
@RequestMapping(value = "/common")
public class PushController {
    private static final Logger logger = LoggerFactory.getLogger(PushController.class);

    @Resource
    private PushService pushService;


    /**
     * 消息推送通知
     *
     * @param input
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/pushMessage", method = RequestMethod.POST,produces= MediaType.APPLICATION_JSON_VALUE)
    public void pushMessage(PushInput input) throws Exception {
        logger.info("消息推送通知，input={}", JSON.toJSONString(input));
        pushService.pushMessage(input);
    }
}
