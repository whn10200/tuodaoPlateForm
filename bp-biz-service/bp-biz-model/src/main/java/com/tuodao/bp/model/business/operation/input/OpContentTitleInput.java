package com.tuodao.bp.model.business.operation.input;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.constant.operation.OpParamExceptionConstant;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Description: 根据contentRemark查询内容管理
 * User: yinping
 * Date: 2017/11/2
 * Copyright:拓道金服 Copyright (c) 2017
 */
public class OpContentTitleInput extends BasePojo implements Serializable {

    private static final long serialVersionUID = 7756649238793237466L;

    @NotNull(message = OpParamExceptionConstant.TASK_IS_NULL+"")
    private Integer contentRemark;

    public Integer getContentRemark() {
        return contentRemark;
    }

    public void setContentRemark(Integer contentRemark) {
        this.contentRemark = contentRemark;
    }
}
