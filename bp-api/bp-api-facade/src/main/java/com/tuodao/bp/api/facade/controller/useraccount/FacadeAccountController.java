package com.tuodao.bp.api.facade.controller.useraccount;

import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.service.useraccount.IFacadeAccountService;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.annotation.AccessToken;
import com.tuodao.bp.model.business.useraccount.input.ValidatePayPwInput;
import com.tuodao.bp.model.business.useraccount.output.AccountSettingInfoOutput;
import com.tuodao.bp.model.business.useraccount.output.FinancialPlannerOutput;
import com.tuodao.bp.model.enums.AccessType;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description: facade account controller
 * @author: mif
 * @date: 2017/9/20
 * @time: 11:03
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@RestController
@RequestMapping(value = "router/account")
public class FacadeAccountController {

    @Resource
    private IFacadeAccountService facadeAccountService;

    /**
     * 获取理财师等级
     *
     * @param basePojo
     * @return
     */
    @RequestMapping(value = "getFinancialPlanner", method = RequestMethod.POST)
    @AccessToken(access = {AccessType.APP, AccessType.PC})
    public RespResult<FinancialPlannerOutput> getFinancialPlanner(BasePojo basePojo) {
        return RespResult.<FinancialPlannerOutput>create().setContent(facadeAccountService.getFinancialPlanner(basePojo));
    }

    /**
     * 验证支付密码
     *
     * @param input 支付密码
     * @return 是否正确
     */
    @RequestMapping(value = "validatePayPw", method = RequestMethod.POST)
    @AccessToken(access = {AccessType.APP, AccessType.PC})
    public RespResult<Boolean> validatePayPw(ValidatePayPwInput input) {
        return RespResult.<Boolean>create().setContent(facadeAccountService.validatePayPw(input));
    }


    /**
     * 获取用户账户设置信息
     *
     * @param basePojo 基础POJO
     * @return 用户账户设置信息
     */
    @RequestMapping(value = "getAccountSetting", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @AccessToken(access = {AccessType.APP, AccessType.PC})
    public RespResult<AccountSettingInfoOutput> getAccountSetting(BasePojo basePojo) {
        return RespResult.<AccountSettingInfoOutput>create().setContent(facadeAccountService.getAccountSetting(basePojo));
    }

}
