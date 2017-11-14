package com.tuodao.bp.model.business.traningcenter.input;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * @description: 外部接口调用交易中心增加z
 * @author: 王艳兵
 * @date: 2017/11/6
 * @time: 14:46
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class AccountLogExtInput implements Serializable {

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
     * 可免费提现金额
     */
    private BigDecimal balanceCash = BigDecimal.valueOf(0);

    /**
     * 0收入1支出2冻结<br>
     * 表 : account_log<br>
     * 对应字段 : change_type<br>
     */
    private Integer changeType;

    public BigDecimal getBalanceCash() {
        return balanceCash;
    }

    public void setBalanceCash(BigDecimal balanceCash) {
        this.balanceCash = balanceCash;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getCashFrost() {
        return cashFrost;
    }

    public void setCashFrost(BigDecimal cashFrost) {
        this.cashFrost = cashFrost;
    }

    public BigDecimal getTenderFrost() {
        return tenderFrost;
    }

    public void setTenderFrost(BigDecimal tenderFrost) {
        this.tenderFrost = tenderFrost;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getRecharge() {
        return recharge;
    }

    public void setRecharge(BigDecimal recharge) {
        this.recharge = recharge;
    }

    public BigDecimal getAwaitInterest() {
        return awaitInterest;
    }

    public void setAwaitInterest(BigDecimal awaitInterest) {
        this.awaitInterest = awaitInterest;
    }

    public BigDecimal getAwaitCapital() {
        return awaitCapital;
    }

    public void setAwaitCapital(BigDecimal awaitCapital) {
        this.awaitCapital = awaitCapital;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getFromRemarks() {
        return fromRemarks;
    }

    public void setFromRemarks(String fromRemarks) {
        this.fromRemarks = fromRemarks;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public String getToRemarks() {
        return toRemarks;
    }

    public void setToRemarks(String toRemarks) {
        this.toRemarks = toRemarks;
    }

    public Integer getIsShow() {
        return isShow;
    }

    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    public BigDecimal getIntrestAccount() {
        return intrestAccount;
    }

    public void setIntrestAccount(BigDecimal intrestAccount) {
        this.intrestAccount = intrestAccount;
    }

    public BigDecimal getFeeAccount() {
        return feeAccount;
    }

    public void setFeeAccount(BigDecimal feeAccount) {
        this.feeAccount = feeAccount;
    }

    public Integer getChangeType() {
        return changeType;
    }

    public void setChangeType(Integer changeType) {
        this.changeType = changeType;
    }

    @Override
    public String toString() {
        return "AccountLogExtInput{" +
                "userId='" + userId + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", type=" + type +
                ", account=" + account +
                ", remarks='" + remarks + '\'' +
                ", total=" + total +
                ", cashFrost=" + cashFrost +
                ", tenderFrost=" + tenderFrost +
                ", balance=" + balance +
                ", recharge=" + recharge +
                ", awaitInterest=" + awaitInterest +
                ", awaitCapital=" + awaitCapital +
                ", fromAccount='" + fromAccount + '\'' +
                ", fromRemarks='" + fromRemarks + '\'' +
                ", toAccount='" + toAccount + '\'' +
                ", toRemarks='" + toRemarks + '\'' +
                ", isShow=" + isShow +
                ", intrestAccount=" + intrestAccount +
                ", feeAccount=" + feeAccount +
                ", balanceCash=" + balanceCash +
                ", changeType=" + changeType +
                '}';
    }
}
