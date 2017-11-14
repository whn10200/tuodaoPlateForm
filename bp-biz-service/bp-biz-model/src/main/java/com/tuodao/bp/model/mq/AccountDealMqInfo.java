package com.tuodao.bp.model.mq;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 账户中心-交易中心资金MQ对象
 * @author: mif
 * @date: 2017/9/14
 * @time: 16:10
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class AccountDealMqInfo implements Serializable {

    private static final long serialVersionUID = -6113409186019969272L;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 类型
     */
    private String type;

    /**
     * 存管账户总资金（分）
     */
    private BigDecimal totalFund;

    /**
     * 累计收益（分）
     */
    private BigDecimal totalEarnings;

    /**
     * 待收总金额（分）
     */
    private BigDecimal dueInFund;

    /**
     * 可用余额(分)
     */
    private BigDecimal usableFund;

    /**
     * 待收本金(分)
     */
    private BigDecimal dueInPrincipal;

    /**
     * 待收利息(分)
     */
    private BigDecimal dueInInterest;

    /**
     * 冻结金额(分)
     */
    private BigDecimal freezeFund;

    /**
     * 可提现金额（分）
     */
    private BigDecimal canWithdrawFund;

    /**
     * 累计充值（分）
     */
    private BigDecimal totalRecharge;

    /**
     * 累计提现（分）
     */
    private BigDecimal totalWithdraw;

    /**
     * 累计投资次数
     */
    private Integer investmentTimes;

    /**
     * 累计投资总额（分）
     */
    private BigDecimal investmentAmount;

    /**
     * 已获得返现奖励 (分)
     */
    private BigDecimal returnAmount;





    public String getUserId() {
        return userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public BigDecimal getTotalFund() {
        return totalFund;
    }

    public void setTotalFund(BigDecimal totalFund) {
        this.totalFund = totalFund;
    }

    public BigDecimal getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(BigDecimal totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    public BigDecimal getDueInFund() {
        return dueInFund;
    }

    public void setDueInFund(BigDecimal dueInFund) {
        this.dueInFund = dueInFund;
    }

    public BigDecimal getUsableFund() {
        return usableFund;
    }

    public void setUsableFund(BigDecimal usableFund) {
        this.usableFund = usableFund;
    }

    public BigDecimal getDueInPrincipal() {
        return dueInPrincipal;
    }

    public void setDueInPrincipal(BigDecimal dueInPrincipal) {
        this.dueInPrincipal = dueInPrincipal;
    }

    public BigDecimal getDueInInterest() {
        return dueInInterest;
    }

    public void setDueInInterest(BigDecimal dueInInterest) {
        this.dueInInterest = dueInInterest;
    }

    public BigDecimal getFreezeFund() {
        return freezeFund;
    }

    public void setFreezeFund(BigDecimal freezeFund) {
        this.freezeFund = freezeFund;
    }

    public BigDecimal getCanWithdrawFund() {
        return canWithdrawFund;
    }

    public void setCanWithdrawFund(BigDecimal canWithdrawFund) {
        this.canWithdrawFund = canWithdrawFund;
    }

    public BigDecimal getTotalRecharge() {
        return totalRecharge;
    }

    public void setTotalRecharge(BigDecimal totalRecharge) {
        this.totalRecharge = totalRecharge;
    }

    public BigDecimal getTotalWithdraw() {
        return totalWithdraw;
    }

    public void setTotalWithdraw(BigDecimal totalWithdraw) {
        this.totalWithdraw = totalWithdraw;
    }

    public Integer getInvestmentTimes() {
        return investmentTimes;
    }

    public void setInvestmentTimes(Integer investmentTimes) {
        this.investmentTimes = investmentTimes;
    }

    public BigDecimal getInvestmentAmount() {
        return investmentAmount;
    }

    public void setInvestmentAmount(BigDecimal investmentAmount) {
        this.investmentAmount = investmentAmount;
    }

    public BigDecimal getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(BigDecimal returnAmount) {
        this.returnAmount = returnAmount;
    }



    @Override
    public String toString() {
        return "AccountDealMqInfo{" +
                "userId='" + userId + '\'' +
                ", type='" + type +
                ", totalFund=" + totalFund +
                ", totalEarnings=" + totalEarnings +
                ", dueInFund=" + dueInFund +
                ", usableFund=" + usableFund +
                ", dueInPrincipal=" + dueInPrincipal +
                ", dueInInterest=" + dueInInterest +
                ", freezeFund=" + freezeFund +
                ", canWithdrawFund=" + canWithdrawFund +
                ", totalRecharge=" + totalRecharge +
                ", totalWithdraw=" + totalWithdraw +
                ", investmentTimes=" + investmentTimes +
                ", investmentAmount=" + investmentAmount +
                ", returnAmount=" + returnAmount +
                '}';
    }



    public AccountDealMqInfo(String userId, String type, BigDecimal totalFund, BigDecimal totalEarnings, BigDecimal dueInFund, BigDecimal usableFund, BigDecimal dueInPrincipal, BigDecimal dueInInterest, BigDecimal freezeFund, BigDecimal canWithdrawFund, BigDecimal totalRecharge, BigDecimal totalWithdraw,
                             Integer investmentTimes, BigDecimal investmentAmount, BigDecimal returnAmount) {
        this.userId = userId;
        this.type = type;
        this.totalFund = totalFund;
        this.totalEarnings = totalEarnings;
        this.dueInFund = dueInFund;
        this.usableFund = usableFund;
        this.dueInPrincipal = dueInPrincipal;
        this.dueInInterest = dueInInterest;
        this.freezeFund = freezeFund;
        this.canWithdrawFund = canWithdrawFund;
        this.totalRecharge = totalRecharge;
        this.totalWithdraw = totalWithdraw;
        this.investmentTimes = investmentTimes;
        this.investmentAmount = investmentAmount;
        this.returnAmount = returnAmount;

    }
    public AccountDealMqInfo() {

    }

}
