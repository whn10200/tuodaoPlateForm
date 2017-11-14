package com.tuodao.bp.model.business.useraccount.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 用户详情
 * @author: mif
 * @date: 2017/10/12
 * @time: 14:47
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class UserInfoDetailOutput implements Serializable {

    private static final long serialVersionUID = -7544919371704477844L;

    private Integer sex;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date birthday;

    private String provinceName;

    private String cityName;

    private String constellation;

    private String consignee;

    private String consigneeMobile;

    private String consigneeAddress;

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getConsigneeMobile() {
        return consigneeMobile;
    }

    public void setConsigneeMobile(String consigneeMobile) {
        this.consigneeMobile = consigneeMobile;
    }

    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("sex", sex)
                .add("birthday", birthday)
                .add("provinceName", provinceName)
                .add("cityName", cityName)
                .add("constellation", constellation)
                .add("consignee", consignee)
                .add("consigneeMobile", consigneeMobile)
                .add("consigneeAddress", consigneeAddress)
                .toString();
    }
}
