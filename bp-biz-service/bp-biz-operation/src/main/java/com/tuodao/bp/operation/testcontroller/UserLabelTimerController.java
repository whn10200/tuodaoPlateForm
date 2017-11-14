package com.tuodao.bp.operation.testcontroller;

import com.tuodao.bp.operation.service.IUserLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 用户标签测试类
 * User: zkai
 * Date: 2017/10/12
 * Time: 16:56
 * Copyright:拓道金服 Copyright (c) 2017
 */
@RestController
@RequestMapping("/demo")
public class UserLabelTimerController {

    @Autowired
    private IUserLabelService userLabelService;
    /**
     * 新手任务定时推送测试
     * @param
     */
    @RequestMapping(value="/userLabelTimer",method= RequestMethod.POST)
    public void userLabelTimer() {
        userLabelService.timeClockUpdateLable();
    }
}
