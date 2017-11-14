package com.tuodao.bp.model.mq;

import com.tuodao.bp.model.depository.DepositoryPara;

import java.io.Serializable;

/**
 * @description: 针对超募的进行投标撤回操作 银行撤回
 * @author: 王艳兵
 * @date: 2017/10/23
 * @time: 10:23
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class TenderBankCancelRequestMqInfo extends DepositoryPara implements Serializable {

    /**
     * 旧订单号
     */
    private String orderNo;

    /**
     * 产品编号
     */
    private String borrowCode;


    /**
     * 用户银行唯一编号
     */
    private String userCode;

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getBorrowCode() {
        return borrowCode;
    }

    public void setBorrowCode(String borrowCode) {
        this.borrowCode = borrowCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
