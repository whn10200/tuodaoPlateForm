package com.tuodao.bp.product.db.model.basic;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BorrowPlanTransfer implements Serializable {
    private Integer id;

    private String userId;

    private Integer tenderId;

    private String borrowName;

    private Integer status;

    private Integer type;

    private BigDecimal account;

    private BigDecimal accountYes;

    private BigDecimal accountSuccessYes;

    private Integer startPeriod;

    private Integer leftPeriod;

    private Integer period;

    private Integer periodType;

    private BigDecimal awardApr;

    private BigDecimal apr;

    private Integer raymentType;

    private Integer preBorrowId;

    private Date transfeStartTime;

    private Date successTime;

    private Date addTime;

    private String remarks;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public BigDecimal getAccountSuccessYes() {
        return accountSuccessYes;
    }

    public void setAccountSuccessYes(BigDecimal accountSuccessYes) {
        this.accountSuccessYes = accountSuccessYes;
    }

    public Integer getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(Integer startPeriod) {
        this.startPeriod = startPeriod;
    }

    public Integer getLeftPeriod() {
        return leftPeriod;
    }

    public void setLeftPeriod(Integer leftPeriod) {
        this.leftPeriod = leftPeriod;
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

    public BigDecimal getAwardApr() {
        return awardApr;
    }

    public void setAwardApr(BigDecimal awardApr) {
        this.awardApr = awardApr;
    }

    public BigDecimal getApr() {
        return apr;
    }

    public void setApr(BigDecimal apr) {
        this.apr = apr;
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

    public Date getTransfeStartTime() {
        return transfeStartTime;
    }

    public void setTransfeStartTime(Date transfeStartTime) {
        this.transfeStartTime = transfeStartTime;
    }

    public Date getSuccessTime() {
        return successTime;
    }

    public void setSuccessTime(Date successTime) {
        this.successTime = successTime;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}