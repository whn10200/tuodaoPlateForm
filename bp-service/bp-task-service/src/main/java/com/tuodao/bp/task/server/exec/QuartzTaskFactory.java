package com.tuodao.bp.task.server.exec;

import com.tuodao.bp.task.server.dao.model.Task;
import com.tuodao.bp.task.server.service.IBusinessNotifyService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description:
 * User: sxj
 * Date: 2017/5/22 0022
 * Time: 18:02
 * Copyright:拓道金服 Copyright (c) 2017
 */
@Component
public class QuartzTaskFactory implements Job {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(QuartzTaskFactory.class);

    @Autowired
    IBusinessNotifyService iBusinessNotifyService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        JobKey jobKey = (JobKey) context.getJobDetail().getKey();
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        Task task = (Task) jobDataMap.get(jobKey.getGroup() + "_" + jobKey.getName());
        logger.info("任务名称: [{}],开始运行...........", task.getTaskname());

        iBusinessNotifyService.notifyBusiness(Integer.parseInt(jobKey.getName()));

    }
}
