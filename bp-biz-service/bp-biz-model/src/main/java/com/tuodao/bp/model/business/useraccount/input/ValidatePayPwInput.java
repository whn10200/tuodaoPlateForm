package com.tuodao.bp.model.business.useraccount.input;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.annotation.Md5;
import com.tuodao.bp.model.constant.RegexpConstant;
import com.tuodao.bp.model.constant.useraccount.UaParamExceptionConstant;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @description: 验证支付密码
 * @author: mif
 * @date: 2017/10/20
 * @time: 16:38
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class ValidatePayPwInput extends BasePojo implements Serializable {

    private static final long serialVersionUID = 4619811558440462151L;
    /**
     * 支付密码
     */
    @NotBlank(message = UaParamExceptionConstant.PAY_PASSWORD_CAN_NOT_BE_BLANK + "")
//    @Pattern(regexp = RegexpConstant.PATTERN_PAY_PW_REGEXP, message = UaParamExceptionConstant.PAY_PASSWORD_MUST_BE_SIX_NUM + "")
    @Md5(message = UaParamExceptionConstant.PASS_WORD_MUST_BE_MD5 + "")
    private String payPw;

    public String getPayPw() {
        return payPw;
    }

    public void setPayPw(String payPw) {
        this.payPw = payPw;
    }
}
