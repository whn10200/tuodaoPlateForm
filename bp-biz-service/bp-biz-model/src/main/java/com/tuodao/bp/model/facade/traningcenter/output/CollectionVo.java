package com.tuodao.bp.model.facade.traningcenter.output;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/9/30
 * @time: 14:13
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class CollectionVo implements Serializable {

    /**
     * 回款时间
     */
    private String collectionTime;

    /**
     * 产品类型
     */
    private String productTitile;

    /**
     * 当前期数
     */
    private int period;

    /**
     * 总期数
     */
    private int periods;

    /**
     * 回款类型 0:本息 1:收益
     */
    private int type;

    /**
     * 回款金额
     */
    private String collectionMoney;

    /**
     * 回款状态
     */
    private int status;

    /**
     * 产品类型 0:散标 1:精选计划
     */
    private int productType;

    /**
     * 产品id
     */
    private int productId;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(String collectionTime) {
        this.collectionTime = collectionTime;
    }

    public String getProductTitile() {
        return productTitile;
    }

    public void setProductTitile(String productTitile) {
        this.productTitile = productTitile;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getPeriods() {
        return periods;
    }

    public void setPeriods(int periods) {
        this.periods = periods;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCollectionMoney() {
        return collectionMoney;
    }

    public void setCollectionMoney(String collectionMoney) {
        this.collectionMoney = collectionMoney;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getProductType() {
        return productType;
    }

    public void setProductType(int productType) {
        this.productType = productType;
    }
}
