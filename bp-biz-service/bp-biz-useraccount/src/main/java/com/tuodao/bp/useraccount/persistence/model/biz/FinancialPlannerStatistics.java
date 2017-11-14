package com.tuodao.bp.useraccount.persistence.model.biz;

import com.google.common.base.MoreObjects;

import java.math.BigDecimal;

/**
 * @description: 用户理财师等级统计信息
 * @author: mif
 * @date: 2017/9/19
 * @time: 10:31
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class FinancialPlannerStatistics {

    /**
     * 达到VIP1等级人数
     */
    private int vip1total;

    /**
     * 总待收金额（分）
     */
    private BigDecimal dueInFundTotal;

    public int getVip1total() {
        return vip1total;
    }

    public void setVip1total(int vip1total) {
        this.vip1total = vip1total;
    }

    public BigDecimal getDueInFundTotal() {
        return dueInFundTotal;
    }

    public void setDueInFundTotal(BigDecimal dueInFundTotal) {
        this.dueInFundTotal = dueInFundTotal;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("vip1total", vip1total)
                .add("dueInFundTotal", dueInFundTotal)
                .toString();
    }
}
