package com.tuodao.bp.model.business.operation.input;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.constant.operation.OpParamExceptionConstant;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Description: 根据bannerRemark查询banner
 * User: yinping
 * Date: 2017/11/2
 * Copyright:拓道金服 Copyright (c) 2017
 */
public class OpBannerManagerInput extends BasePojo implements Serializable {

    private static final long serialVersionUID = -6891055937382916376L;

    @NotNull(message = OpParamExceptionConstant.TASK_IS_NULL+"")
    private Integer bannerFlag;//区分ios android

    private Integer bannerType;//区分banner类型

    public Integer getBannerFlag() {
        return bannerFlag;
    }

    public void setBannerFlag(Integer bannerFlag) {
        this.bannerFlag = bannerFlag;
    }

    public Integer getBannerType() {
        return bannerType;
    }

    public void setBannerType(Integer bannerType) {
        this.bannerType = bannerType;
    }
}
