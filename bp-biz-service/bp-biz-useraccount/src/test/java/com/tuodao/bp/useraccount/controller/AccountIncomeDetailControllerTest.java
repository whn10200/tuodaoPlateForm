package com.tuodao.bp.useraccount.controller;

import com.tuodao.bp.model.annotation.In;
import com.tuodao.bp.model.mq.AccountIncomeDetailMqInfo;
import com.tuodao.bp.useraccount.UserAccountTestApi;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

/**
 * Description: 账户累计收益明细测试类
 * User: zkai
 * Date: 2017/9/14
 * Time: 14:35
 * Copyright:拓道金服 Copyright (c) 2017
 */
public class AccountIncomeDetailControllerTest extends UserAccountTestApi {

    /**
     * 添加账户累计收益明细
     * @throws Exception
     */
    @Test
    public void addAccountIncomeDetail() throws Exception {
        url = "demo/addAccountIncomeDetail";
        AccountIncomeDetailMqInfo input = new AccountIncomeDetailMqInfo();
        input.setRemark("cs");
        input.setIncomeMoney(BigDecimal.valueOf(222));
//        input.setUserId("0af40acde36d45c391c3718f15e28d97");
//        input.setOperator("0af40acde36d45c391c3718f15e28d97");
//        input.setIncomeType("COUPON");
        doPost(MediaType.APPLICATION_JSON, input);
    }
}
