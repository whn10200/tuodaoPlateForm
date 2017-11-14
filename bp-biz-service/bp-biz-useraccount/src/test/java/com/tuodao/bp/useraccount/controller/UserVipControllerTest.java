package com.tuodao.bp.useraccount.controller;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.useraccount.UserAccountTestApi;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.junit.Assert.*;

/**
 * @description: ${Description}
 * @author: mif
 * @date: 2017/10/25
 * @time: 09:16
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class UserVipControllerTest extends UserAccountTestApi {

    @Test
    public void getUserVip() throws Exception {
        url = "ua/userVip/getUserVipInfo";
        BasePojo basePojo = new BasePojo();
        basePojo.setUserId("15988888915-phl9fxv5ihckgdnm4zy9");
        doPost(MediaType.APPLICATION_JSON_UTF8, basePojo);
    }

}