package com.tuodao.bp.model.business.useraccount.output;

import java.io.Serializable;

/**
 * @description: 登录返回类
 * @author: mif
 * @date: 2017/9/8
 * @time: 16:29
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class UserLoginOutput extends UserAccountInfoOutput implements Serializable {
    private static final long serialVersionUID = -1366875466296356014L;

    private String accessId;

    private String accessKey;

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
}
