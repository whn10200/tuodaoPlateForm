package com.tuodao.bp.model.business.operation.output;

import java.io.Serializable;

/**
 * Description: service 用户任务列表查询输出对象
 * User: zkai
 * Date: 2017/9/20
 * Time: 14:34
 * Copyright:拓道金服 Copyright (c) 2017
 */
public class UserTaskListOutput implements Serializable {

    private static final long serialVersionUID = 7396400002315799182L;

    /**
     * 任务编号
     */
    private Integer taskId;
    /**
     * 任务名称
     */
    private String taskName;
    /**
     * 奖励积分
     */
    private Integer score;

    /**
     * 任务类型(1:日常任务,2:新手任务)
     */
    private Integer type;

    /**
     * 奖励描述
     */
    private String description;

    /**
     * 去干嘛(文字描述如:去激活,去关注,去充值等)
     */
    private String todo;

    /**
     * 跳转链接地址
     */
    private String url;

    /**
     * 弹框内容(与url互斥，如果存在code，url地址无效)
     */
    private String code;

    /**
     * 是否完成任务（yes:完成 no:未完成）
     */
    private String isComplete;

    /**
     * 任务是否过期（yes:过期 no:未过期）
     */
    private String isOverdue = "no";

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
        this.taskName = taskName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(String isComplete) {
        this.isComplete = isComplete;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIsOverdue() {
        return isOverdue;
    }

    public void setIsOverdue(String isOverdue) {
        this.isOverdue = isOverdue;
    }
}
