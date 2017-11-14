package com.tuodao.bp.model.business.traningcenter.input;

import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.model.business.useraccount.output.UserDepositOutput;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 标的复审参数
 * @author: 王艳兵
 * @date: 2017/10/17
 * @time: 09:22
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class BorrowRecheckInput  implements Serializable{

    private static final long serialVersionUID = 9134771127725403654L;

    /**
     * 标的id
     */
    private int id;

    /**
     * 标的募集总额
     */
    private BigDecimal borrowAmount;

    /**
     * 借款用途
     */
    private String borrowUse;

    /**
     * 产品编号
     */
    private String productCode;
    /**
     * 借款人iD
     */
    private String userId;

    /**
     * 积分倍数
     */
    private int integralFold;

    /**
     * 期限单位 0:天 1月 2年
     */
    private int periodUnit;

    /**
     * 是否可转让0:否 1:是
     */
    private int isTransferred;


    /**
     * 标的期限
     */
    private int productPeriod;

    /**
     * 计息方式
     */
    private int refundWay;

    /**
     * 借款到期日期
     */
    private Date repayLastTime;
    /**
     * 可转让时间
     */
    private Date transferTime;

    /**
     * 借款人信息
     */
    private UserAccountInfo user;

    /**
     * 借款人存管信息
     */
    private UserDepositOutput userDeposit;

    /**
     * 产品标题
     */
    private String productTitle;

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getBorrowUse() {
        return borrowUse;
    }

    public void setBorrowUse(String borrowUse) {
        this.borrowUse = borrowUse;
    }

    public BigDecimal getBorrowAmount() {
        return borrowAmount;
    }

    public void setBorrowAmount(BigDecimal borrowAmount) {
        this.borrowAmount = borrowAmount;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Date getRepayLastTime() {
        return repayLastTime;
    }

    public void setRepayLastTime(Date repayLastTime) {
        this.repayLastTime = repayLastTime;
    }

    public UserAccountInfo getUser() {
        return user;
    }

    public void setUser(UserAccountInfo user) {
        this.user = user;
    }

    public UserDepositOutput getUserDeposit() {
        return userDeposit;
    }

    public void setUserDeposit(UserDepositOutput userDeposit) {
        this.userDeposit = userDeposit;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(Date transferTime) {
        this.transferTime = transferTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIntegralFold() {
        return integralFold;
    }

    public void setIntegralFold(int integralFold) {
        this.integralFold = integralFold;
    }

    public int getPeriodUnit() {
        return periodUnit;
    }

    public void setPeriodUnit(int periodUnit) {
        this.periodUnit = periodUnit;
    }

    public int getIsTransferred() {
        return isTransferred;
    }

    public void setIsTransferred(int isTransferred) {
        this.isTransferred = isTransferred;
    }


    public int getProductPeriod() {
        return productPeriod;
    }

    public void setProductPeriod(int productPeriod) {
        this.productPeriod = productPeriod;
    }

    public int getRefundWay() {
        return refundWay;
    }

    public void setRefundWay(int refundWay) {
        this.refundWay = refundWay;
    }
}
