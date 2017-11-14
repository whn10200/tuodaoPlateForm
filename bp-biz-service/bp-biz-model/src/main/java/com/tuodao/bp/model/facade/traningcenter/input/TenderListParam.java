package com.tuodao.bp.model.facade.traningcenter.input;

import com.tuodao.bp.model.annotation.In;
import com.tuodao.bp.model.constant.traningcenter.TenderConstant;
import com.tuodao.bp.model.constant.traningcenter.TraningCenterExceptionConstant;

import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/9/22
 * @time: 10:27
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class TenderListParam extends Param{


    /**
     * 投标状态
     */
    @In(value = TenderConstant.SELECT_TENDER_STATUS,message = TraningCenterExceptionConstant.TENDER_STATUS_ERROR + "")
    private Integer status;

    /**
     * 自动投标ID,如果查询的自动投标 则需要将此字段不为空
     */
    private Integer autoTenderId;


    public Integer getAutoTenderId() {
        return autoTenderId;
    }

    public void setAutoTenderId(Integer autoTenderId) {
        this.autoTenderId = autoTenderId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


}
