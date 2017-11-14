package com.tuodao.bp.model;

import java.io.Serializable;

/**
 * Description:
 * User: zkai
 * Date: 2017/9/8
 * Time: 15:25
 * Copyright:拓道金服 Copyright (c) 2017
 */
public class AccessInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String accessId;
    private String accessKey;
    /**
     * 用户编号
     */
    private String userId;
    /**
     * 电话号码
     */
    private String mobile;

    public AccessInfo() {
    }

    public AccessInfo(String accessId, String accessKey, String userId, String mobile) {
        this.accessId = accessId;
        this.accessKey = accessKey;
        this.userId = userId;
        this.mobile = mobile;
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

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
}
