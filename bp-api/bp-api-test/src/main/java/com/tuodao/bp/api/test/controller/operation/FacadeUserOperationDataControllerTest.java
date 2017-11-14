package com.tuodao.bp.api.test.controller.operation;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.unit.test.enums.ContentType;
import org.junit.Test;

/**
 * Description:
 * User: zkai
 * Date: 2017/10/30
 * Time: 14:37
 * Copyright:拓道金服 Copyright (c) 2017
 */
public class FacadeUserOperationDataControllerTest extends OperationTest {

    /**
     * 交互文档_PC重构0914/index.html#g=1&p=我的帐户-账户总览
     * 获取用户运营数据
     * @return
     */
    @Test
    public void getUserOperationData(){
        BasePojo input = new BasePojo();
        doServiceCalc("op/getUserOperationData", ContentType.JSON, input);
    }
}
