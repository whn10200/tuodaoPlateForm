package com.tuodao.bp.model.business.traningcenter.output;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/9/14
 * @time: 13:44
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class BorrowTenderOutput implements Serializable{

    private static final long serialVersionUID = -2521264806112291857L;

    /**
     * 标的id
     */
    private Integer borrowId;

    /**
     * 投资金额
     */
    private BigDecimal amount;

    /**
     * 利息
     */
    private BigDecimal interest = BigDecimal.ZERO;

    /**
     * 奖励
     */
    private BigDecimal reward;
    /**
     * 加息券id
     */
    private Integer voucherCouponId;
    /**
     * 0普通投标 1债权投标
     */
    private Integer tenderType;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 投标ID
     */
    private Integer id;

    /**
     * 抵用券Id
     */
    private Integer voucherId;

    /**
     * 额度
     */
    private BigDecimal voucherMoney;

    /**
     * 券类型0:不使用券 1:抵用券 2:加息券
     */
    private int voucherType;

    /**
     * 投标金额
     */
    private BigDecimal preAccount;

    /**
     * 实际投标金额
     */
    private BigDecimal account;

    /**
     * 平台利息
     */
    private BigDecimal platformInterest = BigDecimal.ZERO;

    /**
     * 总利息
     */
    private BigDecimal accountInterest = BigDecimal.ZERO;

    /**
     * 加息券利息
     */
    private BigDecimal voucherCouponMoney;

    /**
     * 订单号:在投标冻结时已经生成时已经生成
     */
    private String orderId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 投标状态
     */
    private Integer status;

    /**
     * 投标方式:0:手动投标 1:自动投标
     */
    private Integer tenderMode;

    /**
     * 自动投标ID
     */
    private Integer autoTenderId;

    /**
     * 备注信息
     */
    private String remarks;

    /**
     * 查询结果
     */
    private String tenderKey;

    /**
     * 投标渠道
     */
    private Integer channel;

    /**
     * 投标时间
     */
    private Date addTime;

    /**
     * 投标所使用免费提现额度
     */
    private BigDecimal feeAccount;

    public BigDecimal getFeeAccount() {
        return feeAccount;
    }

    public void setFeeAccount(BigDecimal feeAccount) {
        this.feeAccount = feeAccount;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getTenderKey() {
        return tenderKey;
    }

    public void setTenderKey(String tenderKey) {
        this.tenderKey = tenderKey;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(int voucherType) {
        this.voucherType = voucherType;
    }

    public Integer getAutoTenderId() {
        return autoTenderId;
    }

    public void setAutoTenderId(Integer autoTenderId) {
        this.autoTenderId = autoTenderId;
    }

    public Integer getTenderMode() {
        return tenderMode;
    }

    public void setTenderMode(Integer tenderMode) {
        this.tenderMode = tenderMode;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getVoucherCouponMoney() {
        return voucherCouponMoney;
    }

    public void setVoucherCouponMoney(BigDecimal voucherCouponMoney) {
        this.voucherCouponMoney = voucherCouponMoney;
    }

    public BigDecimal getPlatformInterest() {
        return platformInterest;
    }

    public void setPlatformInterest(BigDecimal platformInterest) {
        this.platformInterest = platformInterest;
    }

    public BigDecimal getAccountInterest() {
        return accountInterest;
    }

    public void setAccountInterest(BigDecimal accountInterest) {
        this.accountInterest = accountInterest;
    }

    public Integer getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public BigDecimal getReward() {
        return reward;
    }

    public void setReward(BigDecimal reward) {
        this.reward = reward;
    }

    public Integer getVoucherCouponId() {
        return voucherCouponId;
    }

    public void setVoucherCouponId(Integer voucherCouponId) {
        this.voucherCouponId = voucherCouponId;
    }

    public Integer getTenderType() {
        return tenderType;
    }

    public void setTenderType(Integer tenderType) {
        this.tenderType = tenderType;
    }

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

    public Integer getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Integer voucherId) {
        this.voucherId = voucherId;
    }

    public BigDecimal getVoucherMoney() {
        return voucherMoney;
    }

    public void setVoucherMoney(BigDecimal voucherMoney) {
        this.voucherMoney = voucherMoney;
    }

    public BigDecimal getPreAccount() {
        return preAccount;
    }

    public void setPreAccount(BigDecimal preAccount) {
        this.preAccount = preAccount;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "BorrowTenderOutput{" +
                "borrowId=" + borrowId +
                ", amount=" + amount +
                ", interest=" + interest +
                ", reward=" + reward +
                ", voucherCouponId=" + voucherCouponId +
                ", tenderType=" + tenderType +
                ", mobile='" + mobile + '\'' +
                ", id=" + id +
                ", voucherId=" + voucherId +
                ", voucherMoney=" + voucherMoney +
                ", voucherType=" + voucherType +
                ", preAccount=" + preAccount +
                ", account=" + account +
                ", platformInterest=" + platformInterest +
                ", accountInterest=" + accountInterest +
                ", voucherCouponMoney=" + voucherCouponMoney +
                ", orderId='" + orderId + '\'' +
                ", userId='" + userId + '\'' +
                ", status=" + status +
                ", tenderMode=" + tenderMode +
                ", autoTenderId=" + autoTenderId +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
