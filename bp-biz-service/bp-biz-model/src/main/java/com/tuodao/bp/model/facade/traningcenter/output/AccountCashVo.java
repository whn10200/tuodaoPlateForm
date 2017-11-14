package com.tuodao.bp.model.facade.traningcenter.output;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 返回前台封装对象
 * @author: 王艳兵
 * @date: 2017/9/19
 * @time: 15:58
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class AccountCashVo implements Serializable {

    /**
     * 提现时间
     */
    private String cashTime;

    /**
     * 提现银行
     */
    private String bankName;

    /**
     * 银行卡号
     */
    private String bankNum;

    /**
     * 提现金额
     */
    private String cashAccount;

    /**
     * 到账金额
     */
    private String realAccount;

    /**
     * 提现手续费
     */
    private String fee;

    /**
     * 提现状态
     */
    private int status;


    public String getBankNum() {
        return bankNum;
    }

    public void setBankNum(String bankNum) {
        this.bankNum = bankNum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public String getCashTime() {
        return cashTime;
    }

    public void setCashTime(String cashTime) {
        this.cashTime = cashTime;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCashAccount() {
        return cashAccount;
    }

    public void setCashAccount(String cashAccount) {
        this.cashAccount = cashAccount;
    }

    public String getRealAccount() {
        return realAccount;
    }

    public void setRealAccount(String realAccount) {
        this.realAccount = realAccount;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "AccountCashVo{" +
                "cashTime=" + cashTime +
                ", bankName='" + bankName + '\'' +
                ", cashAccount='" + cashAccount + '\'' +
                ", realAccount='" + realAccount + '\'' +
                ", fee='" + fee + '\'' +
                ", status=" + status +
                ", bankNum='" + bankNum + '\'' +
                '}';
    }
}
