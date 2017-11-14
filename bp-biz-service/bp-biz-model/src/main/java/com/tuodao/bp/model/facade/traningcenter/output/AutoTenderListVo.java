package com.tuodao.bp.model.facade.traningcenter.output;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 查询自动投标列表出参
 * @author: 王艳兵
 * @date: 2017/9/29
 * @time: 14:56
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class AutoTenderListVo implements Serializable{


    private static final long serialVersionUID = -6796681752211957587L;

    /**
     * 自动投标时间
     */
    private String addTime;

    /**
     * 投标金额
     */
    private String tenderMoney;

    /**
     * 最小期限
     */
    private int minPeriod;

    /**
     * 最大期限
     */
    private int maxPeriod;

    /**
     * 是否使用优惠券 0:不使用1 :使用抵用券 2:使用加息券
     */
    private int voucherType;

    /**
     * 优惠券额度
     */
    private double voucherMoney;

    /**
     * 自动投标id
     */
    private int autoTenderId;

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getTenderMoney() {
        return tenderMoney;
    }

    public void setTenderMoney(String tenderMoney) {
        this.tenderMoney = tenderMoney;
    }

    public int getMinPeriod() {
        return minPeriod;
    }

    public void setMinPeriod(int minPeriod) {
        this.minPeriod = minPeriod;
    }

    public int getMaxPeriod() {
        return maxPeriod;
    }

    public void setMaxPeriod(int maxPeriod) {
        this.maxPeriod = maxPeriod;
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

    public int getAutoTenderId() {
        return autoTenderId;
    }

    public void setAutoTenderId(int autoTenderId) {
        this.autoTenderId = autoTenderId;
    }

    @Override
    public String toString() {
        return "AutoTenderListVo{" +
                "addTime=" + addTime +
                ", tenderMoney='" + tenderMoney + '\'' +
                ", minPeriod=" + minPeriod +
                ", maxPeriod=" + maxPeriod +
                ", voucherType=" + voucherType +
                ", voucherMoney=" + voucherMoney +
                ", autoTenderId=" + autoTenderId +
                '}';
    }
}
