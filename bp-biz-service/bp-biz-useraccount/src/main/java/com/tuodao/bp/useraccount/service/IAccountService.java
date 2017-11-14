package com.tuodao.bp.useraccount.service;

import com.tuodao.bp.model.business.useraccount.UserDepositNo;
import com.tuodao.bp.model.business.useraccount.input.ValidatePayPwInput;
import com.tuodao.bp.model.business.useraccount.output.AccountSettingInfoOutput;
import com.tuodao.bp.model.business.useraccount.output.FinancialPlannerOutput;

import java.util.List;

/**
 * @description: 账户相关服务接口
 * @author: mif
 * @date: 2017/8/28
 * @time: 16:28
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public interface IAccountService {
    /**
     * 获取用户理财师等级
     *
     * @param userId 用户编码
     */
    FinancialPlannerOutput selectUserFinancialPlanner(String userId);

    /**
     * 验证支付密码
     *
     * @param input 支付密码
     * @return 是否正确
     */
    Boolean validatePayPw(ValidatePayPwInput input);

    /**
     * 获取存管编码列表
     *
     * @param userIds 用户编码列表
     * @return 存管编码列表
     */
    List<UserDepositNo> getDepositNoList(List<String> userIds);

    /**
     * 获取用户账户设置信息
     *
     * @param userId 用户ID
     * @return 用户账户设置信息
     */
    AccountSettingInfoOutput getAccountSetting(String userId);
}
