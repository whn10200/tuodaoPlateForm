package com.tuodao.bp.model.facade.traningcenter.input;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.annotation.RangeDouble;
import com.tuodao.bp.model.constant.traningcenter.TraningCenterExceptionConstant;


/**
 * @description: 计算收益
 * @author: 王艳兵
 * @date: 2017/10/23
 * @time: 18:31
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class CashCalcParam extends BasePojo {

    /**
     * 提现金额 元
     */
    @RangeDouble(min = 100D,max = 10000000D,message = TraningCenterExceptionConstant.MIN_MAX_CASH_MONEY + "")
    private Double cashMoney;

    public Double getCashMoney() {
        return cashMoney;
    }

    public void setCashMoney(Double cashMoney) {
        this.cashMoney = cashMoney;
    }
}
