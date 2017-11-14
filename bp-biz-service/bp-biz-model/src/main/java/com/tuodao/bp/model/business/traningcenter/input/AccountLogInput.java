package com.tuodao.bp.model.business.traningcenter.input;

import com.tuodao.bp.model.annotation.In;
import com.tuodao.bp.model.constant.traningcenter.AccountLogConstant;
import com.tuodao.bp.model.constant.traningcenter.TraningCenterExceptionConstant;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 资金记录服务间调用入参
 * @author: 王艳兵
 * @date: 2017/9/19
 * @time: 18:54
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class AccountLogInput implements Serializable{
    /**
     * 资金类型
     */
    @In(value = AccountLogConstant.ACCOUNT_SELECT_TYPE,message = TraningCenterExceptionConstant.ACCOUNT_LOG_STATUS_ERROR + "")
    private Integer type;

    /**
     * 查询期数日期
     */
    private Date startTime;

    /**
     * 查询结束日期
     */
    private Date endTime;

    /**
     * 页容量
     */
    private int pageSize;

    /**
     * 当前页
     */
    private int currentPage;

    /**
     * 用户Id
     */
    @NotNull(message = TraningCenterExceptionConstant.USER_ID_IS_NULL + "")
    private String userId;


    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 资金发生变动的金额
     */
    private BigDecimal account;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 资金来源账户 标的ID等
     */
    private String fromAccount;

    /**
     * 资金来源备注
     */
    private String fromRemarks;

    /**
     * 资金去向账户
     */
    private String toAccount;

    /**
     * 资金去向备注
     */
    private String toRemarks;

    /**
     * 0收入1支出2冻结3:解冻
     */
    private int changeType;

    /**
     * 收益发生变动的金额
     */
    private BigDecimal intrestAccount;

    /**
     * 手续费发生变动的金额
     */
    private BigDecimal feeAccount;

    /**
     * 是否显示该条记录
     */
    private Integer isShow;

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getFromRemarks() {
        return fromRemarks;
    }

    public void setFromRemarks(String fromRemarks) {
        this.fromRemarks = fromRemarks;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public String getToRemarks() {
        return toRemarks;
    }

    public void setToRemarks(String toRemarks) {
        this.toRemarks = toRemarks;
    }

    public int getChangeType() {
        return changeType;
    }

    public void setChangeType(int changeType) {
        this.changeType = changeType;
    }

    public BigDecimal getIntrestAccount() {
        return intrestAccount;
    }

    public void setIntrestAccount(BigDecimal intrestAccount) {
        this.intrestAccount = intrestAccount;
    }

    public BigDecimal getFeeAccount() {
        return feeAccount;
    }

    public void setFeeAccount(BigDecimal feeAccount) {
        this.feeAccount = feeAccount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public String toString() {
        return "AccountLogInput{" +
                "type=" + type +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                ", userId='" + userId + '\'' +
                '}';
    }
}
