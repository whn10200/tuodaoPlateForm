package com.tuodao.bp.operation.persistence.model.basic;

import java.io.Serializable;
import java.util.Date;

public class OpInviteRool implements Serializable {
    private Integer id;

    private String roolName;

    private String rewardType;

    private Integer recordCount;

    private Date gmtModify;

    private Date gmtCreate;

    private String gmtCreator;

    private String gmtModifier;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoolName() {
        return roolName;
    }

    public void setRoolName(String roolName) {
        this.roolName = roolName == null ? null : roolName.trim();
    }

    public String getRewardType() {
        return rewardType;
    }

    public void setRewardType(String rewardType) {
        this.rewardType = rewardType == null ? null : rewardType.trim();
    }

    public Integer getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtCreator() {
        return gmtCreator;
    }

    public void setGmtCreator(String gmtCreator) {
        this.gmtCreator = gmtCreator == null ? null : gmtCreator.trim();
    }

    public String getGmtModifier() {
        return gmtModifier;
    }

    public void setGmtModifier(String gmtModifier) {
        this.gmtModifier = gmtModifier == null ? null : gmtModifier.trim();
    }
}