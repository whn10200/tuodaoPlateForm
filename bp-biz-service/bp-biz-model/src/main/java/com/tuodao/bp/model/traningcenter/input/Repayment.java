package com.tuodao.bp.model.traningcenter.input;

import java.io.Serializable;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/9/20
 * @time: 20:14
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class Repayment implements Serializable {

    /**
     * 平台投资人客户号
     */
    private String custNo;

    /**
     * 实际还款总额 = 实际还款本金+体验金+加息金+利息+手续费
     */
    private String realRepayAmt;

    /**
     * 实际还款本金
     */
    private String realRepayAmount;


    /**
     * 体验金
     */
    private String experienceAmt;

    /**
     * 加息利息
     */
    private String ratesAmt;

    /**
     * 实际还款利息
     */
    private String realRepayVal;

    /**
     * 还款手续费
     */
    private String repayFee;

    /**
     * 还款期数
     */
    private String repayNum;

    /**
     * 还款日期
     */
    private String repayDate;

    /**
     * 实际还款日期
     */
    private String realRepayDate;

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getRealRepayAmt() {
        return realRepayAmt;
    }

    public void setRealRepayAmt(String realRepayAmt) {
        this.realRepayAmt = realRepayAmt;
    }

    public String getRealRepayAmount() {
        return realRepayAmount;
    }

    public void setRealRepayAmount(String realRepayAmount) {
        this.realRepayAmount = realRepayAmount;
    }

    public String getExperienceAmt() {
        return experienceAmt;
    }

    public void setExperienceAmt(String experienceAmt) {
        this.experienceAmt = experienceAmt;
    }

    public String getRatesAmt() {
        return ratesAmt;
    }

    public void setRatesAmt(String ratesAmt) {
        this.ratesAmt = ratesAmt;
    }

    public String getRealRepayVal() {
        return realRepayVal;
    }

    public void setRealRepayVal(String realRepayVal) {
        this.realRepayVal = realRepayVal;
    }

    public String getRepayFee() {
        return repayFee;
    }

    public void setRepayFee(String repayFee) {
        this.repayFee = repayFee;
    }

    public String getRepayNum() {
        return repayNum;
    }

    public void setRepayNum(String repayNum) {
        this.repayNum = repayNum;
    }

    public String getRepayDate() {
        return repayDate;
    }

    public void setRepayDate(String repayDate) {
        this.repayDate = repayDate;
    }

    public String getRealRepayDate() {
        return realRepayDate;
    }

    public void setRealRepayDate(String realRepayDate) {
        this.realRepayDate = realRepayDate;
    }
}
