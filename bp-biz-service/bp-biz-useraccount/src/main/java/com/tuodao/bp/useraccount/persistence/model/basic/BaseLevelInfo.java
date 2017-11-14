package com.tuodao.bp.useraccount.persistence.model.basic;

import java.io.Serializable;

public class BaseLevelInfo implements Serializable {
    private Integer id;

    private Integer level;

    private Integer min;

    private Integer max;

    private Integer vipWithdrawTimes;

    private String remark;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Integer getVipWithdrawTimes() {
        return vipWithdrawTimes;
    }

    public void setVipWithdrawTimes(Integer vipWithdrawTimes) {
        this.vipWithdrawTimes = vipWithdrawTimes;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}