package com.tuodao.bp.operation.persistence.model.basic;

import java.io.Serializable;
import java.util.Date;

public class OpUserLabel implements Serializable {
    private Integer id;

    private String userId;

    private String userLabel30;

    private String userLabel60;

    private String userLabel90;

    private String renark;

    private Date gmtCreate;

    private Date gmtModify;

    private String gmtCreator;

    private String gmtModifier;

    private Integer isDel;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserLabel30() {
        return userLabel30;
    }

    public void setUserLabel30(String userLabel30) {
        this.userLabel30 = userLabel30 == null ? null : userLabel30.trim();
    }

    public String getUserLabel60() {
        return userLabel60;
    }

    public void setUserLabel60(String userLabel60) {
        this.userLabel60 = userLabel60 == null ? null : userLabel60.trim();
    }

    public String getUserLabel90() {
        return userLabel90;
    }

    public void setUserLabel90(String userLabel90) {
        this.userLabel90 = userLabel90 == null ? null : userLabel90.trim();
    }

    public String getRenark() {
        return renark;
    }

    public void setRenark(String renark) {
        this.renark = renark == null ? null : renark.trim();
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