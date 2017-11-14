package com.tuodao.bp.model.facade.useraccout.input;

import com.tuodao.bp.model.constant.useraccount.UaParamExceptionConstant;
import com.tuodao.bp.model.business.useraccount.input.ForgetPayPwInput;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @description: facade forget pay password VO
 * @author: mif
 * @date: 2017/9/13
 * @time: 14:54
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class FacadeForgetPayPwInput extends ForgetPayPwInput implements Serializable {

    private static final long serialVersionUID = -2091575893946297933L;
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
