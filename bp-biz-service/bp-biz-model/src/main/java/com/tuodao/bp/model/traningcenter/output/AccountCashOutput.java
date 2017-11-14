package com.tuodao.bp.model.traningcenter.output;

import java.io.Serializable;

/**
 * @description: 提现出参
 * @author: 王艳兵
 * @date: 2017/9/12
 * @time: 18:09
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class AccountCashOutput implements Serializable{

    /**
     * 已提现的总额
     */
    private double cashTotal;

    /**
     * 提现状态:0:申请中:1提现成功2:提现失败
     */
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public AccountCashOutput(){}
    public AccountCashOutput(double cashTotal) {
        this.cashTotal = cashTotal;
    }

    public double getCashTotal() {
        return cashTotal;
    }

    public void setCashTotal(double cashTotal) {
        this.cashTotal = cashTotal;
    }
}
