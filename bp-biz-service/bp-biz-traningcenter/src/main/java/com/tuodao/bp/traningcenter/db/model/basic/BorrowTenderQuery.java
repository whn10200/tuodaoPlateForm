package com.tuodao.bp.traningcenter.db.model.basic;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 投标相关查询条件
 * @author: 王艳兵
 * @date: 2017/9/26
 * @time: 09:45
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class BorrowTenderQuery extends BorrowTender implements Serializable{

    /**
     * 查询开始时间
     */
    private Date startTime;

    /**
     * 查询结束时间
     */
    private Date endTime;


    /**
     * 0:查询所有 1:查询 投标中,投标成功,回款中,回款成功的
     */
    private int filterFail = 0;

    public int getFilterFail() {
        return filterFail;
    }

    public void setFilterFail(int filterFail) {
        this.filterFail = filterFail;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
