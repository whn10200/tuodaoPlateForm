package com.tuodao.bp.model.business.useraccount.input;

import com.google.common.base.MoreObjects;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.annotation.PhoneNum;
import com.tuodao.bp.model.constant.useraccount.UaParamExceptionConstant;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @description: 收件地址
 * @author: mif
 * @date: 2017/10/12
 * @time: 14:52
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class ConsigneeInfoInput extends BasePojo implements Serializable {
    private static final long serialVersionUID = 3135005378277325719L;

    /**
     * 收件人
     */
    @NotNull(message = UaParamExceptionConstant.CONSIGNEE_CAN_NOT_BE_NULL + "")
    @Size(max = 10, message = UaParamExceptionConstant.PARAM_BEYOND_THE_LENGTH + "")
    private String consignee;

    /**
     * 手机人手机
     */
    @NotNull(message = UaParamExceptionConstant.CONSIGNEE_MOBILE_CAN_NOT_BE_NULL + "")
    @PhoneNum(message = UaParamExceptionConstant.MOBILE_FORMAT_ERROR + "")
    private String consigneeMobile;

    /**
     * 收件地址
     */
    @NotNull(message = UaParamExceptionConstant.CONSIGNEE_ADDRESS_CAN_NOT_BE_NULL + "")
    @Size(max = 255, message = UaParamExceptionConstant.PARAM_BEYOND_THE_LENGTH + "")
    private String consigneeAddress;

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getConsigneeMobile() {
        return consigneeMobile;
    }

    public void setConsigneeMobile(String consigneeMobile) {
        this.consigneeMobile = consigneeMobile;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("consignee", consignee)
                .add("consigneeMobile", consigneeMobile)
                .add("consigneeAddress", consigneeAddress)
                .add("userId", userId)
                .toString();
    }
}
