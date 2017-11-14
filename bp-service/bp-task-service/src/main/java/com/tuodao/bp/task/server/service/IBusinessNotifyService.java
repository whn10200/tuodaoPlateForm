package com.tuodao.bp.task.server.service;

/**
 * Description:
 * User: sxj
 * Date: 2017/5/22 0022
 * Time: 20:42
 * Copyright:拓道金服 Copyright (c) 2017
 */
public interface IBusinessNotifyService {

    void notifyBusiness(int taskId);

    String callBack(int taskId);

    void callBackMQ(int taskId);
}
