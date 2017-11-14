package com.tuodao.bp.traningcenter.db.model.basic;

import java.io.Serializable;

public class AccountBankPayment implements Serializable {
    private Integer id;

    private Integer type;

    private String name;

    private String bankId;

    private String paymentCode;

    private Integer status;

    private Integer orderNumber;

    private Long limitOneTime;

    private Long limitOneDay;

    private Long limitOneMonth;

    private String payment;

    private Integer isDirect;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId == null ? null : bankId.trim();
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode == null ? null : paymentCode.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getLimitOneTime() {
        return limitOneTime;
    }

    public void setLimitOneTime(Long limitOneTime) {
        this.limitOneTime = limitOneTime;
    }

    public Long getLimitOneDay() {
        return limitOneDay;
    }

    public void setLimitOneDay(Long limitOneDay) {
        this.limitOneDay = limitOneDay;
    }

    public Long getLimitOneMonth() {
        return limitOneMonth;
    }

    public void setLimitOneMonth(Long limitOneMonth) {
        this.limitOneMonth = limitOneMonth;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment == null ? null : payment.trim();
    }

    public Integer getIsDirect() {
        return isDirect;
    }

    public void setIsDirect(Integer isDirect) {
        this.isDirect = isDirect;
    }
}