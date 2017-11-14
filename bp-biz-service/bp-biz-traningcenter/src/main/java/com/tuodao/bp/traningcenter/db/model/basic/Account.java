package com.tuodao.bp.traningcenter.db.model.basic;

import java.io.Serializable;
import java.math.BigDecimal;

public class Account implements Serializable {
    /**
     * 主键ID<br>
     * 表 : account<br>
     * 对应字段 : id<br>
     */
    private Integer id;

    /**
     * 用户id<br>
     * 表 : account<br>
     * 对应字段 : user_id<br>
     */
    private String userId;

    /**
     * 用户账户总额,总资产=cash_frost+tender_frosh+balance+await_capital+recharge+await_interest 投资可用余额=balance+recharge 可提现金额:balance<br>
     * 表 : account<br>
     * 对应字段 : total<br>
     */
    private BigDecimal total;

    /**
     * 冻结金额:提现冻结<br>
     * 表 : account<br>
     * 对应字段 : cash_frost<br>
     */
    private BigDecimal cashFrost;

    /**
     * 投标冻结(散标投标冻结+精选计划投标冻结)<br>
     * 表 : account<br>
     * 对应字段 : tender_frost<br>
     */
    private BigDecimal tenderFrost;

    /**
     * 可用余额(可提现+充值)<br>
     * 表 : account<br>
     * 对应字段 : balance<br>
     */
    private BigDecimal balance;

    /**
     * 充值金额<br>
     * 表 : account<br>
     * 对应字段 : recharge<br>
     */
    private BigDecimal recharge;

    /**
     * 待收利息总额<br>
     * 表 : account<br>
     * 对应字段 : await_interest<br>
     */
    private BigDecimal awaitInterest;

    /**
     * 待收本金总额<br>
     * 表 : account<br>
     * 对应字段 : await_capital<br>
     */
    private BigDecimal awaitCapital;

    /**
     * 累计充值<br>
     * 表 : account<br>
     * 对应字段 : total_recharge<br>
     */
    private BigDecimal totalRecharge;

    /**
     * 累计收益<br>
     * 表 : account<br>
     * 对应字段 : total_earnings<br>
     */
    private BigDecimal totalEarnings;

    /**
     * 累计提现<br>
     * 表 : account<br>
     * 对应字段 : total_withdraw<br>
     */
    private BigDecimal totalWithdraw;

    /**
     * 已获得返现奖励 (分)<br>
     * 表 : account<br>
     * 对应字段 : return_amount<br>
     */
    private BigDecimal returnAmount;

    /**
     * 已获得抵用券奖励 (分)<br>
     * 表 : account<br>
     * 对应字段 : coupons_amount<br>
     */
    private BigDecimal couponsAmount;

    /**
     * 免费可提现金额 回款本金+回款收益+返现奖励
     */
    private BigDecimal balanceCash;

    public BigDecimal getBalanceCash() {
        return balanceCash;
    }

    public void setBalanceCash(BigDecimal balanceCash) {
        this.balanceCash = balanceCash;
    }

    private static final long serialVersionUID = 1L;

    public Account(String userId) {
        this.userId = userId;
    }

    public Account() {
    }

    public Integer getId() {
        return id;
    }

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
     * @return 用户账户总额,总资产=cash_frost+tender_frosh+balance+await_capital+recharge+await_interest 投资可用余额=balance+recharge 可提现金额:balance
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param total  用户账户总额,总资产=cash_frost+tender_frosh+balance+await_capital+recharge+await_interest 投资可用余额=balance+recharge 可提现金额:balance
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * @return 冻结金额:提现冻结
     */
    public BigDecimal getCashFrost() {
        return cashFrost;
    }

    /**
     * @param cashFrost  冻结金额:提现冻结
     */
    public void setCashFrost(BigDecimal cashFrost) {
        this.cashFrost = cashFrost;
    }

    /**
     * @return 投标冻结(散标投标冻结+精选计划投标冻结)
     */
    public BigDecimal getTenderFrost() {
        return tenderFrost;
    }

    /**
     * @param tenderFrost  投标冻结(散标投标冻结+精选计划投标冻结)
     */
    public void setTenderFrost(BigDecimal tenderFrost) {
        this.tenderFrost = tenderFrost;
    }

    /**
     * @return 可用余额(可投,可提现)
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * @param balance  可用余额(可投,可提现)
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * @return 充值金额(该资金当天不可提现,可投资)
     */
    public BigDecimal getRecharge() {
        return recharge;
    }

    /**
     * @param recharge  充值金额(该资金当天不可提现,可投资)
     */
    public void setRecharge(BigDecimal recharge) {
        this.recharge = recharge;
    }

    /**
     * @return 待收利息总额
     */
    public BigDecimal getAwaitInterest() {
        return awaitInterest;
    }

    /**
     * @param awaitInterest  待收利息总额
     */
    public void setAwaitInterest(BigDecimal awaitInterest) {
        this.awaitInterest = awaitInterest;
    }

    /**
     * @return 待收本金总额
     */
    public BigDecimal getAwaitCapital() {
        return awaitCapital;
    }

