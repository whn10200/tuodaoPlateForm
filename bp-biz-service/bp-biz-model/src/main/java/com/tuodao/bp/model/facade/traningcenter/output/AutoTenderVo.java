package com.tuodao.bp.model.facade.traningcenter.output;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/9/26
 * @time: 14:14
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class AutoTenderVo implements Serializable {

    /**
     * 最小投标金额
     */
    private BigDecimal minAccount;

    /**
     * 最大投标金额
     */
    private BigDecimal maxAccount;

    /**
     * 最小期限
     */
    private Integer minPeriod;

    /**
     * 最大期限
     */
    private Integer maxPeriod;

    /**
     * 是否使用加息券
     */
    private Integer useCoupon;

    /**
     * 状态 0:未开启 1:开启
     */
    private Integer status;


    /**
     * 总的开启自动投标的人数
     */
    private Long totalAutoTender;

    /**
     * 当前排名
     */
    private Long ranking;

    /**
     * 是否开通存管
     */
    private boolean open;

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public Long getTotalAutoTender() {
        return totalAutoTender;
    }

    public void setTotalAutoTender(Long totalAutoTender) {
        this.totalAutoTender = totalAutoTender;
    }

    public Long getRanking() {
        return ranking;
    }

    public void setRanking(Long ranking) {
        this.ranking = ranking;
    }

    public AutoTenderVo() {
    }

    public AutoTenderVo(Integer status,Long totalAutoTender,Long ranking) {
        this.status = status;
        this.totalAutoTender = totalAutoTender;
        this.ranking = ranking;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public BigDecimal getMinAccount() {
        return minAccount;
    }

    public void setMinAccount(BigDecimal minAccount) {
        this.minAccount = minAccount;
    }

    public BigDecimal getMaxAccount() {
        return maxAccount;
    }

    public void setMaxAccount(BigDecimal maxAccount) {
        this.maxAccount = maxAccount;
    }

    @Override
    public String toString() {
        return "AutoTenderVo{" +
                "minAccount=" + minAccount +
                ", maxAccount=" + maxAccount +
                ", minPeriod=" + minPeriod +
                ", maxPeriod=" + maxPeriod +
                ", useCoupon=" + useCoupon +
                ", status=" + status +
                ", totalAutoTender=" + totalAutoTender +
                ", ranking=" + ranking +
                '}';
    }
}
