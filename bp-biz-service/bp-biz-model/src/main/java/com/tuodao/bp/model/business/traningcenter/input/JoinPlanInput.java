package com.tuodao.bp.model.business.traningcenter.input;

import com.tuodao.bp.model.constant.traningcenter.TraningCenterExceptionConstant;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/10/10 0010.
 */
public class JoinPlanInput implements Serializable {
    private static final long serialVersionUID = 8196049617184027161L;

    //预计投资金额
    @NotNull(message = TraningCenterExceptionConstant.PRE_ACCOUNT_IS_NULL+"")
    private BigDecimal preAccount;

    //用户id
    @NotNull(message = TraningCenterExceptionConstant.USER_ID_IS_NULL+"")
    private String userId;

    //产品编号
    @NotNull(message = TraningCenterExceptionConstant.BORROW_ID_IS_NULL+"")
    private Integer borrowId;

    //渠道
    @NotNull(message = TraningCenterExceptionConstant.CHANNEL_IS_NULL+"")
    private Integer channel;

    //优惠券id
    private Integer couponId;

    public BigDecimal getPreAccount() {
        return preAccount;
    }

    public void setPreAccount(BigDecimal preAccount) {
        this.preAccount = preAccount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }
}
