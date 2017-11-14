package com.tuodao.bp.traningcenter.db.model.basic;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AutoTender implements Serializable {
    /**
     * 主键<br>
     * 表 : auto_tender<br>
     * 对应字段 : id<br>
     */
    private Integer id;

    /**
     * 用户id<br>
     * 表 : auto_tender<br>
     * 对应字段 : user_id<br>
     */
    private String userId;

    /**
     * 最小投标金额<br>
     * 表 : auto_tender<br>
     * 对应字段 : min_account<br>
     */
    private BigDecimal minAccount;

    /**
     * 最大投标金额<br>
     * 表 : auto_tender<br>
     * 对应字段 : max_account<br>
     */
    private BigDecimal maxAccount;

    /**
     * 最小投标期限<br>
     * 表 : auto_tender<br>
     * 对应字段 : min_period<br>
     */
    private Integer minPeriod;

    /**
     * 最大投标期限<br>
     * 表 : auto_tender<br>
     * 对应字段 : max_period<br>
     */
    private Integer maxPeriod;

    /**
     * 是否使用优惠券0:不使用 1:使用<br>
     * 表 : auto_tender<br>
     * 对应字段 : use_coupon<br>
     */
    private Integer useCoupon;

    /**
     * 添加时间<br>
     * 表 : auto_tender<br>
     * 对应字段 : add_time<br>
     */
    private Date addTime;


    /**
     * 排名
     */
    private Long weight;

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

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
     * @return 是否使用优惠券0:不使用 1:使用
     */
    public Integer getUseCoupon() {
        return useCoupon;
    }

    /**
     * @param useCoupon  是否使用优惠券0:不使用 1:使用
     */
    public void setUseCoupon(Integer useCoupon) {
        this.useCoupon = useCoupon;
    }

    /**
     * @return 添加时间
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * @param addTime  添加时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
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
        AutoTender other = (AutoTender) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getMinAccount() == null ? other.getMinAccount() == null : this.getMinAccount().equals(other.getMinAccount()))
            && (this.getMaxAccount() == null ? other.getMaxAccount() == null : this.getMaxAccount().equals(other.getMaxAccount()))
            && (this.getMinPeriod() == null ? other.getMinPeriod() == null : this.getMinPeriod().equals(other.getMinPeriod()))
            && (this.getMaxPeriod() == null ? other.getMaxPeriod() == null : this.getMaxPeriod().equals(other.getMaxPeriod()))
            && (this.getUseCoupon() == null ? other.getUseCoupon() == null : this.getUseCoupon().equals(other.getUseCoupon()))
            && (this.getAddTime() == null ? other.getAddTime() == null : this.getAddTime().equals(other.getAddTime()));
    }

    /**
     *
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getMinAccount() == null) ? 0 : getMinAccount().hashCode());
        result = prime * result + ((getMaxAccount() == null) ? 0 : getMaxAccount().hashCode());
        result = prime * result + ((getMinPeriod() == null) ? 0 : getMinPeriod().hashCode());
        result = prime * result + ((getMaxPeriod() == null) ? 0 : getMaxPeriod().hashCode());
        result = prime * result + ((getUseCoupon() == null) ? 0 : getUseCoupon().hashCode());
        result = prime * result + ((getAddTime() == null) ? 0 : getAddTime().hashCode());
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
        sb.append(", userId=").append(userId);
        sb.append(", minAccount=").append(minAccount);
        sb.append(", maxAccount=").append(maxAccount);
        sb.append(", minPeriod=").append(minPeriod);
        sb.append(", maxPeriod=").append(maxPeriod);
        sb.append(", useCoupon=").append(useCoupon);
        sb.append(", addTime=").append(addTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}