package com.tuodao.bp.traningcenter.db.model.basic;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 王艳兵
 */
public class BorrowTender implements Serializable {
    /**
     * 主键<br>
     * 表 : borrow_tender<br>
     * 对应字段 : id<br>
     */
    private Integer id;

    /**
     * 用户id<br>
     * 表 : borrow_tender<br>
     * 对应字段 : user_id<br>
     */
    private String userId;

    /**
     * 标id<br>
     * 表 : borrow_tender<br>
     * 对应字段 : borrow_id<br>
     */
    private Integer borrowId;

    /**
     * 投标状态:0:投标中(资金冻结,匹配中)1:投标成功(还款中)2:投标失败(流标)3:投标失败(撤销标),4:还款完成:5:转让申请中6:转让成功<br>
     * 表 : borrow_tender<br>
     * 对应字段 : status<br>
     */
    private Integer status;

    /**
     * 实际投标金额(用户使用抵用券时)<br>
     * 表 : borrow_tender<br>
     * 对应字段 : account<br>
     */
    private BigDecimal account;

    /**
     * 预计投标金额(用户输入的投标金额)<br>
     * 表 : borrow_tender<br>
     * 对应字段 : pre_account<br>
     */
    private BigDecimal preAccount;

    /**
     * 基础利息收益(标的基本利息+加息券+抵用券)   总利息=account_interest+platform_interest<br>
     * 表 : borrow_tender<br>
     * 对应字段 : account_interest<br>
     */
    private BigDecimal accountInterest;

    /**
     * 平台加息收益(平台加息奖励收益)<br>
     * 表 : borrow_tender<br>
     * 对应字段 : platform_interest<br>
     */
    private BigDecimal platformInterest;

    /**
     * 投标类型:0:普通投标,1债券投标,2:精选计划债权<br>
     * 表 : borrow_tender<br>
     * 对应字段 : tender_type<br>
     */
    private Integer tenderType;

    /**
     * 投标渠道:0:pc,1:ios 2:android 3:h5<br>
     * 表 : borrow_tender<br>
     * 对应字段 : channel<br>
     */
    private Integer channel;

    /**
     * 投标方式:0:手动投标1:自动投标<br>
     * 表 : borrow_tender<br>
     * 对应字段 : tender_mode<br>
     */
    private Integer tenderMode;

    /**
     * 安存交易号<br>
     * 表 : borrow_tender<br>
     * 对应字段 : invest_num<br>
     */
    private String investNum;

    /**
     * 投标时间<br>
     * 表 : borrow_tender<br>
     * 对应字段 : add_time<br>
     */
    private Date addTime;

    /**
     * 更新时间即:满编审核时间或流标时间<br>
     * 表 : borrow_tender<br>
     * 对应字段 : update_time<br>
     */
    private Date updateTime;

    /**
     * 访问ip<br>
     * 表 : borrow_tender<br>
     * 对应字段 : add_ip<br>
     */
    private String addIp;

    /**
     * 备注信息<br>
     * 表 : borrow_tender<br>
     * 对应字段 : remarks<br>
     */
    private String remarks;

    /**
     * 历史的borrow_nid:注意凡带有nid字段的均为兼容老系统的字段<br>
     * 表 : borrow_tender<br>
     * 对应字段 : borrow_nid<br>
     */
    private String borrowNid;

    /**
     * 精选计划投标ID,如果该字段不为空,则为精选计划匹配到的底层标的投标<br>
     * 表 : borrow_tender<br>
     * 对应字段 : choiceness_tender_id<br>
     */
    private Integer choicenessTenderId;

    /**
     * 所使用抵用券ID<br>
     * 表 : borrow_tender<br>
     * 对应字段 : voucher_id<br>
     */
    private Integer voucherId;

    /**
     * 抵用券使用金额(拆分投资时,该字段小于抵用券实际金额)<br>
     * 表 : borrow_tender<br>
     * 对应字段 : voucher_money<br>
     */
    private BigDecimal voucherMoney;

    /**
     * 订单号<br>
     * 表 : borrow_tender<br>
     * 对应字段 : order_id<br>
     */
    private String orderId;

    /**
     * 加息券利息ID<br>
     * 表 : borrow_tender<br>
     * 对应字段 : voucher_coupon_id<br>
     */
    private Integer voucherCouponId;




