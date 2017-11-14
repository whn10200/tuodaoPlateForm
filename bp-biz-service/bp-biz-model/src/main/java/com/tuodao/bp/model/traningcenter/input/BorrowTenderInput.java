package com.tuodao.bp.model.traningcenter.input;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 投标入参
 * @author: 王艳兵
 * @date: 2017/9/14
 * @time: 13:42
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class BorrowTenderInput implements Serializable{

    /**
     * 投标Id
     */
    private Integer id;
    /**
     * 用户id
     */
    public String userId;

    /**
     * 标id
     */
    private Integer borrowId;

    /**
     * 基础利息
     */
    private BigDecimal apr;

    /**
     * 平台加息奖励
     */
    private BigDecimal platformApr;

    /**
     * 预计投标金额(用户输入的投标金额)
     */
    private BigDecimal preAccount;
    /**
     * 投标金额
     */
    private BigDecimal account;

    /**
     * 投标类型:0:普通投标,1债券投标,2:精选计划普通标投资3精选计划转让标投资
     */
    private Integer tenderType;

    /**
     * 投标方式0:手动投标1:自动投标
     */
    private Integer tenderMode;

    /**
     * 投标Ip
     */
    private String addIp;

    /**
     * 抵用券id
     */
    private Integer voucherId;

    /**
     * 抵用券金额或者利息的百分比
     */
    private BigDecimal voucherMoney;


    /**
     * 加息券ID
     */
    private Integer voucherCouponId;

    /**
     * 加息券收益
     */
    private BigDecimal voucherCouponMoney;


    /**
     * 投标渠道:0:pc,1:ios 2:android 3:h5
     */
    private Integer channel;

    /**
     * 订单Id
     */
    private String orderId;

    /**
     * 投资人手机号
     */
    private String mobile;

    private String remarks;

    /**
     * 奖励金额
     */
    private BigDecimal tenderTranAward;

    /**
     * 奖励订单号
     */
    private String tranAwardOrderid;

    /**
     * 是否可转让 0:不可 1:可
     */
    private Integer isTransferred;

    /**
     * 转让时间
     */
    private Date transferTime;

    /**
     * 自动投标Id
     */
    private Integer autoTenderId;

    /**
     * 精选计划投标ID
     */
    private Integer choicenessTenderId;

    public Integer getChoicenessTenderId() {
        return choicenessTenderId;
    }

    public void setChoicenessTenderId(Integer choicenessTenderId) {
        this.choicenessTenderId = choicenessTenderId;
    }

    public BigDecimal getTenderTranAward() {
        return tenderTranAward;
    }

    public void setTenderTranAward(BigDecimal tenderTranAward) {
        this.tenderTranAward = tenderTranAward;
    }

    public String getTranAwardOrderid() {
        return tranAwardOrderid;
    }

    public void setTranAwardOrderid(String tranAwardOrderid) {
        this.tranAwardOrderid = tranAwardOrderid;
    }

    public Integer getIsTransferred() {
        return isTransferred;
    }

    public void setIsTransferred(Integer isTransferred) {
        this.isTransferred = isTransferred;
    }

    public Date getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(Date transferTime) {
        this.transferTime = transferTime;
    }

    public Integer getAutoTenderId() {
        return autoTenderId;
    }

    public void setAutoTenderId(Integer autoTenderId) {
        this.autoTenderId = autoTenderId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
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

    public Integer getTenderType() {
        return tenderType;
    }

    public void setTenderType(Integer tenderType) {
        this.tenderType = tenderType;
    }

    public Integer getTenderMode() {
        return tenderMode;
    }

    public void setTenderMode(Integer tenderMode) {
        this.tenderMode = tenderMode;
    }

    public String getAddIp() {
        return addIp;
    }

    public void setAddIp(String addIp) {
        this.addIp = addIp;
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

    public Integer getVoucherCouponId() {
        return voucherCouponId;
    }

    public void setVoucherCouponId(Integer voucherCouponId) {
        this.voucherCouponId = voucherCouponId;
    }

    public BigDecimal getVoucherCouponMoney() {
        return voucherCouponMoney;
    }

    public void setVoucherCouponMoney(BigDecimal voucherCouponMoney) {
        this.voucherCouponMoney = voucherCouponMoney;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
