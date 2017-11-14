package com.tuodao.bp.model.mq;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * @description: 优惠券发放队列实体
 * @author: mif
 * @date: 2017/9/22
 * @time: 09:41
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class CouponGrantMqInfo implements Serializable {
    private static final long serialVersionUID = -9132357234560317438L;

    private String userId;

    private String mobile;

    /**
     * 活动福利CODE
     * REGISTER:注册大礼包；V1_BIRTHDAY：V1生日礼包；V2_BIRTHDAY：V2生日礼包；V3_BIRTHDAY；V3生日礼包；V4_BIRTHDAY：V4生日礼包；
     * V5_BIRTHDAY：V5生日礼包；V6_BIRTHDAY：V6生日礼包；
     * V1_UP：V1升级大礼包；V2_UP；V2升级大礼包；V3_UP：V3升级大礼包；V4_UP：V4升级大礼包
     * V5_UP：V5升级大礼包；V6_UP：V6升级大礼包；V0_GIFT：V0福利
     * FRIEND_FIRST_INVEST:邀请好友首投奖励
     */
    private String welfareActivityCode;

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

    public String getWelfareActivityCode() {
        return welfareActivityCode;
    }

    public void setWelfareActivityCode(String welfareActivityCode) {
        this.welfareActivityCode = welfareActivityCode;
    }

    public CouponGrantMqInfo() {
    }

    public CouponGrantMqInfo(String userId, String mobile, String welfareActivityCode) {
        this.userId = userId;
        this.mobile = mobile;
        this.welfareActivityCode = welfareActivityCode;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("userId", userId)
                .add("mobile", mobile)
                .add("welfareActivityCode", welfareActivityCode)
                .toString();
    }
}
