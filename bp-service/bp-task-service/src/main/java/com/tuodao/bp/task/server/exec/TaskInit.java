package com.tuodao.bp.task.server.exec;

import com.google.common.base.Objects;
import com.tuodao.bp.task.server.constant.EnumConstant;
import com.tuodao.bp.task.server.dao.mapper.TaskMapper;
import com.tuodao.bp.task.server.dao.model.Task;
import com.tuodao.bp.task.server.dao.model.TaskExample;
import com.tuodao.bp.task.server.service.IJobManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:
 * User: sxj
 * Date: 2017/5/24 0024
 * Time: 09:21
 * Copyright:拓道金服 Copyright (c) 2017
 */
@Component
public class TaskInit {

    private static final Logger logger = LoggerFactory.getLogger(TaskInit.class);

    @Autowired
    TaskMapper taskMapper;

    @Autowired
    IJobManageService jobManageService;

    @PostConstruct
    public void init(){
        TaskExample example = new TaskExample();
        List<Task> list = taskMapper.selectByExample(example);
        for (Task task:list){
            jobManageService.deleteJob(task);
        }
        logger.info("清除原来task的遗留信息完成,清除个数:[{}]",list.size());

        List<Task> taskList = list.stream().filter(task -> Objects.equal(task.getStatus(), EnumConstant.TaskStatus.TASK_STATUS_1.getType())).collect(Collectors.toList());

        for (Task task:taskList) {
            jobManageService.addJob(task);
        }
        logger.info("定时钟添加完成，等待执行,添加个数:[{}]",taskList.size());
    }
}