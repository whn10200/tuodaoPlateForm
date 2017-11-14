package com.tuodao.bp.model.traningcenter.input;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description:
 * @author: wuzf
 * @date: 2017/9/14 0014.
 * @time: 17:40
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class OwnTransferInput implements Serializable {
    private static final long serialVersionUID = 2684991128944728223L;

    private Integer id;

    private String userId;

    private Integer tenderId;

    private String borrowName;

    private BigDecimal account;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getTenderId() {
        return tenderId;
    }

    public void setTenderId(Integer tenderId) {
        this.tenderId = tenderId;
    }

    public String getBorrowName() {
        return borrowName;
    }

    public void setBorrowName(String borrowName) {
        this.borrowName = borrowName;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }
}
