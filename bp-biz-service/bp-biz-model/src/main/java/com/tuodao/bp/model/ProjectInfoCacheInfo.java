package com.tuodao.bp.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/10/10 0010.
 * @author wuzf
 * @author 王艳兵
 */
public class ProjectInfoCacheInfo implements Serializable {


    private static final long serialVersionUID = 8195531434624090734L;
    /**
     * 理财计划产品id 散标id
     */
    private Integer projectId;


    /**
     * 理财计划剩余金额 散标剩余可投金额
     */
    private BigDecimal leftAccount;

    /**
     * 募集总额
     */
    private BigDecimal borrowAmount;

    /**
     * 产品编号
     */
    private String productCode;



    public BigDecimal getBorrowAmount() {
        return borrowAmount;
    }

    public void setBorrowAmount(BigDecimal borrowAmount) {
        this.borrowAmount = borrowAmount;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public BigDecimal getLeftAccount() {
        return leftAccount;
    }

    public void setLeftAccount(BigDecimal leftAccount) {
        this.leftAccount = leftAccount;
    }
}
