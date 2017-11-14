package com.tuodao.bp.operation.service;

/**
 * Description: 服务-新人任务消息推送service
 * User: zkai
 * Date: 2017/10/10
 * Time: 16:56
 * Copyright:拓道金服 Copyright (c) 2017
 */
public interface IOpTaskPushService {

    /**
     * 新人任务定时推送
     */
    void timedPush();
}
