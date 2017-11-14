package com.tuodao.bp.useraccount.controller;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.useraccount.UserAccountTestApi;
import org.junit.Test;
import org.springframework.http.MediaType;


/**
 * Description:
 * User: zkai
 * Date: 2017/9/19
 * Time: 20:46
 * Copyright:拓道金服 Copyright (c) 2017
 */
public class UserVipInfoControllerTest extends UserAccountTestApi {

    /**
     * 修改VIP等级
     * @throws Exception
     */
    @Test
    public void updateVipLevel() throws Exception {
        url = "demo/updateVipLevel";
        BasePojo input = new BasePojo();
        input.setUserId("0af40acde36d45c391c3718f15e28d97");
        doPost(MediaType.APPLICATION_JSON, input);
    }
}
