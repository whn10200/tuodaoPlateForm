package com.tuodao.bp.model.mq;

import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 用户账户中心-运营中心MQ对象(传入值为计算后的值 如：+1，-1)
 * @author: mif
 * @date: 2017/9/14
 * @time: 16:21
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class AccountOperatorMqInfo implements Serializable {

    private static final long serialVersionUID = -810404375225888758L;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 抵用券奖励 (分)
     */
    private BigDecimal voucherAmount;

    /**
     * 积分数量
     */
    private Integer scores;

    /**
     * 抵用劵数量
     */
    private Integer voucher;

    /**
     * 加息劵总数
     */
    private Integer pateCoupon;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getVoucherAmount() {
        return voucherAmount;
    }

    public void setVoucherAmount(BigDecimal voucherAmount) {
        this.voucherAmount = voucherAmount;
    }

    public Integer getScores() {
        return scores;
    }

    public void setScores(Integer scores) {
        this.scores = scores;
    }

    public Integer getVoucher() {
        return voucher;
    }

    public void setVoucher(Integer voucher) {
        this.voucher = voucher;
    }

    public Integer getPateCoupon() {
        return pateCoupon;
    }

    public void setPateCoupon(Integer pateCoupon) {
        this.pateCoupon = pateCoupon;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("userId", userId)
                .add("voucherAmount", voucherAmount)
                .add("scores", scores)
                .add("voucher", voucher)
                .add("pateCoupon", pateCoupon)
                .toString();
    }
}
