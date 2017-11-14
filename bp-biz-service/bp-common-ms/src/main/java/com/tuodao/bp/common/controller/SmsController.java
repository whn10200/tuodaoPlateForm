package com.tuodao.bp.common.controller;

import com.tuodao.bp.common.service.ISmsService;
import com.tuodao.bp.model.business.common.input.SmsInput;
import com.tuodao.bp.model.business.common.output.SmsOutput;
import com.tuodao.bp.utils.WebUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @description: 短信发送控制类
 * @author: mif
 * @date: 2017/8/23
 * @time: 14:37
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@RestController
@RequestMapping(value = "/common")
public class SmsController {

    @Resource
    private ISmsService smsService;

    @RequestMapping(value = "/sendSms", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public SmsOutput sendSms(HttpServletRequest request, SmsInput input) {
        return smsService.sendSms(input, WebUtils.getClientIP(request));
    }
}
