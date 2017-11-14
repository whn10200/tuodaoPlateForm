package com.tuodao.bp.model.traningcenter.input;


import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/9/12
 * @time: 18:04
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class BorrowCollectionInput implements Serializable{

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 当前期数
     */
    private int period;

    /**
     * 总期数
     */
    private int periods;

    /**
     * 预计回款时间
     */
    private Date preCollectionTime;

    /**
     * 预计回款月
     */
    private String preCollectionMonth;

    /**
     * 回款总利息
     */
    private double preInterest;

    /**
     * 回款本金
     */
    private double preCapital;

    /**
     * 本期加息券加息产生的收益
     */
    private double couponAccount;

    /**
     * 本期平台加息产生的收益
     */
    private double platformAccount;

    /**
     * 是否显示:0:不显示 1:显示
     */
    private int isShow;

    /**
     * 投标Id
     */
    private int tenderId;


    public String getPreCollectionMonth() {
        return preCollectionMonth;
    }

    public void setPreCollectionMonth(String preCollectionMonth) {
        this.preCollectionMonth = preCollectionMonth;
    }

    public int getTenderId() {
        return tenderId;
    }

    public void setTenderId(int tenderId) {
        this.tenderId = tenderId;
    }

    public int getIsShow() {
        return isShow;
    }

    public void setIsShow(int isShow) {
        this.isShow = isShow;
    }

    public Date getPreCollectionTime() {
        return preCollectionTime;
    }

    public void setPreCollectionTime(Date preCollectionTime) {
        this.preCollectionTime = preCollectionTime;
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

    public BorrowCollectionInput(){

    }

    public BorrowCollectionInput(String userId){
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "BorrowCollectionInput{" +
                "userId='" + userId + '\'' +
                ", period=" + period +
                ", periods=" + periods +
                ", preCollectionTime=" + preCollectionTime +
                ", preCollectionMonth='" + preCollectionMonth + '\'' +
                ", preInterest=" + preInterest +
                ", preCapital=" + preCapital +
                ", couponAccount=" + couponAccount +
                ", platformAccount=" + platformAccount +
                ", isShow=" + isShow +
                ", tenderId=" + tenderId +
                '}';
    }
}
