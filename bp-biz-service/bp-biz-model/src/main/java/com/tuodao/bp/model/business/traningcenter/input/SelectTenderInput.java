package com.tuodao.bp.model.business.traningcenter.input;

import com.tuodao.bp.model.PagePojo;
import com.tuodao.bp.model.constant.traningcenter.TraningCenterExceptionConstant;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @description:根据userid和状态分页查询理财计划投资记录
 * @author: wuzf
 * @date: 2017/9/20 0020.
 * @time: 11:08
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class SelectTenderInput extends PagePojo {


    private Integer borrowId;

    //状态
    private String status;

    //用户id
//    @NotNull(message = TraningCenterExceptionConstant.USER_ID_IS_NULL+"")
    private String userId;

    //开始时间
    private Date startTime;

    //结束时间
    private Date endTime;

    public Integer getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
