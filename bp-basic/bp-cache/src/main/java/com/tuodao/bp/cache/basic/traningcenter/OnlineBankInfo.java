package com.tuodao.bp.cache.basic.traningcenter;


import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 网银缓存类
 * @author qingli.chen
 * @date 2017/10/12
 * @description
 */
public class OnlineBankInfo implements Serializable {
    private static final long serialVersionUID = -34088723628680681L;

    /**
     * 银行
     */
    private String name;

    private String paymentCode;

    /**
     * 单笔限额
     */
    private BigDecimal limitOneTime;
    /**
     * 当日限额
     */
    private BigDecimal limitOneDay;
    /**
     * 当月限额
     */
    private BigDecimal limitOneMonth;

    private String bankId;

    private String payment;
    /**
     * pc图标地址
     */
    private String pcUrl;
    /**
     * app图标地址
     */
    private String appUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public BigDecimal getLimitOneTime() {
        return limitOneTime;
    }

    public void setLimitOneTime(BigDecimal limitOneTime) {
        this.limitOneTime = limitOneTime;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public BigDecimal getLimitOneDay() {
        return limitOneDay;
    }

    public void setLimitOneDay(BigDecimal limitOneDay) {
        this.limitOneDay = limitOneDay;
    }

    public BigDecimal getLimitOneMonth() {
        return limitOneMonth;
    }

    public void setLimitOneMonth(BigDecimal limitOneMonth) {
        this.limitOneMonth = limitOneMonth;
    }

    public String getPcUrl() {
        return pcUrl;
    }

    public void setPcUrl(String pcUrl) {
        this.pcUrl = pcUrl;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl;
    }

    @Override
    public String toString() {
        return "OnlineBankInfo{" +
                "name='" + name + '\'' +
                ", paymentCode='" + paymentCode + '\'' +
                ", limitOneTime=" + limitOneTime +
                ", limitOneDay=" + limitOneDay +
                ", limitOneMonth=" + limitOneMonth +
                ", bankId='" + bankId + '\'' +
                ", payment='" + payment + '\'' +
                ", pcUrl='" + pcUrl + '\'' +
                ", appUrl='" + appUrl + '\'' +
                '}';
    }
}
