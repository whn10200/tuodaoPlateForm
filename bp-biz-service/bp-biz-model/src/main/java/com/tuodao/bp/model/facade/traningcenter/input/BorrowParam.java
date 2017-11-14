package com.tuodao.bp.model.facade.traningcenter.input;

import com.tuodao.bp.model.PagePojo;
import com.tuodao.bp.model.constant.traningcenter.TraningCenterExceptionConstant;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/11/8
 * @time: 13:32
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class BorrowParam extends PagePojo implements Serializable {

    private static final long serialVersionUID = 1474772015951498039L;
    /**
     * 产品id
     */
    @NotNull(message = TraningCenterExceptionConstant.BORROW_ID_IS_NULL + "")
    private Integer productId;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
