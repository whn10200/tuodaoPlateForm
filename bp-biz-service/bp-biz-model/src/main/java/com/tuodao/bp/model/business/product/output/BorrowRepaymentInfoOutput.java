package com.tuodao.bp.model.business.product.output;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author wuchengjie
 * @Date 2017/9/19 0019 15:11
 * @Introduction
 */
public class BorrowRepaymentInfoOutput implements Serializable {


    private static final long serialVersionUID = -6539079785968689642L;
    /**查询某一个标的未还款期数 上期还款日期  如果没还款过 那么就取复审时间
     */

    private Integer borrowId;

   //未还款期数
    private Integer period;

    //总期数
    private Integer periods;

    //上期还款日期
    private Date lastRepayTime;



    public Integer getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getPeriods() {
        return periods;
    }

    public void setPeriods(Integer periods) {
        this.periods = periods;
    }


    public Date getLastRepayTime() {
        return lastRepayTime;
    }

    public void setLastRepayTime(Date lastRepayTime) {
        this.lastRepayTime = lastRepayTime;
    }
}


