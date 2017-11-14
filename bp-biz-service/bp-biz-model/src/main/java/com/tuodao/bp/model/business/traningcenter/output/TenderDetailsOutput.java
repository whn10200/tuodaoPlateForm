package com.tuodao.bp.model.business.traningcenter.output;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description:
 * @author: wuzf
 * @date: 2017/9/20 0020.
 * @time: 17:04
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class TenderDetailsOutput implements Serializable {
    private static final long serialVersionUID = 7293427339912168255L;

    //产品id
    private Integer borrowId;

    //产品名称
    private String productName;

    //产品编号
    private String borrowNid;

    //产品表示（如新人标）
    private String borrowType;

    //还款方式
    private String repayType;

    //预计退出时间
    private String outTime;

    //加入金额
    private String preAccount;

    //总收益
    private String preAccountInterest;

    //奖励
    private String platformInterest;

    //加息劵id
    private Integer voucherCouponId;

    //抵用券id
    private Integer voucherId;

    //加入时间
    private String addTime;

    //冻结金额
    private String frostAccount;

    //已到账收益
    private String arrivalAccount;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBorrowNid() {
        return borrowNid;
    }

    public void setBorrowNid(String borrowNid) {
        this.borrowNid = borrowNid;
    }

    public String getBorrowType() {
        return borrowType;
    }

    public void setBorrowType(String borrowType) {
        this.borrowType = borrowType;
    }

    public String getRepayType() {
        return repayType;
    }

    public void setRepayType(String repayType) {
        this.repayType = repayType;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public Integer getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }

    public String getPreAccount() {
        return preAccount;
    }

    public void setPreAccount(String preAccount) {
        this.preAccount = preAccount;
    }

    public String getPreAccountInterest() {
        return preAccountInterest;
    }

    public void setPreAccountInterest(String preAccountInterest) {
        this.preAccountInterest = preAccountInterest;
    }

    public String getPlatformInterest() {
        return platformInterest;
    }

    public void setPlatformInterest(String platformInterest) {
        this.platformInterest = platformInterest;
    }

    public Integer getVoucherCouponId() {
        return voucherCouponId;
    }

    public void setVoucherCouponId(Integer voucherCouponId) {
        this.voucherCouponId = voucherCouponId;
    }

    public Integer getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Integer voucherId) {
        this.voucherId = voucherId;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getFrostAccount() {
        return frostAccount;
    }

    public void setFrostAccount(String frostAccount) {
        this.frostAccount = frostAccount;
    }

    public String getArrivalAccount() {
        return arrivalAccount;
    }

    public void setArrivalAccount(String arrivalAccount) {
        this.arrivalAccount = arrivalAccount;
    }
}
