package com.tuodao.bp.traningcenter.db.model.basic;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BorrowChoicenessTender implements Serializable {
    private static final long serialVersionUID = 8349703312369336530L;

    private Integer id;

    private String userId;

    private Integer borrowId;

    private Integer status;

    private BigDecimal account;

    private BigDecimal preAccount;

    private BigDecimal accountInterest;

    private BigDecimal platformInterest;

    private BigDecimal preAccountInterest;

    private BigDecimal voucherInterest;

    private Integer channel;

    private Date addTime;

    private Date updateTime;

    private String addIp;

    private Integer voucherId;

    private Integer voucherCouponId;

    private Integer beforeTenderId;

    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public Integer getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
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

    public BigDecimal getPreAccount() {
        return preAccount;
    }

    public void setPreAccount(BigDecimal preAccount) {
        this.preAccount = preAccount;
    }

    public BigDecimal getAccountInterest() {
        return accountInterest;
    }

    public void setAccountInterest(BigDecimal accountInterest) {
        this.accountInterest = accountInterest;
    }

    public BigDecimal getPlatformInterest() {
        return platformInterest;
    }

    public void setPlatformInterest(BigDecimal platformInterest) {
        this.platformInterest = platformInterest;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getAddIp() {
        return addIp;
    }

    public void setAddIp(String addIp) {
        this.addIp = addIp == null ? null : addIp.trim();
    }

    public Integer getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Integer voucherId) {
        this.voucherId = voucherId;
    }

    public Integer getVoucherCouponId() {
        return voucherCouponId;
    }

    public void setVoucherCouponId(Integer voucherCouponId) {
        this.voucherCouponId = voucherCouponId;
    }

    public BigDecimal getVoucherInterest() {
        return voucherInterest;
    }

    public void setVoucherInterest(BigDecimal voucherInterest) {
        this.voucherInterest = voucherInterest;
    }

    public Integer getBeforeTenderId() {
        return beforeTenderId;
    }

    public void setBeforeTenderId(Integer beforeTenderId) {
        this.beforeTenderId = beforeTenderId;
    }

    public BigDecimal getPreAccountInterest() {
        return preAccountInterest;
    }

    public void setPreAccountInterest(BigDecimal preAccountInterest) {
        this.preAccountInterest = preAccountInterest;
    }
}