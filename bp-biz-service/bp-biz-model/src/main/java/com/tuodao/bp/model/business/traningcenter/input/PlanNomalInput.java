package com.tuodao.bp.model.business.traningcenter.input;

import com.tuodao.bp.model.constant.traningcenter.TraningCenterExceptionConstant;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @description:理财计划下普通新增续贷标的复审参数
 * @author: wuzf
 * @date: 2017/9/15 0015.
 * @time: 14:24
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class PlanNomalInput implements Serializable {
    private static final long serialVersionUID = -4522559033349417556L;

    //精选计划id
    private Integer id;

    //借款基本利率
    @NotNull(message = TraningCenterExceptionConstant.APR_IS_NULL+"")
    private BigDecimal apr;

    //平台奖励利率
    @NotNull(message = TraningCenterExceptionConstant.AWARD_APR_IS_NULL+"")
    private BigDecimal awardScale;

    //加息奖励利率
    @NotNull(message = TraningCenterExceptionConstant.VOUCHER_APR_IS_NULL+"")
    private BigDecimal vouchApr;

    //剩余期限(如果是天标  那就是多少天  其余为月)
    @NotNull(message = TraningCenterExceptionConstant.LEFT_PERIOD_IS_NULL+"")
    private int leftPeriod;

    //开始期数
    @NotNull(message = TraningCenterExceptionConstant.START_PERIOD_IS_NULL+"")
    private int startPeriod;

    //上期还款时间
    @NotNull(message = TraningCenterExceptionConstant.REPAY_TIME_IS_NULL+"")
    private long repayTime;

    //投资id
    @NotNull(message = TraningCenterExceptionConstant.TENDER_ID_IS_NULL+"")
    private Integer tenderId;

    //还款方式
    @NotNull(message = TraningCenterExceptionConstant.RECOVER_TYPE_IS_NULL+"")
    private Integer raymentType;

    //投资金额
    @NotNull(message = TraningCenterExceptionConstant.PRE_ACCOUNT_IS_NULL+"")
    private BigDecimal account;

    //用户userId
    @NotNull(message = TraningCenterExceptionConstant.USER_ID_IS_NULL+"")
    private String userId;

    //产品名称
    private String borrowName;

    //银行编号
    private String orderId;

    //是否展示利息
    private Boolean showIntrest;

    //理财计划id
    private int productId;

    //底层标的id
    private int lastProductId;

    //理财计划到期时间
    private Date repayLastTime;

    //理财计划的预计利息
    private BigDecimal preAccountInterest;

    //是否陪玩账户最后接收的 如果是1 不是0
    private Integer playAccount;

    public Integer getPlayAccount() {
        return playAccount;
    }

    public void setPlayAccount(Integer playAccount) {
        this.playAccount = playAccount;
    }

    public Date getRepayLastTime() {
        return repayLastTime;
    }

    public void setRepayLastTime(Date repayLastTime) {
        this.repayLastTime = repayLastTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public BigDecimal getPreAccountInterest() {
        return preAccountInterest;
    }

    public void setPreAccountInterest(BigDecimal preAccountInterest) {
        this.preAccountInterest = preAccountInterest;
    }

    public int getLastProductId() {
        return lastProductId;
    }

    public void setLastProductId(int lastProductId) {
        this.lastProductId = lastProductId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Boolean getShowIntrest() {
        return showIntrest;
    }

    public void setShowIntrest(Boolean showIntrest) {
        this.showIntrest = showIntrest;
    }


    public BigDecimal getApr() {
        return apr;
    }

    public void setApr(BigDecimal apr) {
        this.apr = apr;
    }

    public BigDecimal getAwardScale() {
        return awardScale;
    }

    public void setAwardScale(BigDecimal awardScale) {
        this.awardScale = awardScale;
    }

    public BigDecimal getVouchApr() {
        return vouchApr;
    }

    public void setVouchApr(BigDecimal vouchApr) {
        this.vouchApr = vouchApr;
    }

    public int getLeftPeriod() {
        return leftPeriod;
    }

    public void setLeftPeriod(int leftPeriod) {
        this.leftPeriod = leftPeriod;
    }

    public int getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(int startPeriod) {
        this.startPeriod = startPeriod;
    }

    public long getRepayTime() {
        return repayTime;
    }

    public void setRepayTime(long repayTime) {
        this.repayTime = repayTime;
    }

    public Integer getTenderId() {
        return tenderId;
    }

    public void setTenderId(Integer tenderId) {
        this.tenderId = tenderId;
    }

    public Integer getRaymentType() {
        return raymentType;
    }

    public void setRaymentType(Integer raymentType) {
        this.raymentType = raymentType;
    }


    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBorrowName() {
        return borrowName;
    }

    public void setBorrowName(String borrowName) {
        this.borrowName = borrowName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

}
