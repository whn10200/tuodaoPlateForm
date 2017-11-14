package com.tuodao.bp.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Description: 积分任务缓存信息
 * User: zkai
 * Date: 2017/9/26
 * Time: 14:18
 * Copyright:拓道金服 Copyright (c) 2017
 */
public class OpScoreTaskCacheInfo implements Serializable {

    private static final long serialVersionUID = 11L;

    /**
     * 主键
     */
    private Long id;

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
     * url
     */
    private String url;

    /**
     * 是否删除(0：已删除；1:正常)
     */
    private Integer isDel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}
