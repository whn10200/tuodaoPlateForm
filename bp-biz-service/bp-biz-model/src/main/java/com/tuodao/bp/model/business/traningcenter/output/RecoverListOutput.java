package com.tuodao.bp.model.business.traningcenter.output;

import java.io.Serializable;

/**
 * @description:我的投资理财计划详情（回款记录）
 * @author: wuzf
 * @date: 2017/9/21 0021.
 * @time: 14:14
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class RecoverListOutput implements Serializable {

    //利息
    private String preInterest;

    //时间
    private String preCollectionTime;

    //状态
    private String status;

    public String getPreInterest() {
        return preInterest;
    }

    public void setPreInterest(String preInterest) {
        this.preInterest = preInterest;
    }

    public String getPreCollectionTime() {
        return preCollectionTime;
    }

    public void setPreCollectionTime(String preCollectionTime) {
        this.preCollectionTime = preCollectionTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
