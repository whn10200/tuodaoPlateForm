package com.tuodao.bp.model.business.operation.input;

import java.io.Serializable;

/**
 * Description: 内容管理输入类
 * User: yinping
 * Date: 2017/9/08
 * Copyright:拓道金服 Copyright (c) 2017
 */
public class OperationInput implements Serializable {

    private static final long serialVersionUID = 7396400002315799183L;

    private Integer contentId;

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }
}
