package com.tuodao.bp.model.business.useraccount.input;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.annotation.In;
import com.tuodao.bp.model.annotation.Md5;
import com.tuodao.bp.model.annotation.PhoneNum;
import com.tuodao.bp.model.constant.RegexpConstant;
import com.tuodao.bp.model.constant.useraccount.UaConstant;
import com.tuodao.bp.model.constant.useraccount.UaParamExceptionConstant;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @description: 用户登录请求
 * @author: mif
 * @date: 2017/9/6
 * @time: 17:46
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class LoginInput extends BasePojo implements Serializable {

    private static final long serialVersionUID = -3286422541317669339L;
    /**
     * 手机号码
     */
    @NotBlank(message = UaParamExceptionConstant.MOBILE_IS_NULL + "")
    @PhoneNum(message = UaParamExceptionConstant.MOBILE_FORMAT_ERROR + "")
    private String mobile;

    /**
     * 登录密码
     */
    @NotBlank(message = UaParamExceptionConstant.LOGIN_PASSWORD_CAN_NOT_BE_BLANK + "")
    @Md5(message = UaParamExceptionConstant.PASS_WORD_MUST_BE_MD5 + "")
    private String loginPassword;

    /**
     * 登录IP
     */
    @Pattern(regexp = RegexpConstant.PATTERN_IP_REGEXP, message = UaParamExceptionConstant.IP_FORMAT_ERROR + "")
    private String loginIp;

    /**
     * 注册来源（0：后台；1：WEB；2：IOS；3：ANDROID；4：H5）
     */
    @NotNull(message = UaParamExceptionConstant.LOGIN_SOURCE_CAN_NOT_BE_NULL + "")
    @In(value = UaConstant.REGISTER_SOURCE_ALL, message = UaParamExceptionConstant.PARAM_VALUE_ERROR + "")
    private Integer loginSource;

    /**
     * 登录版本
     */
    @Size(max = 10, message = UaParamExceptionConstant.PARAM_BEYOND_THE_LENGTH + "")
    private String loginVersion;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Integer getLoginSource() {
        return loginSource;
    }

    public void setLoginSource(Integer loginSource) {
        this.loginSource = loginSource;
    }

    public String getLoginVersion() {
        return loginVersion;
    }

    public void setLoginVersion(String loginVersion) {
        this.loginVersion = loginVersion;
    }
}
