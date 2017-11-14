package com.tuodao.bp.useraccount.persistence.model.biz;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * @description: 地区信息
 * @author: mif
 * @date: 2017/9/21
 * @time: 14:08
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class RegionInfo implements Serializable {
    private static final long serialVersionUID = 3057036892366075805L;

    private String country = "";

    private String province = "";

    private String city = "";

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("country", country)
                .add("province", province)
                .add("city", city)
                .toString();
    }
}
