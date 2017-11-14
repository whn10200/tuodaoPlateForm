package com.tuodao.bp.api.facade.service.useraccount;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.useraccount.input.ValidatePayPwInput;
import com.tuodao.bp.model.business.useraccount.output.AccountSettingInfoOutput;
import com.tuodao.bp.model.business.useraccount.output.FinancialPlannerOutput;

/**
 * @description: facade account service interface
 * @author: mif
 * @date: 2017/9/20
 * @time: 11:05
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public interface IFacadeAccountService {

    /**
     * 获取用户理财师等级信息
     *
     * @param basePojo
     * @return
     */
    FinancialPlannerOutput getFinancialPlanner(BasePojo basePojo);

    /**
     * 验证支付密码
     *
     * @param input 支付密码
     * @return 是否正确
     */
    Boolean validatePayPw(ValidatePayPwInput input);

    /**
     * 获取用户账户设置信息
     *
     * @param basePojo 基础POJO
     * @return 用户账户设置信息
     */
    AccountSettingInfoOutput getAccountSetting(BasePojo basePojo);
}
