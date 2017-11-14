package com.tuodao.bp.model.business.traningcenter.input;

import com.tuodao.bp.model.constant.traningcenter.TraningCenterExceptionConstant;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/10/24 0024.
 */
public class PlanDevelopListInput  implements Serializable{
    private static final long serialVersionUID = -5449827085586227503L;


    private String key;

    //产品id
    @NotNull(message = TraningCenterExceptionConstant.BORROW_ID_IS_NULL+"")
    private Integer borrowId;

    //转让人userid
    @NotNull(message = TraningCenterExceptionConstant.USER_ID_IS_NULL+"")
    private String userId;

    //转让的标对应的投资id
    @NotNull(message = TraningCenterExceptionConstant.TENDER_ID_IS_NULL+"")
    private Integer tenderId;

    //标的名称
    private String borrowName;

    //转让金额
    @NotNull(message = TraningCenterExceptionConstant.TENDER_ID_IS_NULL+"")
    private BigDecimal account;

    //未还款期数
    private Integer period;

    //上期还款日期
    private Date lastRepayTime;

    //总期数
    private Integer periods;

    //原始标的id
    private Integer preBorrowId;

    //使用抵用卷
    private BigDecimal voucherCouponMoney;


    private List<PlanNomalInput> list;


    public Integer getPreBorrowId() {
        return preBorrowId;
    }

    public void setPreBorrowId(Integer preBorrowId) {
        this.preBorrowId = preBorrowId;
    }

    public BigDecimal getVoucherCouponMoney() {
        return voucherCouponMoney;
    }

    public void setVoucherCouponMoney(BigDecimal voucherCouponMoney) {
        this.voucherCouponMoney = voucherCouponMoney;
    }

    public Integer getPeriods() {
        return periods;
    }

    public void setPeriods(Integer periods) {
        this.periods = periods;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Date getLastRepayTime() {
        return lastRepayTime;
    }

    public void setLastRepayTime(Date lastRepayTime) {
        this.lastRepayTime = lastRepayTime;
    }

    public Integer getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    public List<PlanNomalInput> getList() {
        return list;
    }

    public void setList(List<PlanNomalInput> list) {
        this.list = list;
    }
}
