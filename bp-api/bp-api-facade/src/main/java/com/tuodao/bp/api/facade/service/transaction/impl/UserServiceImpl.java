package com.tuodao.bp.api.facade.service.transaction.impl;

import com.tuodao.bp.api.facade.client.useraccount.AccountClient;
import com.tuodao.bp.api.facade.service.transaction.UserService;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.model.business.useraccount.input.ValidatePayPwInput;
import com.tuodao.bp.model.constant.common.UserConstant;
import com.tuodao.bp.result.error.TransactError;
import com.tuodao.bp.result.exception.MicroServiceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/9/13
 * @time: 19:38
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private AccountClient accountClient;

    @Override
    public void verifyUserPayPassword(String userId, String payPassword) {
        ValidatePayPwInput input = new ValidatePayPwInput();
        input.setPayPw(payPassword);
        input.setUserId(userId);
        boolean success = accountClient.validatePayPw(input);
        if(!success){
            throw new MicroServiceException(TransactError.USER_PAY_ERROR);
        }
    }

    @Override
    public void verifyUserCommon(UserAccountInfo user,boolean verifyInner) {
        if(StringUtils.isBlank(user.getDepositNo())){
            throw new MicroServiceException(TransactError.BANK_STATUS_ERROR);
        }
        if(user.getUserType() == UserConstant.USER_TYPE_2){
            throw new MicroServiceException(TransactError.BORROWER_TENDER_ERROR);
        }
        if(user.getUserStatus() != UserConstant.USER_STATUS){
            throw new MicroServiceException(TransactError.USER_LOCK_FROST);
        }
        if(verifyInner && user.getInvestUserType() == UserConstant.INVITE_USER_TYPE_2){
            throw new MicroServiceException(TransactError.USER_TYPE_ERROR);
        }
    }
}
