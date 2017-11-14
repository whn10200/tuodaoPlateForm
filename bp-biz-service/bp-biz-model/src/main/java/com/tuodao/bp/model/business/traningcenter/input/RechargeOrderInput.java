package com.tuodao.bp.model.business.traningcenter.input;

import com.tuodao.bp.model.BasePojo;

import java.util.Date;

/**
 * @author qingli.chen
 * @date 2017/10/16
 * @description
 */
public class RechargeOrderInput extends BasePojo {
    private static final long serialVersionUID = 8580182543327901385L;

    private String orderNo;

    private Integer status;

    private Date payTime;

    public RechargeOrderInput(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
}
