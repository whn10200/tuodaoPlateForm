package com.tuodao.bp.useraccount.persistence.model.basic;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class UserVipInfoHis implements Serializable {
    private Integer id;

    private String userId;

    private Integer vipLevel;

    private Integer maxVipLevel;

    private BigDecimal lastMonthAvg;

    private Date startTime;

    private Date endTime;

    private Integer bigCustomer;

    private String remark;

    private Date gmtCreate;

    private Date gmtModify;

    private String gmtCreator;

    private String gmtModifier;

    private Integer isDel;

    private Integer continusSameLevelTimes;

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

    public Integer getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(Integer vipLevel) {
        this.vipLevel = vipLevel;
    }

    public Integer getMaxVipLevel() {
        return maxVipLevel;
    }

    public void setMaxVipLevel(Integer maxVipLevel) {
        this.maxVipLevel = maxVipLevel;
    }

    public BigDecimal getLastMonthAvg() {
        return lastMonthAvg;
    }

    public void setLastMonthAvg(BigDecimal lastMonthAvg) {
        this.lastMonthAvg = lastMonthAvg;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getBigCustomer() {
        return bigCustomer;
    }

    public void setBigCustomer(Integer bigCustomer) {
        this.bigCustomer = bigCustomer;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public Integer getContinusSameLevelTimes() {
        return continusSameLevelTimes;
    }

    public void setContinusSameLevelTimes(Integer continusSameLevelTimes) {
        this.continusSameLevelTimes = continusSameLevelTimes;
    }
}