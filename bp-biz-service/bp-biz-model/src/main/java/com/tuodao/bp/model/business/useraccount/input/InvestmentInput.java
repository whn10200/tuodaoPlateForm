package com.tuodao.bp.model.business.useraccount.input;

import com.tuodao.bp.model.BasePojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 投资请求类
 * @author: mif
 * @date: 2017/8/29
 * @time: 17:18
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class InvestmentInput extends BasePojo implements Serializable {
    private static final long serialVersionUID = 669564931489579000L;

    /**
     * 投资金额(分)
     */
    private BigDecimal funds;

    /**
     * 收益(分)
     */
    private BigDecimal earnings;

    /**
     * 待收金额(分)
     */
    private BigDecimal dueInFund;

    /**
     * 可用金额(分)
     */
    private BigDecimal usableFund;

    /**
     * 待收本金（分）
     */
    private BigDecimal dueInPrincipal;

    /**
     * 待收利息
     */
    private BigDecimal dueInInterest;


    public BigDecimal getFunds() {
        return funds;
    }

    public void setFunds(BigDecimal funds) {
        this.funds = funds;
    }

    public BigDecimal getEarnings() {
        return earnings;
    }

    public void setEarnings(BigDecimal earnings) {
        this.earnings = earnings;
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
}
