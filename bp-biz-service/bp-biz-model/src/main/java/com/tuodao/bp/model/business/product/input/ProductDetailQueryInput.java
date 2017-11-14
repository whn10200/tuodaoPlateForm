package com.tuodao.bp.model.business.product.input;

import com.tuodao.bp.model.BasePojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 *  理财产品详细查询条件
 */
public class ProductDetailQueryInput extends BasePojo implements Serializable {

    private Integer id;

    private Integer productId;

    private Integer orginalId;

    private Integer borrowId;

    private String borrowTitle;

    private BigDecimal borrowAmount;

    private Integer borrowType;

    private Integer status;

    private Date addTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getOrginalId() {
        return orginalId;
    }

    public void setOrginalId(Integer orginalId) {
        this.orginalId = orginalId;
    }

    public Integer getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }

    public String getBorrowTitle() {
        return borrowTitle;
    }

    public void setBorrowTitle(String borrowTitle) {
        this.borrowTitle = borrowTitle == null ? null : borrowTitle.trim();
    }

    public BigDecimal getBorrowAmount() {
        return borrowAmount;
    }

    public void setBorrowAmount(BigDecimal borrowAmount) {
        this.borrowAmount = borrowAmount;
    }

    public Integer getBorrowType() {
        return borrowType;
    }

    public void setBorrowType(Integer borrowType) {
        this.borrowType = borrowType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}