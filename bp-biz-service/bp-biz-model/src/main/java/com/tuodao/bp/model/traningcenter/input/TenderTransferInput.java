package com.tuodao.bp.model.traningcenter.input;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description:
 * @author: wuzf
 * @date: 2017/9/14 0014.
 * @time: 17:45
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class TenderTransferInput  implements Serializable{

    private static final long serialVersionUID = 6152097435935591117L;

    private static BigDecimal apr;

    private static BigDecimal awardScale;

    private static BigDecimal vouchApr;

    private static int LeftPeriod;

    private static int startPeriod;

    private static long repayTime;

    private Integer id;

    private Integer raymentType;

    private BigDecimal Account;

    private String userId;

    private String borrowName;

    //剩余几期可见
    private Integer leftPeriodShow;

    public Integer getLeftPeriodShow() {
        return leftPeriodShow;
    }

    public void setLeftPeriodShow(Integer leftPeriodShow) {
        this.leftPeriodShow = leftPeriodShow;
    }

    public Integer getRaymentType() {
        return raymentType;
    }

    public void setRaymentType(Integer raymentType) {
        this.raymentType = raymentType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static long getRepayTime() {
        return repayTime;
    }

    public static void setRepayTime(long repayTime) {
        TenderTransferInput.repayTime = repayTime;
    }

    public static BigDecimal getApr() {
        return apr;
    }

    public static void setApr(BigDecimal apr) {
        TenderTransferInput.apr = apr;
    }

    public static BigDecimal getAwardScale() {
        return awardScale;
    }

    public static void setAwardScale(BigDecimal awardScale) {
        TenderTransferInput.awardScale = awardScale;
    }

    public static BigDecimal getVouchApr() {
        return vouchApr;
    }

    public static void setVouchApr(BigDecimal vouchApr) {
        TenderTransferInput.vouchApr = vouchApr;
    }

    public static int getLeftPeriod() {
        return LeftPeriod;
    }

    public static void setLeftPeriod(int leftPeriod) {
        LeftPeriod = leftPeriod;
    }

    public static int getStartPeriod() {
        return startPeriod;
    }

    public static void setStartPeriod(int startPeriod) {
        TenderTransferInput.startPeriod = startPeriod;
    }

    public BigDecimal getAccount() {
        return Account;
    }

    public void setAccount(BigDecimal account) {
        Account = account;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBorrowName() {
        return borrowName;
    }

    public void setBorrowName(String borrowName) {
        this.borrowName = borrowName;
    }
}
