package com.tuodao.bp.model.facade.traningcenter.input;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.constant.traningcenter.TraningCenterExceptionConstant;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/10/26
 * @time: 14:39
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class CollectionExtParam extends BasePojo implements Serializable {

    @NotNull(message = TraningCenterExceptionConstant.TENDER_CALENDAR_IS_NULL + "")
    private String month;


    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
