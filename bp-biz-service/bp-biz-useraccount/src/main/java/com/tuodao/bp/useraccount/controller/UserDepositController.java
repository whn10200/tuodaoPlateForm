package com.tuodao.bp.useraccount.controller;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.useraccount.input.OpenDepositCallbackInput;
import com.tuodao.bp.model.business.useraccount.input.OpenDepositInput;
import com.tuodao.bp.model.business.useraccount.output.UserDepositOutput;
import com.tuodao.bp.model.facade.operation.input.FindBankInfoInput;
import com.tuodao.bp.model.facade.operation.output.FindBankInfoOutput;
import com.tuodao.bp.useraccount.service.IUserDepositService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description: 用户存管相关controller
 * User: zkai
 * Date: 2017/10/16
 * Time: 16:25
 * Copyright:拓道金服 Copyright (c) 2017
 */
@RestController
@RequestMapping(value = "ua/account")
public class UserDepositController {
    @Resource
    private IUserDepositService userDepositService;

    /**
     * 开通存管
     *
     * @param input 请求类型
     */
    @RequestMapping(value = "openDeposit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void openDeposit(OpenDepositInput input) {
        userDepositService.openDeposit(input);
    }


    /**
     * 开通开通存管回调
     *
     * @param input 请求类型
     */
    @Deprecated
    @RequestMapping(value = "openDepositCallback", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void openDepositCallback(OpenDepositCallbackInput input) {
        userDepositService.openDepositCallback(input);
    }


    /**
     * 用户存管信息
     *
     * @param basePojo 基础信息
     * @return 存管信息
     */
    @RequestMapping(value = "getUserDepositInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDepositOutput getUserDepositInfo(BasePojo basePojo) {
        return userDepositService.getUserDepositInfo(basePojo.getUserId());
    }

    /**
     * 根据银行卡号，获取银行信息
     *
     * @param input
     * @return
     */
    @RequestMapping(value = "findBanksByBankIds", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<FindBankInfoOutput> findBanksByBankIds(FindBankInfoInput input) {
        return userDepositService.findBanksByBankIds(input);
    }
}
