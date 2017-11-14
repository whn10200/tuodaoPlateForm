package com.tuodao.bp.model.facade.common.input;

import com.tuodao.bp.model.constant.facade.FacadeExceptionConstant;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @description: 验证短信验证码
 * @author: mif
 * @date: 2017/9/13
 * @time: 14:27
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class FacadeValidateSmsCodeInput extends FacadeSmsInput implements Serializable {

    private static final long serialVersionUID = -2390185251129231726L;

    /**
     * 短信验证码
     */
    @NotBlank(message = FacadeExceptionConstant.SMS_CODE_CAN_NOT_BE_BLANK + "")
    private String smsCode;

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }
}
