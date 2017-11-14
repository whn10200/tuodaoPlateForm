package com.tuodao.bp.model.facade.traningcenter.input;

import com.tuodao.bp.model.PagePojo;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 公用前台查询条件参数
 * @author: 王艳兵
 * @date: 2017/9/19
 * @time: 19:39
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class Param extends PagePojo implements Serializable{

    /**
     * 查询开始时间 yyyy-MM-dd
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd",iso = DateTimeFormat.ISO.DATE)
    private Date startTime;

    /**
     * 查询结束时间 yyyy-MM-dd
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd",iso = DateTimeFormat.ISO.DATE)
    private Date endTime;



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
