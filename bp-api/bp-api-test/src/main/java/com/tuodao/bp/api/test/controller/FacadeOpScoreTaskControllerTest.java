package com.tuodao.bp.api.test.controller;

import com.tuodao.bp.model.business.operation.input.OpScoreTaskDetailInput;
import com.tuodao.bp.model.business.operation.input.UserTaskListInput;
import com.tuodao.bp.unit.test.BaseAPI;
import com.tuodao.bp.unit.test.enums.ContentType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Description: 聚合-运营中心用户任务关系相关测试
 * User: zkai
 * Date: 2017/9/22
 * Time: 9:52
 * Copyright:拓道金服 Copyright (c) 2017
 */
public class FacadeOpScoreTaskControllerTest extends BaseAPI {

    @Before
    public void setUp(){
        this.url = "http://localhost:8088/api/router/";
        this.accessId = "af40e04cc8855d3184e4d6d18f9e0bd5";
        this.accessKey = "v8ayqa4aduanqbhagyamgazagyanwbkadiayqa1agiaywbmagiazqbmadkaywayadmaoqa2aguayqbkageaoabm";
    }

    /**
     * 查找用户任务(新手任务,日常任务)
     * @throws Exception
     */
    @Test
    public void findUserTask() throws Exception {
        UserTaskListInput input = new UserTaskListInput();
//        input.setUserId("0af40acde36d45c391c3718f15e28d97");
        input.setType(2);

        doService("op/findUserTask", ContentType.JSON, input);
    }

    /**
     * 根据用户编号,获取任务信息
     */
    @Test
    public void opScoreTaskDetail() throws Exception {
        OpScoreTaskDetailInput input = new OpScoreTaskDetailInput();
//        input.setUserId("0af40acde36d45c391c3718f15e28d97");
        input.setTaskId(1L);
        doService("op/opScoreTaskDetail", ContentType.JSON, input);
    }


    /**
     * 根据用户编号,获取用户积分明细信息
     */
    @Test
    public void userScoreDetailList() throws Exception {
        OpScoreTaskDetailInput input = new OpScoreTaskDetailInput();
//        input.setUserId("0af40acde36d45c391c3718f15e28d97");
        doService("op/userScoreDetailList", ContentType.JSON, input);
    }

    @After
    public void complete() {
        responseJSON();
    }
}
