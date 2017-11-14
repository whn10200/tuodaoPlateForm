package com.tuodao.bp.traningcenter.db.model.basic;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 王艳兵
 */
public class BorrowCollection implements Serializable {
    /**
     * 主键<br>
     * 表 : borrow_collection<br>
     * 对应字段 : id<br>
     */
    private Integer id;

    /**
     * 用户id<br>
     * 表 : borrow_collection<br>
     * 对应字段 : user_id<br>
     */
    private String userId;

    /**
     * 投标Id<br>
     * 表 : borrow_collection<br>
     * 对应字段 : tender_id<br>
     */
    private Integer tenderId;

    /**
     * 回款状态:0:未回款1:已回款<br>
     * 表 : borrow_collection<br>
     * 对应字段 : status<br>
     */
    private Integer status;

    /**
     * 本期为第几期<br>
     * 表 : borrow_collection<br>
     * 对应字段 : period<br>
     */
    private Integer period;

    /**
     * 总期数<br>
     * 表 : borrow_collection<br>
     * 对应字段 : periods<br>
     */
    private Integer periods;

    /**
     * 预计回款时间<br>
     * 表 : borrow_collection<br>
     * 对应字段 : pre_collection_time<br>
     */
    private Date preCollectionTime;

    /**
     * 预计回款时间 月 减少查询
     */
    private String preCollectionMonth;

    /**
     * 预计回款利息(基础利息+加息券利息+平台加息利息)<br>
     * 表 : borrow_collection<br>
     * 对应字段 : pre_interest<br>
     */
    private BigDecimal preInterest;

    /**
     * 预计回款本金<br>
     * 表 : borrow_collection<br>
     * 对应字段 : pre_capital<br>
     */
    private BigDecimal preCapital;

    /**
     * 实际回款时间<br>
     * 表 : borrow_collection<br>
     * 对应字段 : collection_time<br>
     */
    private Date collectionTime;

    /**
     *实际回款月
     */
    private String collectionMonth;
    /**
     * 实际回款利息(基础利息+加息券利息+平台加息利息)<br>
     * 表 : borrow_collection<br>
     * 对应字段 : interest<br>
     */
    private BigDecimal interest;

    /**
     * 实际回款本金<br>
     * 表 : borrow_collection<br>
     * 对应字段 : capital<br>
     */
    private BigDecimal capital;

    /**
     * 当期加息券产生的收益<br>
     * 表 : borrow_collection<br>
     * 对应字段 : coupon_account<br>
     */
    private BigDecimal couponAccount;

    /**
     * 平台加息产生的收益<br>
     * 表 : borrow_collection<br>
     * 对应字段 : platform_account<br>
     */
    private BigDecimal platformAccount;

    /**
     * 备注信息<br>
     * 表 : borrow_collection<br>
     * 对应字段 : remarks<br>
     */
    private String remarks;

    /**
     * 是否显示该条回款计划,0:不显示(精选计划超出回款期数部分),1:显示<br>
     * 表 : borrow_collection<br>
     * 对应字段 : is_show<br>
     */
    private Integer isShow;

    /**
     * 精选计划的投标id<br>
     * 表 : borrow_collection<br>
     * 对应字段 : tender_nid<br>
     */
    private Integer tenderNid;

    /**
     * 标的id    查询使用勿删
     */
    private Integer borrowId;

    /**
     * 0不还款1还款
     */
    public Integer isRevocer=1;

    /**
     * 还款本金是否释放到资金池中 0不放1放
     */
    public Integer isCapital=0;

    public String getCollectionMonth() {
        return collectionMonth;
    }

    public void setCollectionMonth(String collectionMonth) {
        this.collectionMonth = collectionMonth;
    }

    public String getPreCollectionMonth() {
        return preCollectionMonth;
    }

