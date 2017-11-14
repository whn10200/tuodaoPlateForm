package com.tuodao.bp.model.facade.common.input;

import com.tuodao.bp.model.annotation.In;
import com.tuodao.bp.model.annotation.PhoneNum;
import com.tuodao.bp.model.constant.facade.FacadeConstants;
import com.tuodao.bp.model.constant.facade.FacadeExceptionConstant;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @description: 聚合层短信请求
 * @author: mif
 * @date: 2017/9/12
 * @time: 11:58
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class FacadeSmsInput implements Serializable {
    private static final long serialVersionUID = 8736941330905929253L;
    /**
     * 手机号码（多个已分号“；”分隔）
     */
    @NotBlank(message = FacadeExceptionConstant.MOBILE_CAN_NOT_BE_NULL + "")
    @PhoneNum(message = FacadeExceptionConstant.MOBILE_FORMAT_ERROR + "")
    private String mobile;
    /**
     * 短信类型（register:注册；findLoginPw：找回登录密码：findPayPw：找回支付密码；feedback:意见反馈）
     */
    @NotBlank(message = FacadeExceptionConstant.SMS_TYPE_CAN_NOT_BE_BLANK + "")
    @In(value = FacadeConstants.SMS_TYPE_ALL, message = FacadeExceptionConstant.SMS_TYPE_VALUE_ERROR + "")
    private String smsType;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSmsType() {
        return smsType;
    }

    public void setSmsType(String smsType) {
        this.smsType = smsType;
    }
}
