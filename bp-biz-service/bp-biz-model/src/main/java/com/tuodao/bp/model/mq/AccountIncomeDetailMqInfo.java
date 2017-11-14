package com.tuodao.bp.model.mq;

import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Description: 账户累计收益详细MQ对象
 * User: zkai
 * Date: 2017/9/14
 * Time: 16:08
 * Copyright:拓道金服 Copyright (c) 2017
 */
public class AccountIncomeDetailMqInfo implements Serializable {
    private static final long serialVersionUID = -6239144350114106052L;

    /**
     * 用户编码
     */
    private String userId;

    /**
     * 明细类型（INVESTMENT：投资收益(分)；COUPON：优惠劵（分）；REWARD：平台奖励（分）；INVITE ：邀请奖励（分））
     */
    private String incomeType;

    /**
     * 明细金额（分）
     */
    private BigDecimal incomeMoney;

    /**
     * 备注
     */
    private String remark;

    /**
     * 操作人
     */
    private String operator;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }

    public BigDecimal getIncomeMoney() {
        return incomeMoney;
    }

    public void setIncomeMoney(BigDecimal incomeMoney) {
        this.incomeMoney = incomeMoney;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("userId", userId)
                .add("incomeType", incomeType)
                .add("incomeMoney", incomeMoney)
                .add("remark", remark)
                .add("operator", operator)
                .toString();
    }
}
