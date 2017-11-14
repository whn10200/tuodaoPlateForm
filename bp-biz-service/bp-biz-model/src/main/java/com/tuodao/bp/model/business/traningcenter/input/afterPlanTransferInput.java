package com.tuodao.bp.model.business.traningcenter.input;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description:
 * @author: wuzf
 * @date: 2017/9/12 0012.
 * @time: 16:14
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class afterPlanTransferInput implements Serializable {
    private static final long serialVersionUID = -4376493065592181464L;

    private Integer id;

    private String userId;

    private Integer tenderId;

    //下一期还款时间
    private long repayTime;

    //理财计划复审时间
    private long planReverifyTime;

    private String borrowName;

    private BigDecimal account;

    private BigDecimal apr;

    //平台奖励
    private BigDecimal awardScale;

    private BigDecimal accountYes;

    private BigDecimal accountSuccessYes;

    private Integer startPeriod;

    private Integer leftPeriod;

    private Integer period;

    private Integer periodType;



    private Integer raymentType;

    private Integer preBorrowId;

    private Date transfeStartTime;

    private Date successTime;

    private Date addTime;

    private String remarks;

    public BigDecimal getAwardScale() {
        return awardScale;
    }

    public void setAwardScale(BigDecimal awardScale) {
        this.awardScale = awardScale;
    }

    public long getPlanReverifyTime() {
        return planReverifyTime;
    }

    public void setPlanReverifyTime(long planReverifyTime) {
        this.planReverifyTime = planReverifyTime;
    }

    public long getRepayTime() {
        return repayTime;
    }

    public void setRepayTime(long repayTime) {
        this.repayTime = repayTime;
    }

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
