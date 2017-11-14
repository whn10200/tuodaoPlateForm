package com.tuodao.bp.model.facade.useraccout.input;

import com.tuodao.bp.model.constant.useraccount.UaParamExceptionConstant;
import com.tuodao.bp.model.business.useraccount.input.ForgetLoginPwInput;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @description: 聚合层忘记登录密码请求封装
 * @author: mif
 * @date: 2017/9/13
 * @time: 14:52
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class FacadeForgetLoginPwInput extends ForgetLoginPwInput implements Serializable {
    private static final long serialVersionUID = 8303174613296085598L;

    /**
     * 短信验证码
     */
    @NotBlank(message = UaParamExceptionConstant.SMS_CODE_CANT_BE_BLANK + "")
    @Size(max = 6, message = UaParamExceptionConstant.PARAM_BEYOND_THE_LENGTH + "")
    private String smsCode;

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }
}
