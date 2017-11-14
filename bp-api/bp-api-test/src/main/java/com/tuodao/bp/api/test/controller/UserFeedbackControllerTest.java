package com.tuodao.bp.api.test.controller;

import com.tuodao.bp.model.facade.useraccout.input.FacadeAddUserFeedBack;
import com.tuodao.bp.unit.test.BaseAPI;
import com.tuodao.bp.unit.test.enums.ContentType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Description: 用户反馈相关测试类
 * User: zkai
 * Date: 2017/9/14
 * Time: 9:40
 * Copyright:拓道金服 Copyright (c) 2017
 */
public class UserFeedbackControllerTest extends BaseAPI {

    @Before
    public void setUp(){
        this.url = "http://localhost:8088/api/router/";
        this.accessId = "0ac6ac7b73e09ed28412ee389f9ed05f";
        this.accessKey = "accessKey";
    }

    /**
     * 用户详细查询
     */
    @Test
    public void addUserFeedBack(){
        try{
            FacadeAddUserFeedBack input = new FacadeAddUserFeedBack();
            // 终端来源:0:pc,1:ios,2:android,3:h5
            input.setSource(0);
            // 版本号
            input.setVersion("1.0");
            // 反馈内容
            input.setContent("用户反馈测试内容");
            // 电话号码
            input.setMobile("15988888891");
            // 短信验证码
//            input.setSmsCode("968194");

            input.setUserId("0af40acde36d45c391c3718f15e28d97");

            doService("/ua/addUserFeedBack", ContentType.JSON, input);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @After
    public void complete() {
        responseJSON();
    }
}
