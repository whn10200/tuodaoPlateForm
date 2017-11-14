package com.tuodao.bp.task.server.dao.model;

import java.io.Serializable;
import java.util.Date;

public class Business implements Serializable {
    private Integer businessid;

    private Integer notify;

    private Integer notifymanid;

    private Date createtime;

    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public Integer getBusinessid() {
        return businessid;
    }

    public void setBusinessid(Integer businessid) {
        this.businessid = businessid;
    }

    public Integer getNotify() {
        return notify;
    }

    public void setNotify(Integer notify) {
        this.notify = notify;
    }

    public Integer getNotifymanid() {
        return notifymanid;
    }

    public void setNotifymanid(Integer notifymanid) {
        this.notifymanid = notifymanid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}