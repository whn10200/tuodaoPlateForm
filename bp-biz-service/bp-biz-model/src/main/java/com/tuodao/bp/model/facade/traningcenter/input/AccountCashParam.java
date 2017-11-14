package com.tuodao.bp.model.facade.traningcenter.input;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.annotation.Range;
import com.tuodao.bp.model.annotation.RangeDouble;
import com.tuodao.bp.model.annotation.RangeLength;
import com.tuodao.bp.model.constant.traningcenter.TraningCenterExceptionConstant;


/**
 * @description: 提现请求参数
 * @author: 王艳兵
 * @date: 2017/9/13
 * @time: 10:31
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class AccountCashParam extends BasePojo{


    /**
     * 提现金额
     */
    @RangeDouble(min = 100D,max = 10000000D,message = TraningCenterExceptionConstant.MIN_MAX_CASH_MONEY + "")
    private Double cashMoney;

    /**
     * 支付密码
     */
    @RangeLength(min = 32,max = 32,message = TraningCenterExceptionConstant.PAY_PASSWORD + "" )
    private String payPassword;

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }


    public Double getCashMoney() {
        return cashMoney;
    }

    public void setCashMoney(Double cashMoney) {
        this.cashMoney = cashMoney;
    }
}
