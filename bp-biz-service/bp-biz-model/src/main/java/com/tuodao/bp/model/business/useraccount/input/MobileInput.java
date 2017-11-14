package com.tuodao.bp.model.business.useraccount.input;

import com.google.common.base.MoreObjects;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.annotation.PhoneNum;
import com.tuodao.bp.model.constant.useraccount.UaParamExceptionConstant;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * @description: 手机号码
 * @author: mif
 * @date: 2017/10/25
 * @time: 09:28
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class MobileInput implements Serializable {
    private static final long serialVersionUID = -3443269191701250874L;

    /**
     * 手机号码
     */
    @NotBlank(message = UaParamExceptionConstant.MOBILE_IS_NULL + "")
    @PhoneNum(message = UaParamExceptionConstant.MOBILE_FORMAT_ERROR + "")
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .toString();
    }
}
