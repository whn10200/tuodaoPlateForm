package com.tuodao.bp.model.business.traningcenter.output;

import java.io.Serializable;
import java.util.List;

/**
 * @description::释放债权出参list
 * @author: wuzf
 * @date: 2017/11/06 0028.
 * @time: 16:40
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class SelectClaimListOutput implements Serializable{

    private static final long serialVersionUID = 8372704207969710708L;
    private Integer productId;

    private List<SelectClaimOutput> list;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public List<SelectClaimOutput> getList() {
        return list;
    }

    public void setList(List<SelectClaimOutput> list) {
        this.list = list;
    }
}
