package com.tuodao.bp.model.business.operation.input;

import com.tuodao.bp.model.PagePojo;

import java.io.Serializable;

/**
 * Description: service 用户任务列表查询输入对象
 * User: zkai
 * Date: 2017/9/20
 * Time: 14:34
 * Copyright:拓道金服 Copyright (c) 2017
 */
public class UserTaskListInput extends PagePojo implements Serializable {

    private static final long serialVersionUID = 7396400002315799182L;

    /**
     * 任务类型(1:日常任务,2:新手任务)
     */
    private Integer type;


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
