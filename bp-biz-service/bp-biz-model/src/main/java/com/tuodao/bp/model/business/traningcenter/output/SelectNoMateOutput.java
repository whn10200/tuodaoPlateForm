package com.tuodao.bp.model.business.traningcenter.output;

import com.tuodao.bp.model.annotation.In;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description:查询未匹配的资金出参
 * @author: wuzf
 * @date: 2017/10/18
 * @time: 13:56
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class SelectNoMateOutput implements Serializable{

    private static final long serialVersionUID = -6685384701754436311L;

    /**
     * 加入id 投资时候需要参数
     */
    private Integer id;

    /**
     * userid 投资时候需要参数
     */
    private String userId;

    /**
     * 已匹配金额
     */
    private BigDecimal account;

    /**
     * 加入金额
     */
    private BigDecimal preAccount;

    /**
     * 渠道
     */
    private Integer channel;

    /**
     * 加息券id
     */
    private Integer voucherId;

    /**
     * 抵用卷id
     */
    private Integer voucherCouponId;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 理财计划id
     */
    private Integer productId;


    public Integer getVoucherCouponId() {
        return voucherCouponId;
    }

    public void setVoucherCouponId(Integer voucherCouponId) {
        this.voucherCouponId = voucherCouponId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    public BigDecimal getPreAccount() {
        return preAccount;
    }

    public void setPreAccount(BigDecimal preAccount) {
        this.preAccount = preAccount;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Integer getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Integer voucherId) {
        this.voucherId = voucherId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
