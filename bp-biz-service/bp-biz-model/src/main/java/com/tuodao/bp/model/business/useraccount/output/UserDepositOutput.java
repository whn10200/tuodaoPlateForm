package com.tuodao.bp.model.business.useraccount.output;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * @description: 用户存管信息
 * @author: mif
 * @date: 2017/10/18
 * @time: 14:10
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class UserDepositOutput implements Serializable {
    private static final long serialVersionUID = -5556567726648691757L;

    private String userId;
    /**
     * 存管编号
     */
    private String depositNo;

    private String realName;

    private String idCard;

    private String bankCode;
    /**
     * 银行民称
     */
    private String bankName;

    private String bankNum;

    private String reservationMobile;

    /**
     * 是否已开通存管账户（0:未开通；1:已开通;2:开通中;3开通失败）
     */
    private Integer isOpenDeposit;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDepositNo() {
        return depositNo;
    }

    public void setDepositNo(String depositNo) {
        this.depositNo = depositNo;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankNum() {
        return bankNum;
    }

    public void setBankNum(String bankNum) {
        this.bankNum = bankNum;
    }

    public String getReservationMobile() {
        return reservationMobile;
    }

    public void setReservationMobile(String reservationMobile) {
        this.reservationMobile = reservationMobile;
    }

    public Integer getIsOpenDeposit() {
        return isOpenDeposit;
    }

    public void setIsOpenDeposit(Integer isOpenDeposit) {
        this.isOpenDeposit = isOpenDeposit;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("userId", userId)
                .add("realName", realName)
                .add("idCard", idCard)
                .add("bankCode", bankCode)
                .add("bankName", bankName)
                .add("bankNum", bankNum)
                .add("reservationMobile", reservationMobile)
                .add("isOpenDeposit", isOpenDeposit)
                .toString();
    }
}
