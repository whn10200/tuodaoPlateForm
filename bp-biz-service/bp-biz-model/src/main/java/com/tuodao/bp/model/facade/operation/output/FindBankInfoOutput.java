package com.tuodao.bp.model.facade.operation.output;

import java.io.Serializable;

/**
 * Description: 根据银行卡列表，获取用户银行信息返回信息
 * User: zkai
 * Date: 2017/10/30
 * Time: 16:36
 * Copyright:拓道金服 Copyright (c) 2017
 */
public class FindBankInfoOutput implements Serializable {
    private static final long serialVersionUID = 8736941330905929213L;

    /**
     * 银行卡号
     */
    private String bankCardId;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 银行编码
     */
    private String bankCode;

    /**
     * 备注
     */
    private String remark;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankCardId() {
        return bankCardId;
    }

    public void setBankCardId(String bankCardId) {
        this.bankCardId = bankCardId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
