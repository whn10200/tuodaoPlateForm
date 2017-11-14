package com.tuodao.bp.model.facade.traningcenter.input;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.annotation.RangeLength;
import com.tuodao.bp.model.constant.traningcenter.TraningCenterExceptionConstant;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description:约标密码验证 图形验证码验证
 * @author: 王艳兵
 * @date: 2017/11/10
 * @time: 14:26
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class TenderOrderParam extends BasePojo implements Serializable {

    /**
     * 产品id
     */
    @NotNull(message = TraningCenterExceptionConstant.BORROW_ID_IS_NULL + "")
    private Integer productId;

    /**
     * 待验证的密码或者图形验证码
     */
    @RangeLength(min = 32,max = 32,message = TraningCenterExceptionConstant.ORDER_TENDER_PASSWORD + "" )
    private String orderPassword;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getOrderPassword() {
        return orderPassword;
    }

    public void setOrderPassword(String orderPassword) {
        this.orderPassword = orderPassword;
    }
}
