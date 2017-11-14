package com.tuodao.bp.model.business.traningcenter.output;

import java.io.Serializable;

/**
 * @description:我的投资（精选计划投资详情债权明细）
 * @author: wuzf
 * @date: 2017/9/21 0021.
 * @time: 11:24
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class UnderTenderOutput implements Serializable{
    private static final long serialVersionUID = -6700453414312420362L;

    //产品名称
    private String productName;

    //产品编号
    private String borrowNid;

    //投资时间
    private String addTime;

    //投资金额
    private String account;

    //状态
    private String status;

    //是否展示有安存链接(0表示不展示1表示展示)
    private Integer showAncun;

    //安存保全号
    private String  investNum;


    private String borrowId;

    public String getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(String borrowId) {
        this.borrowId = borrowId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBorrowNid() {
        return borrowNid;
    }

    public void setBorrowNid(String borrowNid) {
        this.borrowNid = borrowNid;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getShowAncun() {
        return showAncun;
    }

    public void setShowAncun(Integer showAncun) {
        this.showAncun = showAncun;
    }

    public String getInvestNum() {
        return investNum;
    }

    public void setInvestNum(String investNum) {
        this.investNum = investNum;
    }
}
