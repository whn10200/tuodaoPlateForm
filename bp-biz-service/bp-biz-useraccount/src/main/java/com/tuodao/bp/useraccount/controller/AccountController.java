package com.tuodao.bp.useraccount.controller;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.UserIdListInput;
import com.tuodao.bp.model.business.useraccount.UserDepositNo;
import com.tuodao.bp.model.business.useraccount.input.ValidatePayPwInput;
import com.tuodao.bp.model.business.useraccount.output.AccountSettingInfoOutput;
import com.tuodao.bp.model.business.useraccount.output.FinancialPlannerOutput;
import com.tuodao.bp.useraccount.service.IAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 账户相关控制类
 * @author: mif
 * @date: 2017/8/28
 * @time: 15:41
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@RestController
@RequestMapping(value = "ua/account")
public class AccountController {

    private final static Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Resource
    private IAccountService accountService;

    /**
     * 获取理财师等级
     *
     * @param basePojo
     * @return
     */
    @RequestMapping(value = "getFinancialPlanner", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public FinancialPlannerOutput getFinancialPlanner(BasePojo basePojo) {
        return accountService.selectUserFinancialPlanner(basePojo.getUserId());
    }

    /**
     * 验证支付密码
     *
     * @param input 支付密码
     * @return 是否正确
     */
    @RequestMapping(value = "validatePayPw", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean validatePayPw(ValidatePayPwInput input) {
        return accountService.validatePayPw(input);
    }

    /**
     * 获取存管编码列表
     *
     * @param userIdListInput 用户ID列表
     * @return 存管列表
     */
    @RequestMapping(value = "getDepositNoList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDepositNo> getDepositNoList(UserIdListInput userIdListInput) {
        return accountService.getDepositNoList(userIdListInput.getUserIds());
    }

    /**
     * 获取用户账户设置信息
     *
     * @param basePojo 基础POJO
     * @return 用户账户设置信息
     */
    @RequestMapping(value = "getAccountSetting", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public AccountSettingInfoOutput getAccountSetting(BasePojo basePojo) {
        return accountService.getAccountSetting(basePojo.getUserId());
    }

}
