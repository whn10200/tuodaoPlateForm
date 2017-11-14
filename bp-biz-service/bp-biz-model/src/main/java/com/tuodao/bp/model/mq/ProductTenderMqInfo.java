package com.tuodao.bp.model.mq;

import com.tuodao.bp.model.business.traningcenter.input.TenderExecutor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 投资的队列
 * @author: wuchengjie
 * @date: 2017/9/25
 * @time: 09:41
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class ProductTenderMqInfo extends TenderExecutor implements Serializable {

    private static final long serialVersionUID = -8962009069333260348L;

    private Integer productId;  //理财计划id

    private Integer tenderId;  //购买理财计划的 投资id

    private String userId;

    private Integer borrowId;  //散标或债权的id 实际购买的Id

    private BigDecimal tenderAmount; //分

    private int type; //类型(1:散标，2:债权)





    /**
     * 业务处理类型 0:散标投标 1:精选计划投标 2债权投标  根据该类型确定最终消费端处理的逻辑
     */
    private int businessType;
    /**
     * 散标相关参数
     */
    private TenderExecutor executor;

    public int getBusinessType() {
        return businessType;
    }

    public void setBusinessType(int businessType) {
        this.businessType = businessType;
    }

    public TenderExecutor getExecutor() {
        return executor;
    }

    public void setExecutor(TenderExecutor executor) {
        this.executor = executor;
    }

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


    public Integer getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }

    public BigDecimal getTenderAmount() {
        return tenderAmount;
    }

    public void setTenderAmount(BigDecimal tenderAmount) {
        this.tenderAmount = tenderAmount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public ProductTenderMqInfo() {
    }

    @Override
    public String toString() {
        return "ProductTenderMqInfo{" +
                "productId=" + productId +
                ", tenderId=" + tenderId +
                ", userId='" + userId + '\'' +
                ", borrowId=" + borrowId +
                ", tenderAmount=" + tenderAmount +
                ", type=" + type +
                ", businessType=" + businessType +
                ", executor=" + executor +
                '}';
    }
}
