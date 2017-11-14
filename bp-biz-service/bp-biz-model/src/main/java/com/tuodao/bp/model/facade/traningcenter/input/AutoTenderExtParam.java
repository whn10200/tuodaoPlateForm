package com.tuodao.bp.model.facade.traningcenter.input;

import com.tuodao.bp.model.PagePojo;
import com.tuodao.bp.model.constant.traningcenter.TraningCenterExceptionConstant;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/9/29
 * @time: 16:30
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class AutoTenderExtParam extends PagePojo implements Serializable {

    /**
     * 自动投标id
     */
    @NotNull(message = TraningCenterExceptionConstant.AUTO_TENDER_ID_NOT_NULL + "")
    private Integer autoTenderId;

    public Integer getAutoTenderId() {
        return autoTenderId;
    }

    public void setAutoTenderId(Integer autoTenderId) {
        this.autoTenderId = autoTenderId;
    }
}
