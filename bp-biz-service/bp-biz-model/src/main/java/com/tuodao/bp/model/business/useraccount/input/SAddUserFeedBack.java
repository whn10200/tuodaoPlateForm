package com.tuodao.bp.model.business.useraccount.input;

import com.tuodao.bp.model.annotation.In;
import com.tuodao.bp.model.constant.useraccount.UaConstant;
import com.tuodao.bp.model.constant.useraccount.UaParamExceptionConstant;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Description: 服务-添加用户反馈输入类
 * User: zkai
 * Date: 2017/9/12
 * Time: 14:40
 * Copyright:拓道金服 Copyright (c) 2017
 */
public class SAddUserFeedBack implements Serializable {
    private static final long serialVersionUID = 669564931489579000L;

    /**
     * 用户编号
     */
    private String userId;
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
    private String mobile;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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
}
