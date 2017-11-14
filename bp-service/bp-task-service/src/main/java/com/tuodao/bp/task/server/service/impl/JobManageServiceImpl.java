package com.tuodao.bp.task.server.service.impl;

import com.tuodao.bp.task.server.dao.mapper.TaskMapper;
import com.tuodao.bp.task.server.dao.model.Task;
import com.tuodao.bp.task.server.exec.QuartzTaskFactory;
import com.tuodao.bp.task.server.exec.TaskListener;
import com.tuodao.bp.task.server.service.IJobManageService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:
 * User: sxj
 * Date: 2017/5/22 0022
 * Time: 17:51
 * Copyright:拓道金服 Copyright (c) 2017
 */
@Service
public class JobManageServiceImpl implements IJobManageService {

    private static final Logger logger = LoggerFactory.getLogger(JobManageServiceImpl.class);

    @Autowired
    Scheduler scheduler;

    @Autowired
    TaskMapper taskMapper;

    @Override
    public void addJob(Task businessTask){

        String taskIdName = String.valueOf(businessTask.getTaskid());
        String taskIdGroup = String.valueOf(businessTask.getBusinessid());
        TriggerKey triggerKey = TriggerKey.triggerKey(taskIdName, taskIdGroup);

        try {
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (null == trigger) {
                // 构建job信息
                JobDetail job = JobBuilder.newJob(QuartzTaskFactory.class).withIdentity(taskIdName, taskIdGroup).build();
                // 构建job参数信息
                 job.getJobDataMap().put(taskIdGroup + "_" + taskIdName, businessTask);

                // 表达式调度构建器
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(businessTask.getCorn());
                // 按新的cronExpression表达式构建一个新的trigger
                trigger = TriggerBuilder.newTrigger().withIdentity(taskIdName, taskIdGroup).withSchedule(scheduleBuilder).build();

                scheduler.scheduleJob(job, trigger);
                //绑定监听
                scheduler.getListenerManager().addJobListener(new TaskListener());
            }else {
                // trigger已存在，则更新相应的定时设置
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(businessTask.getCorn());
                // 按新的cronExpression表达式重新构建trigger
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
                // 按新的trigger重新设置job执行
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        } catch (SchedulerException e) {
            logger.error("add task error,message:{}",e.getMessage(),e);
        }
    }

    @Override
    public void modifyJobCorn(Task businessTask) {

        TriggerKey triggerKey = TriggerKey.triggerKey(String.valueOf(businessTask.getTaskid()), String.valueOf(businessTask.getBusinessid()));

        //获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
        CronTrigger trigger = null;
        try {
            trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(businessTask.getCorn());

            //按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey)
                    .withSchedule(scheduleBuilder).build();

            //按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            logger.error("modify task error,message:{}",e.getMessage(),e);
        }
    }

    @Override
    public void pauseJob(Task businessTask) {
        JobKey jobKey = JobKey.jobKey(String.valueOf(businessTask.getTaskid()), String.valueOf(businessTask.getBusinessid()));
        try {
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            logger.error("pause task error,message:{}",e.getMessage(),e);
            e.printStackTrace();
        }
    }

    @Override
    public void resumeJob(Task businessTask){
        JobKey jobKey = JobKey.jobKey(String.valueOf(businessTask.getTaskid()), String.valueOf(businessTask.getBusinessid()));
        try {
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            logger.error("resume task error,message:{}",e.getMessage(),e);
        }
    }

    @Override
    public void deleteJob(Task businessTask) {
        JobKey jobKey = JobKey.jobKey(String.valueOf(businessTask.getTaskid()), String.valueOf(businessTask.getBusinessid()));
        try {
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            logger.error("delete task error,message:{}",e.getMessage(),e);
        }
    }

    @Override
    public void runJob(Task businessTask) {
        JobKey jobKey = JobKey.jobKey(String.valueOf(businessTask.getTaskid()), String.valueOf(businessTask.getBusinessid()));
        try {
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            logger.error("run task error,message:{}",e.getMessage(),e);
        }
    }
}
