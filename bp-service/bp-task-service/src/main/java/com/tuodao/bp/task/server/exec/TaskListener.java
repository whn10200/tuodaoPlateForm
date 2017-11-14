package com.tuodao.bp.task.server.exec;

import com.tuodao.bp.task.server.dao.model.Task;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description:
 * User: sxj
 * Date: 2017/5/24 0024
 * Time: 09:44
 * Copyright:拓道金服 Copyright (c) 2017
 */
public class TaskListener implements JobListener {

    private static final Logger logger = LoggerFactory.getLogger(TaskListener.class);

    @Override
    public String getName() {
        return "TaskListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        //TODO  任务执行之前
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        //这个方法正常情况下不执行,但是如果当TriggerListener中的vetoJobExecution方法返回true时,那么执行这个方法
    }

    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        if (e!=null){
            JobKey jobKey = (JobKey) jobExecutionContext.getJobDetail().getKey();
            JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
            Task task = (Task) jobDataMap.get(jobKey.getGroup() + "_" + jobKey.getName());
            logger.error("任务名称: [{}],出现异常，请尽快处理！异常信息：{}",task.getTaskname(),e.getMessage(),e);

            //TODO 发邮件、发短信
        }
    }
}
