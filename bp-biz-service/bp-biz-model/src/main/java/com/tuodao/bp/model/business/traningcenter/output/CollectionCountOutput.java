package com.tuodao.bp.model.business.traningcenter.output;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 用户已获取的回款收益
 * @author: 王艳兵
 * @date: 2017/11/7
 * @time: 15:25
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class CollectionCountOutput implements Serializable {

    private static final long serialVersionUID = -505152030699127135L;
    /**
     * 投标收益
     */
    private BigDecimal tenderInterest;

    /**
     * 平台加息收益
     */
    private BigDecimal platformInterest;

    /**
     * 加息券回款 收益
     */
    private BigDecimal couponInterest;

    public BigDecimal getTenderInterest() {
        return tenderInterest;
    }

    public void setTenderInterest(BigDecimal tenderInterest) {
        this.tenderInterest = tenderInterest;
    }

    public BigDecimal getPlatformInterest() {
        return platformInterest;
    }

    public void setPlatformInterest(BigDecimal platformInterest) {
        this.platformInterest = platformInterest;
    }

    public BigDecimal getCouponInterest() {
        return couponInterest;
    }

    public void setCouponInterest(BigDecimal couponInterest) {
        this.couponInterest = couponInterest;
    }



    @Override
    public String toString() {
        return "CollectionCountOutput{" +
                "tenderInterest=" + tenderInterest +
                ", platformInterest=" + platformInterest +
                ", couponInterest=" + couponInterest +
                '}';
    }
}
