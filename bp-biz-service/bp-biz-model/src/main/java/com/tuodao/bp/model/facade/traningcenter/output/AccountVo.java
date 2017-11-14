package com.tuodao.bp.model.facade.traningcenter.output;

import java.io.Serializable;

/**
 * @description: 用户资产信息
 * @author: 王艳兵
 * @date: 2017/10/20
 * @time: 15:53
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class AccountVo implements Serializable {

    /**
     * 总资产
     */
    private String totalCapital;

    /**
     * 总收益
     */
    private String totalEarnings;

    /**
     * 总待收 (本息)
     */
    private String totalAwait;

    /**
     * 可用余额 包含充值
     */
    private String totalBalance;

    /**
     * 可用余额 包含充值 值
     */
    private double totalBalanceValue;

    /**
     * 待收本金
     */
    private String totalAwaitCapital;

    /**
     * 待收本金值
     */
    private double totalAwaitCapitalValue;

    /**
     * 待收利息
     */
    private String totalAwaitInterest;

    /**
     * 待收利息 值
     */
    private double totalAwaitInterestValue;


    /**
     * 总冻结金额= 投标冻结 提现冻结
     */
    private String totalFrost;

    /**
     * 总冻结金额 值
     */
    private double totalFrostValue;

    /**
     * 散标投标冻结
     */
    private String totalTenderFrost;

    /**
     * 提现冻结
     */
    private String totalCashFrost;

    /**
     * 精选计划投标冻结
     */
    private String totalPlanTenderFrost;

    public String getTotalPlanTenderFrost() {
        return totalPlanTenderFrost;
    }

    public void setTotalPlanTenderFrost(String totalPlanTenderFrost) {
        this.totalPlanTenderFrost = totalPlanTenderFrost;
    }

    public double getTotalBalanceValue() {
        return totalBalanceValue;
    }

    public void setTotalBalanceValue(double totalBalanceValue) {
        this.totalBalanceValue = totalBalanceValue;
    }

    public double getTotalAwaitCapitalValue() {
        return totalAwaitCapitalValue;
    }

    public void setTotalAwaitCapitalValue(double totalAwaitCapitalValue) {
        this.totalAwaitCapitalValue = totalAwaitCapitalValue;
    }

    public double getTotalAwaitInterestValue() {
        return totalAwaitInterestValue;
    }

    public void setTotalAwaitInterestValue(double totalAwaitInterestValue) {
        this.totalAwaitInterestValue = totalAwaitInterestValue;
    }

    public double getTotalFrostValue() {
        return totalFrostValue;
    }

    public void setTotalFrostValue(double totalFrostValue) {
        this.totalFrostValue = totalFrostValue;
    }

    public String getTotalCapital() {
        return totalCapital;
    }

    public void setTotalCapital(String totalCapital) {
        this.totalCapital = totalCapital;
    }

    public String getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(String totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    public String getTotalAwait() {
        return totalAwait;
    }

    public void setTotalAwait(String totalAwait) {
        this.totalAwait = totalAwait;
    }

    public String getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(String totalBalance) {
        this.totalBalance = totalBalance;
    }

    public String getTotalAwaitCapital() {
        return totalAwaitCapital;
    }

    public void setTotalAwaitCapital(String totalAwaitCapital) {
        this.totalAwaitCapital = totalAwaitCapital;
    }

    public String getTotalAwaitInterest() {
        return totalAwaitInterest;
    }

    public void setTotalAwaitInterest(String totalAwaitInterest) {
        this.totalAwaitInterest = totalAwaitInterest;
    }

    public String getTotalFrost() {
        return totalFrost;
    }

    public void setTotalFrost(String totalFrost) {
        this.totalFrost = totalFrost;
    }

    public String getTotalTenderFrost() {
        return totalTenderFrost;
    }

    public void setTotalTenderFrost(String totalTenderFrost) {
        this.totalTenderFrost = totalTenderFrost;
    }

    public String getTotalCashFrost() {
        return totalCashFrost;
    }

    public void setTotalCashFrost(String totalCashFrost) {
        this.totalCashFrost = totalCashFrost;
    }
}
