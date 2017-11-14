package com.tuodao.bp.model.traningcenter.input;

import com.tuodao.bp.model.annotation.Range;
import com.tuodao.bp.model.annotation.RangeDouble;
import com.tuodao.bp.model.constant.traningcenter.TraningCenterExceptionConstant;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description: 提现相关入参
 * @author: 王艳兵
 * @date: 2017/9/12
 * @time: 18:08
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class AccountCashApplyInput implements Serializable{

    /**
     * 用户Id
     */
    @NotNull(message = TraningCenterExceptionConstant.USER_ID_IS_NULL + "")
    private String userId;

    /**
     * 订单编号 唯一
     */
    private String orderNo;

    /**
     * 提现ip
     */
    private String addIp;

    /**
     * 提现来源
     */
    private Integer source;

    /**
     * 提现手续费
     */
    private BigDecimal fee;

    /**
     * 可免费提现金额
     */
    private BigDecimal balanceCash = BigDecimal.valueOf(0);


    private BigDecimal account;

    /**
     * 银行卡号
     */
    private String bankNum;

    /**
     * 是否使用免费提现次数 0:不使用 1:使用
     */
    private Integer useFree;

    public BigDecimal getBalanceCash() {
        return balanceCash;
    }

    public void setBalanceCash(BigDecimal balanceCash) {
        this.balanceCash = balanceCash;
    }

    public Integer getUseFree() {
        return useFree;
    }

    public void setUseFree(Integer useFree) {
        this.useFree = useFree;
    }

    public AccountCashApplyInput(){}

    public String getAddIp() {
        return addIp;
    }

    public void setAddIp(String addIp) {
        this.addIp = addIp;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    public String getBankNum() {
        return bankNum;
    }

    public void setBankNum(String bankNum) {
        this.bankNum = bankNum;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public AccountCashApplyInput(String userId){
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "AccountCashApplyInput{" +
                "userId='" + userId + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", addIp='" + addIp + '\'' +
                ", source=" + source +
                ", fee=" + fee +
                ", balanceCash=" + balanceCash +
                ", account=" + account +
                ", bankNum='" + bankNum + '\'' +
                ", useFree=" + useFree +
                '}';
    }
}
