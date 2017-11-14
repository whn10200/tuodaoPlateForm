package com.tuodao.bp.useraccount.controller;

import com.tuodao.bp.useraccount.UserAccountTestApi;
import com.tuodao.bp.model.BasePojo;
import org.junit.Test;
import org.springframework.http.MediaType;

/**
 * @description: ${Description}
 * @author: mif
 * @date: 2017/9/5
 * @time: 16:50
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class UserAccountControllerTest extends UserAccountTestApi {
    @Test
    public void getUserAccountInfo() throws Exception {
        url = "ua/getUserAccountInfo";
        BasePojo pojo = new BasePojo();
        pojo.setUserId("13012345678-xmvbys2eozwzpk4lojj1");
        doPost(MediaType.APPLICATION_JSON, pojo);
    }

}