    public void setPreCollectionMonth(String preCollectionMonth) {
        this.preCollectionMonth = preCollectionMonth;
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
     * @return 投标Id
     */
    public Integer getTenderId() {
        return tenderId;
    }


    private BigDecimal simulatedInterest;

    public BigDecimal getSimulatedInterest() {
        return simulatedInterest;
    }

    public void setSimulatedInterest(BigDecimal simulatedInterest) {
        this.simulatedInterest = simulatedInterest;
    }

    /**
     * @param tenderId  投标Id
     */
    public void setTenderId(Integer tenderId) {
        this.tenderId = tenderId;
    }

    /**
     * @return 回款状态:0:未回款1:已回款
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status  回款状态:0:未回款1:已回款
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return 本期为第几期
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     * @param period  本期为第几期
     */
    public void setPeriod(Integer period) {
        this.period = period;
    }

    /**
     * @return 总期数
     */
    public Integer getPeriods() {
        return periods;
    }

    /**
     * @param periods  总期数
     */
    public void setPeriods(Integer periods) {
        this.periods = periods;
    }

    /**
     * @return 预计回款时间
     */
    public Date getPreCollectionTime() {
        return preCollectionTime;
    }

    /**
     * @param preCollectionTime  预计回款时间
     */
    public void setPreCollectionTime(Date preCollectionTime) {
        this.preCollectionTime = preCollectionTime;
    }

    /**
     * @return 预计回款利息(基础利息+加息券利息+平台加息利息)
     */
    public BigDecimal getPreInterest() {
        return preInterest;
    }

    /**
     * @param preInterest  预计回款利息(基础利息+加息券利息+平台加息利息)
     */
    public void setPreInterest(BigDecimal preInterest) {
        this.preInterest = preInterest;
    }

    /**
     * @return 预计回款本金
     */
    public BigDecimal getPreCapital() {
        return preCapital;
    }

    /**
     * @param preCapital  预计回款本金
     */
    public void setPreCapital(BigDecimal preCapital) {
        this.preCapital = preCapital;
    }

    /**
     * @return 实际回款时间
     */
    public Date getCollectionTime() {
        return collectionTime;
    }

    /**
     * @param collectionTime  实际回款时间
     */
    public void setCollectionTime(Date collectionTime) {
        this.collectionTime = collectionTime;
    }

    /**
     * @return 实际回款利息(基础利息+加息券利息+平台加息利息)
     */
    public BigDecimal getInterest() {
        return interest;
    }

    /**
     * @param interest  实际回款利息(基础利息+加息券利息+平台加息利息)
     */
    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    /**
     * @return 实际回款本金
     */
    public BigDecimal getCapital() {
        return capital;
    }

    /**
     * @param capital  实际回款本金
     */
    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    /**
     * @return 当期加息券产生的收益
     */
    public BigDecimal getCouponAccount() {
        return couponAccount;
    }

    /**
     * @param couponAccount  当期加息券产生的收益
     */
    public void setCouponAccount(BigDecimal couponAccount) {
        this.couponAccount = couponAccount;
    }

    /**
     * @return 平台加息产生的收益
     */
    public BigDecimal getPlatformAccount() {
        return platformAccount;
    }

    /**
     * @param platformAccount  平台加息产生的收益
     */
    public void setPlatformAccount(BigDecimal platformAccount) {
        this.platformAccount = platformAccount;
    }

    /**
     * @return 备注信息
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks  备注信息
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * @return 是否显示该条回款计划,0:不显示(精选计划超出回款期数部分),1:显示
     */
    public Integer getIsShow() {
        return isShow;
    }

    /**
     * @param isShow  是否显示该条回款计划,0:不显示(精选计划超出回款期数部分),1:显示
     */
    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    /**
     * @return 精选计划的投标id
     */
    public Integer getTenderNid() {
        return tenderNid;
    }


    /**
     * @param tenderNid  精选计划的投标id
     */
    public void setTenderNid(Integer tenderNid) {
        this.tenderNid = tenderNid;
    }

    public Integer getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }


    public Integer getIsRevocer() {
        return isRevocer;
    }

    public void setIsRevocer(Integer isRevocer) {
        this.isRevocer = isRevocer;
    }

    public Integer getIsCapital() {
        return isCapital;
    }

