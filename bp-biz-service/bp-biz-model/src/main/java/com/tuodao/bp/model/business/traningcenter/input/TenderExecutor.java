package com.tuodao.bp.model.business.traningcenter.input;

import com.tuodao.bp.model.business.operation.output.UserDiscountOutput;
import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.model.constant.traningcenter.TraningCenterExceptionConstant;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @description: 投标队列参数
 * @author: 王艳兵
 * @date: 2017/9/14
 * @time: 09:36
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class TenderExecutor implements Serializable{

    /**
     * 用于查看投标结果的唯一key
     */
    private String tenderKey;
    /**
     * 产品信息
     */
    private ProductOutput product;

    /**
     * 抵用券信息
     */
    private UserDiscountOutput discount;

    /**
     * 投标金额
     */
    @NotNull(message = TraningCenterExceptionConstant.TENDER_MONEY_IS_NULL + "")
    private double tenderMoney;

    /**
     * 投资渠道,0:pc,1:ios 2:android 3:h5
     */
    private int channel;

    /**
     * 投标IP
     */
    private String addIp;

    /**
     * 投标方式 0:手动 1:自动
     */
    private int tenderMode;

    /**
     * 投标类型:0:散标 1:债权 2:精选计划普通标 3：精选计划转让标
     */
    private int tenderType;
    /**
     * 自动投标ID 默认非自动投标
     */
    private int autoTenderId = 0;

    /**
     * 用户信息
     */
    private UserAccountInfo user;

    /**
     * 精选计划投标ID
     */
    private Integer choicenessTenderId;

    /**
     * 投标奖励 例如转让时的投标奖励
     */
    private Double tenderTranAward = 0D;

    /**
     * 奖励订单号
     */
    private String tranAwardOrderid;



    public UserDiscountOutput getDiscount() {
        return discount;
    }

    public void setDiscount(UserDiscountOutput discount) {
        this.discount = discount;
    }

    public String getTenderKey() {
        return tenderKey;
    }

    public void setTenderKey(String tenderKey) {
        this.tenderKey = tenderKey;
    }

    public TenderExecutor() {
    }

    public int getTenderType() {
        return tenderType;
    }

    public void setTenderType(int tenderType) {
        this.tenderType = tenderType;
    }



    public ProductOutput getProduct() {
        return product;
    }

    public void setProduct(ProductOutput product) {
        this.product = product;
    }

    public double getTenderMoney() {
        return tenderMoney;
    }

    public void setTenderMoney(double tenderMoney) {
        this.tenderMoney = tenderMoney;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public String getAddIp() {
        return addIp;
    }

    public void setAddIp(String addIp) {
        this.addIp = addIp;
    }

    public int getTenderMode() {
        return tenderMode;
    }

    public void setTenderMode(int tenderMode) {
        this.tenderMode = tenderMode;
    }

    public int getAutoTenderId() {
        return autoTenderId;
    }

    public void setAutoTenderId(int autoTenderId) {
        this.autoTenderId = autoTenderId;
    }

    public UserAccountInfo getUser() {
        return user;
    }

    public void setUser(UserAccountInfo user) {
        this.user = user;
    }

    public Integer getChoicenessTenderId() {
        return choicenessTenderId;
    }

    public void setChoicenessTenderId(Integer choicenessTenderId) {
        this.choicenessTenderId = choicenessTenderId;
    }

    public Double getTenderTranAward() {
        return tenderTranAward;
    }

    public void setTenderTranAward(Double tenderTranAward) {
        this.tenderTranAward = tenderTranAward;
    }

    public String getTranAwardOrderid() {
        return tranAwardOrderid;
    }

    public void setTranAwardOrderid(String tranAwardOrderid) {
        this.tranAwardOrderid = tranAwardOrderid;
    }

    @Override
    public String toString() {
        return "TenderExecutor{" +
                "tenderKey='" + tenderKey + '\'' +
                ", product=" + product +
                ", discount=" + discount +
                ", tenderMoney=" + tenderMoney +
                ", channel=" + channel +
                ", addIp='" + addIp + '\'' +
                ", tenderMode=" + tenderMode +
                ", tenderType=" + tenderType +
                ", autoTenderId=" + autoTenderId +
                ", user=" + user +
                ", choicenessTenderId=" + choicenessTenderId +
                ", tenderTranAward=" + tenderTranAward +
                ", tranAwardOrderid='" + tranAwardOrderid + '\'' +
                '}';
    }
}
