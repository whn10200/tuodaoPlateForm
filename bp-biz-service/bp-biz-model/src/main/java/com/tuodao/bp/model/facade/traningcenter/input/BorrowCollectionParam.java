package com.tuodao.bp.model.facade.traningcenter.input;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/9/20
 * @time: 10:13
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class BorrowCollectionParam extends Param{

    /**
     * 投标Id
     */
    private int tenderId;

    public int getTenderId() {
        return tenderId;
    }

    public void setTenderId(int tenderId) {
        this.tenderId = tenderId;
    }
}
