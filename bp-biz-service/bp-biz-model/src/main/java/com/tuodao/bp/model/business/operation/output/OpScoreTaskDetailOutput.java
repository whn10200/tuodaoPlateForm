package com.tuodao.bp.model.business.operation.output;

import java.io.Serializable;

/**
 * Description: 任务详细返回接口
 * User: zkai
 * Date: 2017/9/21
 * Time: 19:31
 * Copyright:拓道金服 Copyright (c) 2017
 */
public class OpScoreTaskDetailOutput  implements Serializable {

    private static final long serialVersionUID = 7396400002315799182L;

    private Long id;

    private String taskName;

    private Integer score;

    private Integer type;

    private String description;

    private String todo;

    private String url;


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

}
