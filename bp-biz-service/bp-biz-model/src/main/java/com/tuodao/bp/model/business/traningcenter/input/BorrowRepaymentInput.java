package com.tuodao.bp.model.business.traningcenter.input;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 还款输入类
 * @author: 王艳兵
 * @date: 2017/9/19
 * @time: 14:40
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class BorrowRepaymentInput implements Serializable{

    /**
     * 借款人Id
     */
    private String userId;

    /**
     * 标的Id
     */
    private int borrowId;

    /**
     * 标的编号
     */
    private String borrowCode;

    /**
     * 第几期
     */
    private int period;

    /**
     * 总期数
     */
    private int periods;

    /**
     * 手续费
     */
    private double fee;

    /**
     * 本期应还利息
     */
    private double preInterest;

    /**
     * 本期应还本金
     */
    private double preCapital;


    /**
     * 预计回款时间
     */
    private Date preRepayTime;


    public String getBorrowCode() {
        return borrowCode;
    }

    public void setBorrowCode(String borrowCode) {
        this.borrowCode = borrowCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getPeriods() {
        return periods;
    }

    public void setPeriods(int periods) {
        this.periods = periods;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public double getPreInterest() {
        return preInterest;
    }

    public void setPreInterest(double preInterest) {
        this.preInterest = preInterest;
    }

    public double getPreCapital() {
        return preCapital;
    }

    public void setPreCapital(double preCapital) {
        this.preCapital = preCapital;
    }

    public Date getPreRepayTime() {
        return preRepayTime;
    }

    public void setPreRepayTime(Date preRepayTime) {
        this.preRepayTime = preRepayTime;
    }

    @Override
    public String toString() {
        return "BorrowRepaymentInput{" +
                "userId='" + userId + '\'' +
                ", borrowId=" + borrowId +
                ", period=" + period +
                ", periods=" + periods +
                ", fee=" + fee +
                ", preInterest=" + preInterest +
                ", preCapital=" + preCapital +
                ", preRepayTime=" + preRepayTime +
                '}';
    }
}
