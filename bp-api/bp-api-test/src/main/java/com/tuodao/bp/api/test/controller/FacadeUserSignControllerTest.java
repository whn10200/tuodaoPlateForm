package com.tuodao.bp.api.test.controller;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.unit.test.BaseAPI;
import com.tuodao.bp.unit.test.enums.ContentType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Description: 用户签到测试类
 * User: zkai
 * Date: 2017/9/26
 * Time: 10:58
 * Copyright:拓道金服 Copyright (c) 2017
 */
public class FacadeUserSignControllerTest extends BaseAPI {

    @Before
    public void setUp(){
        this.url = "http://localhost:8088/api/router/";
        this.accessId = "2cb531b2600ff43644acae3748cb3148";
        this.accessKey = "v8anwbjadeazga4aduazqayadkamwbkadmazqa2admaywa0admaywayadkanga4agiaoqbkagiayqa4adaanga4";
    }

    /**
     * 用户签到
     */
    @Test
    public void userSign() throws Exception {
        BasePojo input = new BasePojo();
//        input.setUserId("0af40acde36d45c391c3718f15e28d97");
        doService("op/userSign", ContentType.JSON, input);
    }

    /**
     * 判断用户是否签到
     */
    @Test
    public void checkSign() throws Exception {
        BasePojo input = new BasePojo();
//        input.setUserId("0af40acde36d45c391c3718f15e28d97");
        doService("op/checkSign", ContentType.JSON, input);
    }

    @After
    public void complete() {
        responseJSON();
    }
}
