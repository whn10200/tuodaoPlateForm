package com.tuodao.bp.useraccount.controller;

import com.google.common.collect.Lists;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.UserIdListInput;
import com.tuodao.bp.model.business.useraccount.input.OpenDepositInput;
import com.tuodao.bp.model.business.useraccount.input.ValidatePayPwInput;
import com.tuodao.bp.useraccount.UserAccountTestApi;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.util.List;

/**
 * @description: ${Description}
 * @author: mif
 * @date: 2017/9/4
 * @time: 16:29
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class AccountControllerTest extends UserAccountTestApi {
    @Test
    public void openDeposit() throws Exception {
        url = "ua/account/openDeposit";
        OpenDepositInput input = new OpenDepositInput();
        input.setUserId("3a148684c95d491597e7cbb22b97c74e");
        input.setMobile("15988888888");
        input.setRealName("realName");
        input.setIdCard("330724199212190712");
        input.setReservationMobile("15988888888");
        input.setBankCode("ACBC");
        input.setBankNum("8888888888888888");
//        input.setDepositNo("D-001");

        doPost(MediaType.APPLICATION_JSON_UTF8, input);
    }


    @Test
    public void getFinancialPlanner() throws Exception {
        url = "ua/account/getFinancialPlanner";
        BasePojo basePojo = new BasePojo();
        basePojo.setUserId("15988888812-unv41rmip6nruy11tlem");

        doPost(MediaType.APPLICATION_JSON_UTF8, basePojo);
    }


    @Test
    public void validatePayPw() throws Exception {
        url = "ua/account/validatePayPw";
        ValidatePayPwInput input = new ValidatePayPwInput();
        input.setUserId("15988888811-4h1fjfjeey863ouk41zh");
        input.setPayPw("123456");

        doPost(MediaType.APPLICATION_JSON_UTF8, input);
    }

    @Test
    public void getDepositNoList() throws Exception {
        url = "ua/account/getDepositNoList";
        UserIdListInput idListInput = new UserIdListInput();
        List<String> userIds = Lists.newArrayList();
        userIds.add("15957178759-3ih6jbus2db1zluio58b");
        userIds.add("3123");
        userIds.add("15988888888-e56fsscdd2o3u8ditin4");
        userIds.add("3a148684c95d491597e7cbb22b97c74e");
        idListInput.setUserId("15957178759-3ih6jbus2db1zluio58b");
        idListInput.setUserIds(userIds);
        doPost(MediaType.APPLICATION_JSON_UTF8, idListInput);
    }

}