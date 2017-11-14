package com.tuodao.bp.common.controller;

import com.alibaba.fastjson.JSON;
import com.tuodao.bp.common.CommonTestApi;
import com.tuodao.bp.db.model.basic.ConfigPushTemp;
import com.tuodao.bp.model.business.common.input.PushInput;
import org.junit.Test;
import org.springframework.http.MediaType;

/**
 * @description: 消息推送记录
 * @author: zkai
 * @date: 2017/10/27
 * @time: 10:33
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class PushControllerTest extends CommonTestApi {
    @Test
    public void pushMessage() throws Exception {
        url = "common/pushMessage";
        PushInput pushInput = new PushInput();
        // 推送对象 1:所有平台 2: 推送到对应别名的设备上 3:推送给安卓 4:推送给ios
        pushInput.setPushObject(4);
        // 透传内容：格式
        ConfigPushTemp configPushTemp = new ConfigPushTemp();

        configPushTemp.setTitle("消息推送测试标题IOS");
        configPushTemp.setContent("推送消息测试内容IOS");
        pushInput.setPushContent(JSON.toJSONString(configPushTemp));
//        pushInput.setPushAlias("18203659661");
//        pushInput.setPushTime();
        doPost(MediaType.APPLICATION_JSON_UTF8, pushInput);
    }

}