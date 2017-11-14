package com.tuodao.bp.model.facade.traningcenter.input;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.annotation.RangeDouble;
import com.tuodao.bp.model.annotation.RangeLength;
import com.tuodao.bp.model.constant.traningcenter.TraningCenterExceptionConstant;

import javax.validation.constraints.NotNull;

/**
 * @description: 投标请求参数,投标信息查询请求参数
 * @author: 王艳兵
 * @date: 2017/9/13
 * @time: 19:49
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class TenderParam extends BasePojo {


    /**
     * 标的Id
     */
    @NotNull(message = TraningCenterExceptionConstant.BORROW_ID_IS_NULL + "")
    private Integer borrowId;

    /**
     * 投标金额
     */
    @RangeDouble(message = TraningCenterExceptionConstant.TENDER_MONEY_UNDERSIZE + "")
    private Double tenderMoney;

    /**
     * 支付密码
     */
    @RangeLength(min = 32,max = 32,message = TraningCenterExceptionConstant.PAY_PASSWORD + "" )
    private String payPassword;

    /**
     * 加息券Id
     */
    private Integer  voucherId;

    /**
     * 约标密码
     */
    @RangeLength(required = false,min = 32,max = 32,message = TraningCenterExceptionConstant.ORDER_TENDER_PASSWORD + "" )
    private String orderPassword;

    /**
     * 图形验证码
     */
    private String authCode;

    /**
     * 渠道来源:0:pc,1:ios,2:android,3:h5,4:后台
     */
    private Integer channel;

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Integer getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getTenderMoney() {
        return tenderMoney;
    }

    public void setTenderMoney(Double tenderMoney) {
        this.tenderMoney = tenderMoney;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public Integer getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Integer voucherId) {
        this.voucherId = voucherId;
    }

    public String getOrderPassword() {
        return orderPassword;
    }

    public void setOrderPassword(String orderPassword) {
        this.orderPassword = orderPassword;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }
}
