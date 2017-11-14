package com.tuodao.bp.common.controller;

import com.tuodao.bp.common.service.IEmailService;
import com.tuodao.bp.model.business.common.input.EmailInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description: 邮件发送
 * @author: mif
 * @date: 2017/9/11
 * @time: 09:54
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@RestController
@RequestMapping(value = "/common")
public class EmailController {

    private static Logger logger = LoggerFactory.getLogger(EmailController.class);

    @Resource
    private IEmailService emailService;

    /**
     * 邮件发送
     *
     * @param input 邮件发送请求封装
     */
    @RequestMapping(value = "sendEmail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void sendEmail(EmailInput input) {
        logger.debug("to send email ，input={} ", input);
        emailService.sendEmail(input);
    }
}
