package com.tuodao.bp.model.facade.traningcenter.input;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.annotation.RangeDouble;
import com.tuodao.bp.model.constant.traningcenter.TraningCenterExceptionConstant;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

/**
 * @description: 自动投标
 * @author: 王艳兵
 * @date: 2017/9/26
 * @time: 14:04
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class AutoTenderParam extends BasePojo implements Serializable {

    /**
     * 最小投标金额
     */
    @RangeDouble(min=100,max=100000,message= TraningCenterExceptionConstant.MIN_MAX_AUTO_TENDER_MONEY + "")
    private Double minAccount;

    /**
     * 最大投标金额
     */
    @RangeDouble(min=100,max=100000,message= TraningCenterExceptionConstant.MIN_MAX_AUTO_TENDER_MONEY + "")
    private Double maxAccount;

    /**
     * 最小期限
     */
    @Range(min=1,max=36,message= TraningCenterExceptionConstant.MIN_MAX_AUTO_TENDER_PERIOD + "")
    private Integer minPeriod;

    /**
     * 最大期限
     */
    @Range(min=1,max=36,message= TraningCenterExceptionConstant.MIN_MAX_AUTO_TENDER_PERIOD + "")
    private Integer maxPeriod;

    /**
     * 是否使用加息券
     */
    private Integer useCoupon = 0;


    public Double getMinAccount() {
        return minAccount;
    }

    public void setMinAccount(Double minAccount) {
        this.minAccount = minAccount;
    }

    public Double getMaxAccount() {
        return maxAccount;
    }

    public void setMaxAccount(Double maxAccount) {
        this.maxAccount = maxAccount;
    }

    public Integer getMinPeriod() {
        return minPeriod;
    }

    public void setMinPeriod(Integer minPeriod) {
        this.minPeriod = minPeriod;
    }

    public Integer getMaxPeriod() {
        return maxPeriod;
    }

    public void setMaxPeriod(Integer maxPeriod) {
        this.maxPeriod = maxPeriod;
    }

    public Integer getUseCoupon() {
        return useCoupon;
    }

    public void setUseCoupon(Integer useCoupon) {
        this.useCoupon = useCoupon;
    }

    @Override
    public String toString() {
        return "AutoTenderParam{" +
                "minAccount=" + minAccount +
                ", maxAccount=" + maxAccount +
                ", minPeriod=" + minPeriod +
                ", maxPeriod=" + maxPeriod +
                ", useCoupon=" + useCoupon +
                ", userId='" + userId + '\'' +
                '}';
    }
}
