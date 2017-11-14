package com.tuodao.bp.model.business.traningcenter.input;

import com.tuodao.bp.model.PagePojo;
import com.tuodao.bp.model.constant.traningcenter.TraningCenterExceptionConstant;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description:当传入只有一个唯一id时候公用类
 * @author: wuzf
 * @date: 2017/9/20 0020.
 * @time: 17:00
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class JustIdInput extends PagePojo implements Serializable  {
    private static final long serialVersionUID = 5613022883594601748L;

    @NotNull(message = TraningCenterExceptionConstant.JUST_ID_IS_NULL+"")
    private Integer justId;

    public Integer getJustId() {
        return justId;
    }

    public void setJustId(Integer justId) {
        this.justId = justId;
    }
}
