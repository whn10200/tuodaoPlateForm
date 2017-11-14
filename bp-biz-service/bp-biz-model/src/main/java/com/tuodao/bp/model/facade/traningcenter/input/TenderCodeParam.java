package com.tuodao.bp.model.facade.traningcenter.input;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.annotation.RangeLength;
import com.tuodao.bp.model.constant.traningcenter.TraningCenterExceptionConstant;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description: 图形验证码校验
 * @author: 王艳兵
 * @date: 2017/11/10
 * @time: 14:30
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class TenderCodeParam extends BasePojo implements Serializable {
    /**
     * 待验证的密码或者图形验证码
     */
    @RangeLength(min = 4,max = 4,message = TraningCenterExceptionConstant.TENDER_CODE_ERROR + "" )
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
