package com.tuodao.bp.model.business.traningcenter.input;

import com.tuodao.bp.model.constant.traningcenter.TraningCenterExceptionConstant;

import javax.validation.constraints.NotNull;
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

    //产品id
    @NotNull(message = TraningCenterExceptionConstant.BORROW_ID_IS_NULL+"")
    private Integer borrowId;

    //用户userid
    @NotNull(message = TraningCenterExceptionConstant.USER_ID_IS_NULL+"")
    private String userId;

    @NotNull(message = TraningCenterExceptionConstant.TENDER_ID_IS_NULL+"")
    private Integer tenderId;

    private String borrowName;

    @NotNull(message = TraningCenterExceptionConstant.TENDER_ID_IS_NULL+"")
    private BigDecimal account;


    public Integer getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
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
