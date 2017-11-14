package com.tuodao.bp.operation.persistence.model.basic;

import java.io.Serializable;
import java.math.BigDecimal;

public class OpUserOperationData implements Serializable {
    private String userId;

    private String userMobile;

    private BigDecimal voucherAmount;

    private Integer usableScores;

    private Integer usableVoucher;

    private Integer usablePateCoupon;

    private Integer usableGold;

    private Integer continuousSignTimes;

    private Integer cumulativeSignTimes;

    private Integer freeGetNumber;

    private Integer freeGetNumberPerpetual;

    private Integer freeGetNumberExpire;

    private static final long serialVersionUID = 1L;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile == null ? null : userMobile.trim();
    }

    public BigDecimal getVoucherAmount() {
        return voucherAmount;
    }

    public void setVoucherAmount(BigDecimal voucherAmount) {
        this.voucherAmount = voucherAmount;
    }

    public Integer getUsableScores() {
        return usableScores;
    }

    public void setUsableScores(Integer usableScores) {
        this.usableScores = usableScores;
    }

    public Integer getUsableVoucher() {
        return usableVoucher;
    }

    public void setUsableVoucher(Integer usableVoucher) {
        this.usableVoucher = usableVoucher;
    }

    public Integer getUsablePateCoupon() {
        return usablePateCoupon;
    }

    public void setUsablePateCoupon(Integer usablePateCoupon) {
        this.usablePateCoupon = usablePateCoupon;
    }

    public Integer getUsableGold() {
        return usableGold;
    }

    public void setUsableGold(Integer usableGold) {
        this.usableGold = usableGold;
    }

    public Integer getContinuousSignTimes() {
        return continuousSignTimes;
    }

    public void setContinuousSignTimes(Integer continuousSignTimes) {
        this.continuousSignTimes = continuousSignTimes;
    }

    public Integer getCumulativeSignTimes() {
        return cumulativeSignTimes;
    }

    public void setCumulativeSignTimes(Integer cumulativeSignTimes) {
        this.cumulativeSignTimes = cumulativeSignTimes;
    }

    public Integer getFreeGetNumber() {
        return freeGetNumber;
    }

    public void setFreeGetNumber(Integer freeGetNumber) {
        this.freeGetNumber = freeGetNumber;
    }

    public Integer getFreeGetNumberPerpetual() {
        return freeGetNumberPerpetual;
    }

    public void setFreeGetNumberPerpetual(Integer freeGetNumberPerpetual) {
        this.freeGetNumberPerpetual = freeGetNumberPerpetual;
    }

    public Integer getFreeGetNumberExpire() {
        return freeGetNumberExpire;
    }

    public void setFreeGetNumberExpire(Integer freeGetNumberExpire) {
        this.freeGetNumberExpire = freeGetNumberExpire;
    }
}