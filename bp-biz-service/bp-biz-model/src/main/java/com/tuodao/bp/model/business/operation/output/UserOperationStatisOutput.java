package com.tuodao.bp.model.business.operation.output;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 用户运营统计数据返回
 * author hechuan
 * <p>
 * created on 2017/9/25
 * <p>
 * since V1.0.0
 */
public class UserOperationStatisOutput implements Serializable {
    private static final long serialVersionUID = 9185546491303708867L;

    /**
     * 连续签到次数
     */
    private Integer continuousSignTmes;

    /**
     * 累计签到次数
     */
    private Integer cumulativeSignTmes;

    /**
     * 抵用券数量
     */
    private Integer usableVoucher;

    /**
     * 可用总积分
     */
    private Integer usableScores;

    /**
     * 可用加息劵总数
     */
    private Integer usablePateCoupon;

    /**
     * 抵用券金额
     */
    private BigDecimal voucherAmount;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 手机号码
     */
    private String userMobile;

    /**
     * 可用金币
     */
    private Integer usableGold;

    public Integer getContinuousSignTmes() {
        return continuousSignTmes;
    }

    public void setContinuousSignTmes(Integer continuousSignTmes) {
        this.continuousSignTmes = continuousSignTmes;
    }

    public Integer getCumulativeSignTmes() {
        return cumulativeSignTmes;
    }

    public void setCumulativeSignTmes(Integer cumulativeSignTmes) {
        this.cumulativeSignTmes = cumulativeSignTmes;
    }

    public Integer getUsableVoucher() {
        return usableVoucher;
    }

    public void setUsableVoucher(Integer usableVoucher) {
        this.usableVoucher = usableVoucher;
    }

    public Integer getUsableScores() {
        return usableScores;
    }

    public void setUsableScores(Integer usableScores) {
        this.usableScores = usableScores;
    }

    public Integer getUsablePateCoupon() {
        return usablePateCoupon;
    }

    public void setUsablePateCoupon(Integer usablePateCoupon) {
        this.usablePateCoupon = usablePateCoupon;
    }

    public BigDecimal getVoucherAmount() {
        return voucherAmount;
    }

    public void setVoucherAmount(BigDecimal voucherAmount) {
        this.voucherAmount = voucherAmount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public Integer getUsableGold() {
        return usableGold;
    }

    public void setUsableGold(Integer usableGold) {
        this.usableGold = usableGold;
    }
}
