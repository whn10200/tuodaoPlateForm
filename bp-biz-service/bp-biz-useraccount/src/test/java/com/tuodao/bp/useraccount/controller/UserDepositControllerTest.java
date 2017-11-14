package com.tuodao.bp.useraccount.controller;

import com.tuodao.bp.model.facade.operation.input.FindBankInfoInput;
import com.tuodao.bp.useraccount.UserAccountTestApi;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 用户存管相关controller
 * User: zkai
 * Date: 2017/10/16
 * Time: 16:25
 * Copyright:拓道金服 Copyright (c) 2017
 */
public class UserDepositControllerTest  extends UserAccountTestApi {
    @Test
    public void openDeposit() throws Exception {
        url = "ua/account/findBanksByBankIds";
        FindBankInfoInput input = new FindBankInfoInput();
        input.setUserId("0af40acde36d45c391c3718f15e28d97");
        List<String> bankIds = new ArrayList<>();
        bankIds.add("8888888888888888");
        bankIds.add("8888888888888887");
        input.setBankIds(bankIds);
        doPost(MediaType.APPLICATION_JSON_UTF8, input);
    }

}
