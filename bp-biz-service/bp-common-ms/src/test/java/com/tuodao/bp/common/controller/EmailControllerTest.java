package com.tuodao.bp.common.controller;

import com.tuodao.bp.common.CommonTestApi;
import com.tuodao.bp.model.business.common.input.EmailInput;
import org.junit.Test;
import org.springframework.http.MediaType;

/**
 * @description: ${Description}
 * @author: mif
 * @date: 2017/9/11
 * @time: 10:33
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class EmailControllerTest extends CommonTestApi {
    @Test
    public void sendEmail() throws Exception {
        url = "common/sendEmail";
        EmailInput emailInput = new EmailInput();
        emailInput.setReceivers("mifu1@51tuodao.com;;mifu2012@sina.com");
        emailInput.setSubject("test11");
        emailInput.setContent("测试邮件11");
        doPost(MediaType.APPLICATION_JSON_UTF8, emailInput);
    }

}