package com.tuodao.bp.model.business.traningcenter.output;

import com.tuodao.bp.model.facade.traningcenter.output.AutoTenderVo;

import java.io.Serializable;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/9/26
 * @time: 19:39
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class AutoTenderOutput extends AutoTenderVo implements Serializable {
    /**
     * 用户ID
     */
    private String userId;

    /**
     * 自动投标ID
     */
    private Integer id;

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
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "AutoTenderOutput{" +
                "userId='" + userId + '\'' +
                ", id=" + id +
                '}';
    }
}
