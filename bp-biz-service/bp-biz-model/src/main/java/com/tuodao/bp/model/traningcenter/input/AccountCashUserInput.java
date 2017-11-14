package com.tuodao.bp.model.traningcenter.input;

import java.io.Serializable;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/9/22
 * @time: 10:06
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class AccountCashUserInput implements Serializable{
    /**
     * 用户Id
     */
    private String userId;

    public AccountCashUserInput() {
    }

    public AccountCashUserInput(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
