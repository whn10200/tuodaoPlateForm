package com.tuodao.bp.model.business.traningcenter.input;

import com.tuodao.bp.model.PagePojo;
import com.tuodao.bp.model.constant.traningcenter.CreditAssignmentConstant;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 我的债权转让列表查询对象
 * @author qingli.chen
 * @date 2017/9/14
 * @description
 */
public class CreditAssignmentInput extends PagePojo {
    private static final long serialVersionUID = 1314314004279466613L;

    /**
     * 开始时间
     */
//    @NotNull(message = CreditAssignmentConstant.BEGIN_TIME_IS_NULL + "")
    private Date startTime;

    /**
     * 结束时间
     */
//    @NotNull(message = CreditAssignmentConstant.END_TIME_IS_NULL + "")
    private Date endTime;

    /**
     * 状态
     * 0可转让 1转让中, 2已转让 3已经受让
     */
    @NotNull(message = CreditAssignmentConstant.STATUS_IS_NULL + "")
    private Integer status;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
