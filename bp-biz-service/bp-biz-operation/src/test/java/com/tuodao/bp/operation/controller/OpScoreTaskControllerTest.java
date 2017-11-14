package com.tuodao.bp.operation.controller;

import com.tuodao.bp.model.business.operation.input.OpScoreTaskDetailInput;
import com.tuodao.bp.model.business.operation.input.UserTaskListInput;
import com.tuodao.bp.operation.OperationTestApi;
import org.junit.Test;
import org.springframework.http.MediaType;

/**
 * Description: 用户积分同步
 * User: zkai
 * Date: 2017/9/14
 * Time: 14:35
 * Copyright:拓道金服 Copyright (c) 2017
 */
public class OpScoreTaskControllerTest extends OperationTestApi {

    /**
     * 查找用户任务(新手任务,日常任务)
     * @throws Exception
     */
    @Test
    public void findUserTask() throws Exception {
        url = "operation/scoreTask/findUserTask";
        UserTaskListInput input = new UserTaskListInput();
        input.setUserId("0af40acde36d45c391c3718f15e28d97");
        input.setType(2);
        doPost(MediaType.APPLICATION_JSON, input);
    }

    /**
     * 根据用户编号,获取任务信息
     */
    @Test
    public void opScoreTaskDetail() throws Exception {
        url = "operation/scoreTask/opScoreTaskDetail";
        OpScoreTaskDetailInput input = new OpScoreTaskDetailInput();
        input.setUserId("0af40acde36d45c391c3718f15e28d97");
        input.setTaskId(1L);
        doPost(MediaType.APPLICATION_JSON, input);
    }
}
