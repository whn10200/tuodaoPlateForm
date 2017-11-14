package com.tuodao.bp.model.facade.traningcenter.input;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.constant.traningcenter.TraningCenterExceptionConstant;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/9/25
 * @time: 16:07
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class TenderDetailParam extends BasePojo implements Serializable {

    @NotNull(message = TraningCenterExceptionConstant.TENDER_ID_IS_NULL + "")
    private Integer tenderId;


    public Integer getTenderId() {
        return tenderId;
    }

    public void setTenderId(Integer tenderId) {
        this.tenderId = tenderId;
    }
}
