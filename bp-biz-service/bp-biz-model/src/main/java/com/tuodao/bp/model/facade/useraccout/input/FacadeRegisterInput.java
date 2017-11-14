package com.tuodao.bp.model.facade.useraccout.input;

import com.tuodao.bp.model.constant.useraccount.UaParamExceptionConstant;
import com.tuodao.bp.model.business.useraccount.input.RegisterInput;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @description: 聚合层注册输入
 * @author: mif
 * @date: 2017/9/12
 * @time: 10:47
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class FacadeRegisterInput extends RegisterInput implements Serializable {
    private static final long serialVersionUID = 8803561105944436931L;

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
