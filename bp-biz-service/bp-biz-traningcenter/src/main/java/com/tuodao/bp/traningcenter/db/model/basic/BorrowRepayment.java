package com.tuodao.bp.traningcenter.db.model.basic;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BorrowRepayment implements Serializable {
    /**
     * 主键<br>
     * 表 : borrow_repayment<br>
     * 对应字段 : id<br>
     */
    private Integer id;

    /**
     * 借款人ID<br>
     * 表 : borrow_repayment<br>
     * 对应字段 : user_id<br>
     */
    private String userId;

    /**
     * 标id<br>
     * 表 : borrow_repayment<br>
     * 对应字段 : borrow_id<br>
     */
    private Integer borrowId;

    /**
     * 还款状态:0:未还1:已还,2:提前还款<br>
     * 表 : borrow_repayment<br>
     * 对应字段 : status<br>
     */
    private Integer status;

    /**
     * 还款期数<br>
     * 表 : borrow_repayment<br>
     * 对应字段 : period<br>
     */
    private Integer period;

    /**
     * 总期数<br>
     * 表 : borrow_repayment<br>
     * 对应字段 : periods<br>
     */
    private Integer periods;

    /**
     * 还款手续费<br>
     * 表 : borrow_repayment<br>
     * 对应字段 : fee<br>
     */
    private BigDecimal fee;

    /**
     * 预计还款利息<br>
     * 表 : borrow_repayment<br>
     * 对应字段 : pre_interest<br>
     */
    private BigDecimal preInterest;

    /**
     * 预计还款本金<br>
     * 表 : borrow_repayment<br>
     * 对应字段 : pre_capital<br>
     */
    private BigDecimal preCapital;

    /**
     * 预计还款时间<br>
     * 表 : borrow_repayment<br>
     * 对应字段 : pre_repay_time<br>
     */
    private Date preRepayTime;

    /**
     * 实际还款利息<br>
     * 表 : borrow_repayment<br>
     * 对应字段 : interest<br>
     */
    private BigDecimal interest;

    /**
     * 实际还款本金<br>
     * 表 : borrow_repayment<br>
     * 对应字段 : capital<br>
     */
    private BigDecimal capital;

    /**
     * 实际还款时间<br>
     * 表 : borrow_repayment<br>
     * 对应字段 : repay_time<br>
     */
    private Date repayTime;

    /**
     * 还款方式:0平台代付,1:个人还款<br>
     * 表 : borrow_repayment<br>
     * 对应字段 : repay_mode<br>
     */
    private Integer repayMode;

    /**
     * 备注信息<br>
     * 表 : borrow_repayment<br>
     * 对应字段 : remarks<br>
     */
    private String remarks;

    /**
     * 历史的borrow_nid:注意凡带有nid字段的均为兼容老系统的字段<br>
     * 表 : borrow_repayment<br>
     * 对应字段 : borrow_nid<br>
     */
    private String borrowNid;

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
     * @return 借款人ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId  借款人ID
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * @return 标id
     */
    public Integer getBorrowId() {
        return borrowId;
    }

    /**
     * @param borrowId  标id
     */
    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }

    /**
     * @return 还款状态:0:未还1:已还,2:提前还款
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status  还款状态:0:未还1:已还,2:提前还款
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return 还款期数
     */
    public Integer getPeriod() {
        return period;
    }

    /**
     * @param period  还款期数
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
     * @return 还款手续费
     */
    public BigDecimal getFee() {
        return fee;
    }

    /**
     * @param fee  还款手续费
     */
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    /**
     * @return 预计还款利息
     */
    public BigDecimal getPreInterest() {
        return preInterest;
    }

    /**
     * @param preInterest  预计还款利息
     */
    public void setPreInterest(BigDecimal preInterest) {
        this.preInterest = preInterest;
    }

    /**
     * @return 预计还款本金
     */
    public BigDecimal getPreCapital() {
        return preCapital;
    }

    /**
     * @param preCapital  预计还款本金
     */
    public void setPreCapital(BigDecimal preCapital) {
        this.preCapital = preCapital;
    }

    /**
     * @return 预计还款时间
     */
    public Date getPreRepayTime() {
        return preRepayTime;
    }

    /**
     * @param preRepayTime  预计还款时间
     */
    public void setPreRepayTime(Date preRepayTime) {
        this.preRepayTime = preRepayTime;
    }

    /**
     * @return 实际还款利息
     */
    public BigDecimal getInterest() {
        return interest;
    }

    /**
     * @param interest  实际还款利息
     */
    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    /**
     * @return 实际还款本金
     */
    public BigDecimal getCapital() {
        return capital;
    }

    /**
     * @param capital  实际还款本金
     */
    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    /**
     * @return 实际还款时间
     */
    public Date getRepayTime() {
        return repayTime;
    }

    /**
     * @param repayTime  实际还款时间
     */
    public void setRepayTime(Date repayTime) {
        this.repayTime = repayTime;
    }

    /**
     * @return 还款方式:0平台代付,1:个人还款
     */
    public Integer getRepayMode() {
        return repayMode;
    }

    /**
     * @param repayMode  还款方式:0平台代付,1:个人还款
     */
    public void setRepayMode(Integer repayMode) {
        this.repayMode = repayMode;
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
     * @return 历史的borrow_nid:注意凡带有nid字段的均为兼容老系统的字段
     */
    public String getBorrowNid() {
        return borrowNid;
    }

    /**
     * @param borrowNid  历史的borrow_nid:注意凡带有nid字段的均为兼容老系统的字段
     */
    public void setBorrowNid(String borrowNid) {
        this.borrowNid = borrowNid == null ? null : borrowNid.trim();
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
        BorrowRepayment other = (BorrowRepayment) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getBorrowId() == null ? other.getBorrowId() == null : this.getBorrowId().equals(other.getBorrowId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getPeriod() == null ? other.getPeriod() == null : this.getPeriod().equals(other.getPeriod()))
            && (this.getPeriods() == null ? other.getPeriods() == null : this.getPeriods().equals(other.getPeriods()))
            && (this.getFee() == null ? other.getFee() == null : this.getFee().equals(other.getFee()))
            && (this.getPreInterest() == null ? other.getPreInterest() == null : this.getPreInterest().equals(other.getPreInterest()))
            && (this.getPreCapital() == null ? other.getPreCapital() == null : this.getPreCapital().equals(other.getPreCapital()))
            && (this.getPreRepayTime() == null ? other.getPreRepayTime() == null : this.getPreRepayTime().equals(other.getPreRepayTime()))
            && (this.getInterest() == null ? other.getInterest() == null : this.getInterest().equals(other.getInterest()))
            && (this.getCapital() == null ? other.getCapital() == null : this.getCapital().equals(other.getCapital()))
            && (this.getRepayTime() == null ? other.getRepayTime() == null : this.getRepayTime().equals(other.getRepayTime()))
            && (this.getRepayMode() == null ? other.getRepayMode() == null : this.getRepayMode().equals(other.getRepayMode()))
            && (this.getRemarks() == null ? other.getRemarks() == null : this.getRemarks().equals(other.getRemarks()))
            && (this.getBorrowNid() == null ? other.getBorrowNid() == null : this.getBorrowNid().equals(other.getBorrowNid()));
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
        result = prime * result + ((getBorrowId() == null) ? 0 : getBorrowId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getPeriod() == null) ? 0 : getPeriod().hashCode());
        result = prime * result + ((getPeriods() == null) ? 0 : getPeriods().hashCode());
        result = prime * result + ((getFee() == null) ? 0 : getFee().hashCode());
        result = prime * result + ((getPreInterest() == null) ? 0 : getPreInterest().hashCode());
        result = prime * result + ((getPreCapital() == null) ? 0 : getPreCapital().hashCode());
        result = prime * result + ((getPreRepayTime() == null) ? 0 : getPreRepayTime().hashCode());
        result = prime * result + ((getInterest() == null) ? 0 : getInterest().hashCode());
        result = prime * result + ((getCapital() == null) ? 0 : getCapital().hashCode());
        result = prime * result + ((getRepayTime() == null) ? 0 : getRepayTime().hashCode());
        result = prime * result + ((getRepayMode() == null) ? 0 : getRepayMode().hashCode());
        result = prime * result + ((getRemarks() == null) ? 0 : getRemarks().hashCode());
        result = prime * result + ((getBorrowNid() == null) ? 0 : getBorrowNid().hashCode());
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
        sb.append(", borrowId=").append(borrowId);
        sb.append(", status=").append(status);
        sb.append(", period=").append(period);
        sb.append(", periods=").append(periods);
        sb.append(", fee=").append(fee);
        sb.append(", preInterest=").append(preInterest);
        sb.append(", preCapital=").append(preCapital);
        sb.append(", preRepayTime=").append(preRepayTime);
        sb.append(", interest=").append(interest);
        sb.append(", capital=").append(capital);
        sb.append(", repayTime=").append(repayTime);
        sb.append(", repayMode=").append(repayMode);
        sb.append(", remarks=").append(remarks);
        sb.append(", borrowNid=").append(borrowNid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}