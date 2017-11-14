package com.tuodao.bp.model.mq;

import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 运营中心消费-投资回款理财师邀请奖励
 * @author: mif
 * @date: 2017/9/26
 * @time: 10:09
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class OpPaybackInviteAwardMqInfo implements Serializable {
    private static final long serialVersionUID = -122926341496685468L;
    /**
     * 用户编号
     */
    private String userId;

    /**
     * 用户号码
     */
    private String mobile;

    /**
     * 注册时间
     */
    private Date registerTime;

    /**
     * 直接被邀请人号码
     */
    private String directInviteeMobile;
    /**
     * 间接被邀请人号码
     */
    private String indirectInviteeMobile;

    /**
     * 奖励金额（分）
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

    public String getDirectInviteeMobile() {
        return directInviteeMobile;
    }

    public void setDirectInviteeMobile(String directInviteeMobile) {
        this.directInviteeMobile = directInviteeMobile;
    }

    public String getIndirectInviteeMobile() {
        return indirectInviteeMobile;
    }

    public void setIndirectInviteeMobile(String indirectInviteeMobile) {
        this.indirectInviteeMobile = indirectInviteeMobile;
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
                .add("directInviteeMobile", directInviteeMobile)
                .add("indirectInviteeMobile", indirectInviteeMobile)
                .add("awardMoney", awardMoney)
                .toString();
    }
}
