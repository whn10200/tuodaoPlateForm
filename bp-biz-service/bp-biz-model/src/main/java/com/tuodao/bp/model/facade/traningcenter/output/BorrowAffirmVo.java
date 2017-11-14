package com.tuodao.bp.model.facade.traningcenter.output;

import java.io.Serializable;

/**
 * @description: 投标确认界面
 * @author: 王艳兵
 * @date: 2017/11/10
 * @time: 17:14
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class BorrowAffirmVo implements Serializable {

    /**
     * 剩余可投
     */
    private String surplusAccount;

    /**
     * 剩余可投 值
     */
    private String surplusAccountValue;

    /**
     * 起投金额
     */
    private String minAccount;

    /**
     * 起投金额值
     */
    private String minAccountValue;

    /**
     * 可用余额
     */
    private String balance;

    /**
     * 可用余额 值
     */
    private String balanceValue;

    public String getSurplusAccount() {
        return surplusAccount;
    }

    public void setSurplusAccount(String surplusAccount) {
        this.surplusAccount = surplusAccount;
    }

    public String getSurplusAccountValue() {
        return surplusAccountValue;
    }

    public void setSurplusAccountValue(String surplusAccountValue) {
        this.surplusAccountValue = surplusAccountValue;
    }

    public String getMinAccount() {
        return minAccount;
    }

    public void setMinAccount(String minAccount) {
        this.minAccount = minAccount;
    }

    public String getMinAccountValue() {
        return minAccountValue;
    }

    public void setMinAccountValue(String minAccountValue) {
        this.minAccountValue = minAccountValue;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getBalanceValue() {
        return balanceValue;
    }

    public void setBalanceValue(String balanceValue) {
        this.balanceValue = balanceValue;
    }

    @Override
    public String toString() {
        return "BorrowAffirmVo{" +
                "surplusAccount='" + surplusAccount + '\'' +
                ", surplusAccountValue='" + surplusAccountValue + '\'' +
                ", minAccount='" + minAccount + '\'' +
                ", minAccountValue='" + minAccountValue + '\'' +
                ", balance='" + balance + '\'' +
                ", balanceValue='" + balanceValue + '\'' +
                '}';
    }
}
