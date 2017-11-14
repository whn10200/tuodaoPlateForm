package com.tuodao.bp.operation.persistence.model.basic;

import java.io.Serializable;
import java.util.Date;

public class OpBannerManager implements Serializable {
    private Integer bannerId;

    private String location;

    private Integer status;

    private String bannerName;

    private String bannerRemark;

    private Integer bannerFlag;

    private String bannerImg;

    private String bannerUrl;

    private Integer bannerSort;

    private Integer isShow;

    private Date beginTime;

    private Date endTime;

    private Date gmtCreate;

    private Date gmtModify;

    private String gmtCreator;

    private String gmtModifier;

    private Integer isDel;

    private Integer bannerType;

    private Integer isStick;

    private Integer isAlwaysUse;

    private static final long serialVersionUID = 1L;

    public Integer getBannerId() {
        return bannerId;
    }

    public void setBannerId(Integer bannerId) {
        this.bannerId = bannerId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBannerName() {
        return bannerName;
    }

    public void setBannerName(String bannerName) {
        this.bannerName = bannerName == null ? null : bannerName.trim();
    }

    public String getBannerRemark() {
        return bannerRemark;
    }

    public void setBannerRemark(String bannerRemark) {
        this.bannerRemark = bannerRemark == null ? null : bannerRemark.trim();
    }

    public Integer getBannerFlag() {
        return bannerFlag;
    }

    public void setBannerFlag(Integer bannerFlag) {
        this.bannerFlag = bannerFlag;
    }

    public String getBannerImg() {
        return bannerImg;
    }

    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg == null ? null : bannerImg.trim();
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl == null ? null : bannerUrl.trim();
    }

    public Integer getBannerSort() {
        return bannerSort;
    }

    public void setBannerSort(Integer bannerSort) {
        this.bannerSort = bannerSort;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public Integer getBannerType() {
        return bannerType;
    }

    public void setBannerType(Integer bannerType) {
        this.bannerType = bannerType;
    }

    public Integer getIsStick() {
        return isStick;
    }

    public void setIsStick(Integer isStick) {
        this.isStick = isStick;
    }

    public Integer getIsAlwaysUse() {
        return isAlwaysUse;
    }

    public void setIsAlwaysUse(Integer isAlwaysUse) {
        this.isAlwaysUse = isAlwaysUse;
    }
}