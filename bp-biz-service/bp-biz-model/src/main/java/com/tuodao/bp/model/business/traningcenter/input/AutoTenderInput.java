package com.tuodao.bp.model.business.traningcenter.input;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 自动投标入参
 * @author: 王艳兵
 * @date: 2017/9/26
 * @time: 14:23
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class AutoTenderInput implements Serializable {

    /**
     * 用户ID
     */
    private String userId;
    /**
     * 最小投标金额
     */
    private BigDecimal minAccount;

    /**
     * 最大投标金额
     */
    private BigDecimal maxAccount;

    /**
     * 最小期限
     */
    private Integer minPeriod;

    /**
     * 最大期限
     */
    private Integer maxPeriod;

    /**
     * 是否使用加息券
     */
    private Integer useCoupon;



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getMinAccount() {
        return minAccount;
    }

    public void setMinAccount(BigDecimal minAccount) {
        this.minAccount = minAccount;
    }

    public BigDecimal getMaxAccount() {
        return maxAccount;
    }

    public void setMaxAccount(BigDecimal maxAccount) {
        this.maxAccount = maxAccount;
    }

    public Integer getMinPeriod() {
        return minPeriod;
    }

    public void setMinPeriod(Integer minPeriod) {
        this.minPeriod = minPeriod;
    }

    public Integer getMaxPeriod() {
        return maxPeriod;
    }

    public void setMaxPeriod(Integer maxPeriod) {
        this.maxPeriod = maxPeriod;
    }

    public Integer getUseCoupon() {
        return useCoupon;
    }

    public void setUseCoupon(Integer useCoupon) {
        this.useCoupon = useCoupon;
    }
}
