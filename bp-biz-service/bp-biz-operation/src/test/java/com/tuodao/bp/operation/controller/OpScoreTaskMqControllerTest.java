package com.tuodao.bp.operation.controller;

import com.tuodao.bp.model.mq.OpScoreTaskMqInput;
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
public class OpScoreTaskMqControllerTest extends OperationTestApi {

    /**
     * 用户积分同步
     * @throws Exception
     */
    @Test
    public void opScoreTaskMq() throws Exception {
        url = "demo/opScoreTaskMq";
        OpScoreTaskMqInput input = new OpScoreTaskMqInput();
        input.setUserId("0af40acde36d45c391c3718f15e28d97");
        input.setMobile("15988888891");
        input.setTaskId(11L);
        input.setRemark("签到积分");
        input.setScore(2);
        input.setSource("2017-09-22签到");
        doPost(MediaType.APPLICATION_JSON, input);
    }
}
