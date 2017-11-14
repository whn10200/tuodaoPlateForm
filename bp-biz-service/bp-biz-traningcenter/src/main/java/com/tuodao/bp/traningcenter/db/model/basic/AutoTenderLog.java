package com.tuodao.bp.traningcenter.db.model.basic;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 王艳兵
 */
public class AutoTenderLog implements Serializable {
    /**
     * 主键<br>
     * 表 : auto_tender_log<br>
     * 对应字段 : id<br>
     */
    private Integer id;

    /**
     * 自动投标ID(auto_tender表主键)<br>
     * 表 : auto_tender_log<br>
     * 对应字段 : auto_tender_id<br>
     */
    private Integer autoTenderId;

    /**
     * 用户id<br>
     * 表 : auto_tender_log<br>
     * 对应字段 : user_id<br>
     */
    private String userId;

    /**
     * 最小投标金额<br>
     * 表 : auto_tender_log<br>
     * 对应字段 : min_account<br>
     */
    private BigDecimal minAccount;

    /**
     * 最大投标金额<br>
     * 表 : auto_tender_log<br>
     * 对应字段 : max_account<br>
     */
    private BigDecimal maxAccount;

    /**
     * 最小投标期限<br>
     * 表 : auto_tender_log<br>
     * 对应字段 : min_period<br>
     */
    private Integer minPeriod;

    /**
     * 最大投标期限<br>
     * 表 : auto_tender_log<br>
     * 对应字段 : max_period<br>
     */
    private Integer maxPeriod;

    /**
     * 添加时间或投标时间<br>
     * 表 : auto_tender_log<br>
     * 对应字段 : add_time<br>
     */
    private Date addTime;

    /**
     * 是否使用最佳优惠券:0:不使用1 :使用<br>
     * 表 : auto_tender_log<br>
     * 对应字段 : use_coupon<br>
     */
    private Integer useCoupon;

    /**
     * 操作类型:0:非投资 1:投资<br>
     * 表 : auto_tender_log<br>
     * 对应字段 : type<br>
     */
    private Integer type;

    /**
     * 投标金额<br>
     * 表 : auto_tender_log<br>
     * 对应字段 : tender_money<br>
     */
    private BigDecimal tenderMoney;

    /**
     * 券类型:0:不使用券,1:抵用券 2:加息券<br>
     * 表 : auto_tender_log<br>
     * 对应字段 : voucher_type<br>
     */
    private Integer voucherType;

    /**
     * 券额度<br>
     * 表 : auto_tender_log<br>
     * 对应字段 : voucher_money<br>
     */
    private BigDecimal voucherMoney;

    private static final long serialVersionUID = 1L;

    /**
     * @return 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id  主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 自动投标ID(auto_tender表主键)
     */
    public Integer getAutoTenderId() {
        return autoTenderId;
    }

    /**
     * @param autoTenderId  自动投标ID(auto_tender表主键)
     */
    public void setAutoTenderId(Integer autoTenderId) {
        this.autoTenderId = autoTenderId;
    }

    /**
     * @return 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId  用户id
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * @return 最小投标金额
     */
    public BigDecimal getMinAccount() {
        return minAccount;
    }

    /**
     * @param minAccount  最小投标金额
     */
    public void setMinAccount(BigDecimal minAccount) {
        this.minAccount = minAccount;
    }

    /**
     * @return 最大投标金额
     */
    public BigDecimal getMaxAccount() {
        return maxAccount;
    }

    /**
     * @param maxAccount  最大投标金额
     */
    public void setMaxAccount(BigDecimal maxAccount) {
        this.maxAccount = maxAccount;
    }

    /**
     * @return 最小投标期限
     */
    public Integer getMinPeriod() {
        return minPeriod;
    }

    /**
     * @param minPeriod  最小投标期限
     */
    public void setMinPeriod(Integer minPeriod) {
        this.minPeriod = minPeriod;
    }

    /**
     * @return 最大投标期限
     */
    public Integer getMaxPeriod() {
        return maxPeriod;
    }

    /**
     * @param maxPeriod  最大投标期限
     */
    public void setMaxPeriod(Integer maxPeriod) {
        this.maxPeriod = maxPeriod;
    }

    /**
     * @return 添加时间或投标时间
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * @param addTime  添加时间或投标时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * @return 是否使用最佳优惠券:0:不使用1 :使用
     */
    public Integer getUseCoupon() {
        return useCoupon;
    }

