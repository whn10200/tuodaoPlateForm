package com.tuodao.bp.api.facade.client.useraccount;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.useraccount.input.OpenDepositInput;
import com.tuodao.bp.model.business.useraccount.output.UserDepositOutput;
import com.tuodao.bp.model.facade.operation.input.FindBankInfoInput;
import com.tuodao.bp.model.facade.operation.output.FindBankInfoOutput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Description: 用户存管FEIGN
 * User: zkai
 * Date: 2017/10/17
 * Time: 10:36
 * Copyright:拓道金服 Copyright (c) 2017
 */
@FeignClient(value = "BIZ-USER-ACCOUNT")
public interface UserDepositFegin {
    /**
     * 用户详情
     *
     * @param input 开通存管输入类
     */
    @RequestMapping(value = "ua/account/openDeposit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    void openDeposit(OpenDepositInput input);

    /**
     * 获取存管信息
     *
     * @param basePojo 基础信息
     * @return 存管信息
     */
    @RequestMapping(value = "ua/account/getUserDepositInfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    UserDepositOutput getUserDepositInfo(BasePojo basePojo);

    /**
     * 根据银行卡号，获取银行信息
     *
     * @param input
     * @return
     */
    @RequestMapping(value = "ua/account/findBanksByBankIds", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<FindBankInfoOutput> findBanksByBankIds(FindBankInfoInput input);
}
