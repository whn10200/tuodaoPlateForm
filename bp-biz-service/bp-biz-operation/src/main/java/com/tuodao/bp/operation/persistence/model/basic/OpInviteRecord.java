package com.tuodao.bp.operation.persistence.model.basic;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OpInviteRecord implements Serializable {
    private Integer id;

    private String userId;

    private String friendPhoneNum;

    private Double vouchers;

    private BigDecimal directCashback;

    private BigDecimal indirectCashback;

    private Date registerTime;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getFriendPhoneNum() {
        return friendPhoneNum;
    }

    public void setFriendPhoneNum(String friendPhoneNum) {
        this.friendPhoneNum = friendPhoneNum == null ? null : friendPhoneNum.trim();
    }

    public Double getVouchers() {
        return vouchers;
    }

    public void setVouchers(Double vouchers) {
        this.vouchers = vouchers;
    }

    public BigDecimal getDirectCashback() {
        return directCashback;
    }

    public void setDirectCashback(BigDecimal directCashback) {
        this.directCashback = directCashback;
    }

    public BigDecimal getIndirectCashback() {
        return indirectCashback;
    }

    public void setIndirectCashback(BigDecimal indirectCashback) {
        this.indirectCashback = indirectCashback;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
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