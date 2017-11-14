package com.tuodao.bp.model.business.useraccount.output;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 理财师等级信息
 * @author: mif
 * @date: 2017/9/19
 * @time: 10:42
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class FinancialPlannerOutput implements Serializable {

    private static final long serialVersionUID = 1854131457024934851L;

    /**
     * 当前理财师等级（1：初级理财师；2：中级理财师；3：金牌理财师）
     */
    private Integer currentLevel;
    /**
     * 当前理财师等级名称
     */
    private String currentLevelName;

    /**
     * 当前V1好友数
     */
    private Integer currentV1Count;

    /**
     * 当前好友总待收
     */
    private BigDecimal currentDueInFundTotal;

    /**
     * 上一级理财师等级
     */
    private Integer upLevel;

    /**
     * 上一级理财师等级名称
     */
    private String upLevelName;

    /**
     * 距离上一级相差V1好友数
     */
    private Integer differV1Count;

    /**
     * 距离上一级相差总待收金额
     */
    private BigDecimal differDueInFundTotal;

    /**
     * 是否后台设置
     */
    private boolean adminSetting;

    /**
     * 能否返现
     */
    private boolean canCashback;

    public FinancialPlannerOutput() {
        this.adminSetting = false;
        this.canCashback = false;
    }

    public Integer getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Integer currentLevel) {
        this.currentLevel = currentLevel;
    }

    public String getCurrentLevelName() {
        return currentLevelName;
    }

    public void setCurrentLevelName(String currentLevelName) {
        this.currentLevelName = currentLevelName;
    }

    public Integer getCurrentV1Count() {
        return currentV1Count;
    }

    public void setCurrentV1Count(Integer currentV1Count) {
        this.currentV1Count = currentV1Count;
    }

    public BigDecimal getCurrentDueInFundTotal() {
        return currentDueInFundTotal;
    }

    public void setCurrentDueInFundTotal(BigDecimal currentDueInFundTotal) {
        this.currentDueInFundTotal = currentDueInFundTotal;
    }

    public Integer getUpLevel() {
        return upLevel;
    }

    public void setUpLevel(Integer upLevel) {
        this.upLevel = upLevel;
    }

    public String getUpLevelName() {
        return upLevelName;
    }

    public void setUpLevelName(String upLevelName) {
        this.upLevelName = upLevelName;
    }

    public Integer getDifferV1Count() {
        return differV1Count;
    }

    public void setDifferV1Count(Integer differV1Count) {
        this.differV1Count = differV1Count;
    }

    public BigDecimal getDifferDueInFundTotal() {
        return differDueInFundTotal;
    }

    public void setDifferDueInFundTotal(BigDecimal differDueInFundTotal) {
        this.differDueInFundTotal = differDueInFundTotal;
    }

    public boolean isAdminSetting() {
        return adminSetting;
    }

    public void setAdminSetting(boolean adminSetting) {
        this.adminSetting = adminSetting;
    }

    public boolean isCanCashback() {
        return canCashback;
    }

    public void setCanCashback(boolean canCashback) {
        this.canCashback = canCashback;
    }
}
