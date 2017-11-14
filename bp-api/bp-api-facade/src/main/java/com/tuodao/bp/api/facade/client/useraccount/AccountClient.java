package com.tuodao.bp.api.facade.client.useraccount;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.UserIdListInput;
import com.tuodao.bp.model.business.useraccount.UserDepositNo;
import com.tuodao.bp.model.business.useraccount.input.ValidatePayPwInput;
import com.tuodao.bp.model.business.useraccount.output.AccountSettingInfoOutput;
import com.tuodao.bp.model.business.useraccount.output.FinancialPlannerOutput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @description: user account center account info feign client
 * @author: mif
 * @date: 2017/9/20
 * @time: 11:00
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@FeignClient(value = "BIZ-USER-ACCOUNT")
public interface AccountClient {

    /**
     * 获取理财师等级
     *
     * @param basePojo 输入参数
     * @return
     */
    @RequestMapping(value = "ua/account/getFinancialPlanner", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    FinancialPlannerOutput getFinancialPlanner(BasePojo basePojo);

    /**
     * 验证支付密码
     *
     * @param input 支付密码
     * @return 是否正确
     */
    @RequestMapping(value = "ua/account/validatePayPw", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Boolean validatePayPw(ValidatePayPwInput input);

    /**
     * 获取存管编码列表
     *
     * @param userIdListInput 用户ID列表
     * @return 存管列表
     */
    @RequestMapping(value = "ua/account/getDepositNoList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<UserDepositNo> getDepositNoList(UserIdListInput userIdListInput);

    /**
     * 获取用户账户设置信息
     *
     * @param basePojo 基础POJO
     * @return 用户账户设置信息
     */
    @RequestMapping(value = "ua/account/getAccountSetting", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    AccountSettingInfoOutput getAccountSetting(BasePojo basePojo);
}
