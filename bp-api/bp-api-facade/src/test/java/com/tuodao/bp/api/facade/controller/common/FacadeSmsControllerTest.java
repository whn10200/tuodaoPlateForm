package com.tuodao.bp.api.facade.controller.common;

import com.tuodao.bp.api.facade.FacadeTestApi;
import com.tuodao.bp.model.constant.facade.FacadeConstants;
import com.tuodao.bp.model.facade.common.input.FacadeSmsInput;
import com.tuodao.bp.model.facade.common.input.FacadeValidateSmsCodeInput;
import org.junit.Test;
import org.springframework.http.MediaType;

/**
 * @description: ${Description}
 * @author: mif
 * @date: 2017/9/12
 * @time: 16:02
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class FacadeSmsControllerTest extends FacadeTestApi {
    @Test
    public void sendSms() throws Exception {
        url = "common/sendSms";
        FacadeSmsInput input = new FacadeSmsInput();
        input.setMobile("15988888889");
        input.setSmsType(FacadeConstants.SMS_TYPE_REGISTER);
//        input.setSmsType(FacadeConstants.SMS_TYPE_FORGET_LOGIN_PW);
//        input.setSmsType(FacadeConstants.SMS_TYPE_FORGET_PAY_PW);
        doPost(MediaType.APPLICATION_JSON_UTF8, input);
    }

    @Test
    public void validateSmsCode() throws Exception {
        url = "common/validateSmsCode";
        FacadeValidateSmsCodeInput input = new FacadeValidateSmsCodeInput();
        input.setMobile("15988888884");
        input.setSmsType(FacadeConstants.SMS_TYPE_REGISTER);
        input.setSmsCode("761493");
        doPost(MediaType.APPLICATION_JSON_UTF8, input);
    }

}