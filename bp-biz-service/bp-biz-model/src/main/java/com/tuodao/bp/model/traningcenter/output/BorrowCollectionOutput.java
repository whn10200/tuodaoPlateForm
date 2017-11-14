package com.tuodao.bp.model.traningcenter.output;

import java.io.Serializable;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/9/12
 * @time: 18:04
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class BorrowCollectionOutput implements Serializable {

    /**
     * 已回款总额
     */
    private double collectionTotal;

    public BorrowCollectionOutput(){}

    public BorrowCollectionOutput(double collectionTotal) {
        this.collectionTotal = collectionTotal;
    }

    public double getCollectionTotal() {
        return collectionTotal;
    }

    public void setCollectionTotal(double collectionTotal) {
        this.collectionTotal = collectionTotal;
    }
}
