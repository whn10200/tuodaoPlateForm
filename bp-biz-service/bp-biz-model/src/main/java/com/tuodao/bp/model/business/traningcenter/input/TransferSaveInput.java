package com.tuodao.bp.model.business.traningcenter.input;

import com.tuodao.bp.model.BasePojo;

import javax.validation.constraints.NotNull;
import com.tuodao.bp.model.constant.traningcenter.CreditAssignmentConstant;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author qingli.chen
 * @date 2017/9/21
 * @description 债转保存 入参
 */
public class TransferSaveInput extends BasePojo {

    private static final long serialVersionUID = 7353419810175633210L;


    private Integer id;

    //投标id
    @NotNull(message = CreditAssignmentConstant.TENDER_IS_NULL + "")
    private Integer tenderId;

    //标的名称
    @NotEmpty(message = CreditAssignmentConstant.BORROW_NAME_IS_NULL + "")
    private String borrowName;

    //状态
    @NotNull(message = CreditAssignmentConstant.STATUS_IS_NULL + "")
    private Integer status;

    //金额
    @NotNull(message = CreditAssignmentConstant.ACCOUNT_IS_NULL + "")
    private BigDecimal account;

    //借款期限
    @NotNull(message = CreditAssignmentConstant.PERIOD_IS_NULL + "")
    private Integer period;

    //期限类型 0天 1月 2年
    @NotNull(message = CreditAssignmentConstant.PERIOD_TYPE_IS_NULL + "")
    private Integer periodType;

    //年化率
    @NotNull(message = CreditAssignmentConstant.APR_IS_NULL + "")
    private BigDecimal apr;

    //平台奖励
    private BigDecimal platformApr;

    //还款方式
    @NotNull(message = CreditAssignmentConstant.RAYMENT_TYPE + "")
    private Integer raymentType;

    //原始标的
    private Integer preBorrowId;

    //转让时间
    @NotNull(message = CreditAssignmentConstant.ADD_TIME_IS_NULL + "")
    private Date addTime;

    //转让结束时间
    private Date endTime;

    //转让手续费
    @NotNull(message = CreditAssignmentConstant.FEE_IS_NULL + "")
    private BigDecimal fee;

    //转让价值
    private BigDecimal transferWorth;

    //
    private Integer toSource;

    //转让盈亏
    private BigDecimal profit;

    //给转让人的利息
    private BigDecimal returnInterest;

    //复审时间
    private Date verifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTenderId() {
        return tenderId;
    }

    public void setTenderId(Integer tenderId) {
        this.tenderId = tenderId;
    }

    public String getBorrowName() {
        return borrowName;
    }

    public void setBorrowName(String borrowName) {
        this.borrowName = borrowName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getPeriodType() {
        return periodType;
    }

    public void setPeriodType(Integer periodType) {
        this.periodType = periodType;
    }

    public BigDecimal getApr() {
        return apr;
    }

    public void setApr(BigDecimal apr) {
        this.apr = apr;
    }

    public BigDecimal getPlatformApr() {
        return platformApr;
    }

    public void setPlatformApr(BigDecimal platformApr) {
        this.platformApr = platformApr;
    }

    public Integer getRaymentType() {
        return raymentType;
    }

    public void setRaymentType(Integer raymentType) {
        this.raymentType = raymentType;
    }

    public Integer getPreBorrowId() {
        return preBorrowId;
    }

    public void setPreBorrowId(Integer preBorrowId) {
        this.preBorrowId = preBorrowId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getTransferWorth() {
        return transferWorth;
    }

    public void setTransferWorth(BigDecimal transferWorth) {
        this.transferWorth = transferWorth;
    }

    public Integer getToSource() {
        return toSource;
    }

    public void setToSource(Integer toSource) {
        this.toSource = toSource;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public BigDecimal getReturnInterest() {
        return returnInterest;
    }

    public void setReturnInterest(BigDecimal returnInterest) {
        this.returnInterest = returnInterest;
    }

    public Date getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(Date verifyTime) {
        this.verifyTime = verifyTime;
    }
}

