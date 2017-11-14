package com.tuodao.bp.model.facade.traningcenter.output;

import java.io.Serializable;

/**
 * @description: 借款协议书
 * @author: 王艳兵
 * @date: 2017/10/25
 * @time: 11:06
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class BorrowProtocolVo implements Serializable {

    /**
     * 借款人名称
     */
    private String borrowUserName;

    /**
     * 借款人id 不完全显示
     */
    private String borrowUserId;

    /**
     * 借款人身份证号
     */
    private String borrowIdNum;

    /**
     * 投资人名称
     */
    private String userName;

    /**
     * 投资人手机号
     */
    private String mobile;

    /**
     * 投资人手机号码
     */
    private String idNum;

    /**
     * 投标金额
     */
    private String tenderMoney;

    /**
     * 投标期限
     */
    private int period;

    /**
     * 期限单位 0：天（day）1：月（month）2：年（year）
     */
    private int periodUnit;
    /**
     * 借款起息日
     */
    private String startTime;

    /**
     * 借款到期日
     */
    private String endTime;

    public int getPeriodUnit() {
        return periodUnit;
    }

    public void setPeriodUnit(int periodUnit) {
        this.periodUnit = periodUnit;
    }

    public String getBorrowUserName() {
        return borrowUserName;
    }

    public void setBorrowUserName(String borrowUserName) {
        this.borrowUserName = borrowUserName;
    }

    public String getBorrowUserId() {
        return borrowUserId;
    }

    public void setBorrowUserId(String borrowUserId) {
        this.borrowUserId = borrowUserId;
    }

    public String getBorrowIdNum() {
        return borrowIdNum;
    }

    public void setBorrowIdNum(String borrowIdNum) {
        this.borrowIdNum = borrowIdNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getTenderMoney() {
        return tenderMoney;
    }

    public void setTenderMoney(String tenderMoney) {
        this.tenderMoney = tenderMoney;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
