package com.tuodao.bp.model.business.useraccount;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 用户存管帐号
 * @author: mif
 * @date: 2017/10/25
 * @time: 10:26
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Data
public class UserDepositNo implements Serializable {
    private static final long serialVersionUID = -5953333054650329421L;

    /**
     * 用户编码
     */
    private String userId;

    /**
     * 存管编号
     */
    private String depositNo;

    public UserDepositNo() {
    }

    public UserDepositNo(String userId, String depositNo) {
        this.userId = userId;
        this.depositNo = depositNo;
    }

}
