package com.tuodao.bp.task.server.dao.model;

import java.io.Serializable;
import java.util.Date;

public class BusinessServer implements Serializable {
    private Integer serverid;

    private Integer businessid;

    private String serveradd;

    private Date createtime;

    private Date updatetime;

    private static final long serialVersionUID = 1L;

    public Integer getServerid() {
        return serverid;
    }

    public void setServerid(Integer serverid) {
        this.serverid = serverid;
    }

    public Integer getBusinessid() {
        return businessid;
    }

    public void setBusinessid(Integer businessid) {
        this.businessid = businessid;
    }

    public String getServeradd() {
        return serveradd;
    }

    public void setServeradd(String serveradd) {
        this.serveradd = serveradd == null ? null : serveradd.trim();
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