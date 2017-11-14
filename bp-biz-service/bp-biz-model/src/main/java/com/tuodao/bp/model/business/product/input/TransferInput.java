package com.tuodao.bp.model.business.product.input;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author wuchengjie
 * @Date 2017/9/22 0022 15:21
 * @Introduction
 */
public class TransferInput implements Serializable {


    private static final long serialVersionUID = -6155171849531447607L;
    /**
     *  投资的id
     */
    private Integer tenderId;

    private String userId;

    private BigDecimal amount;

    public Integer getTenderId() {
        return tenderId;
    }

    public void setTenderId(Integer tenderId) {
        this.tenderId = tenderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