    public void setIsCapital(Integer isCapital) {
        this.isCapital = isCapital;
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
        BorrowCollection other = (BorrowCollection) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getTenderId() == null ? other.getTenderId() == null : this.getTenderId().equals(other.getTenderId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getPeriod() == null ? other.getPeriod() == null : this.getPeriod().equals(other.getPeriod()))
            && (this.getPeriods() == null ? other.getPeriods() == null : this.getPeriods().equals(other.getPeriods()))
            && (this.getPreCollectionTime() == null ? other.getPreCollectionTime() == null : this.getPreCollectionTime().equals(other.getPreCollectionTime()))
            && (this.getPreInterest() == null ? other.getPreInterest() == null : this.getPreInterest().equals(other.getPreInterest()))
            && (this.getPreCapital() == null ? other.getPreCapital() == null : this.getPreCapital().equals(other.getPreCapital()))
            && (this.getCollectionTime() == null ? other.getCollectionTime() == null : this.getCollectionTime().equals(other.getCollectionTime()))
            && (this.getInterest() == null ? other.getInterest() == null : this.getInterest().equals(other.getInterest()))
            && (this.getCapital() == null ? other.getCapital() == null : this.getCapital().equals(other.getCapital()))
            && (this.getCouponAccount() == null ? other.getCouponAccount() == null : this.getCouponAccount().equals(other.getCouponAccount()))
            && (this.getPlatformAccount() == null ? other.getPlatformAccount() == null : this.getPlatformAccount().equals(other.getPlatformAccount()))
            && (this.getRemarks() == null ? other.getRemarks() == null : this.getRemarks().equals(other.getRemarks()))
            && (this.getIsShow() == null ? other.getIsShow() == null : this.getIsShow().equals(other.getIsShow()))
            && (this.getTenderNid() == null ? other.getTenderNid() == null : this.getTenderNid().equals(other.getTenderNid()));
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
        result = prime * result + ((getTenderId() == null) ? 0 : getTenderId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getPeriod() == null) ? 0 : getPeriod().hashCode());
        result = prime * result + ((getPeriods() == null) ? 0 : getPeriods().hashCode());
        result = prime * result + ((getPreCollectionTime() == null) ? 0 : getPreCollectionTime().hashCode());
        result = prime * result + ((getPreInterest() == null) ? 0 : getPreInterest().hashCode());
        result = prime * result + ((getPreCapital() == null) ? 0 : getPreCapital().hashCode());
        result = prime * result + ((getCollectionTime() == null) ? 0 : getCollectionTime().hashCode());
        result = prime * result + ((getInterest() == null) ? 0 : getInterest().hashCode());
        result = prime * result + ((getCapital() == null) ? 0 : getCapital().hashCode());
        result = prime * result + ((getCouponAccount() == null) ? 0 : getCouponAccount().hashCode());
        result = prime * result + ((getPlatformAccount() == null) ? 0 : getPlatformAccount().hashCode());
        result = prime * result + ((getRemarks() == null) ? 0 : getRemarks().hashCode());
        result = prime * result + ((getIsShow() == null) ? 0 : getIsShow().hashCode());
        result = prime * result + ((getTenderNid() == null) ? 0 : getTenderNid().hashCode());
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
        sb.append(", tenderId=").append(tenderId);
        sb.append(", status=").append(status);
        sb.append(", period=").append(period);
        sb.append(", periods=").append(periods);
        sb.append(", preCollectionTime=").append(preCollectionTime);
        sb.append(", preInterest=").append(preInterest);
        sb.append(", preCapital=").append(preCapital);
        sb.append(", collectionTime=").append(collectionTime);
        sb.append(", interest=").append(interest);
        sb.append(", capital=").append(capital);
        sb.append(", couponAccount=").append(couponAccount);
        sb.append(", platformAccount=").append(platformAccount);
        sb.append(", remarks=").append(remarks);
        sb.append(", isShow=").append(isShow);
        sb.append(", tenderNid=").append(tenderNid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}