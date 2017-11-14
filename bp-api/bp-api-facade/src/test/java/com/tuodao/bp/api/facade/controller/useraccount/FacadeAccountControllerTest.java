package com.tuodao.bp.api.facade.controller.useraccount;

import com.tuodao.bp.api.facade.FacadeTestApi;
import com.tuodao.bp.model.BasePojo;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.junit.Assert.*;

/**
 * @description: ${Description}
 * @author: mif
 * @date: 2017/9/20
 * @time: 11:13
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class FacadeAccountControllerTest extends FacadeTestApi {

    @Test
    public void getFinancialPlanner() throws Exception {
        url = "account/getFinancialPlanner";
        BasePojo basePojo = new BasePojo();
        basePojo.setUserId("15988888812-unv41rmip6nruy11tlem");

        doPost(MediaType.APPLICATION_JSON_UTF8, basePojo);
    }

}