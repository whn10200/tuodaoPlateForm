package com.tuodao.bp.model.traningcenter.input;

import java.io.Serializable;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/9/13
 * @time: 20:04
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class BorrowInput implements Serializable {

    /**
     * 标的Id
     */
    private Integer borrowId;


    public BorrowInput() {

    }

    public BorrowInput(Integer borrowId) {
        this.borrowId = borrowId;
    }

    public Integer getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }
}
