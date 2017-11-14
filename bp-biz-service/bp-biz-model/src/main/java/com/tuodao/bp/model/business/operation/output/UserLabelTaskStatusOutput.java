package com.tuodao.bp.model.business.operation.output;

import java.io.Serializable;

/**
 * Description: 用户标签任务完成情况信息
 * User: zkai
 * Date: 2017/10/9
 * Time: 14:46
 * Copyright:拓道金服 Copyright (c) 2017
 */
public class UserLabelTaskStatusOutput implements Serializable {
    private static final long serialVersionUID = -9055123542078758664L;

    /**
     * 标签任务编号
     */
    private int labelTaskId;
    /**
     * 标签任务名称
     */
    private String labelTaskName;
    /**
     * 是否已完成
     */
    private String isComplete;

    public int getLabelTaskId() {
        return labelTaskId;
    }

    public void setLabelTaskId(int labelTaskId) {
        this.labelTaskId = labelTaskId;
    }

    public String getLabelTaskName() {
        return labelTaskName;
    }

    public void setLabelTaskName(String labelTaskName) {
        this.labelTaskName = labelTaskName;
    }

    public String getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(String isComplete) {
        this.isComplete = isComplete;
    }
}
