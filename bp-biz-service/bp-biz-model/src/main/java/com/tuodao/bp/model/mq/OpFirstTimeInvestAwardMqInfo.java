package com.tuodao.bp.model.mq;

import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 用户首投-邀请奖励信息
 * @author: mif
 * @date: 2017/9/26
 * @time: 09:31
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class OpFirstTimeInvestAwardMqInfo implements Serializable {

    private static final long serialVersionUID = 3222577478420828269L;
    /**
     * 用户编号
     */
    private String userId;

    /**
     * 用户手机号码
     */
    private String mobile;

    /**
     * 注册时间
     */
    private Date registerTime;

    /**
     * 直接邀请人用户编号
     */
    private String directInviterNo;
    /**
     * 直接邀请人号码
     */
    private String directInviterMobile;

    /**
     * 间接邀请人用户编号
     */
    private String indirectInviterNo;
    /**
     * 间接邀请人号码
     */
    private String indirectInviterMobile;

    /**
     * 奖励金鹅
     */
    private BigDecimal awardMoney;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getDirectInviterNo() {
        return directInviterNo;
    }

    public void setDirectInviterNo(String directInviterNo) {
        this.directInviterNo = directInviterNo;
    }

    public String getDirectInviterMobile() {
        return directInviterMobile;
    }

    public void setDirectInviterMobile(String directInviterMobile) {
        this.directInviterMobile = directInviterMobile;
    }

    public String getIndirectInviterNo() {
        return indirectInviterNo;
    }

    public void setIndirectInviterNo(String indirectInviterNo) {
        this.indirectInviterNo = indirectInviterNo;
    }

    public String getIndirectInviterMobile() {
        return indirectInviterMobile;
    }

    public void setIndirectInviterMobile(String indirectInviterMobile) {
        this.indirectInviterMobile = indirectInviterMobile;
    }

    public BigDecimal getAwardMoney() {
        return awardMoney;
    }

    public void setAwardMoney(BigDecimal awardMoney) {
        this.awardMoney = awardMoney;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("userId", userId)
                .add("mobile", mobile)
                .add("registerTime", registerTime)
                .add("directInviterNo", directInviterNo)
                .add("directInviterMobile", directInviterMobile)
                .add("indirectInviterNo", indirectInviterNo)
                .add("indirectInviterMobile", indirectInviterMobile)
                .add("awardMoney", awardMoney)
                .toString();
    }
}
