package com.tuodao.bp.model.business.useraccount.output;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 用户VIP信息
 * @author: mif
 * @date: 2017/10/25
 * @time: 09:03
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class UserVipInfoOutput implements Serializable {
    private static final long serialVersionUID = -5662891447309889191L;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 当前VIP等级
     */
    private Integer vipLevel;

    /**
     * 历史最大VIP等级
     */
    private Integer maxVipLevel;
    /**
     * 上月日均账户资产(分)
     */
    private BigDecimal lastMonthAvg;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 是否大客户（0：否；1：是；）
     */
    private Integer bigCustomer;

    /**
     * 备注
     */
    private String remark;

    /**
     * 本月日均资产(分)
     */
    private BigDecimal thisMonthAvg;

    /**
     * 是否最大等级
     */
    private boolean ifMaxLevel;

    /**
     * 下一等级（距离xx等级）
     */
    private Integer nextLevel;

    /**
     * 还差下一等级金额(分)
     */
    private BigDecimal distanceNextLevelAmount;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
        this.remark = remark;
    }

    public BigDecimal getThisMonthAvg() {
        return thisMonthAvg;
    }

    public void setThisMonthAvg(BigDecimal thisMonthAvg) {
        this.thisMonthAvg = thisMonthAvg;
    }

    public Integer getNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(Integer nextLevel) {
        this.nextLevel = nextLevel;
    }

    public BigDecimal getDistanceNextLevelAmount() {
        return distanceNextLevelAmount;
    }

    public void setDistanceNextLevelAmount(BigDecimal distanceNextLevelAmount) {
        this.distanceNextLevelAmount = distanceNextLevelAmount;
    }

    public boolean isIfMaxLevel() {
        return ifMaxLevel;
    }

    public void setIfMaxLevel(boolean ifMaxLevel) {
        this.ifMaxLevel = ifMaxLevel;
    }

    @Override
    public String toString() {
        return "UserVipInfoOutput{" +
                "userId='" + userId + '\'' +
                ", vipLevel=" + vipLevel +
                ", maxVipLevel=" + maxVipLevel +
                ", lastMonthAvg=" + lastMonthAvg +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", bigCustomer=" + bigCustomer +
                ", remark='" + remark + '\'' +
                ", thisMonthAvg=" + thisMonthAvg +
                ", ifMaxLevel=" + ifMaxLevel +
                ", nextLevel=" + nextLevel +
                ", distanceNextLevelAmount=" + distanceNextLevelAmount +
                '}';
    }
}
