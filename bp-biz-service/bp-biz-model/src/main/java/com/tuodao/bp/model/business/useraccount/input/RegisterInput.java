package com.tuodao.bp.model.business.useraccount.input;

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
 * @description: 注册请求类
 * @author: mif
 * @date: 2017/8/28
 * @time: 10:37
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class RegisterInput implements Serializable {
    private static final long serialVersionUID = 3578961609778304301L;

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
     * 密码安级别（1：弱；2：强；3：最高）
     */
    @NotNull(message = UaParamExceptionConstant.PW_SECURITY_LEVEL_CAN_NOT_BE_NULL + "")
    @In(value = UaConstant.PW_SECURITY_LEVEL_ALL, message = UaParamExceptionConstant.PARAM_VALUE_ERROR + "")
    private Integer pwSecurityLevel;

    /**
     * 邀请人手机号码/邀请码
     */
    @PhoneNum(message = UaParamExceptionConstant.MOBILE_FORMAT_ERROR + "")
    @Size(max = 11, message = UaParamExceptionConstant.PARAM_BEYOND_THE_LENGTH + "")
    private String inviterCode;

    /**
     * 邀请类型（1：专属链接；2：社交平台；3：邀请码）
     */
    @In(value = UaConstant.INVITE_TYPE_ALL, message = UaParamExceptionConstant.PARAM_VALUE_ERROR + "")
    private Integer inviteType;

    /**
     * 用户类型（1：投资用户：2：融资用户；）
     */
    @In(value = UaConstant.USER_TYPE_ALL, message = UaParamExceptionConstant.PARAM_VALUE_ERROR + "")
    private Integer userType;

    /**
     * 投资用户类型(1：普通用户；2：内部用户)
     */
    @In(value = UaConstant.INVEST_USER_TYPE_ALL, message = UaParamExceptionConstant.PARAM_VALUE_ERROR + "")
    private Integer investUserType;

    /**
     * 注册来源（0：后台；1：WEB；2：IOS；3：ANDROID；4：H5）
     */
    @NotNull(message = UaParamExceptionConstant.REGISTER_SOURCE_CAN_NOT_BE_NULL + "")
    @In(value = UaConstant.REGISTER_SOURCE_ALL, message = UaParamExceptionConstant.PARAM_VALUE_ERROR + "")
    private Integer registerSource;

    /**
     * 注册IP
     */
    @Pattern(regexp = RegexpConstant.PATTERN_IP_REGEXP, message = UaParamExceptionConstant.IP_FORMAT_ERROR + "")
    private String registerIp;

    /**
     * 注册版本
     */
    @Size(max = 10, message = UaParamExceptionConstant.PARAM_BEYOND_THE_LENGTH + "")
    private String registerVersion;

    /**
     * 来源渠道（好友邀请、VIVO、360等等）
     */
    @Size(max = 25, message = UaParamExceptionConstant.PARAM_BEYOND_THE_LENGTH + "")
    private String sourceChannel;

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

    public Integer getPwSecurityLevel() {
        return pwSecurityLevel;
    }

    public void setPwSecurityLevel(Integer pwSecurityLevel) {
        this.pwSecurityLevel = pwSecurityLevel;
    }

    public String getInviterCode() {
        return inviterCode;
    }

    public void setInviterCode(String inviterCode) {
        this.inviterCode = inviterCode;
    }

    public Integer getInviteType() {
        return inviteType;
    }

    public void setInviteType(Integer inviteType) {
        this.inviteType = inviteType;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getInvestUserType() {
        return investUserType;
    }

    public void setInvestUserType(Integer investUserType) {
        this.investUserType = investUserType;
    }

    public Integer getRegisterSource() {
        return registerSource;
    }

    public void setRegisterSource(Integer registerSource) {
        this.registerSource = registerSource;
    }

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }

    public String getRegisterVersion() {
        return registerVersion;
    }

    public void setRegisterVersion(String registerVersion) {
        this.registerVersion = registerVersion;
    }

    public String getSourceChannel() {
        return sourceChannel;
    }

    public void setSourceChannel(String sourceChannel) {
        this.sourceChannel = sourceChannel;
    }
}
