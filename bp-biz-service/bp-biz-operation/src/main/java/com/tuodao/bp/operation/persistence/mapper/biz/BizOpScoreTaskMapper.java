package com.tuodao.bp.operation.persistence.mapper.biz;

import com.tuodao.bp.model.business.operation.input.UserTaskListInput;
import com.tuodao.bp.model.business.operation.output.UserTaskListOutput;

import java.util.List;

/**
 * Description: 自定义积分任务Mapper
 * User: zkai
 * Date: 2017/9/20
 * Time: 14:32
 * Copyright:拓道金服 Copyright (c) 2017
 */
public interface BizOpScoreTaskMapper {

    /**
     * 查找用户任务
     * @param userScoreTaskListInput
     * @return
     */
    List<UserTaskListOutput> findUserTask(UserTaskListInput userScoreTaskListInput);
}
