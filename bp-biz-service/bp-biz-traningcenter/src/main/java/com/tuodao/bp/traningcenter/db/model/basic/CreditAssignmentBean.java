package com.tuodao.bp.traningcenter.db.model.basic;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author qingli.chen
 * @date 2017/9/14
 * @description 债转列表 bean
 */
public class CreditAssignmentBean implements Serializable {

    private static final long serialVersionUID = 1240154309489191506L;

    //产品名称
    private String productTitle;

    //期数
    private Integer productPeriod;

    //产品id
    private Integer id;

    //投标id
    private Integer tenderId;

    //产品编号
    private String productCode;

    //年化率
    private BigDecimal borrowApr;

    //可转让金额
    private BigDecimal recoverCapital;

    /**
     * 投资金额
     */
    private BigDecimal preAccount;

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public Integer getProductPeriod() {
        return productPeriod;
    }

    public void setProductPeriod(Integer productPeriod) {
        this.productPeriod = productPeriod;
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
        this.productCode = productCode;
    }

    public BigDecimal getBorrowApr() {
        return borrowApr;
    }

    public void setBorrowApr(BigDecimal borrowApr) {
        this.borrowApr = borrowApr;
    }

    public BigDecimal getRecoverCapital() {
        return recoverCapital;
    }

    public void setRecoverCapital(BigDecimal recoverCapital) {
        this.recoverCapital = recoverCapital;
    }

    public Integer getTenderId() {
        return tenderId;
    }

    public void setTenderId(Integer tenderId) {
        this.tenderId = tenderId;
    }

    public BigDecimal getPreAccount() {
        return preAccount;
    }

    public void setPreAccount(BigDecimal preAccount) {
        this.preAccount = preAccount;
    }
}
