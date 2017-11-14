package com.tuodao.bp.model.facade.useraccout.input;

import com.tuodao.bp.model.constant.facade.FacadeExceptionConstant;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @description: 解密邀请码
 * @author: mif
 * @date: 2017/9/15
 * @time: 15:30
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class DecodeInviteCodeInput implements Serializable {
    /**
     * 邀请码
     */
    @NotBlank(message = FacadeExceptionConstant.INVITE_CODE_CAN_NOT_BE_BLANK + "")
    @Size(max = 16, message = FacadeExceptionConstant.INVITE_CODE_BEYOND_THE_LENGTH + "")
    private String inviteCode;

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }
    
}


