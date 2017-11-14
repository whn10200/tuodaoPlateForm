package com.tuodao.bp.api.test.controller;

import com.tuodao.bp.model.business.useraccount.input.OpenDepositInput;
import com.tuodao.bp.unit.test.BaseAPI;
import com.tuodao.bp.unit.test.enums.ContentType;
import com.tuodao.bp.utils.MD5Utils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Description:
 * User: zkai
 * Date: 2017/10/17
 * Time: 11:24
 * Copyright:拓道金服 Copyright (c) 2017
 */
public class FaceUserDspositControllerTest extends BaseAPI {
    @Before
    public void setUp(){
        this.url = "http://localhost:8088/api/router/";
        this.accessId = "36fce150503458e2354302f09c4ea23e";
        this.accessKey = "/v8azqa2agmazabkaduazaa2aguangblagqamqbhagqazabkaguaywa4agyaoqawadeamga5agqamaaxagmaoqbk";
    }

    /**
     * 用户开通存管
     */
    @Test
    public void openDeposit(){
        try{
            OpenDepositInput input = new OpenDepositInput();
            input.setRealName("realName");
            input.setIdCard("330724199212190712");
            input.setReservationMobile("18868807380");
            input.setBankCode("ACBC");
            input.setBankNum("8888888888888888");
            input.setPayPassword(MD5Utils.md5("123456"));
            doService("/ua/openDeposit", ContentType.JSON, input);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @After
    public void complete() {
        responseJSON();
    }
}
