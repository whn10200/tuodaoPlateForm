package com.tuodao.bp.model.business.useraccount.input;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.annotation.Md5;
import com.tuodao.bp.model.annotation.PhoneNum;
import com.tuodao.bp.model.constant.useraccount.UaParamExceptionConstant;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @description: 忘记支付密码
 * @author: mif
 * @date: 2017/9/13
 * @time: 11:16
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class ForgetPayPwInput implements Serializable {

    private static final long serialVersionUID = -698455386674815910L;
    /**
     * 手机号码
     */
    @NotBlank(message = UaParamExceptionConstant.MOBILE_IS_NULL + "")
    @PhoneNum(message = UaParamExceptionConstant.MOBILE_FORMAT_ERROR + "")
    private String mobile;

    /**
     * 新密码
     */
    @NotBlank(message = UaParamExceptionConstant.PAY_PASSWORD_CAN_NOT_BE_BLANK + "")
    @Md5(message = UaParamExceptionConstant.PASS_WORD_MUST_BE_MD5 + "")
    private String payPw;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPayPw() {
        return payPw;
    }

    public void setPayPw(String payPw) {
        this.payPw = payPw;
    }
}
