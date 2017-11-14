package com.tuodao.bp.model.facade.useraccout.input;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.annotation.In;
import com.tuodao.bp.model.annotation.PhoneNum;
import com.tuodao.bp.model.constant.useraccount.UaConstant;
import com.tuodao.bp.model.constant.useraccount.UaParamExceptionConstant;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Description: 聚合-添加用户反馈输入类
 * User: zkai
 * Date: 2017/9/12
 * Time: 15:48
 * Copyright:拓道金服 Copyright (c) 2017
 */
public class FacadeAddUserFeedBack extends BasePojo implements Serializable {
    private static final long serialVersionUID = 669564931489579000L;

    /**
     * 终端来源:0:pc,1:ios,2:android,3:h5
     */
    @NotNull(message = UaParamExceptionConstant.LOGIN_SOURCE_CAN_NOT_BE_NULL + "")
    @In(value = UaConstant.REGISTER_SOURCE_ALL, message = UaParamExceptionConstant.PARAM_VALUE_ERROR + "")
    private Integer source;
    /**
     * 版本号
     */
    private String version;
    /**
     * 反馈内容
     */
    @NotBlank(message = UaParamExceptionConstant.FEEDBACK_CONTENT_NOT_BE_BLANK+"")
    private String content;

    /**
     * 电话号码
     */
    @NotBlank(message = UaParamExceptionConstant.MOBILE_IS_NULL + "")
    @PhoneNum(message = UaParamExceptionConstant.MOBILE_FORMAT_ERROR + "")
    private String mobile;

    /**
     * 短信验证码
     */
    private String smsCode;

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }
}
