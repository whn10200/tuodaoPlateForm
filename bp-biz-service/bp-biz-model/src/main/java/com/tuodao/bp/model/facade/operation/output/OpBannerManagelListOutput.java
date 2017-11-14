package com.tuodao.bp.model.facade.operation.output;

import java.io.Serializable;

/**
 * Description: banner管理 输出类
 * User: yinping
 * Date: 2017/11/2
 * Copyright:拓道金服 Copyright (c) 2017
 */
public class OpBannerManagelListOutput implements Serializable {

    private static final long serialVersionUID = 8231487619339601818L;

    private String bannerUrl;

    private String bannerImg;

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getBannerImg() {
        return bannerImg;
    }

    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg;
    }
}
