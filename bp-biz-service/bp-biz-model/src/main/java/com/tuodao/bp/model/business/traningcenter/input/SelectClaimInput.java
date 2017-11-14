package com.tuodao.bp.model.business.traningcenter.input;

import java.io.Serializable;

/**
 * @description:释放债权入参
 * @author: wuzf
 * @date: 2017/9/28 0028.
 * @time: 16:37
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class SelectClaimInput implements Serializable {
    private static final long serialVersionUID = 4585652131011764260L;

    private Integer justId;

    //0表示投资id1表示产品id
    private Integer type;

    public Integer getJustId() {
        return justId;
    }

    public void setJustId(Integer justId) {
        this.justId = justId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
