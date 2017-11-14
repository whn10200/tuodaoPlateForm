package com.tuodao.bp.model.mq;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * Description: 运营中心积分同步mq
 * User: zkai
 * Date: 2017/9/20
 * Time: 20:32
 * Copyright:拓道金服 Copyright (c) 2017
 */
public class OpScoreTaskMqInput  implements Serializable {
    private static final long serialVersionUID = -6917870803046763118L;

    /**
     * 任务编号
     */
    private Long taskId;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 用户电话号码
     */
    private String mobile;

    /**
     * 来源
     */
    private String source;

    /**
     * 积分
     */
    private Integer score;

    /**
     * 备注
     */
    private String remark;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("taskId", taskId)
                .add("userId", userId)
                .add("mobile", mobile)
                .add("source", source)
                .add("score", score)
                .add("remark", remark)
                .toString();
    }
}
