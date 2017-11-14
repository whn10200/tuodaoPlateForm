package com.tuodao.bp.useraccount.service;

import com.tuodao.bp.model.business.useraccount.input.OpenDepositCallbackInput;
import com.tuodao.bp.model.business.useraccount.input.OpenDepositInput;
import com.tuodao.bp.model.business.useraccount.output.UserDepositOutput;
import com.tuodao.bp.model.facade.operation.input.FindBankInfoInput;
import com.tuodao.bp.model.facade.operation.output.FindBankInfoOutput;

import java.util.List;

/**
 * Description:
 * User: zkai
 * Date: 2017/10/16
 * Time: 16:37
 * Copyright:拓道金服 Copyright (c) 2017
 */
public interface IUserDepositService {
    /**
     * 开通存管
     *
     * @param input
     */
    void openDeposit(OpenDepositInput input);

    /**
     * 开通存管回调
     *
     * @param input
     */
    void openDepositCallback(OpenDepositCallbackInput input);

    /**
     * 获取用户存管信息
     *
     * @param userId 用户Id
     * @return 存管信息
     */
    UserDepositOutput getUserDepositInfo(String userId);

    List<FindBankInfoOutput> findBanksByBankIds(FindBankInfoInput input);
}
