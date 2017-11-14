package com.tuodao.bp.model.business.useraccount;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @description: 用户账户基本信息
 * @author: mif
 * @date: 2017/9/5
 * @time: 16:16
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class UserAccountInfo implements Serializable {
    private static final long serialVersionUID = 3935104666842408599L;

    /**
     * 用户编码
     */
    private String userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 密码安级别（0：弱；1：强；2：最高）
     */
    private Integer pwSecurityLevel;

    /**
     * 头像路径
     */
    private String avatarUrl;

    /**
     * 直接邀请人
     */
    private String directInviterNo;

    /**
     * 间接邀请人
     */
    private String indirectInviterNo;

    /**
     * 用户状态（0：冻结 1：正常 2：注销）
     */
    private Integer userStatus;

    /**
     * 用户类型（0：投资用户：1：融资用户；）
     */
    private Integer userType;

    /**
     * 是否已投资（0：否；1：是）
     */
    private Integer investFlag;

    /**
     * 投资用户类型(0：普通用户；1：内部用户)
     */
    private Integer investUserType;

    /**
     * 是否新手（0：非新手：1：新手）
     */
    private Integer isNewbie;

    /**
     * 是否已开通存管账户（0:未开通；1:已开通;2:开通中;3开通失败）
     */
    private Integer isOpenDeposit;

    /**
     * 是否已绑定银行卡（0：未绑；1：已绑定）
     */
    private Integer isBindBank;

    /**
     * 来源渠道（好友邀请、VIVO、360等等）
     */
    private String sourceChannel;

    /**
     * 邀请码
     */
    private String inviteCode;
    /**
     * 邀请类型
     */
    private Integer InviteType;

    /**
     * 注册日期
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date registerDate;
    /**
     * 存管编号
     */
    private String depositNo;

    /**
     * 存管账户总资金（分）
     */
    private BigDecimal totalFund = new BigDecimal("0.00");

    /**
     * 累计收益（分）
     */
    private BigDecimal totalEarnings = new BigDecimal("0.00");
    /**
     * 待收总金额（分）
     */
    private BigDecimal dueInFund = new BigDecimal("0.00");

    /**
     * 可用余额(分)
     */
    private BigDecimal usableFund = new BigDecimal("0.00");

    /**
     * 待收本金(分)
     */
    private BigDecimal dueInPrincipal = new BigDecimal("0.00");

    /**
     * 待收利息(分)
     */
    private BigDecimal dueInInterest = new BigDecimal("0.00");

    /**
     * 冻结金额(分)
     */
    private BigDecimal freezeFund = new BigDecimal("0.00");
    /**
     * 可提现金额（分）
     */
    private BigDecimal canWithdrawFund = new BigDecimal("0.00");

    /**
     * 累计充值（分）
     */
    private BigDecimal totalRecharge = new BigDecimal("0.00");

    /**
     * 累计提现（分）
     */
    private BigDecimal totalWithdraw = new BigDecimal("0.00");

    /**
     * 投资次数
     */
    private Integer investmentTimes = 1;

    /**
     * 投资总额
     */
    private BigDecimal investmentAmount = new BigDecimal("0.00");

    /**
     * 已获得返现奖励 (分)
     */
    private BigDecimal returnAmount = new BigDecimal("0.00");


    public UserAccountInfo() {

    }

    public UserAccountInfo(String userId) {
        this.userId = userId;
        this.mobile = "15868854824";
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getPwSecurityLevel() {
        return pwSecurityLevel;
    }

    public void setPwSecurityLevel(Integer pwSecurityLevel) {
        this.pwSecurityLevel = pwSecurityLevel;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getDirectInviterNo() {
        return directInviterNo;
    }

    public void setDirectInviterNo(String directInviterNo) {
        this.directInviterNo = directInviterNo;
    }

    public String getIndirectInviterNo() {
        return indirectInviterNo;
    }

    public void setIndirectInviterNo(String indirectInviterNo) {
        this.indirectInviterNo = indirectInviterNo;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getInvestFlag() {
        return investFlag;
    }

    public void setInvestFlag(Integer investFlag) {
        this.investFlag = investFlag;
    }

    public Integer getInvestUserType() {
        return investUserType;
    }

    public void setInvestUserType(Integer investUserType) {
        this.investUserType = investUserType;
    }

    public Integer getIsNewbie() {
        return isNewbie;
    }

    public void setIsNewbie(Integer isNewbie) {
        this.isNewbie = isNewbie;
    }

    public Integer getIsOpenDeposit() {
        return isOpenDeposit;
    }

    public void setIsOpenDeposit(Integer isOpenDeposit) {
        this.isOpenDeposit = isOpenDeposit;
    }

    public Integer getIsBindBank() {
        return isBindBank;
    }

    public void setIsBindBank(Integer isBindBank) {
        this.isBindBank = isBindBank;
    }

    public String getSourceChannel() {
        return sourceChannel;
    }

    public void setSourceChannel(String sourceChannel) {
        this.sourceChannel = sourceChannel;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public Integer getInviteType() {
        return InviteType;
    }

    public void setInviteType(Integer inviteType) {
        InviteType = inviteType;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getDepositNo() {
        return depositNo;
    }

    public void setDepositNo(String depositNo) {
        this.depositNo = depositNo;
    }

    public BigDecimal getTotalFund() {
        return totalFund;
    }

    public void setTotalFund(BigDecimal totalFund) {
        this.totalFund = totalFund;
    }

    public BigDecimal getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(BigDecimal totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    public BigDecimal getDueInFund() {
        return dueInFund;
    }

    public void setDueInFund(BigDecimal dueInFund) {
        this.dueInFund = dueInFund;
    }

    public BigDecimal getUsableFund() {
        return usableFund;
    }

    public void setUsableFund(BigDecimal usableFund) {
        this.usableFund = usableFund;
    }

    public BigDecimal getDueInPrincipal() {
        return dueInPrincipal;
    }

    public void setDueInPrincipal(BigDecimal dueInPrincipal) {
        this.dueInPrincipal = dueInPrincipal;
    }

    public BigDecimal getDueInInterest() {
        return dueInInterest;
    }

    public void setDueInInterest(BigDecimal dueInInterest) {
        this.dueInInterest = dueInInterest;
    }

    public BigDecimal getFreezeFund() {
        return freezeFund;
    }

    public void setFreezeFund(BigDecimal freezeFund) {
        this.freezeFund = freezeFund;
    }

    public BigDecimal getCanWithdrawFund() {
        return canWithdrawFund;
    }

    public void setCanWithdrawFund(BigDecimal canWithdrawFund) {
        this.canWithdrawFund = canWithdrawFund;
    }

    public BigDecimal getTotalRecharge() {
        return totalRecharge;
    }

    public void setTotalRecharge(BigDecimal totalRecharge) {
        this.totalRecharge = totalRecharge;
    }

    public BigDecimal getTotalWithdraw() {
        return totalWithdraw;
    }

    public void setTotalWithdraw(BigDecimal totalWithdraw) {
        this.totalWithdraw = totalWithdraw;
    }

    public Integer getInvestmentTimes() {
        return investmentTimes;
    }

    public void setInvestmentTimes(Integer investmentTimes) {
        this.investmentTimes = investmentTimes;
    }

    public BigDecimal getInvestmentAmount() {
        return investmentAmount;
    }

    public void setInvestmentAmount(BigDecimal investmentAmount) {
        this.investmentAmount = investmentAmount;
    }

    public BigDecimal getReturnAmount() {
        return returnAmount;
    }

    public void setReturnAmount(BigDecimal returnAmount) {
        this.returnAmount = returnAmount;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("userId", userId)
                .add("userName", userName)
                .add("mobile", mobile)
                .add("pwSecurityLevel", pwSecurityLevel)
                .add("avatarUrl", avatarUrl)
                .add("directInviterNo", directInviterNo)
                .add("indirectInviterNo", indirectInviterNo)
                .add("userStatus", userStatus)
                .add("userType", userType)
                .add("investFlag", investFlag)
                .add("investUserType", investUserType)
                .add("isNewbie", isNewbie)
                .add("isOpenDeposit", isOpenDeposit)
                .add("isBindBank", isBindBank)
                .add("sourceChannel", sourceChannel)
                .add("inviteCode", inviteCode)
                .add("InviteType", InviteType)
                .add("registerDate", registerDate)
                .add("depositNo", depositNo)
                .add("totalFund", totalFund)
                .add("totalEarnings", totalEarnings)
                .add("dueInFund", dueInFund)
                .add("usableFund", usableFund)
                .add("dueInPrincipal", dueInPrincipal)
                .add("dueInInterest", dueInInterest)
                .add("freezeFund", freezeFund)
                .add("canWithdrawFund", canWithdrawFund)
                .add("totalRecharge", totalRecharge)
                .add("totalWithdraw", totalWithdraw)
                .add("investmentTimes", investmentTimes)
                .add("investmentAmount", investmentAmount)
                .add("returnAmount", returnAmount)
                .toString();
    }
}
