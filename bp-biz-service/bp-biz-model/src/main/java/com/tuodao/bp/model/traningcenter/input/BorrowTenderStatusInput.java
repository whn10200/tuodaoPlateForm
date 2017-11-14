package com.tuodao.bp.model.traningcenter.input;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/9/22
 * @time: 10:31
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class BorrowTenderStatusInput implements Serializable{


    /**
     * 投标Id
     */
    private Integer tenderId;

    /**
     * 投标状态
     */
    private Integer status;

    /**
     * 投标备注
     */
    private String remarks;

    /**
     * 平台加息利率%
     */
    private BigDecimal platformApr;

    /**
     * 投标期限
     */
    private Integer period;

    /**
     * 基础利率
     */
    private BigDecimal apr;

    /**
     * 计息方式
     */
    private Integer refundWay;


    public Integer getTenderId() {
        return tenderId;
    }

    public void setTenderId(Integer tenderId) {
        this.tenderId = tenderId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public BigDecimal getPlatformApr() {
        return platformApr;
    }

    public void setPlatformApr(BigDecimal platformApr) {
        this.platformApr = platformApr;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public BigDecimal getApr() {
        return apr;
    }

    public void setApr(BigDecimal apr) {
        this.apr = apr;
    }

    public Integer getRefundWay() {
        return refundWay;
    }

    public void setRefundWay(Integer refundWay) {
        this.refundWay = refundWay;
    }
}
