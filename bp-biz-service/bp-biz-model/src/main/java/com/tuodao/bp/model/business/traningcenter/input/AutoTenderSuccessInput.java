package com.tuodao.bp.model.business.traningcenter.input;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 自动投标 投标成功 入参
 * @author: 王艳兵
 * @date: 2017/9/29
 * @time: 10:39
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class AutoTenderSuccessInput implements Serializable{


    private static final long serialVersionUID = -3286938926783854081L;

    /**
     * 投标金额
     */
    private BigDecimal tenderMoney;

    /**
     * 自动投标Id
     */
    private Integer autoTenderId;

    /**
     * 券类型 0:不使用 1:使用抵用券2:使用加息券
     */
    private Integer voucherType;

    /**
     * 券面值
     */
    private BigDecimal voucherMoney;

    /**
     * 用户ID
     */
    private String userId;

    public BigDecimal getTenderMoney() {
        return tenderMoney;
    }

    public void setTenderMoney(BigDecimal tenderMoney) {
        this.tenderMoney = tenderMoney;
    }

    public Integer getAutoTenderId() {
        return autoTenderId;
    }

    public void setAutoTenderId(Integer autoTenderId) {
        this.autoTenderId = autoTenderId;
    }

    public Integer getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(Integer voucherType) {
        this.voucherType = voucherType;
    }

    public BigDecimal getVoucherMoney() {
        return voucherMoney;
    }

    public void setVoucherMoney(BigDecimal voucherMoney) {
        this.voucherMoney = voucherMoney;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
