package com.tuodao.bp.model.traningcenter.input;

import com.tuodao.bp.model.business.traningcenter.output.BorrowTenderOutput;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.model.business.useraccount.output.UserDepositOutput;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/11/7
 * @time: 18:36
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class UserCollectionInput implements Serializable {

    /**
     * 生成的回款列表
     */
    private List<BorrowCollectionInput> collectionList;

    /**
     * 投标信息
     */
    private BorrowTenderOutput borrowTender;
    /**
     * 用户信息
     */
    private UserAccountInfo user;

    /**
     * 用户存管信息
     */
    private UserDepositOutput userDeposit;

    public BorrowTenderOutput getBorrowTender() {
        return borrowTender;
    }

    public void setBorrowTender(BorrowTenderOutput borrowTender) {
        this.borrowTender = borrowTender;
    }

    public List<BorrowCollectionInput> getCollectionList() {
        return collectionList;
    }

    public void setCollectionList(List<BorrowCollectionInput> collectionList) {
        this.collectionList = collectionList;
    }

    public UserAccountInfo getUser() {
        return user;
    }

    public void setUser(UserAccountInfo user) {
        this.user = user;
    }

    public UserDepositOutput getUserDeposit() {
        return userDeposit;
    }

    public void setUserDeposit(UserDepositOutput userDeposit) {
        this.userDeposit = userDeposit;
    }
}
