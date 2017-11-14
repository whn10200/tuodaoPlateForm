package com.tuodao.bp.traningcenter.db.model.basic;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author qingli.chen
 * @date 2017-09-15
 */
public class BorrowCollectionCapital implements Serializable {

    private static final long serialVersionUID = 4085376693379235281L;

    /**
     * 预计回款本金
     */
    private BigDecimal preCapital;

    /**
     * 实际回款本金
     */
    private BigDecimal capital;

    /**
     * 总期数
     */
    private Integer sumCount;

    /**
     * 实际回款利息
     */
    private BigDecimal interest;

    /**
     * 投标id
     */
    private Integer tenderId;

    public BigDecimal getPreCapital() {
        return preCapital;
    }

    public void setPreCapital(BigDecimal preCapital) {
        this.preCapital = preCapital;
    }

    public Integer getSumCount() {
        return sumCount;
    }

    public void setSumCount(Integer sumCount) {
        this.sumCount = sumCount;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public Integer getTenderId() {
        return tenderId;
    }

    public void setTenderId(Integer tenderId) {
        this.tenderId = tenderId;
    }
}