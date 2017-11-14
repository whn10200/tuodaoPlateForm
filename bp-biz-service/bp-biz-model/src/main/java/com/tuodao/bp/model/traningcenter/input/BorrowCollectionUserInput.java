package com.tuodao.bp.model.traningcenter.input;

import java.io.Serializable;

/**
 * @description: 查询用户回款总额使用
 * @author: 王艳兵
 * @date: 2017/9/22
 * @time: 10:04
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class BorrowCollectionUserInput implements Serializable{

    /**
     * 用户ID
     */
    private String userId;

    public BorrowCollectionUserInput(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
