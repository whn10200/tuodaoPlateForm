package com.tuodao.bp.model.mq;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * @description: 用户VIP升级MQ信息
 * @author: mif
 * @date: 2017/9/28
 * @time: 17:51
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class OpVipUpMqInfo implements Serializable {
    private static final long serialVersionUID = 2228075155774297593L;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 用户手机号码
     */
    private String mobile;
    /**
     * 当前VIP等级
     */
    private int vipLevel;

    /**
     * 最大VIP等级
     */
    private int maxVipLevel;

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

    public int getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(int vipLevel) {
        this.vipLevel = vipLevel;
    }

    public int getMaxVipLevel() {
        return maxVipLevel;
    }

    public void setMaxVipLevel(int maxVipLevel) {
        this.maxVipLevel = maxVipLevel;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("userId", userId)
                .add("mobile", mobile)
                .add("vipLevel", vipLevel)
                .add("maxVipLevel", maxVipLevel)
                .toString();
    }
}
