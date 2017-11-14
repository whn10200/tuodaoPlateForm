package com.tuodao.bp.operation.controller;

import com.tuodao.bp.model.PagePojo;
import com.tuodao.bp.operation.OperationTestApi;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.junit.Assert.*;

/**
 * @description: ${Description}
 * @author: mif
 * @date: 2017/10/9
 * @time: 17:53
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class InviteControllerTest extends OperationTestApi {

    @Test
    public void getInviteRecord() throws Exception {
        url = "operation/invite/getInviteRecord";
        PagePojo pagePojo = new PagePojo();
        pagePojo.setUserId("15988888915-phl9fxv5ihckgdnm4zy9");
        doPost(MediaType.APPLICATION_JSON_UTF8, pagePojo);
    }

    @Test
    public void getTotalInviteAward() throws Exception {
        url = "operation/invite/getTotalInviteAward";
        PagePojo pagePojo = new PagePojo();
        pagePojo.setUserId("15988888934-lcym383a4hpw1jwmvhjk");
        doPost(MediaType.APPLICATION_JSON_UTF8, pagePojo);
    }
}