package com.tuodao.bp.utils;

import java.util.Date;

/**
 * @description: 生成还款计划回款计划
 * @author: 王艳兵
 * @date: 2017/9/18
 * @time: 17:59
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class BorrowPlan {

    /**
     * 期数
     */
    private int period;

    /**
     * 总期数
     */
    private int periods;

    /**
     * 预计回款时间
     */
    private Date preTime;

    /**
     * 预计回款月
     */
    private String preMonth;

    /**
     * 预计回款利息
     */
    private double preInterest;

    /**
     * 预计回款本金
     */
    private double preCapital;

    /**
     * 当期加息券收益
     */
    private double couponAccount;

    /**
     * 平台加息收益
     */
    private double platformAccount;

    /**
     * 回款时间
     */
    private Date preCollectionTime;

    public String getPreMonth() {
        return preMonth;
    }

    public void setPreMonth(String preMonth) {
        this.preMonth = preMonth;
    }

    public Date getPreCollectionTime() {
        return preCollectionTime;
    }

    public void setPreCollectionTime(Date preCollectionTime) {
        this.preCollectionTime = preCollectionTime;
    }

    public int getPeriods() {
        return periods;
    }

    public void setPeriods(int periods) {
        this.periods = periods;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public Date getPreTime() {
        return preTime;
    }

    public void setPreTime(Date preTime) {
        this.preTime = preTime;
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

    public double getCouponAccount() {
        return couponAccount;
    }

    public void setCouponAccount(double couponAccount) {
        this.couponAccount = couponAccount;
    }

    public double getPlatformAccount() {
        return platformAccount;
    }

    public void setPlatformAccount(double platformAccount) {
        this.platformAccount = platformAccount;
    }

    @Override
    public String toString() {
        return "BorrowPlan{" +
                "period=" + period +
                ", periods=" + periods +
                ", preTime=" + preTime +
                ", preInterest=" + preInterest +
                ", preCapital=" + preCapital +
                ", couponAccount=" + couponAccount +
                ", platformAccount=" + platformAccount +
                ", preCollectionTime=" + preCollectionTime +
                '}';
    }
}
