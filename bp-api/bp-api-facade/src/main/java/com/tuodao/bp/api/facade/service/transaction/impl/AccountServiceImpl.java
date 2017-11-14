package com.tuodao.bp.api.facade.service.transaction.impl;

import com.tuodao.bp.api.facade.client.transaction.AccountClient;
import com.tuodao.bp.api.facade.service.transaction.AccountService;
import com.tuodao.bp.model.business.operation.output.UserDiscountOutput;
import com.tuodao.bp.model.constant.operation.UserDiscountConstant;
import com.tuodao.bp.model.traningcenter.output.AccountOutput;
import com.tuodao.bp.result.error.TransactError;
import com.tuodao.bp.result.exception.MicroServiceException;
import com.tuodao.bp.utils.BigDecimalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 账户信息
 * @author: 王艳兵
 * @date: 2017/9/22
 * @time: 15:25
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountClient accountClient;

    @Override
    public AccountOutput verifyCashAccount(String userId, double money) {
        AccountOutput output = getUserAccount(userId);
        if(money > output.getBalance().subtract(output.getRecharge()).doubleValue()){
            throw new MicroServiceException(TransactError.CASH_MONEY_ERROR);
        }
        return output;
    }

    @Override
    public AccountOutput verifyAccount(String userId, double money) {
        AccountOutput account = getUserAccount(userId);
        double useTotal = account.getBalance().doubleValue();
        if(money > useTotal){
            throw new MicroServiceException(TransactError.ACCOUNT_BALANCE_ERROR);
        }
        return account;
    }

    @Override
    public AccountOutput verifyTenderAccount(String userId, double tenderMoney, UserDiscountOutput discount) {
        if(discount != null && discount.getDiscountType() == UserDiscountConstant.VOUCHER_TYPE){
            tenderMoney = BigDecimalUtils.sub(tenderMoney,Double.parseDouble(discount.getDiscountAvailable()));
        }
        return verifyAccount(userId,tenderMoney);
    }

    @Override
    public AccountOutput getUserAccount(String userId){
        return accountClient.getUserAccount(userId);
    }

}
