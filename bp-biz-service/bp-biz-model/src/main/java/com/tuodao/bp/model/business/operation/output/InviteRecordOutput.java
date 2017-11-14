package com.tuodao.bp.model.business.operation.output;

import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 邀请记录
 * @author: mif
 * @date: 2017/10/9
 * @time: 18:03
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class InviteRecordOutput implements Serializable {


    private String friendPhoneNum;

    private Double vouchers;

    private BigDecimal directCashback;

    private BigDecimal indirectCashback;

    public String getFriendPhoneNum() {
        return friendPhoneNum;
    }

    public void setFriendPhoneNum(String friendPhoneNum) {
        this.friendPhoneNum = friendPhoneNum;
    }

    public Double getVouchers() {
        return vouchers;
    }

    public void setVouchers(Double vouchers) {
        this.vouchers = vouchers;
    }

    public BigDecimal getDirectCashback() {
        return directCashback;
    }

    public void setDirectCashback(BigDecimal directCashback) {
        this.directCashback = directCashback;
    }

    public BigDecimal getIndirectCashback() {
        return indirectCashback;
    }

    public void setIndirectCashback(BigDecimal indirectCashback) {
        this.indirectCashback = indirectCashback;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("friendPhoneNum", friendPhoneNum)
                .add("vouchers", vouchers)
                .add("directCashback", directCashback)
                .add("indirectCashback", indirectCashback)
                .toString();
    }
}
