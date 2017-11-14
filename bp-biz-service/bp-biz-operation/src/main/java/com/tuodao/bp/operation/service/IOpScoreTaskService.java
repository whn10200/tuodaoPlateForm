package com.tuodao.bp.operation.service;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.business.operation.input.OpScoreTaskDetailInput;
import com.tuodao.bp.model.business.operation.input.UserTaskListInput;
import com.tuodao.bp.model.business.operation.output.OpScoreTaskDetailOutput;
import com.tuodao.bp.model.business.operation.output.UserTaskListOutput;
import com.tuodao.bp.model.facade.operation.input.UserScoreDetailListInput;
import com.tuodao.bp.model.facade.operation.output.OpUserScoreDetailListOutput;
import com.tuodao.bp.model.mq.OpScoreTaskMqInput;

/**
 * Description: 积分任务相关service
 * User: zkai
 * Date: 2017/9/20
 * Time: 15:29
 * Copyright:拓道金服 Copyright (c) 2017
 */
public interface IOpScoreTaskService {
    /**
     * 查找用户任务
     * @param userScoreTaskListInput
     * @return
     */
    PageInfo<UserTaskListOutput> findUserTask(UserTaskListInput userScoreTaskListInput);

    /**
     * 用户完成任务，添加 用户任务关系表
     * @param input
     */
    void userCompleteTask(OpScoreTaskMqInput input);

    /**
     * 获取 积分任务详细 信息
     * @param input
     * @return
     */
    OpScoreTaskDetailOutput OpScoreTaskDetail(OpScoreTaskDetailInput input);

    /**
     * 获取 用户积分明细表
     * @param input
     * @return
     */
    PageInfo<OpUserScoreDetailListOutput> getUserScoreDetailList(UserScoreDetailListInput input);
}
