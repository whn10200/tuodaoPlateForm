package com.tuodao.bp.traningcenter.db.model.basic;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BorrowTransfer implements Serializable {
    private Integer id;

    private String userId;

    private Integer tenderId;

    private String borrowName;

    private Integer status;

    private BigDecimal account;

    private BigDecimal accountYes;

    private Integer period;

    private Integer periodType;

    private BigDecimal apr;

    private BigDecimal platformApr;

    private Integer raymentType;

    private Integer preBorrowId;

    private Date addTime;

    private Date endTime;

    private BigDecimal accountMin;

    private BigDecimal fee;

    private Date verifyTime;

    private String remarks;

    private BigDecimal transferWorth;

    private Integer toSource;

    private BigDecimal profit;

    private BigDecimal returnInterest;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getTenderId() {
        return tenderId;
    }

    public void setTenderId(Integer tenderId) {
        this.tenderId = tenderId;
    }

    public String getBorrowName() {
        return borrowName;
    }

    public void setBorrowName(String borrowName) {
        this.borrowName = borrowName == null ? null : borrowName.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    public BigDecimal getAccountYes() {
        return accountYes;
    }

    public void setAccountYes(BigDecimal accountYes) {
        this.accountYes = accountYes;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getPeriodType() {
        return periodType;
    }

    public void setPeriodType(Integer periodType) {
        this.periodType = periodType;
    }

    public BigDecimal getApr() {
        return apr;
    }

    public void setApr(BigDecimal apr) {
        this.apr = apr;
    }

    public BigDecimal getPlatformApr() {
        return platformApr;
    }

    public void setPlatformApr(BigDecimal platformApr) {
        this.platformApr = platformApr;
    }

    public Integer getRaymentType() {
        return raymentType;
    }

    public void setRaymentType(Integer raymentType) {
        this.raymentType = raymentType;
    }

    public Integer getPreBorrowId() {
        return preBorrowId;
    }

    public void setPreBorrowId(Integer preBorrowId) {
        this.preBorrowId = preBorrowId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getAccountMin() {
        return accountMin;
    }

    public void setAccountMin(BigDecimal accountMin) {
        this.accountMin = accountMin;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public Date getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(Date verifyTime) {
        this.verifyTime = verifyTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public BigDecimal getTransferWorth() {
        return transferWorth;
    }

    public void setTransferWorth(BigDecimal transferWorth) {
        this.transferWorth = transferWorth;
    }

    public Integer getToSource() {
        return toSource;
    }

    public void setToSource(Integer toSource) {
        this.toSource = toSource;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public BigDecimal getReturnInterest() {
        return returnInterest;
    }

    public void setReturnInterest(BigDecimal returnInterest) {
        this.returnInterest = returnInterest;
    }
}