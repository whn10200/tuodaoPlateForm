package com.tuodao.bp.model.business.useraccount.input;

import com.tuodao.bp.model.BasePojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 提现请求封装
 * @author: mif
 * @date: 2017/8/29
 * @time: 14:42
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class WithdrawInput extends BasePojo implements Serializable {
    private static final long serialVersionUID = -7999815435165019075L;

    /**
     * 提现金额(分)
     */
    private BigDecimal funds;

    public BigDecimal getFunds() {
        return funds;
    }

    public void setFunds(BigDecimal funds) {
        this.funds = funds;
    }
}
