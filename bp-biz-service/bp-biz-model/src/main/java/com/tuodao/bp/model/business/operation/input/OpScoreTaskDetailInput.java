package com.tuodao.bp.model.business.operation.input;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.constant.operation.OpParamExceptionConstant;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Description: 获取 积分任务详细 信息
 * User: zkai
 * Date: 2017/9/21
 * Time: 16:58
 * Copyright:拓道金服 Copyright (c) 2017
 */
public class OpScoreTaskDetailInput extends BasePojo implements Serializable {

    private static final long serialVersionUID = 7396400002315799183L;

    @NotNull(message = OpParamExceptionConstant.TASK_IS_NULL+"")
    private Long taskId;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}
