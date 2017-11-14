package com.tuodao.bp.operation.persistence.model.basic;

import java.io.Serializable;
import java.util.Date;

public class OpUserScore implements Serializable {
    private String userId;

    private String userMobile;

    private Integer scoreTotal;

    private Integer scoreUsed;

    private Integer scoreInvalid;

    private Integer scoreCurrent;

    private Integer scopeExpireYear;

    private Date gmtCreate;

    private Date gmtModify;

    private String gmtCreator;

    private String gmtModifier;

    private Integer isDel;

    private static final long serialVersionUID = 1L;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile == null ? null : userMobile.trim();
    }

    public Integer getScoreTotal() {
        return scoreTotal;
    }

    public void setScoreTotal(Integer scoreTotal) {
        this.scoreTotal = scoreTotal;
    }

    public Integer getScoreUsed() {
        return scoreUsed;
    }

    public void setScoreUsed(Integer scoreUsed) {
        this.scoreUsed = scoreUsed;
    }

    public Integer getScoreInvalid() {
        return scoreInvalid;
    }

    public void setScoreInvalid(Integer scoreInvalid) {
        this.scoreInvalid = scoreInvalid;
    }

    public Integer getScoreCurrent() {
        return scoreCurrent;
    }

    public void setScoreCurrent(Integer scoreCurrent) {
        this.scoreCurrent = scoreCurrent;
    }

    public Integer getScopeExpireYear() {
        return scopeExpireYear;
    }

    public void setScopeExpireYear(Integer scopeExpireYear) {
        this.scopeExpireYear = scopeExpireYear;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
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

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}