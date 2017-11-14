package com.tuodao.bp.model.traningcenter.output;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 用户资金账户 出参
 * @author: 王艳兵
 * @date: 2017/9/12
 * @time: 17:43
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class AccountOutput implements Serializable{

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 总资产
     */
    private BigDecimal total;

    /**
     * 投标冻结
     */
    private BigDecimal tenderFrost;

    /**
     * 提现冻结
     */
    private BigDecimal cashFrost;

    /**
     * 用户可用金额(可提现+充值)
     */
    private BigDecimal balance;

    /**
     * 充值金额
     */
    private BigDecimal recharge;

    /**
     * 待收利息
     */
    private BigDecimal awaitInterest;

    /**
     * 待收本金
     */
    private BigDecimal awaitCapital;

    /**
     * 累计收益
     */
    private BigDecimal totalEarnings;

    /**
     * 免费提现金额
     */
    private BigDecimal balanceCash;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getBalanceCash() {
        return balanceCash;
    }

    public void setBalanceCash(BigDecimal balanceCash) {
        this.balanceCash = balanceCash;
    }

    public BigDecimal getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(BigDecimal totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    public AccountOutput(){
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getTenderFrost() {
        return tenderFrost;
    }

    public void setTenderFrost(BigDecimal tenderFrost) {
        this.tenderFrost = tenderFrost;
    }

    public BigDecimal getCashFrost() {
        return cashFrost;
    }

    public void setCashFrost(BigDecimal cashFrost) {
        this.cashFrost = cashFrost;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getRecharge() {
        return recharge;
    }

    public void setRecharge(BigDecimal recharge) {
        this.recharge = recharge;
    }

    public BigDecimal getAwaitInterest() {
        return awaitInterest;
    }

    public void setAwaitInterest(BigDecimal awaitInterest) {
        this.awaitInterest = awaitInterest;
    }

    public BigDecimal getAwaitCapital() {
        return awaitCapital;
    }

    public void setAwaitCapital(BigDecimal awaitCapital) {
        this.awaitCapital = awaitCapital;
    }

    @Override
    public String toString() {
        return "AccountOutput{" +
                "total=" + total +
                ", tenderFrost=" + tenderFrost +
                ", cashFrost=" + cashFrost +
                ", balance=" + balance +
                ", recharge=" + recharge +
                ", awaitInterest=" + awaitInterest +
                ", awaitCapital=" + awaitCapital +
                ", totalEarnings=" + totalEarnings +
                '}';
    }
}
