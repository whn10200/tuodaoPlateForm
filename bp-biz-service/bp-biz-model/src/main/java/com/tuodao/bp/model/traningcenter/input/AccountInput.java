package com.tuodao.bp.model.traningcenter.input;

import java.io.Serializable;

/**
 * @description: 用户资金账户入参
 * @author: 王艳兵
 * @date: 2017/9/12
 * @time: 17:45
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class AccountInput implements Serializable {

    private String userId;

    public AccountInput(){
    }

    public AccountInput(String userId){
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
