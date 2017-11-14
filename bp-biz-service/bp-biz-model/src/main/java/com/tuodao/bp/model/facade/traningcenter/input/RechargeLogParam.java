package com.tuodao.bp.model.facade.traningcenter.input;

import com.tuodao.bp.model.BasePojo;

import java.util.Date;


/**
 * 充值记录，查询参数
 * @author qingli.chen
 * @date 2017/10/16
 * @description
 */
public class RechargeLogParam extends BasePojo {
    private static final long serialVersionUID = 8197641989031484907L;

    private Date startTime;

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
