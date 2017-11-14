package com.tuodao.bp.useraccount.service;

import com.tuodao.bp.model.mq.AccountIncomeDetailMqInfo;

/**
 * Description: 同步账户累计收益明细相关service
 * User: zkai
 * Date: 2017/9/13
 * Time: 15:46
 * Copyright:拓道金服 Copyright (c) 2017
 */
public interface IAccountIncomeDetailService {
    /**
     * 添加账户累计收益明细
     * @param accountIncomeDetail
     */
    void addAccountIncomeDetail(AccountIncomeDetailMqInfo accountIncomeDetail);
}
