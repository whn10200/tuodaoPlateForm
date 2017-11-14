package com.tuodao.bp.model.facade.traningcenter.output;

import java.io.Serializable;

/**
 * @description: 回款日历,日期数
 * @author: 王艳兵
 * @date: 2017/10/9
 * @time: 11:55
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class CollectionDayVo implements Serializable {

    private static final long serialVersionUID = 4932332490023609076L;

    /**
     * 回款日期 1-31之间
     */
    private int day;

    /**
     * 回款状态 0:未回款 1:已回款
     */
    private int status;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CollectionDayVo{" +
                "day=" + day +
                ", status=" + status +
                '}';
    }
}
