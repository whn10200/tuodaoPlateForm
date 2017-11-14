package com.tuodao.bp.traningcenter.service.impl;

import com.tuodao.bp.model.traningcenter.output.AccountOutput;
import com.tuodao.bp.model.traningcenter.input.AccountInput;
import com.tuodao.bp.result.error.TransactError;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.traningcenter.db.mapper.basic.AccountMapper;
import com.tuodao.bp.traningcenter.db.model.basic.Account;
import com.tuodao.bp.traningcenter.mq.provider.ProducerMq;
import com.tuodao.bp.traningcenter.service.AccountService;
import com.tuodao.bp.utils.TransferUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @description: 账户信息service
 * @author: 王艳兵
 * @date: 2017/9/5
 * @time: 11:57
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;



    @Override
    public AccountOutput getUserAccount(String userId) {
        Account account = accountMapper.getByUserId(userId);
        if(account == null){
            throw new BizFeignException(TransactError.ACCOUNT_NOT_FOUND);
        }
        return TransferUtil.transferBean(account, AccountOutput.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createUserAccount(String userId) {
        Account account = new Account(userId);
        accountMapper.insertSelective(account);
    }

}
