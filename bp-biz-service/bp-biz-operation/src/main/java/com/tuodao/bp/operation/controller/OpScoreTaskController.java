package com.tuodao.bp.operation.controller;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.business.operation.input.OpScoreTaskDetailInput;
import com.tuodao.bp.model.business.operation.input.UserTaskListInput;
import com.tuodao.bp.model.business.operation.output.OpScoreTaskDetailOutput;
import com.tuodao.bp.model.business.operation.output.UserTaskListOutput;
import com.tuodao.bp.model.facade.operation.input.UserScoreDetailListInput;
import com.tuodao.bp.model.facade.operation.output.OpUserScoreDetailListOutput;
import com.tuodao.bp.operation.service.IOpScoreTaskService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description: 服务-积分任务修改controller
 * User: zkai
 * Date: 2017/9/21
 * Time: 14:24
 * Copyright:拓道金服 Copyright (c) 2017
 */
@RestController
@RequestMapping(value = "operation/scoreTask")
public class OpScoreTaskController {
    @Resource
    private IOpScoreTaskService opScoreTaskService;

    /**
     * 查找用户任务(新手任务,日常任务)
     *
     * @param input
     */
    @RequestMapping(value = "findUserTask", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<UserTaskListOutput> findUserTask(UserTaskListInput input) {
        return opScoreTaskService.findUserTask(input);
    }

    /**
     * 根据任务编号,获取任务信息
     *
     * @param input
     */
    @RequestMapping(value = "opScoreTaskDetail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public OpScoreTaskDetailOutput OpScoreTaskDetail(OpScoreTaskDetailInput input) {
        return opScoreTaskService.OpScoreTaskDetail(input);
    }

    /**
     * 根据用户编号,获取用户积分明细信息
     *
     * @param input
     */
    @RequestMapping(value = "userScoreDetailList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<OpUserScoreDetailListOutput> getUserScoreDetailList(UserScoreDetailListInput input) {
        return opScoreTaskService.getUserScoreDetailList(input);
    }
}
