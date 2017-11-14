package com.tuodao.bp.api.facade.client.transaction;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.facade.traningcenter.input.CashListParam;
import com.tuodao.bp.model.facade.traningcenter.output.AccountCashVo;
import com.tuodao.bp.model.traningcenter.input.AccountCashApplyInput;
import com.tuodao.bp.model.traningcenter.output.AccountCashOutput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description: 提现接口
 * @author: 王艳兵
 * @date: 2017/9/12
 * @time: 18:11
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@FeignClient("BIZ-TRANING-CENTER")
public interface AccountCashClient {

    /**
     * 根据用户id获取已经提现的总额
     * @param userId
     * @return
     */
    @RequestMapping(value = "/account/getCashTotalByUserId",consumes = MediaType.APPLICATION_JSON_VALUE)
    AccountCashOutput getCashTotalByUserId(@RequestParam("userId") String userId);


    /**
     * 提现申请请求银行
     * @param input
     */
    @RequestMapping(value = "/account/cashBankRequest",consumes = MediaType.APPLICATION_JSON_VALUE)
    void cashBankRequest(AccountCashApplyInput input);



    /**
     * 分页获取用户的提现记录
     * @param param
     * @return
     */
    @RequestMapping(value = "/user/account/getUserCashListByPage",consumes = MediaType.APPLICATION_JSON_VALUE)
    PageInfo<AccountCashVo> getUserCashListByPage(CashListParam param);
}
