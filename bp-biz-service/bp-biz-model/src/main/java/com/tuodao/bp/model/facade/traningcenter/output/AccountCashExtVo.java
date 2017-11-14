package com.tuodao.bp.model.facade.traningcenter.output;

import java.io.Serializable;

/**
 * @description: 进入提现页面后数据请求
 * @author: 王艳兵
 * @date: 2017/10/23
 * @time: 18:38
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class AccountCashExtVo implements Serializable{

    /**
     * 状态 0:未开通存管 1:已开通存管
     */
    private int status;

    /**
     * 银行编号
     */
    private String bankCode;

    /**
     * 银行卡号
     */
    private String bankNum;

    /**
     * 可提现金额
     */
    private double balanceValue;

    /**
     * 可提现金额
     */
    private String balance;

    /**
     * 可用提现次数
     */
    private int cashNum;

    /**
     * 是否为清算时间
     */
    private boolean clearTime;

    /**
     * 投资过 可提现金额
     */
    private double tenderCash;

    /**
     * 未投资过 可以提现金额(需要收取手续费)
     */
    private double chargeCash;

    public double getTenderCash() {
        return tenderCash;
    }

    public void setTenderCash(double tenderCash) {
        this.tenderCash = tenderCash;
    }

    public double getChargeCash() {
        return chargeCash;
    }

    public void setChargeCash(double chargeCash) {
        this.chargeCash = chargeCash;
    }

    public boolean isClearTime() {
        return clearTime;
    }

    public void setClearTime(boolean clearTime) {
        this.clearTime = clearTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankNum() {
        return bankNum;
    }

    public void setBankNum(String bankNum) {
        this.bankNum = bankNum;
    }

    public double getBalanceValue() {
        return balanceValue;
    }

    public void setBalanceValue(double balanceValue) {
        this.balanceValue = balanceValue;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public int getCashNum() {
        return cashNum;
    }

    public void setCashNum(int cashNum) {
        this.cashNum = cashNum;
    }
}
