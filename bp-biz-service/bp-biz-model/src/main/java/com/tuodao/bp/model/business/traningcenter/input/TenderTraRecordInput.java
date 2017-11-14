package com.tuodao.bp.model.business.traningcenter.input;

import com.tuodao.bp.model.PagePojo;

import java.io.Serializable;

/**
 * @description:
 * @author: wuzf
 * @date: 2017/9/18 0018.
 * @time: 15:41
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class TenderTraRecordInput  extends PagePojo implements Serializable {
    private static final long serialVersionUID = -6093702028572547980L;

    //产品id
    private Integer borrowId;
    //状态
    private  Integer status;

    public Integer getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
