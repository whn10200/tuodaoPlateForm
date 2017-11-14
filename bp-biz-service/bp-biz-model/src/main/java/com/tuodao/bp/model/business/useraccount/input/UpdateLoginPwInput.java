package com.tuodao.bp.model.business.useraccount.input;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.annotation.Md5;
import com.tuodao.bp.model.constant.useraccount.UaParamExceptionConstant;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @description: 修改登录密码
 * @author: mif
 * @date: 2017/9/7
 * @time: 15:02
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class UpdateLoginPwInput extends BasePojo implements Serializable {
    private static final long serialVersionUID = 652443834923127351L;

    /**
     * 旧密码
     */
    @NotBlank(message = UaParamExceptionConstant.OLD_PW_CAN_NOT_BE_BLANK + "")
    @Md5(message = UaParamExceptionConstant.PASS_WORD_MUST_BE_MD5 + "")
    private String oldLoginPw;

    /**
     * 新密码
     */
    @NotBlank(message = UaParamExceptionConstant.NEW_PW_CAN_NOT_BE_BLANK + "")
    @Md5(message = UaParamExceptionConstant.PASS_WORD_MUST_BE_MD5 + "")
    private String newLoginPw;

    public String getOldLoginPw() {
        return oldLoginPw;
    }

    public void setOldLoginPw(String oldLoginPw) {
        this.oldLoginPw = oldLoginPw;
    }

    public String getNewLoginPw() {
        return newLoginPw;
    }

    public void setNewLoginPw(String newLoginPw) {
        this.newLoginPw = newLoginPw;
    }
}
