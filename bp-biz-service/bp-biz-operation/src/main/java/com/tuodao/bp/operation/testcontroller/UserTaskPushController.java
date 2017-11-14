package com.tuodao.bp.operation.testcontroller;

import com.tuodao.bp.operation.service.IOpTaskPushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:  新手任务定时推送测试controller
 * User: zkai
 * Date: 2017/9/19
 * Time: 20:13
 * Copyright:拓道金服 Copyright (c) 2017
 */
@RestController
@RequestMapping("/demo")
public class UserTaskPushController {

    @Autowired
    private IOpTaskPushService opTaskPushService;

    /**
     * 新手任务定时推送测试
     * @param
     */
    @RequestMapping(value="/noviceTaskPush",method= RequestMethod.POST)
    public void noviceTaskPush() {
        opTaskPushService.timedPush();
    }
}
