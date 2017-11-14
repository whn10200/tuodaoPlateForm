package com.tuodao.bp.model.mq;

import com.google.common.base.MoreObjects;
import com.tuodao.bp.model.business.traningcenter.input.TenderExecutor;

import java.io.Serializable;

/**
 * @description: 用户优惠券状态变更MQ信息
 * @author: mif
 * @date: 2017/10/12
 * @time: 10:57
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class OpDiscountsChangeMqInfo implements Serializable {
    private static final long serialVersionUID = 280395403346672091L;
    /**
     * 用户Id
     */
    private String userId;

    /**
     * 优惠券ID
     */
    private Long disId;

    /**
     * 优惠券状态（1：可使用，2：已使用，3：已过期）
     */
    private Integer disStaus;

    /**
     * 投标相关信息 传递过去 再传递回来
     */
    private TenderExecutor executor;

    /**
     * 成功：true,失败：false
     */
    private boolean success;

    /**
     * 成功的原因或失败的原因
     */
    private String remark;

    public TenderExecutor getExecutor() {
        return executor;
    }

    public void setExecutor(TenderExecutor executor) {
        this.executor = executor;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getDisId() {
        return disId;
    }

    public void setDisId(Long disId) {
        this.disId = disId;
    }

    public Integer getDisStaus() {
        return disStaus;
    }

    public void setDisStaus(Integer disStaus) {
        this.disStaus = disStaus;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("userId", userId)
                .add("disId", disId)
                .add("disStaus", disStaus)
                .add("executor",executor)
                .add("success",success)
                .add("remark",remark)
                .toString();
    }
}
