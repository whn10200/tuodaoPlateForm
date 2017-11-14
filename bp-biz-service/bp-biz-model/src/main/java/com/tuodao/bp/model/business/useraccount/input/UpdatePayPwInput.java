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
 * @description: 修改支付密码
 * @author: mif
 * @date: 2017/9/14
 * @time: 13:55
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class UpdatePayPwInput extends BasePojo implements Serializable {
    private static final long serialVersionUID = -5996356319530095141L;

    /**
     * 旧密码
     */
    @NotBlank(message = UaParamExceptionConstant.OLD_PW_CAN_NOT_BE_BLANK + "")
    @Md5(message = UaParamExceptionConstant.PASS_WORD_MUST_BE_MD5 + "")
    private String oldPayPw;

    /**
     * 新密码
     */
    @NotBlank(message = UaParamExceptionConstant.NEW_PW_CAN_NOT_BE_BLANK + "")
    @Md5(message = UaParamExceptionConstant.PASS_WORD_MUST_BE_MD5 + "")
    private String newPayPw;

    public String getOldPayPw() {
        return oldPayPw;
    }

    public void setOldPayPw(String oldPayPw) {
        this.oldPayPw = oldPayPw;
    }

    public String getNewPayPw() {
        return newPayPw;
    }

    public void setNewPayPw(String newPayPw) {
        this.newPayPw = newPayPw;
    }
}