    /**
     * 加息券利息<br>
     * 表 : borrow_tender<br>
     * 对应字段 : voucher_coupon_id<br>
     */
    private BigDecimal voucherCouponMoney;

    /**
     * 投标奖励<br>
     * 表 : borrow_tender<br>
     * 对应字段 : tender_tran_award<br>
     */
    private BigDecimal tenderTranAward;

    /**
     * 奖励的订单号<br>
     * 表 : borrow_tender<br>
     * 对应字段 : tran_award_orderid<br>
     */
    private String tranAwardOrderid;


    /**
     * 标的名称
     */
    private String productTitle;

    /**
     * 标的编号
     */
    private String productCode;

    /**
     * 是否可转 0否 1是
     */
    private Integer isTransferred;

    /**
     * 可转让时间
     */
    private Date transferTime;

    /**
     * 手机号码
     */
    private String mobile;


    /**
     * 自动投标id
     */
    private Integer autoTenderId;

    /**
     * 投标时 使用的免费提现额度
     */
    private BigDecimal feeAccount;

    public BigDecimal getFeeAccount() {
        return feeAccount;
    }

    public void setFeeAccount(BigDecimal feeAccount) {
        this.feeAccount = feeAccount;
    }

    public Integer getAutoTenderId() {
        return autoTenderId;
    }

