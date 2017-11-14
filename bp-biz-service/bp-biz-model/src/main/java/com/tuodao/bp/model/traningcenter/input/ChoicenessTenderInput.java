package com.tuodao.bp.model.traningcenter.input;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @description:理财计划投资相关类
 * @author: wuzf
 * @date: 2017/9/8 0008.
 * @time: 14:03
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class ChoicenessTenderInput implements Serializable {

    private static final long serialVersionUID = 4787393864909168428L;

    private BigDecimal preAccount;

    private String userId;

    private BigInteger borrowId;

    private BigInteger channel;

    private BigInteger voucherId;

    private BigInteger voucherCouponId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public BigDecimal getPreAccount() {
        return preAccount;
    }

    public void setPreAccount(BigDecimal preAccount) {
        this.preAccount = preAccount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigInteger getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(BigInteger borrowId) {
        this.borrowId = borrowId;
    }

    public BigInteger getChannel() {
        return channel;
    }

    public void setChannel(BigInteger channel) {
        this.channel = channel;
    }

    public BigInteger getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(BigInteger voucherId) {
        this.voucherId = voucherId;
    }

    public BigInteger getVoucherCouponId() {
        return voucherCouponId;
    }

    public void setVoucherCouponId(BigInteger voucherCouponId) {
        this.voucherCouponId = voucherCouponId;
    }
}
