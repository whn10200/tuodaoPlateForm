package com.tuodao.bp.model.business.traningcenter.output;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description::释放债权出参
 * @author: wuzf
 * @date: 2017/9/28 0028.
 * @time: 16:40
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class SelectClaimOutput  implements Serializable{
    private static final long serialVersionUID = -8293569213534998302L;


    //用户id
    private String userId;

    //投资id
    private Integer tenderId;

    //转让金额
    private BigDecimal preCapital;

    //总期限
    private Integer periods;

    //开始期数
    private Integer period;

    //原始底层标的id
    private Integer borrowId;

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

    public BigDecimal getPreCapital() {
        return preCapital;
    }

    public void setPreCapital(BigDecimal preCapital) {
        this.preCapital = preCapital;
    }

    public Integer getPeriods() {
        return periods;
    }

    public void setPeriods(Integer periods) {
        this.periods = periods;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }
}
