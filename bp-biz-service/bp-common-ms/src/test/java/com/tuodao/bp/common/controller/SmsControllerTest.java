package com.tuodao.bp.common.controller;

import com.tuodao.bp.common.CommonTestApi;
import com.tuodao.bp.model.business.common.input.SmsInput;
import org.junit.Test;
import org.springframework.http.MediaType;

/**
 * @description: ${Description}
 * @author: mif
 * @date: 2017/9/8
 * @time: 14:42
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class SmsControllerTest extends CommonTestApi {

    @Test
    public void sendSms() throws Exception {
        url = "common/sendSms";
        SmsInput input = new SmsInput();
        input.setMobiles("1595717875a;15957178759;;");
        input.setContent("您的验证码是：123456。请不要把验证码泄露给其他人。");
        input.setCustomerIp("127.0.0.7");
        doPost(MediaType.APPLICATION_JSON_UTF8, input);
    }

}