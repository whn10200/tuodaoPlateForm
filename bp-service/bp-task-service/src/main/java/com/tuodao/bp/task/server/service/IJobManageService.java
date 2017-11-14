package com.tuodao.bp.task.server.service;

import com.tuodao.bp.task.server.dao.model.Task;

/**
 * Description:
 * User: sxj
 * Date: 2017/5/22 0022
 * Time: 17:51
 * Copyright:拓道金服 Copyright (c) 2017
 */
public interface IJobManageService {
    /**
     * 添加任务
     * @param businessTask
     */
    void addJob(Task businessTask);

    /**
     * 修改任务
     * @param businessTask
     */
    void modifyJobCorn(Task businessTask);

    /**
     * 暂停任务
     * @param businessTask
     */
    void pauseJob(Task businessTask);

    /**
     * 释放任务
     * @param businessTask
     */
    void resumeJob(Task businessTask);

    /**
     * 删除任务
     * @param businessTask
     */
    void deleteJob(Task businessTask);

    /**
     * 运行任务
     * @param businessTask
     */
    void runJob(Task businessTask);
}
