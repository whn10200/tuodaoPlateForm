package com.tuodao.bp.model.facade.traningcenter.output;

import java.io.Serializable;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/11/7
 * @time: 14:31
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class AccountAppVo implements Serializable {

    /**
     * 总资产
     */
    private String total;

    /**
     * 可用余额
     */
    private String balance;

    /**
     * 可用金额 值
     */
    private String balanceValue;

    /**
     * 总待收
     */
    private String awaitTotal;

    /**
     * 总代收 值
     */
    private String awaitTotalValue;

    /**
     * 待收本金
     */
    private String awaitCapital;

    /**
     * 待收利息
     */
    private String awaitInterest;

    /**
     * 总冻结金额
     */
    private String frostTotal;

    /**
     * 总冻结 值
     */
    private String frostTotalValue;

    /**
     * 总累计收益
     */
    private String interestTotal;

    /**
     * 投资收益
     */
    private String tenderInterest;

    /**
     * 投资收益 值
     */
    private String tenderInterestValue;

    /**
     * 优惠券收益
     */
    private String voucherInterest;

    /**
     * 优惠券收益 值
     */
    private String voucherInterestValue;

    /**
     * 平台收益
     */
    private String platformInterest;

    /**
     * 平台收益 值
     */
    private String platformInterestValue;

    /**
     * 邀请收益
     */
    private String inviteInterest;

    /**
     * 邀请奖励
     *
     */
    private String inviteInterestValue;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
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

    public String getAwaitTotal() {
        return awaitTotal;
    }

    public void setAwaitTotal(String awaitTotal) {
        this.awaitTotal = awaitTotal;
    }

    public String getAwaitTotalValue() {
        return awaitTotalValue;
    }

    public void setAwaitTotalValue(String awaitTotalValue) {
        this.awaitTotalValue = awaitTotalValue;
    }

    public String getAwaitCapital() {
        return awaitCapital;
    }

    public void setAwaitCapital(String awaitCapital) {
        this.awaitCapital = awaitCapital;
    }

    public String getAwaitInterest() {
        return awaitInterest;
    }

    public void setAwaitInterest(String awaitInterest) {
        this.awaitInterest = awaitInterest;
    }

    public String getFrostTotal() {
        return frostTotal;
    }

    public void setFrostTotal(String frostTotal) {
        this.frostTotal = frostTotal;
    }

    public String getFrostTotalValue() {
        return frostTotalValue;
    }

    public void setFrostTotalValue(String frostTotalValue) {
        this.frostTotalValue = frostTotalValue;
    }

    public String getInterestTotal() {
        return interestTotal;
    }

    public void setInterestTotal(String interestTotal) {
        this.interestTotal = interestTotal;
    }

    public String getTenderInterest() {
        return tenderInterest;
    }

    public void setTenderInterest(String tenderInterest) {
        this.tenderInterest = tenderInterest;
    }

    public String getTenderInterestValue() {
        return tenderInterestValue;
    }

    public void setTenderInterestValue(String tenderInterestValue) {
        this.tenderInterestValue = tenderInterestValue;
    }

    public String getVoucherInterest() {
        return voucherInterest;
    }

    public void setVoucherInterest(String voucherInterest) {
        this.voucherInterest = voucherInterest;
    }

    public String getVoucherInterestValue() {
        return voucherInterestValue;
    }

    public void setVoucherInterestValue(String voucherInterestValue) {
        this.voucherInterestValue = voucherInterestValue;
    }

    public String getPlatformInterest() {
        return platformInterest;
    }

    public void setPlatformInterest(String platformInterest) {
        this.platformInterest = platformInterest;
    }

    public String getPlatformInterestValue() {
        return platformInterestValue;
    }

    public void setPlatformInterestValue(String platformInterestValue) {
        this.platformInterestValue = platformInterestValue;
    }

    public String getInviteInterest() {
        return inviteInterest;
    }

    public void setInviteInterest(String inviteInterest) {
        this.inviteInterest = inviteInterest;
    }

    public String getInviteInterestValue() {
        return inviteInterestValue;
    }

    public void setInviteInterestValue(String inviteInterestValue) {
        this.inviteInterestValue = inviteInterestValue;
    }
}