    /**
     * @param useCoupon  是否使用最佳优惠券:0:不使用1 :使用
     */
    public void setUseCoupon(Integer useCoupon) {
        this.useCoupon = useCoupon;
    }

    /**
     * @return 操作类型:0:非投资 1:投资
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type  操作类型:0:非投资 1:投资
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return 投标金额
     */
    public BigDecimal getTenderMoney() {
        return tenderMoney;
    }

    /**
     * @param tenderMoney  投标金额
     */
    public void setTenderMoney(BigDecimal tenderMoney) {
        this.tenderMoney = tenderMoney;
    }

    /**
     * @return 券类型:0:不使用券,1:抵用券 2:加息券
     */
    public Integer getVoucherType() {
        return voucherType;
    }

    /**
     * @param voucherType  券类型:0:不使用券,1:抵用券 2:加息券
     */
    public void setVoucherType(Integer voucherType) {
        this.voucherType = voucherType;
    }

    /**
     * @return 券额度
     */
    public BigDecimal getVoucherMoney() {
        return voucherMoney;
    }

    /**
     * @param voucherMoney  券额度
     */
    public void setVoucherMoney(BigDecimal voucherMoney) {
        this.voucherMoney = voucherMoney;
    }

    /**
     *
     * @param that
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        AutoTenderLog other = (AutoTenderLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAutoTenderId() == null ? other.getAutoTenderId() == null : this.getAutoTenderId().equals(other.getAutoTenderId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getMinAccount() == null ? other.getMinAccount() == null : this.getMinAccount().equals(other.getMinAccount()))
            && (this.getMaxAccount() == null ? other.getMaxAccount() == null : this.getMaxAccount().equals(other.getMaxAccount()))
            && (this.getMinPeriod() == null ? other.getMinPeriod() == null : this.getMinPeriod().equals(other.getMinPeriod()))
            && (this.getMaxPeriod() == null ? other.getMaxPeriod() == null : this.getMaxPeriod().equals(other.getMaxPeriod()))
            && (this.getAddTime() == null ? other.getAddTime() == null : this.getAddTime().equals(other.getAddTime()))
            && (this.getUseCoupon() == null ? other.getUseCoupon() == null : this.getUseCoupon().equals(other.getUseCoupon()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getTenderMoney() == null ? other.getTenderMoney() == null : this.getTenderMoney().equals(other.getTenderMoney()))
            && (this.getVoucherType() == null ? other.getVoucherType() == null : this.getVoucherType().equals(other.getVoucherType()))
            && (this.getVoucherMoney() == null ? other.getVoucherMoney() == null : this.getVoucherMoney().equals(other.getVoucherMoney()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAutoTenderId() == null) ? 0 : getAutoTenderId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getMinAccount() == null) ? 0 : getMinAccount().hashCode());
        result = prime * result + ((getMaxAccount() == null) ? 0 : getMaxAccount().hashCode());
        result = prime * result + ((getMinPeriod() == null) ? 0 : getMinPeriod().hashCode());
        result = prime * result + ((getMaxPeriod() == null) ? 0 : getMaxPeriod().hashCode());
        result = prime * result + ((getAddTime() == null) ? 0 : getAddTime().hashCode());
        result = prime * result + ((getUseCoupon() == null) ? 0 : getUseCoupon().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getTenderMoney() == null) ? 0 : getTenderMoney().hashCode());
        result = prime * result + ((getVoucherType() == null) ? 0 : getVoucherType().hashCode());
        result = prime * result + ((getVoucherMoney() == null) ? 0 : getVoucherMoney().hashCode());
        return result;
    }

    /**
     *
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", autoTenderId=").append(autoTenderId);
        sb.append(", userId=").append(userId);
        sb.append(", minAccount=").append(minAccount);
        sb.append(", maxAccount=").append(maxAccount);
        sb.append(", minPeriod=").append(minPeriod);
        sb.append(", maxPeriod=").append(maxPeriod);
        sb.append(", addTime=").append(addTime);
        sb.append(", useCoupon=").append(useCoupon);
        sb.append(", type=").append(type);
        sb.append(", tenderMoney=").append(tenderMoney);
        sb.append(", voucherType=").append(voucherType);
        sb.append(", voucherMoney=").append(voucherMoney);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}