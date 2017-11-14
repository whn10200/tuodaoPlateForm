package com.tuodao.bp.model.business.useraccount.input;

import java.io.Serializable;

/**
 * @description: 用户信息接口入参
 * @author: 王艳兵
 * @date: 2017/9/12
 * @time: 18:27
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class UserInput implements Serializable {

    /**
     * 用户id
     */
    private String userId;

    public UserInput() {
    }

    public UserInput(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
