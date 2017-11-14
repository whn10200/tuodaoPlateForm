package com.tuodao.bp.operation.persistence.model.basic;

import java.io.Serializable;

public class OpLabelTask implements Serializable {
    private Integer taskId;

    private String taskName;

    private Long scoreTaskId;

    private String remark;

    private static final long serialVersionUID = 1L;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }

    public Long getScoreTaskId() {
        return scoreTaskId;
    }

    public void setScoreTaskId(Long scoreTaskId) {
        this.scoreTaskId = scoreTaskId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}