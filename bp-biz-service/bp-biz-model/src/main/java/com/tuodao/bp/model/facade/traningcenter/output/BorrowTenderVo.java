package com.tuodao.bp.model.facade.traningcenter.output;


import java.io.Serializable;
import java.util.Date;

/**
 * @description: 返回前台的投资列表的出参对象
 * @author: 王艳兵
 * @date: 2017/9/19
 * @time: 19:47
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class BorrowTenderVo implements Serializable{

    /**
     * 投标ID
     */
    private int tenderId;

    /**
     * 标的Id
     */
    private int productId;

    /**
     * 标的编号
     */
    private String productCode;

    /**
     * 标的名称
     */
    private String productTitle;

    /**
     * 期数
     */
    private int period;

    /**
     * 投标时间
     */
    private String tenderTime;

    /**
     * 投标金额
     */
    private String preAccount;

    /**
     * 预计利息 总收益
     */
    private String interest;

    /**
     * 抵用券类型 0:没有使用,1:抵用券 2:加息券
     */
    private int voucherType;

    /**
     * 抵用券面值
     */
    private double voucherMoney;


    /**
     * 投标状态
     */
    private int status;


    /**
     * 数据来源店面
     */
    private String sourceStore;

    /**
     * 标的状态
     */
    private int productStatus;

    /**
     * 计息方式 0:等额本息,1:按月付息 2:按天计息
     */
    private int refundWay;

    /**
     * 奖励 = 平台加息
     */
    private double award;

    /**
     * 到期日期
     */
    private String endTime;

    /**
     * 基础利率
     */
    private double baseApr;

    /**
     * 平台利率
     */
    private double platformApr;

    /**
     * 加息券利率
     */
    private double couponApr;

    /**
     * 精选计划上层ID 0 :为非精选计划ID
     */
    private int choicenessTenderId;



    public int getChoicenessTenderId() {
        return choicenessTenderId;
    }

    public void setChoicenessTenderId(int choicenessTenderId) {
        this.choicenessTenderId = choicenessTenderId;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public double getBaseApr() {
        return baseApr;
    }

    public void setBaseApr(double baseApr) {
        this.baseApr = baseApr;
    }

    public double getPlatformApr() {
        return platformApr;
    }

    public void setPlatformApr(double platformApr) {
        this.platformApr = platformApr;
    }

    public double getCouponApr() {
        return couponApr;
    }

    public void setCouponApr(double couponApr) {
        this.couponApr = couponApr;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public double getAward() {
        return award;
    }

    public void setAward(double award) {
        this.award = award;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }

    public int getRefundWay() {
        return refundWay;
    }

    public void setRefundWay(int refundWay) {
        this.refundWay = refundWay;
    }

    public String getSourceStore() {
        return sourceStore;
    }

    public void setSourceStore(String sourceStore) {
        this.sourceStore = sourceStore;
    }

    public int getTenderId() {
        return tenderId;
    }

    public void setTenderId(int tenderId) {
        this.tenderId = tenderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getTenderTime() {
        return tenderTime;
    }

    public void setTenderTime(String tenderTime) {
        this.tenderTime = tenderTime;
    }

    public String getPreAccount() {
        return preAccount;
    }

    public void setPreAccount(String preAccount) {
        this.preAccount = preAccount;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public int getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(int voucherType) {
        this.voucherType = voucherType;
    }

    public double getVoucherMoney() {
        return voucherMoney;
    }

    public void setVoucherMoney(double voucherMoney) {
        this.voucherMoney = voucherMoney;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
