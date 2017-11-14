package com.tuodao.bp.operation.persistence.model.basic;

import java.io.Serializable;

public class OpTaskPushInfo implements Serializable {
    private Long id;

    private Integer triggerTime;

    private String completedTasks;

    private String incompletedTasks;

    private String remark;

    private String smsCode;

    private String pushCode;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(Integer triggerTime) {
        this.triggerTime = triggerTime;
    }

    public String getCompletedTasks() {
        return completedTasks;
    }

    public void setCompletedTasks(String completedTasks) {
        this.completedTasks = completedTasks == null ? null : completedTasks.trim();
    }

    public String getIncompletedTasks() {
        return incompletedTasks;
    }

    public void setIncompletedTasks(String incompletedTasks) {
        this.incompletedTasks = incompletedTasks == null ? null : incompletedTasks.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode == null ? null : smsCode.trim();
    }

    public String getPushCode() {
        return pushCode;
    }

    public void setPushCode(String pushCode) {
        this.pushCode = pushCode == null ? null : pushCode.trim();
    }
}