package com.tuodao.bp.model.facade.traningcenter.input;


import com.tuodao.bp.model.annotation.In;
import com.tuodao.bp.model.constant.traningcenter.AccountLogConstant;
import com.tuodao.bp.model.constant.traningcenter.TraningCenterExceptionConstant;

/**
 * @description: 资金记录入参
 * @author: 王艳兵
 * @date: 2017/9/19
 * @time: 18:04
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class AccountLogParam extends Param {
    /**
     * 资金类型
     */
    @In(value = AccountLogConstant.ACCOUNT_SELECT_TYPE,message = TraningCenterExceptionConstant.ACCOUNT_LOG_STATUS_ERROR + "")
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
