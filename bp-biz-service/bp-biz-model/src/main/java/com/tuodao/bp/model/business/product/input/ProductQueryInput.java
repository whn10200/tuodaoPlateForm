package com.tuodao.bp.model.business.product.input;

import com.tuodao.bp.model.PagePojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class    ProductQueryInput extends PagePojo implements Serializable {


    private static final long serialVersionUID = -725454201634638737L;
    private Integer id;

    private String productCode;

    private Integer borrowType;

    private Integer refundWay;

    private Integer productPeriodStart;

    private Integer productPeriodEnd;

    private String periodUnit;

    private Integer defineType;

    private Integer isApp;

    private Integer productStatus;

    private Integer productType;

    private Integer isJunior = 1;

    private Integer limitBegin;

    private Integer limitSize;

    public Integer getLimitBegin() {
        return limitBegin;
    }

    public void setLimitBegin(Integer limitBegin) {
        this.limitBegin = limitBegin;
    }

    public Integer getLimitSize() {
        return limitSize;
    }

    public void setLimitSize(Integer limitSize) {
        this.limitSize = limitSize;
    }


    public Integer getIsJunior() {
        return isJunior;
    }

    public void setIsJunior(Integer isJunior) {
        this.isJunior = isJunior;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }


    public Integer getBorrowType() {
        return borrowType;
    }

    public void setBorrowType(Integer borrowType) {
        this.borrowType = borrowType;
    }

    public Integer getRefundWay() {
        return refundWay;
    }

    public void setRefundWay(Integer refundWay) {
        this.refundWay = refundWay;
    }

    public Integer getProductPeriodStart() {
        return productPeriodStart;
    }

    public void setProductPeriodStart(Integer productPeriodStart) {
        this.productPeriodStart = productPeriodStart;
    }

    public Integer getProductPeriodEnd() {
        return productPeriodEnd;
    }

    public void setProductPeriodEnd(Integer productPeriodEnd) {
        this.productPeriodEnd = productPeriodEnd;
    }

    public String getPeriodUnit() {
        return periodUnit;
    }

    public void setPeriodUnit(String periodUnit) {
        this.periodUnit = periodUnit;
    }

    public Integer getDefineType() {
        return defineType;
    }

    public void setDefineType(Integer defineType) {
        this.defineType = defineType;
    }

    public Integer getIsApp() {
        return isApp;
    }

    public void setIsApp(Integer isApp) {
        this.isApp = isApp;
    }

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }
}