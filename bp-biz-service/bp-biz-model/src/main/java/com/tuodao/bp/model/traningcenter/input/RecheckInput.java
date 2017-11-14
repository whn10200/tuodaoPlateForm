package com.tuodao.bp.model.traningcenter.input;

import com.tuodao.bp.model.business.traningcenter.input.BorrowRecheckInput;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/11/7
 * @time: 17:57
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class RecheckInput implements Serializable{

    private static final long serialVersionUID = -868872250731572428L;
    /**
     * 生成的回款列表
     */
    private List<UserCollectionInput> collectionList;

    /**
     * 标的信息
     */
    private BorrowRecheckInput borrowRecheckInput;

    public List<UserCollectionInput> getCollectionList() {
        return collectionList;
    }

    public void setCollectionList(List<UserCollectionInput> collectionList) {
        this.collectionList = collectionList;
    }

    public BorrowRecheckInput getBorrowRecheckInput() {
        return borrowRecheckInput;
    }

    public void setBorrowRecheckInput(BorrowRecheckInput borrowRecheckInput) {
        this.borrowRecheckInput = borrowRecheckInput;
    }
}
