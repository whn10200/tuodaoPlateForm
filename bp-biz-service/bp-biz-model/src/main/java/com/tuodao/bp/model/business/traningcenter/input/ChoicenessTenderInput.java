package com.tuodao.bp.model.business.traningcenter.input;


import com.tuodao.bp.model.constant.traningcenter.TraningCenterExceptionConstant;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @description:加入理财计划相关类
 * @author: wuzf
 * @date: 2017/9/8 0008.
 * @time: 14:03
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class ChoicenessTenderInput implements Serializable {

    private static final long serialVersionUID = 4787393864909168428L;

    //预计投资金额
    @NotNull(message = TraningCenterExceptionConstant.PRE_ACCOUNT_IS_NULL+"")
    private BigDecimal preAccount;

    //用户id
    @NotNull(message = TraningCenterExceptionConstant.USER_ID_IS_NULL+"")
    private String userId;

    //产品编号
    @NotNull(message = TraningCenterExceptionConstant.BORROW_ID_IS_NULL+"")
    private Integer borrowId;

    //渠道
    @NotNull(message = TraningCenterExceptionConstant.CHANNEL_IS_NULL+"")
    private Integer channel;

    //加息卷id
    private Integer voucherId;

    //抵用卷id
    private Integer voucherCouponId;

    //抵扣卷金额
    private String dkamount;

    //总利率
    @NotNull(message = TraningCenterExceptionConstant.ALL_APR_IS_NULL+"")
    private BigDecimal allApr;

    //加息利率
    @NotNull(message = TraningCenterExceptionConstant.VOUCHER_APR_IS_NULL+"")
    private BigDecimal voucherApr;

    //奖励利率
    @NotNull(message = TraningCenterExceptionConstant.AWARD_APR_IS_NULL+"")
    private BigDecimal awardApr;


    //0天标1月标
    @NotNull(message = TraningCenterExceptionConstant.BORROW_TYPE_IS_NULL+"")
    private Integer borrowType;

    //回款方式（按月付息0等额本息1按天付息2）
    @NotNull(message = TraningCenterExceptionConstant.RECOVER_TYPE_IS_NULL+"")
    private Integer recoverType;

    //期限
    @NotNull(message = TraningCenterExceptionConstant.PERIOD_IS_NULL+"")
    private Integer period;


    //手机
    private String mobile;

    //缓存标识
    private String key;

    //ip
    private String ip;


    /**
     * 金额限制(如:5000元以上的标的)
     */
    private BigDecimal moneyLimit;


    public BigDecimal getMoneyLimit() {
        return moneyLimit;
    }

    public String getDkamount() {
        return dkamount;
    }

    public void setDkamount(String dkamount) {
        this.dkamount = dkamount;
    }

    public void setMoneyLimit(BigDecimal moneyLimit) {
        this.moneyLimit = moneyLimit;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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

    public Integer getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
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

    public Integer getBorrowType() {
        return borrowType;
    }

    public void setBorrowType(Integer borrowType) {
        this.borrowType = borrowType;
    }

    public Integer getRecoverType() {
        return recoverType;
    }

    public void setRecoverType(Integer recoverType) {
        this.recoverType = recoverType;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public BigDecimal getAllApr() {
        return allApr;
    }

    public void setAllApr(BigDecimal allApr) {
        this.allApr = allApr;
    }

    public BigDecimal getVoucherApr() {
        return voucherApr;
    }

    public void setVoucherApr(BigDecimal voucherApr) {
        this.voucherApr = voucherApr;
    }

    public BigDecimal getAwardApr() {
        return awardApr;
    }

    public void setAwardApr(BigDecimal awardApr) {
        this.awardApr = awardApr;
    }
}
