package com.tuodao.bp.model.business.operation.input;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.constant.operation.OpParamExceptionConstant;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Description: 根据contentId查询内容管理
 * User: yinping
 * Date: 2017/11/9
 * Copyright:拓道金服 Copyright (c) 2017
 */
public class OpContentInput extends BasePojo implements Serializable {

    private static final long serialVersionUID = -5780853701586954204L;

    @NotNull(message = OpParamExceptionConstant.TASK_IS_NULL+"")
    private Integer contentId;

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }
}
