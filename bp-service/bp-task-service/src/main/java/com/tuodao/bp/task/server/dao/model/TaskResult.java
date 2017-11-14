package com.tuodao.bp.task.server.dao.model;

import java.io.Serializable;
import java.util.Date;

public class TaskResult implements Serializable {
    private Integer id;

    private Integer taskid;

    private Date lastruntime;

    private Date lastendtime;

    private Integer runserverid;

    private Integer status;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTaskid() {
        return taskid;
    }

    public void setTaskid(Integer taskid) {
        this.taskid = taskid;
    }

    public Date getLastruntime() {
        return lastruntime;
    }

    public void setLastruntime(Date lastruntime) {
        this.lastruntime = lastruntime;
    }

    public Date getLastendtime() {
        return lastendtime;
    }

    public void setLastendtime(Date lastendtime) {
        this.lastendtime = lastendtime;
    }

    public Integer getRunserverid() {
        return runserverid;
    }

    public void setRunserverid(Integer runserverid) {
        this.runserverid = runserverid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}