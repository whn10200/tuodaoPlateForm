package com.tuodao.bp.model.traningcenter.input;

import java.io.Serializable;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/9/22
 * @time: 10:21
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class AccountCashStatusInput implements Serializable{

    private String orderNo;

    private int orderStatus;

    public AccountCashStatusInput() {
    }

    public AccountCashStatusInput(String orderNo, int orderStatus) {
        this.orderNo = orderNo;
        this.orderStatus = orderStatus;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }
}
