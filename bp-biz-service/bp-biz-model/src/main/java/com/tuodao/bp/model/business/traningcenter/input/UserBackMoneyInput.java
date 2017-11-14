package com.tuodao.bp.model.business.traningcenter.input;

import com.tuodao.bp.model.PagePojo;
import com.tuodao.bp.model.constant.traningcenter.TraningCenterExceptionConstant;

import javax.validation.constraints.NotNull;

/**
 * @author qingli.chen
 * @date 2017/9/19
 * @description
 */
public class UserBackMoneyInput extends PagePojo {
    private static final long serialVersionUID = -3780231168427324440L;

    /**
     * 投资id
     */
    @NotNull(message = TraningCenterExceptionConstant.TENDER_ID_IS_NULL + "")
    private Integer tenderId;

    //产品id
    private Integer borrowId;

    /**
     * 当前期数
     */
    private Integer period;

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Integer getTenderId() {
        return tenderId;
    }

    public void setTenderId(Integer tenderId) {
        this.tenderId = tenderId;
    }

    public Integer getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }

}
