package com.tuodao.bp.operation.controller;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.IdInput;
import com.tuodao.bp.operation.OperationTestApi;
import org.junit.Test;
import org.springframework.data.annotation.Id;
import org.springframework.http.MediaType;

import static org.junit.Assert.*;

/**
 * @description: ${Description}
 * @author: mif
 * @date: 2017/10/12
 * @time: 09:25
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class UserDiscountsControllerTest extends OperationTestApi {
    @Test
    public void getUserDiscountPagedList() throws Exception {
    }

    @Test
    public void getUserDiscountById() throws Exception {
        url = "userDiscounts/getUserDiscountById";
        IdInput idInput = new IdInput();
        idInput.setId(50L);
        idInput.setUserId("15988888925-caqdfgojsrcgna8oi8b8");
        doPost(MediaType.APPLICATION_JSON_UTF8, idInput);
    }

    @Test
    public void getRegisterWelfareDiscounts() throws Exception {
        url = "userDiscounts/getRegisterWelfareDiscounts";
        BasePojo basePojo = new BasePojo();
        basePojo.setUserId("15988888933-bd1qqqk6wxmwpkf61ko2");
        doPost(MediaType.APPLICATION_JSON_UTF8, basePojo);
    }

}