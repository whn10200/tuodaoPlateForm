package com.tuodao.bp.model.mq;

import java.io.Serializable;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/10/27
 * @time: 09:32
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class AccountMqInfo implements Serializable {


    private static final long serialVersionUID = 1966059915239046008L;

    /**
     * 用户ID
     */
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
