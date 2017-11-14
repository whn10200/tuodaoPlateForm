package com.tuodao.bp.common.service;

import com.tuodao.bp.model.business.common.input.EmailInput;

/**
 * @description:
 * @author: mif
 * @date: 2017/9/11
 * @time: 11:45
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public interface IEmailService {
    /**
     * 邮件发送
     *
     * @param input
     */
    void sendEmail(EmailInput input);
}
