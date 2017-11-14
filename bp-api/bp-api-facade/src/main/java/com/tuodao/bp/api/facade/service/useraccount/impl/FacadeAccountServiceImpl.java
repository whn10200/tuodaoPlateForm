package com.tuodao.bp.api.facade.service.useraccount.impl;

import com.tuodao.bp.api.facade.client.useraccount.AccountClient;
import com.tuodao.bp.api.facade.service.useraccount.IFacadeAccountService;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.useraccount.input.ValidatePayPwInput;
import com.tuodao.bp.model.business.useraccount.output.AccountSettingInfoOutput;
import com.tuodao.bp.model.business.useraccount.output.FinancialPlannerOutput;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: facade account service interface implement
 * @author: mif
 * @date: 2017/9/20
 * @time: 11:07
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Service
public class FacadeAccountServiceImpl implements IFacadeAccountService {

    @Resource
    private AccountClient accountClient;

    @Override
    public FinancialPlannerOutput getFinancialPlanner(BasePojo basePojo) {
        return accountClient.getFinancialPlanner(basePojo);
    }

    /**
     * 验证支付密码
     *
     * @param input 支付密码
     * @return 是否正确
     */
    @Override
    public Boolean validatePayPw(ValidatePayPwInput input) {
        return accountClient.validatePayPw(input);
    }

    /**
     * 获取用户账户设置信息
     *
     * @param basePojo 基础POJO
     * @return 用户账户设置信息
     */
    @Override
    public AccountSettingInfoOutput getAccountSetting(BasePojo basePojo) {
        return accountClient.getAccountSetting(basePojo);
    }
}
