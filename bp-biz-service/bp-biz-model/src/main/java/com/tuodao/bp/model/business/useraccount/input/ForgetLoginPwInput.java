package com.tuodao.bp.model.business.useraccount.input;

import com.tuodao.bp.model.annotation.In;
import com.tuodao.bp.model.annotation.Md5;
import com.tuodao.bp.model.annotation.PhoneNum;
import com.tuodao.bp.model.constant.useraccount.UaConstant;
import com.tuodao.bp.model.constant.useraccount.UaParamExceptionConstant;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 忘记登录密码
 * @author: mif
 * @date: 2017/9/13
 * @time: 11:30
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class ForgetLoginPwInput implements Serializable {
    private static final long serialVersionUID = 8020747565840349072L;

    /**
     * 手机号码
     */
    @NotBlank(message = UaParamExceptionConstant.MOBILE_IS_NULL + "")
    @PhoneNum(message = UaParamExceptionConstant.MOBILE_FORMAT_ERROR + "")
    private String mobile;

    /**
     * 新密码
     */
    @NotBlank(message = UaParamExceptionConstant.LOGIN_PASSWORD_CAN_NOT_BE_BLANK + "")
    @Md5(message = UaParamExceptionConstant.PASS_WORD_MUST_BE_MD5 + "")
    private String loginPw;

    /**
     * 密码安级别（1：弱；2：强；3：最高）
     */
    @NotNull(message = UaParamExceptionConstant.PW_SECURITY_LEVEL_CAN_NOT_BE_NULL + "")
    @In(value = UaConstant.PW_SECURITY_LEVEL_ALL, message = UaParamExceptionConstant.PARAM_VALUE_ERROR + "")
    private Integer pwSecurityLevel;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLoginPw() {
        return loginPw;
    }

    public void setLoginPw(String loginPw) {
        this.loginPw = loginPw;
    }

    public Integer getPwSecurityLevel() {
        return pwSecurityLevel;
    }

    public void setPwSecurityLevel(Integer pwSecurityLevel) {
        this.pwSecurityLevel = pwSecurityLevel;
    }
}
