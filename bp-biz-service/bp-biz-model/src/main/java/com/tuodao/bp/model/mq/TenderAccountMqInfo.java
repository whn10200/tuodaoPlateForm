package com.tuodao.bp.model.mq;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 投标相关成功异步返回其他服务对象
 * @author: 王艳兵
 * @date: 2017/11/1
 * @time: 13:59
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class TenderAccountMqInfo implements Serializable{

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 类型 0:投标 1:回款
     */
    private int type;

    /**
     * 首投金额
     */
    private BigDecimal firstTenderAccount;
    /**
     * 是否首投(true 是 false 否)
     */
    private Boolean isFirstTender = false;

    /**
     *需要返现的收益
     * @return
     */
    private BigDecimal awardInterest;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public BigDecimal getFirstTenderAccount() {
        return firstTenderAccount;
    }

    public void setFirstTenderAccount(BigDecimal firstTenderAccount) {
        this.firstTenderAccount = firstTenderAccount;
    }

    public Boolean getIsFirstTender() {
        return isFirstTender;
    }

    public void setIsFirstTender(Boolean isFirstTender) {
        this.isFirstTender = isFirstTender;
    }

    public BigDecimal getAwardInterest() {
        return awardInterest;
    }

    public void setAwardInterest(BigDecimal awardInterest) {
        this.awardInterest = awardInterest;
    }

    @Override
    public String toString() {
        return "TenderAccountMqInfo{" +
                "userId='" + userId + '\'' +
                ", type=" + type +
                ", firstTenderAccount=" + firstTenderAccount +
                ", isFirstTender=" + isFirstTender +
                ", awardInterest=" + awardInterest +
                '}';
    }
}
