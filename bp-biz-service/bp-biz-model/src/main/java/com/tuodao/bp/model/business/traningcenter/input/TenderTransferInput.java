package com.tuodao.bp.model.business.traningcenter.input;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description:
 * @author: wuzf
 * @date: 2017/9/14 0014.
 * @time: 17:45
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class TenderTransferInput  implements Serializable{

    private static final long serialVersionUID = 6152097435935591117L;

    //基本利率
    private BigDecimal apr;

    //平台奖励
    private BigDecimal awardScale;

    //加息劵
    private BigDecimal vouchApr;

    //剩余期限
    private int leftPeriod;

    //开始期数
    private int startPeriod;

    //上期还款时间
    private long repayTime;


    //还款方式
    private Integer raymentType;

    //投资金额
    private BigDecimal account;

    //用户userId
    private String userId;

    //产品名称
    private String borrowName;

    //订单号
    private String orderId;

    //理财计划满标复审时间
    private long leftPeriodTime;

//    //投标id
    private Integer tenderId;


    //属于哪一个理财计划
    private Integer productId;

    //理财计划到期时间
    private Date repayLastTime;

    public Date getRepayLastTime() {
        return repayLastTime;
    }

    public void setRepayLastTime(Date repayLastTime) {
        this.repayLastTime = repayLastTime;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public long getLeftPeriodTime() {
        return leftPeriodTime;
    }

    public void setLeftPeriodTime(long leftPeriodTime) {
        this.leftPeriodTime = leftPeriodTime;
    }

    public Integer getRaymentType() {
        return raymentType;
    }

    public void setRaymentType(Integer raymentType) {
        this.raymentType = raymentType;
    }



    public BigDecimal getApr() {
        return apr;
    }

    public void setApr(BigDecimal apr) {
        this.apr = apr;
    }

    public BigDecimal getAwardScale() {
        return awardScale;
    }

    public void setAwardScale(BigDecimal awardScale) {
        this.awardScale = awardScale;
    }

    public BigDecimal getVouchApr() {
        return vouchApr;
    }

    public void setVouchApr(BigDecimal vouchApr) {
        this.vouchApr = vouchApr;
    }


    public int getLeftPeriod() {
        return leftPeriod;
    }

    public void setLeftPeriod(int leftPeriod) {
        this.leftPeriod = leftPeriod;
    }

    public int getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(int startPeriod) {
        this.startPeriod = startPeriod;
    }

    public long getRepayTime() {
        return repayTime;
    }

    public void setRepayTime(long repayTime) {
        this.repayTime = repayTime;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBorrowName() {
        return borrowName;
    }

    public void setBorrowName(String borrowName) {
        this.borrowName = borrowName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getTenderId() {
        return tenderId;
    }

    public void setTenderId(Integer tenderId) {
        this.tenderId = tenderId;
    }
}