    /**
     * @param awaitCapital  待收本金总额
     */
    public void setAwaitCapital(BigDecimal awaitCapital) {
        this.awaitCapital = awaitCapital;
    }

    /**
     * @return 累计充值
     */
    public BigDecimal getTotalRecharge() {
        return totalRecharge;
    }

    /**
     * @param totalRecharge  累计充值
     */
    public void setTotalRecharge(BigDecimal totalRecharge) {
        this.totalRecharge = totalRecharge;
    }

    /**
     * @return 累计收益
     */
    public BigDecimal getTotalEarnings() {
        return totalEarnings;
    }

    /**
     * @param totalEarnings  累计收益
     */
    public void setTotalEarnings(BigDecimal totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    /**
     * @return 累计提现
     */
    public BigDecimal getTotalWithdraw() {
        return totalWithdraw;
    }

    /**
     * @param totalWithdraw  累计提现
     */
    public void setTotalWithdraw(BigDecimal totalWithdraw) {
        this.totalWithdraw = totalWithdraw;
    }

    /**
     * @return 已获得返现奖励 (分)
     */
    public BigDecimal getReturnAmount() {
        return returnAmount;
    }

    /**
     * @param returnAmount  已获得返现奖励 (分)
     */
    public void setReturnAmount(BigDecimal returnAmount) {
        this.returnAmount = returnAmount;
    }

    /**
     * @return 已获得抵用券奖励 (分)
     */
    public BigDecimal getCouponsAmount() {
        return couponsAmount;
    }

    /**
     * @param couponsAmount  已获得抵用券奖励 (分)
     */
    public void setCouponsAmount(BigDecimal couponsAmount) {
        this.couponsAmount = couponsAmount;
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
        Account other = (Account) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getTotal() == null ? other.getTotal() == null : this.getTotal().equals(other.getTotal()))
            && (this.getCashFrost() == null ? other.getCashFrost() == null : this.getCashFrost().equals(other.getCashFrost()))
            && (this.getTenderFrost() == null ? other.getTenderFrost() == null : this.getTenderFrost().equals(other.getTenderFrost()))
            && (this.getBalance() == null ? other.getBalance() == null : this.getBalance().equals(other.getBalance()))
            && (this.getRecharge() == null ? other.getRecharge() == null : this.getRecharge().equals(other.getRecharge()))
            && (this.getAwaitInterest() == null ? other.getAwaitInterest() == null : this.getAwaitInterest().equals(other.getAwaitInterest()))
            && (this.getAwaitCapital() == null ? other.getAwaitCapital() == null : this.getAwaitCapital().equals(other.getAwaitCapital()))
            && (this.getTotalRecharge() == null ? other.getTotalRecharge() == null : this.getTotalRecharge().equals(other.getTotalRecharge()))
            && (this.getTotalEarnings() == null ? other.getTotalEarnings() == null : this.getTotalEarnings().equals(other.getTotalEarnings()))
            && (this.getTotalWithdraw() == null ? other.getTotalWithdraw() == null : this.getTotalWithdraw().equals(other.getTotalWithdraw()))
            && (this.getReturnAmount() == null ? other.getReturnAmount() == null : this.getReturnAmount().equals(other.getReturnAmount()))
            && (this.getCouponsAmount() == null ? other.getCouponsAmount() == null : this.getCouponsAmount().equals(other.getCouponsAmount()));
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
        result = prime * result + ((getTotal() == null) ? 0 : getTotal().hashCode());
        result = prime * result + ((getCashFrost() == null) ? 0 : getCashFrost().hashCode());
        result = prime * result + ((getTenderFrost() == null) ? 0 : getTenderFrost().hashCode());
        result = prime * result + ((getBalance() == null) ? 0 : getBalance().hashCode());
        result = prime * result + ((getRecharge() == null) ? 0 : getRecharge().hashCode());
        result = prime * result + ((getAwaitInterest() == null) ? 0 : getAwaitInterest().hashCode());
        result = prime * result + ((getAwaitCapital() == null) ? 0 : getAwaitCapital().hashCode());
        result = prime * result + ((getTotalRecharge() == null) ? 0 : getTotalRecharge().hashCode());
        result = prime * result + ((getTotalEarnings() == null) ? 0 : getTotalEarnings().hashCode());
        result = prime * result + ((getTotalWithdraw() == null) ? 0 : getTotalWithdraw().hashCode());
        result = prime * result + ((getReturnAmount() == null) ? 0 : getReturnAmount().hashCode());
        result = prime * result + ((getCouponsAmount() == null) ? 0 : getCouponsAmount().hashCode());
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
        sb.append(", total=").append(total);
        sb.append(", cashFrost=").append(cashFrost);
        sb.append(", tenderFrost=").append(tenderFrost);
        sb.append(", balance=").append(balance);
        sb.append(", recharge=").append(recharge);
        sb.append(", awaitInterest=").append(awaitInterest);
        sb.append(", awaitCapital=").append(awaitCapital);
        sb.append(", totalRecharge=").append(totalRecharge);
        sb.append(", totalEarnings=").append(totalEarnings);
        sb.append(", totalWithdraw=").append(totalWithdraw);
        sb.append(", returnAmount=").append(returnAmount);
        sb.append(", couponsAmount=").append(couponsAmount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}