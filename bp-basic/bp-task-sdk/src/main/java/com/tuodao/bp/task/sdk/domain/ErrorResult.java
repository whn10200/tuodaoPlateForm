package com.tuodao.bp.task.sdk.domain;

import java.io.Serializable;

/**
 * author hechuan
 * <p>
 * created on 2017/9/22
 * <p>
 * since V1.0.0
 */
public class ErrorResult implements Serializable{

    private static final long serialVersionUID = 5812293264969319754L;

    /**
     * 执行IP
     */
    private String exeIp;

    /**
     * 执行的当前时间
     */
    private String exeTime;

    /**
     * 执行的任务名称
     */
    private String exeTaskName;

    /**
     * 执行的错误描述
     */
    private String exeDescription;

    public String getExeIp() {
        return exeIp;
    }

    public void setExeIp(String exeIp) {
        this.exeIp = exeIp;
    }

    public String getExeTime() {
        return exeTime;
    }

    public void setExeTime(String exeTime) {
        this.exeTime = exeTime;
    }

    public String getExeTaskName() {
        return exeTaskName;
    }

    public void setExeTaskName(String exeTaskName) {
        this.exeTaskName = exeTaskName;
    }

    public String getExeDescription() {
        return exeDescription;
    }

    public void setExeDescription(String exeDescription) {
        this.exeDescription = exeDescription;
    }
}
