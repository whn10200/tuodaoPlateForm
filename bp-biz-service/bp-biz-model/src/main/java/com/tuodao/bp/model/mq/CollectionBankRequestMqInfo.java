package com.tuodao.bp.model.mq;

import com.tuodao.bp.model.depository.DepositoryPara;
import com.tuodao.bp.model.traningcenter.input.Repayment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/10/19
 * @time: 15:58
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class CollectionBankRequestMqInfo extends DepositoryPara implements Serializable {

    /**
     * 回款列表
     */
    private List<Repayment> data = new ArrayList<>();

    /**
     *  产品id
     */
    private String borrowId;

    /**
     * 要还期数
     */
    private String period;

    /**
     * 是否还清
     */
    private String isPayoff;

    /**
     * 订单号
     */
    private String orderNo;



    public List<Repayment> getData() {
        return data;
    }

    public void setData(List<Repayment> data) {
        this.data = data;
    }

    public String getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(String borrowId) {
        this.borrowId = borrowId;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getIsPayoff() {
        return isPayoff;
    }

    public void setIsPayoff(String isPayoff) {
        this.isPayoff = isPayoff;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }


}
