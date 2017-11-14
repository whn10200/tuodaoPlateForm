package com.tuodao.bp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/11 0011.
 * @author zwuf
 * @author 王艳兵
 */
public class ReturnCacheInfo implements Serializable{
    private static final long serialVersionUID = 1615503749670373105L;

    //主键
    @JsonIgnore
    private String key;

    /**
     * 返回编码
     */
    private int code;

    /**
     * 返回状态（0处理中 1成功 2失败 ）
     */
    private int status;

    /**
     * 返回内容
     */
    private String content;

    /**
     * 预计利息
     */
    private String preInterest;

    /**
     * 加入金额
     */
    private String amount;

    /**
     * 标的 剩余可投金额
     */
    private String residueAmount;

    /**
     * 使用券类型 0:不使用 1:使用抵用券 2:加息券
     */
    private int voucherType;

    /**
     * 券面值
     */
    private double voucherMoney;

    public int getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(int voucherType) {
        this.voucherType = voucherType;
    }

    public double getVoucherMoney() {
        return voucherMoney;
    }

    public void setVoucherMoney(double voucherMoney) {
        this.voucherMoney = voucherMoney;
    }

    public String getResidueAmount() {
        return residueAmount;
    }

    public void setResidueAmount(String residueAmount) {
        this.residueAmount = residueAmount;
    }

    public String getPreInterest() {
        return preInterest;
    }

    public void setPreInterest(String preInterest) {
        this.preInterest = preInterest;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
