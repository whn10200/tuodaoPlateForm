package com.tuodao.bp.useraccount.persistence.model.basic;

import java.io.Serializable;
import java.math.BigDecimal;


public class UserInviteInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userId;

    private String userPhone;

    private Integer financialPlannerLevel;//理财师等级

    private String financialPlannerLevelName;//理财师等级名称

    private String invitePerson;

    private String inviteRegisterType;

    private Integer invitePersonNum;

    private Integer investTimes;

    private BigDecimal investTotal;

    private BigDecimal totalDirectCash;

    private BigDecimal totalIndirectCash;

    private BigDecimal vouchers;//抵用券总额

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Integer getFinancialPlannerLevel() {
        return financialPlannerLevel;
    }

    public void setFinancialPlannerLevel(Integer financialPlannerLevel) {
        this.financialPlannerLevel = financialPlannerLevel;
    }

    public String getInvitePerson() {
        return invitePerson;
    }

    public void setInvitePerson(String invitePerson) {
        this.invitePerson = invitePerson;
    }

    public String getInviteRegisterType() {
        return inviteRegisterType;
    }

    public void setInviteRegisterType(String inviteRegisterType) {
        this.inviteRegisterType = inviteRegisterType;
    }

    public Integer getInvitePersonNum() {
        return invitePersonNum;
    }

    public void setInvitePersonNum(Integer invitePersonNum) {
        this.invitePersonNum = invitePersonNum;
    }

    public Integer getInvestTimes() {
        return investTimes;
    }

    public void setInvestTimes(Integer investTimes) {
        this.investTimes = investTimes;
    }

    public BigDecimal getInvestTotal() {
        return investTotal;
    }

    public void setInvestTotal(BigDecimal investTotal) {
        this.investTotal = investTotal;
    }

    public BigDecimal getTotalDirectCash() {
        return totalDirectCash;
    }

    public void setTotalDirectCash(BigDecimal totalDirectCash) {
        this.totalDirectCash = totalDirectCash;
    }

    public BigDecimal getTotalIndirectCash() {
        return totalIndirectCash;
    }

    public void setTotalIndirectCash(BigDecimal totalIndirectCash) {
        this.totalIndirectCash = totalIndirectCash;
    }

    public BigDecimal getVouchers() {
        return vouchers;
    }

    public void setVouchers(BigDecimal vouchers) {
        this.vouchers = vouchers;
    }

    public String getFinancialPlannerLevelName() {
        return financialPlannerLevelName;
    }

    public void setFinancialPlannerLevelName(String financialPlannerLevelName) {
        this.financialPlannerLevelName = financialPlannerLevelName;
    }
}