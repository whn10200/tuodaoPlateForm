package com.tuodao.bp.traningcenter.db.model.basic;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AccountLog implements Serializable {
    /**
     * 主键id<br>
     * 表 : account_log<br>
     * 对应字段 : id<br>
     */
    private Long id;

    /**
     * 用户id<br>
     * 表 : account_log<br>
     * 对应字段 : user_id<br>
     */
    private String userId;

    /**
     * 订单流水号,与银行资金流水号关联<br>
     * 表 : account_log<br>
     * 对应字段 : order_no<br>
     */
    private String orderNo;

    /**
     * 投资人相关:0:充值(+)(投资人),  1:投标冻结(~)(投资人),2:投标成功(-)(投资人),3:提现冻结(~)(投资人),4:提现成功(-)(投资人),5:提现手续费(-)(投资人),6:提现失败(~)(投资人),7:投资撤回(+)(投资人),8:标的回款(+)(本金利息)(投资人),9:标的提前回款(+)(本金利息)(投资人),10:投资推广提成(+)(邀请好友投资),11:还款推广提成(+)(邀请好友回款),12:债券转让冻结(~)(受让人),13:债权转让流标解冻(~)(受让人),14:债券转让回收本金(+)(转让人),15:债权转让回收利息(+)(转让人),16:债权转让扣除手续费(-)(转让人),17:债权转让获取手续费(+)(受让人),18:债权转让投标成功(+)(受让人),19:精选计划投资冻结(~)(投资人),20:精选计划投资解冻(~)(投资人),21:精选计划利息奖励(+)(投资人),22:精选计划利息扣除(-)(投资人)借款人相关:30:借款获取募集金额(+),31:扣除手续费(-)(借款手续费),32:提现募集金额(-)(借款人提现到个人银行卡)平台相关:40:平台充值(资金账户到子账户),41:平台提现(子账户到资金账户),42:平台转账(子账户之间的转账),43:满标平台收入手续费,44:收入代偿(清算账户到代偿人),45:线下还款(清算账户到代偿人账户),46:委托还款(代偿人到标的账户),47:成废后到代偿户(续贷标到代偿人账户),48:债券转让手续费(+)(平台获取)49:精选计划手续费收入(+)50:精选计划手续费支出(+)
     * 表 : account_log<br>
     * 对应字段 : type<br>
     */
    private Integer type;

    /**
     * 本次资金变动发生的金额<br>
     * 表 : account_log<br>
     * 对应字段 : account<br>
     */
    private BigDecimal account = BigDecimal.valueOf(0);

    /**
     * 创建时间<br>
     * 表 : account_log<br>
     * 对应字段 : add_time<br>
     */
    private Date addTime;

    /**
     * 备注信息:非特殊条件下禁止手动添加或更新资金记录,如需请在该字段备注<br>
     * 表 : account_log<br>
     * 对应字段 : remarks<br>
     */
    private String remarks;

    /**
     * 账户备份:用户账户总额,总资产=cash_frost+tender_frosh+balance+await_capital+recharge+await_interest 投资可用余额=balance+recharge 可提现金额:balance<br>
     * 表 : account_log<br>
     * 对应字段 : total<br>
     */
    private BigDecimal total = BigDecimal.valueOf(0);

    /**
     * 账户备份:提现冻结金额<br>
     * 表 : account_log<br>
     * 对应字段 : cash_frost<br>
     */
    private BigDecimal cashFrost = BigDecimal.valueOf(0);

    /**
     * 账户备份:投标冻结(散标,精选计划)<br>
     * 表 : account_log<br>
     * 对应字段 : tender_frost<br>
     */
    private BigDecimal tenderFrost = BigDecimal.valueOf(0);

    /**
     * 账户备份:可用余额(可投资,可提现金额)<br>
     * 表 : account_log<br>
     * 对应字段 : balance<br>
     */
    private BigDecimal balance = BigDecimal.valueOf(0);

    /**
     * 账户备份:充值金额(可投资不可提现金额)<br>
     * 表 : account_log<br>
     * 对应字段 : recharge<br>
     */
    private BigDecimal recharge = BigDecimal.valueOf(0);

    /**
     * 账户备份:总待收利息<br>
     * 表 : account_log<br>
     * 对应字段 : await_interest<br>
     */
    private BigDecimal awaitInterest = BigDecimal.valueOf(0);

    /**
     * 账户备份:总待收本金<br>
     * 表 : account_log<br>
     * 对应字段 : await_capital<br>
     */
    private BigDecimal awaitCapital = BigDecimal.valueOf(0);

    /**
     * 资金来源账户(如果为平台账户该字段有值)<br>
     * 表 : account_log<br>
     * 对应字段 : from_account<br>
     */
    private String fromAccount ;

    /**
     * 账户名称备注(例如平台子账户,清算账户,加息券子账户等)<br>
     * 表 : account_log<br>
     * 对应字段 : from_remarks<br>
     */
    private String fromRemarks;

    /**
     * 资金去往账户(如果为平台账户该字段有值)<br>
     * 表 : account_log<br>
     * 对应字段 : to_account<br>
     */
    private String toAccount;

    /**
     * 账户名称备注(例如平台子账户,加息券子账户等)<br>
     * 表 : account_log<br>
     * 对应字段 : to_remarks<br>
     */
    private String toRemarks;

    /**
     * 是否显示资金日志,0:不显示(精选计划底层标的资金记录) 1:显示<br>
     * 表 : account_log<br>
     * 对应字段 : is_show<br>
     */
    private Integer isShow;

    /**
     * 收益发生变动的资金<br>
     * 表 : account_log<br>
     * 对应字段 : intrest_account<br>
     */
    private BigDecimal intrestAccount = BigDecimal.valueOf(0);

    /**
     * 手续费发生变动的资金<br>
     * 表 : account_log<br>
     * 对应字段 : fee_account<br>
     */
    private BigDecimal feeAccount = BigDecimal.valueOf(0);

    /**
     * 0收入1支出2冻结<br>
     * 表 : account_log<br>
     * 对应字段 : change_type<br>
     */
    private Integer changeType;

    /**
     * 可免费提现金额<br>
     *     表 : account_log<br>
     *         对应字段 : balance_cash<br>
     */
    private BigDecimal balanceCash = BigDecimal.valueOf(0);

    public BigDecimal getBalanceCash() {
        return balanceCash;
    }

    public void setBalanceCash(BigDecimal balanceCash) {
        this.balanceCash = balanceCash;
    }

    private static final long serialVersionUID = 1L;

    /**
     * @return 主键id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id  主键id
     */
    public void setId(Long id) {
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
     * @return 订单流水号,与银行资金流水号关联
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * @param orderNo  订单流水号,与银行资金流水号关联
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     * @return 投资人相关:0:充值(+)(投资人),  1:投标成功(-)(投资人),2:投标成功待收增加(~)(投资人),3:提现冻结(~)(投资人),4:提现成功(-)(投资人),5:提现手续费(-)(投资人),6:提现失败(~)(投资人),7:投资撤回(+)(投资人),8:标的回款(+)(本金利息)(投资人),9:标的提前回款(+)(本金利息)(投资人),10:投资推广提成(+)(邀请好友投资),11:还款推广提成(+)(邀请好友回款),12:债券转让冻结(~)(受让人),13:债权转让流标解冻(~)(受让人),14:债券转让回收本金(+)(转让人),15:债权转让回收利息(+)(转让人),16:债权转让扣除手续费(-)(转让人),17:债权转让获取手续费(+)(受让人),18:债权转让投标成功(+)(受让人),19:精选计划投资冻结(~)(投资人),20:精选计划投资解冻(~)(投资人),21:精选计划利息奖励(+)(投资人),22:精选计划利息扣除(-)(投资人)借款人相关:30:借款获取募集金额(+),31:扣除手续费(-)(借款手续费),32:提现募集金额(-)(借款人提现到个人银行卡)平台相关:40:平台充值(资金账户到子账户),41:平台提现(子账户到资金账户),42:平台转账(子账户之间的转账),43:满标平台收入手续费,44:收入代偿(清算账户到代偿人),45:线下还款(清算账户到代偿人账户),46:委托还款(代偿人到标的账户),47:成废后到代偿户(续贷标到代偿人账户),48:债券转让手续费(+)(平台获取)49:精选计划手续费收入(+)50:精选计划手续费支出(+)
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type  投资人相关:0:充值(+)(投资人),  1:投标成功(-)(投资人),2:投标成功待收增加(~)(投资人),3:提现冻结(~)(投资人),4:提现成功(-)(投资人),5:提现手续费(-)(投资人),6:提现失败(~)(投资人),7:投资撤回(+)(投资人),8:标的回款(+)(本金利息)(投资人),9:标的提前回款(+)(本金利息)(投资人),10:投资推广提成(+)(邀请好友投资),11:还款推广提成(+)(邀请好友回款),12:债券转让冻结(~)(受让人),13:债权转让流标解冻(~)(受让人),14:债券转让回收本金(+)(转让人),15:债权转让回收利息(+)(转让人),16:债权转让扣除手续费(-)(转让人),17:债权转让获取手续费(+)(受让人),18:债权转让投标成功(+)(受让人),19:精选计划投资冻结(~)(投资人),20:精选计划投资解冻(~)(投资人),21:精选计划利息奖励(+)(投资人),22:精选计划利息扣除(-)(投资人)借款人相关:30:借款获取募集金额(+),31:扣除手续费(-)(借款手续费),32:提现募集金额(-)(借款人提现到个人银行卡)平台相关:40:平台充值(资金账户到子账户),41:平台提现(子账户到资金账户),42:平台转账(子账户之间的转账),43:满标平台收入手续费,44:收入代偿(清算账户到代偿人),45:线下还款(清算账户到代偿人账户),46:委托还款(代偿人到标的账户),47:成废后到代偿户(续贷标到代偿人账户),48:债券转让手续费(+)(平台获取)49:精选计划手续费收入(+)50:精选计划手续费支出(+)
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return 本次资金变动发生的金额
     */
    public BigDecimal getAccount() {
        return account;
    }

    /**
     * @param account  本次资金变动发生的金额
     */
    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    /**
     * @return 创建时间
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * @param addTime  创建时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * @return 备注信息:非特殊条件下禁止手动添加或更新资金记录,如需请在该字段备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks  备注信息:非特殊条件下禁止手动添加或更新资金记录,如需请在该字段备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    /**
     * @return 账户备份:用户账户总额,总资产=cash_frost+tender_frosh+balance+await_capital+recharge+await_interest 投资可用余额=balance+recharge 可提现金额:balance
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param total  账户备份:用户账户总额,总资产=cash_frost+tender_frosh+balance+await_capital+recharge+await_interest 投资可用余额=balance+recharge 可提现金额:balance
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * @return 账户备份:提现冻结金额
     */
    public BigDecimal getCashFrost() {
        return cashFrost;
    }

    /**
     * @param cashFrost  账户备份:提现冻结金额
     */
    public void setCashFrost(BigDecimal cashFrost) {
        this.cashFrost = cashFrost;
    }

    /**
     * @return 账户备份:投标冻结(散标,精选计划)
     */
    public BigDecimal getTenderFrost() {
        return tenderFrost;
    }

    /**
     * @param tenderFrost  账户备份:投标冻结(散标,精选计划)
     */
    public void setTenderFrost(BigDecimal tenderFrost) {
        this.tenderFrost = tenderFrost;
    }

    /**
     * @return 账户备份:可用余额(可投资,可提现金额)
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * @param balance  账户备份:可用余额(可投资,可提现金额)
     */
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * @return 账户备份:充值金额(可投资不可提现金额)
     */
    public BigDecimal getRecharge() {
        return recharge;
    }

    /**
     * @param recharge  账户备份:充值金额(可投资不可提现金额)
     */
    public void setRecharge(BigDecimal recharge) {
        this.recharge = recharge;
    }

    /**
     * @return 账户备份:总待收利息
     */
    public BigDecimal getAwaitInterest() {
        return awaitInterest;
    }

    /**
     * @param awaitInterest  账户备份:总待收利息
     */
    public void setAwaitInterest(BigDecimal awaitInterest) {
        this.awaitInterest = awaitInterest;
    }

    /**
     * @return 账户备份:总待收本金
     */
    public BigDecimal getAwaitCapital() {
        return awaitCapital;
    }

    /**
     * @param awaitCapital  账户备份:总待收本金
     */
    public void setAwaitCapital(BigDecimal awaitCapital) {
        this.awaitCapital = awaitCapital;
    }

    /**
     * @return 资金来源账户(如果为平台账户该字段有值)
     */
    public String getFromAccount() {
        return fromAccount;
    }

    /**
     * @param fromAccount  资金来源账户(如果为平台账户该字段有值)
     */
    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    /**
     * @return 账户名称备注(例如平台子账户,清算账户,加息券子账户等)
     */
    public String getFromRemarks() {
        return fromRemarks;
    }

    /**
     * @param fromRemarks  账户名称备注(例如平台子账户,清算账户,加息券子账户等)
     */
    public void setFromRemarks(String fromRemarks) {
        this.fromRemarks = fromRemarks == null ? null : fromRemarks.trim();
    }

    /**
     * @return 资金去往账户(如果为平台账户该字段有值)
     */
    public String getToAccount() {
        return toAccount;
    }

    /**
     * @param toAccount  资金去往账户(如果为平台账户该字段有值)
     */
    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    /**
     * @return 账户名称备注(例如平台子账户,加息券子账户等)
     */
    public String getToRemarks() {
        return toRemarks;
    }

    /**
     * @param toRemarks  账户名称备注(例如平台子账户,加息券子账户等)
     */
    public void setToRemarks(String toRemarks) {
        this.toRemarks = toRemarks == null ? null : toRemarks.trim();
    }

    /**
     * @return 是否显示资金日志,0:不显示(精选计划底层标的资金记录) 1:显示
     */
    public Integer getIsShow() {
        return isShow;
    }

    /**
     * @param isShow  是否显示资金日志,0:不显示(精选计划底层标的资金记录) 1:显示
     */
    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    /**
     * @return 收益发生变动的资金
     */
    public BigDecimal getIntrestAccount() {
        return intrestAccount;
    }

    /**
     * @param intrestAccount  收益发生变动的资金
     */
    public void setIntrestAccount(BigDecimal intrestAccount) {
        this.intrestAccount = intrestAccount;
    }

    /**
     * @return 手续费发生变动的资金
     */
    public BigDecimal getFeeAccount() {
        return feeAccount;
    }

    /**
     * @param feeAccount  手续费发生变动的资金
     */
    public void setFeeAccount(BigDecimal feeAccount) {
        this.feeAccount = feeAccount;
    }

    /**
     * @return 0收入1支出2冻结
     */
    public Integer getChangeType() {
        return changeType;
    }

    /**
     * @param changeType  0收入1支出2冻结
     */
    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
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
        AccountLog other = (AccountLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getOrderNo() == null ? other.getOrderNo() == null : this.getOrderNo().equals(other.getOrderNo()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getAccount() == null ? other.getAccount() == null : this.getAccount().equals(other.getAccount()))
            && (this.getAddTime() == null ? other.getAddTime() == null : this.getAddTime().equals(other.getAddTime()))
            && (this.getRemarks() == null ? other.getRemarks() == null : this.getRemarks().equals(other.getRemarks()))
            && (this.getTotal() == null ? other.getTotal() == null : this.getTotal().equals(other.getTotal()))
            && (this.getCashFrost() == null ? other.getCashFrost() == null : this.getCashFrost().equals(other.getCashFrost()))
            && (this.getTenderFrost() == null ? other.getTenderFrost() == null : this.getTenderFrost().equals(other.getTenderFrost()))
            && (this.getBalance() == null ? other.getBalance() == null : this.getBalance().equals(other.getBalance()))
            && (this.getRecharge() == null ? other.getRecharge() == null : this.getRecharge().equals(other.getRecharge()))
            && (this.getAwaitInterest() == null ? other.getAwaitInterest() == null : this.getAwaitInterest().equals(other.getAwaitInterest()))
            && (this.getAwaitCapital() == null ? other.getAwaitCapital() == null : this.getAwaitCapital().equals(other.getAwaitCapital()))
            && (this.getFromAccount() == null ? other.getFromAccount() == null : this.getFromAccount().equals(other.getFromAccount()))
            && (this.getFromRemarks() == null ? other.getFromRemarks() == null : this.getFromRemarks().equals(other.getFromRemarks()))
            && (this.getToAccount() == null ? other.getToAccount() == null : this.getToAccount().equals(other.getToAccount()))
            && (this.getToRemarks() == null ? other.getToRemarks() == null : this.getToRemarks().equals(other.getToRemarks()))
            && (this.getIsShow() == null ? other.getIsShow() == null : this.getIsShow().equals(other.getIsShow()))
            && (this.getIntrestAccount() == null ? other.getIntrestAccount() == null : this.getIntrestAccount().equals(other.getIntrestAccount()))
            && (this.getFeeAccount() == null ? other.getFeeAccount() == null : this.getFeeAccount().equals(other.getFeeAccount()))
            && (this.getChangeType() == null ? other.getChangeType() == null : this.getChangeType().equals(other.getChangeType()));
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
        result = prime * result + ((getOrderNo() == null) ? 0 : getOrderNo().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getAccount() == null) ? 0 : getAccount().hashCode());
        result = prime * result + ((getAddTime() == null) ? 0 : getAddTime().hashCode());
        result = prime * result + ((getRemarks() == null) ? 0 : getRemarks().hashCode());
        result = prime * result + ((getTotal() == null) ? 0 : getTotal().hashCode());
        result = prime * result + ((getCashFrost() == null) ? 0 : getCashFrost().hashCode());
        result = prime * result + ((getTenderFrost() == null) ? 0 : getTenderFrost().hashCode());
        result = prime * result + ((getBalance() == null) ? 0 : getBalance().hashCode());
        result = prime * result + ((getRecharge() == null) ? 0 : getRecharge().hashCode());
        result = prime * result + ((getAwaitInterest() == null) ? 0 : getAwaitInterest().hashCode());
        result = prime * result + ((getAwaitCapital() == null) ? 0 : getAwaitCapital().hashCode());
        result = prime * result + ((getFromAccount() == null) ? 0 : getFromAccount().hashCode());
        result = prime * result + ((getFromRemarks() == null) ? 0 : getFromRemarks().hashCode());
        result = prime * result + ((getToAccount() == null) ? 0 : getToAccount().hashCode());
        result = prime * result + ((getToRemarks() == null) ? 0 : getToRemarks().hashCode());
        result = prime * result + ((getIsShow() == null) ? 0 : getIsShow().hashCode());
        result = prime * result + ((getIntrestAccount() == null) ? 0 : getIntrestAccount().hashCode());
        result = prime * result + ((getFeeAccount() == null) ? 0 : getFeeAccount().hashCode());
        result = prime * result + ((getChangeType() == null) ? 0 : getChangeType().hashCode());
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
        sb.append(", orderNo=").append(orderNo);
        sb.append(", type=").append(type);
        sb.append(", account=").append(account);
        sb.append(", addTime=").append(addTime);
        sb.append(", remarks=").append(remarks);
        sb.append(", total=").append(total);
        sb.append(", cashFrost=").append(cashFrost);
        sb.append(", tenderFrost=").append(tenderFrost);
        sb.append(", balance=").append(balance);
        sb.append(", recharge=").append(recharge);
        sb.append(", awaitInterest=").append(awaitInterest);
        sb.append(", awaitCapital=").append(awaitCapital);
        sb.append(", fromAccount=").append(fromAccount);
        sb.append(", fromRemarks=").append(fromRemarks);
        sb.append(", toAccount=").append(toAccount);
        sb.append(", toRemarks=").append(toRemarks);
        sb.append(", isShow=").append(isShow);
        sb.append(", intrestAccount=").append(intrestAccount);
        sb.append(", feeAccount=").append(feeAccount);
        sb.append(", changeType=").append(changeType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}