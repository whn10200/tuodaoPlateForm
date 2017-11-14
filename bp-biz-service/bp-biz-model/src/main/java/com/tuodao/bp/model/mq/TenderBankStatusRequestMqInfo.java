package com.tuodao.bp.model.mq;

import com.google.common.base.MoreObjects;
import com.tuodao.bp.model.depository.DepositoryPara;

import java.io.Serializable;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/10/19
 * @time: 15:20
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class TenderBankStatusRequestMqInfo extends DepositoryPara implements Serializable{

    private static final long serialVersionUID = -7485055097651141271L;

    /**
     * 要查询投标结果的订单号
     */
    private String orderNo;


    @Override
    public String getOrderNo() {
        return orderNo;
    }
    @Override
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("orderNo",orderNo)
                .add("isSuccess",super.isSuccess())
                .toString();
    }
}