    public void setAutoTenderId(Integer autoTenderId) {
        this.autoTenderId = autoTenderId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    private static final long serialVersionUID = 1L;

    public BigDecimal getVoucherCouponMoney() {
        return voucherCouponMoney;
    }

    public void setVoucherCouponMoney(BigDecimal voucherCouponMoney) {
        this.voucherCouponMoney = voucherCouponMoney;
    }

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
     * @return 投标状态:0:投标中(资金冻结,匹配中)1:投标成功(还款中)2:投标失败(流标)3:投标失败(撤销标),4:还款完成:5:转让申请中6:转让成功
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status  投标状态:0:投标中(资金冻结,匹配中)1:投标成功(还款中)2:投标失败(流标)3:投标失败(撤销标),4:还款完成:5:转让申请中6:转让成功
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return 实际投标金额(用户使用抵用券时)
     */
    public BigDecimal getAccount() {
        return account;
    }

    /**
     * @param account  实际投标金额(用户使用抵用券时)
     */
    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    /**
     * @return 预计投标金额(用户输入的投标金额)
     */
    public BigDecimal getPreAccount() {
        return preAccount;
    }

    /**
     * @param preAccount  预计投标金额(用户输入的投标金额)
     */
    public void setPreAccount(BigDecimal preAccount) {
        this.preAccount = preAccount;
    }

    /**
     * @return 基础利息收益(标的基本利息+加息券+抵用券)   总利息=account_interest+platform_interest
     */
    public BigDecimal getAccountInterest() {
        return accountInterest;
    }

    /**
     * @param accountInterest  基础利息收益(标的基本利息+加息券+抵用券)   总利息=account_interest+platform_interest
     */
    public void setAccountInterest(BigDecimal accountInterest) {
        this.accountInterest = accountInterest;
    }

    /**
     * @return 平台加息收益(平台加息奖励收益)
     */
    public BigDecimal getPlatformInterest() {
        return platformInterest;
    }

    /**
     * @param platformInterest  平台加息收益(平台加息奖励收益)
     */
    public void setPlatformInterest(BigDecimal platformInterest) {
        this.platformInterest = platformInterest;
    }

    /**
     * @return 投标类型:0:普通投标,1债券投标,2:精选计划债权
     */
    public Integer getTenderType() {
        return tenderType;
    }

    /**
     * @param tenderType  投标类型:0:普通投标,1债券投标,2:精选计划债权
     */
    public void setTenderType(Integer tenderType) {
        this.tenderType = tenderType;
    }

    /**
     * @return 投标渠道:0:pc,1:ios 2:android 3:h5
     */
    public Integer getChannel() {
        return channel;
    }

    /**
     * @param channel  投标渠道:0:pc,1:ios 2:android 3:h5
     */
    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    /**
     * @return 投标方式:0:手动投标1:自动投标
     */
    public Integer getTenderMode() {
        return tenderMode;
    }

    /**
     * @param tenderMode  投标方式:0:手动投标1:自动投标
     */
    public void setTenderMode(Integer tenderMode) {
        this.tenderMode = tenderMode;
    }

    /**
     * @return 安存交易号
     */
    public String getInvestNum() {
        return investNum;
    }

    /**
     * @param investNum  安存交易号
     */
    public void setInvestNum(String investNum) {
        this.investNum = investNum == null ? null : investNum.trim();
    }

    /**
     * @return 投标时间
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * @param addTime  投标时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * @return 更新时间即:满编审核时间或流标时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime  更新时间即:满编审核时间或流标时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return 访问ip
     */
    public String getAddIp() {
        return addIp;
    }

    /**
     * @param addIp  访问ip
     */
    public void setAddIp(String addIp) {
        this.addIp = addIp == null ? null : addIp.trim();
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
     * @return 精选计划投标ID,如果该字段不为空,则为精选计划匹配到的底层标的投标
     */
    public Integer getChoicenessTenderId() {
        return choicenessTenderId;
    }

    /**
     * @param choicenessTenderId  精选计划投标ID,如果该字段不为空,则为精选计划匹配到的底层标的投标
     */
    public void setChoicenessTenderId(Integer choicenessTenderId) {
        this.choicenessTenderId = choicenessTenderId;
    }

    /**
     * @return 所使用抵用券ID
     */
    public Integer getVoucherId() {
        return voucherId;
    }

    /**
     * @param voucherId  所使用抵用券ID
     */
    public void setVoucherId(Integer voucherId) {
        this.voucherId = voucherId;
    }

    /**
     * @return 抵用券使用金额(拆分投资时,该字段小于抵用券实际金额)
     */
    public BigDecimal getVoucherMoney() {
        return voucherMoney;
    }

    /**
     * @param voucherMoney  抵用券使用金额(拆分投资时,该字段小于抵用券实际金额)
     */
    public void setVoucherMoney(BigDecimal voucherMoney) {
        this.voucherMoney = voucherMoney;
    }

    /**
     * @return 订单号
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId  订单号
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * @return 加息券利息
     */
    public Integer getVoucherCouponId() {
        return voucherCouponId;
    }

    /**
     * @param voucherCouponId  加息券利息
     */
    public void setVoucherCouponId(Integer voucherCouponId) {
        this.voucherCouponId = voucherCouponId;
    }

    /**
     * @return 投标奖励
     */
    public BigDecimal getTenderTranAward() {
        return tenderTranAward;
    }

    /**
     * @param tenderTranAward  投标奖励
     */
    public void setTenderTranAward(BigDecimal tenderTranAward) {
        this.tenderTranAward = tenderTranAward;
    }

    /**
     * @return 奖励的订单号
     */
    public String getTranAwardOrderid() {
        return tranAwardOrderid;
    }

    /**
     * @param tranAwardOrderid  奖励的订单号
     */
    public void setTranAwardOrderid(String tranAwardOrderid) {
        this.tranAwardOrderid = tranAwardOrderid == null ? null : tranAwardOrderid.trim();
    }


    public Integer getIsTransferred() {
        return isTransferred;
    }

    public void setIsTransferred(Integer isTransferred) {
        this.isTransferred = isTransferred;
    }

    public Date getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(Date transferTime) {
        this.transferTime = transferTime;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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
        BorrowTender other = (BorrowTender) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getBorrowId() == null ? other.getBorrowId() == null : this.getBorrowId().equals(other.getBorrowId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getAccount() == null ? other.getAccount() == null : this.getAccount().equals(other.getAccount()))
            && (this.getPreAccount() == null ? other.getPreAccount() == null : this.getPreAccount().equals(other.getPreAccount()))
            && (this.getAccountInterest() == null ? other.getAccountInterest() == null : this.getAccountInterest().equals(other.getAccountInterest()))
            && (this.getPlatformInterest() == null ? other.getPlatformInterest() == null : this.getPlatformInterest().equals(other.getPlatformInterest()))
            && (this.getTenderType() == null ? other.getTenderType() == null : this.getTenderType().equals(other.getTenderType()))
            && (this.getChannel() == null ? other.getChannel() == null : this.getChannel().equals(other.getChannel()))
            && (this.getTenderMode() == null ? other.getTenderMode() == null : this.getTenderMode().equals(other.getTenderMode()))
            && (this.getInvestNum() == null ? other.getInvestNum() == null : this.getInvestNum().equals(other.getInvestNum()))
            && (this.getAddTime() == null ? other.getAddTime() == null : this.getAddTime().equals(other.getAddTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getAddIp() == null ? other.getAddIp() == null : this.getAddIp().equals(other.getAddIp()))
            && (this.getRemarks() == null ? other.getRemarks() == null : this.getRemarks().equals(other.getRemarks()))
            && (this.getBorrowNid() == null ? other.getBorrowNid() == null : this.getBorrowNid().equals(other.getBorrowNid()))
            && (this.getChoicenessTenderId() == null ? other.getChoicenessTenderId() == null : this.getChoicenessTenderId().equals(other.getChoicenessTenderId()))
            && (this.getVoucherId() == null ? other.getVoucherId() == null : this.getVoucherId().equals(other.getVoucherId()))
            && (this.getVoucherMoney() == null ? other.getVoucherMoney() == null : this.getVoucherMoney().equals(other.getVoucherMoney()))
            && (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getVoucherCouponId() == null ? other.getVoucherCouponId() == null : this.getVoucherCouponId().equals(other.getVoucherCouponId()))
            && (this.getTenderTranAward() == null ? other.getTenderTranAward() == null : this.getTenderTranAward().equals(other.getTenderTranAward()))
            && (this.getTranAwardOrderid() == null ? other.getTranAwardOrderid() == null : this.getTranAwardOrderid().equals(other.getTranAwardOrderid()));
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
        result = prime * result + ((getAccount() == null) ? 0 : getAccount().hashCode());
        result = prime * result + ((getPreAccount() == null) ? 0 : getPreAccount().hashCode());
        result = prime * result + ((getAccountInterest() == null) ? 0 : getAccountInterest().hashCode());
        result = prime * result + ((getPlatformInterest() == null) ? 0 : getPlatformInterest().hashCode());
        result = prime * result + ((getTenderType() == null) ? 0 : getTenderType().hashCode());
        result = prime * result + ((getChannel() == null) ? 0 : getChannel().hashCode());
        result = prime * result + ((getTenderMode() == null) ? 0 : getTenderMode().hashCode());
        result = prime * result + ((getInvestNum() == null) ? 0 : getInvestNum().hashCode());
        result = prime * result + ((getAddTime() == null) ? 0 : getAddTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getAddIp() == null) ? 0 : getAddIp().hashCode());
        result = prime * result + ((getRemarks() == null) ? 0 : getRemarks().hashCode());
        result = prime * result + ((getBorrowNid() == null) ? 0 : getBorrowNid().hashCode());
        result = prime * result + ((getChoicenessTenderId() == null) ? 0 : getChoicenessTenderId().hashCode());
        result = prime * result + ((getVoucherId() == null) ? 0 : getVoucherId().hashCode());
        result = prime * result + ((getVoucherMoney() == null) ? 0 : getVoucherMoney().hashCode());
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getVoucherCouponId() == null) ? 0 : getVoucherCouponId().hashCode());
        result = prime * result + ((getTenderTranAward() == null) ? 0 : getTenderTranAward().hashCode());
        result = prime * result + ((getTranAwardOrderid() == null) ? 0 : getTranAwardOrderid().hashCode());
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
        sb.append(", account=").append(account);
        sb.append(", preAccount=").append(preAccount);
        sb.append(", accountInterest=").append(accountInterest);
        sb.append(", platformInterest=").append(platformInterest);
        sb.append(", tenderType=").append(tenderType);
        sb.append(", channel=").append(channel);
        sb.append(", tenderMode=").append(tenderMode);
        sb.append(", investNum=").append(investNum);
        sb.append(", addTime=").append(addTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", addIp=").append(addIp);
        sb.append(", remarks=").append(remarks);
        sb.append(", borrowNid=").append(borrowNid);
        sb.append(", choicenessTenderId=").append(choicenessTenderId);
        sb.append(", voucherId=").append(voucherId);
        sb.append(", voucherMoney=").append(voucherMoney);
        sb.append(", orderId=").append(orderId);
        sb.append(", voucherCouponId=").append(voucherCouponId);
        sb.append(", tenderTranAward=").append(tenderTranAward);
        sb.append(", tranAwardOrderid=").append(tranAwardOrderid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}