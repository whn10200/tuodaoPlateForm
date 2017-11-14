package com.tuodao.bp.operation.controller;

import com.tuodao.bp.model.business.operation.input.OpCooperationInput;
import com.tuodao.bp.model.business.operation.input.OpScoreTaskDetailInput;
import com.tuodao.bp.model.business.operation.input.UserTaskListInput;
import com.tuodao.bp.operation.OperationTestApi;
import org.junit.Test;
import org.springframework.http.MediaType;

/**
 * Description: 加盟商管理测试
 * Author: yinping
 * Date: 2017/9/25
 * Time: 14:35
 * Copyright:拓道金服 Copyright (c) 2017
 */
public class CooperationControllerTest extends OperationTestApi {

    /**
     * 加盟商管理測試
     * @throws Exception
     */
    @Test
    public void addCooperation() throws Exception {
        url = "cooperation/addOpCooperation";
        OpCooperationInput input = new OpCooperationInput();
        input.setUsername("李四");
        input.setMobile("15087938374");
        doPost(MediaType.APPLICATION_JSON, input);
    }
}
