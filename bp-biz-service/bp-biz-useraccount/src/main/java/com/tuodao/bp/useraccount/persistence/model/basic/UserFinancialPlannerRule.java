package com.tuodao.bp.useraccount.persistence.model.basic;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UserFinancialPlannerRule implements Serializable {
    private Integer id;

    private Integer financialPlannerLevel;

    private Integer vip1InviteeTotal;

    private BigDecimal dueInFundTotal;

    private Double directCashbackPer;

    private Double indirectCashbackPer;

    private Integer validityType;

    private Date gmtCreate;

    private Date gmtModify;

    private String gmtCreator;

    private String gmtModifier;

    private Integer isDel;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFinancialPlannerLevel() {
        return financialPlannerLevel;
    }

    public void setFinancialPlannerLevel(Integer financialPlannerLevel) {
        this.financialPlannerLevel = financialPlannerLevel;
    }

    public Integer getVip1InviteeTotal() {
        return vip1InviteeTotal;
    }

    public void setVip1InviteeTotal(Integer vip1InviteeTotal) {
        this.vip1InviteeTotal = vip1InviteeTotal;
    }

    public BigDecimal getDueInFundTotal() {
        return dueInFundTotal;
    }

    public void setDueInFundTotal(BigDecimal dueInFundTotal) {
        this.dueInFundTotal = dueInFundTotal;
    }

    public Double getDirectCashbackPer() {
        return directCashbackPer;
    }

    public void setDirectCashbackPer(Double directCashbackPer) {
        this.directCashbackPer = directCashbackPer;
    }

    public Double getIndirectCashbackPer() {
        return indirectCashbackPer;
    }

    public void setIndirectCashbackPer(Double indirectCashbackPer) {
        this.indirectCashbackPer = indirectCashbackPer;
    }

    public Integer getValidityType() {
        return validityType;
    }

    public void setValidityType(Integer validityType) {
        this.validityType = validityType;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public String getGmtCreator() {
        return gmtCreator;
    }

    public void setGmtCreator(String gmtCreator) {
        this.gmtCreator = gmtCreator == null ? null : gmtCreator.trim();
    }

    public String getGmtModifier() {
        return gmtModifier;
    }

    public void setGmtModifier(String gmtModifier) {
        this.gmtModifier = gmtModifier == null ? null : gmtModifier.trim();
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}