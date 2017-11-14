package com.tuodao.bp.model.facade.traningcenter.output;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/9/19
 * @time: 18:11
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class AccountLogVo implements Serializable{

    /**
     * 交易时间
     */
    private String transactionTime;

    /**
     * 资金记录类型
     */
    private int type;

    /**
     * 交易金额
     */
    private String account;

    /**
     * 账户可用余额
     */
    private String balance;

    /**
     * 备注
     */
    private String remarks;

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "AccountLogVo{" +
                "transactionTime=" + transactionTime +
                ", type=" + type +
                ", account=" + account +
                ", balance=" + balance +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
