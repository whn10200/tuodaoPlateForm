package com.tuodao.bp.model.business.traningcenter.input;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2017/10/30 0030.
 */
public class PlanTenderInput {

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    public BigDecimal getPreAccount() {
        return preAccount;
    }

    public void setPreAccount(BigDecimal preAccount) {
        this.preAccount = preAccount;
    }

    public BigDecimal getAccountInterest() {
        return accountInterest;
    }

    public void setAccountInterest(BigDecimal accountInterest) {
        this.accountInterest = accountInterest;
    }

    public BigDecimal getPlatformInterest() {
        return platformInterest;
    }

    public void setPlatformInterest(BigDecimal platformInterest) {
        this.platformInterest = platformInterest;
    }

    public Integer getTenderType() {
        return tenderType;
    }

    public void setTenderType(Integer tenderType) {
        this.tenderType = tenderType;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Integer getTenderMode() {
        return tenderMode;
    }

    public void setTenderMode(Integer tenderMode) {
        this.tenderMode = tenderMode;
    }

    public String getInvestNum() {
        return investNum;
    }

    public void setInvestNum(String investNum) {
        this.investNum = investNum;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getAddIp() {
        return addIp;
    }

    public void setAddIp(String addIp) {
        this.addIp = addIp;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getBorrowNid() {
        return borrowNid;
    }

    public void setBorrowNid(String borrowNid) {
        this.borrowNid = borrowNid;
    }

    public Integer getChoicenessTenderId() {
        return choicenessTenderId;
    }

    public void setChoicenessTenderId(Integer choicenessTenderId) {
        this.choicenessTenderId = choicenessTenderId;
    }

    public Integer getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Integer voucherId) {
        this.voucherId = voucherId;
    }

    public BigDecimal getVoucherMoney() {
        return voucherMoney;
    }

    public void setVoucherMoney(BigDecimal voucherMoney) {
        this.voucherMoney = voucherMoney;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getVoucherCouponId() {
        return voucherCouponId;
    }

    public void setVoucherCouponId(Integer voucherCouponId) {
        this.voucherCouponId = voucherCouponId;
    }

    public BigDecimal getVoucherCouponMoney() {
        return voucherCouponMoney;
    }

    public void setVoucherCouponMoney(BigDecimal voucherCouponMoney) {
        this.voucherCouponMoney = voucherCouponMoney;
    }

    public BigDecimal getTenderTranAward() {
        return tenderTranAward;
    }

    public void setTenderTranAward(BigDecimal tenderTranAward) {
        this.tenderTranAward = tenderTranAward;
    }

    public String getTranAwardOrderid() {
        return tranAwardOrderid;
    }

    public void setTranAwardOrderid(String tranAwardOrderid) {
        this.tranAwardOrderid = tranAwardOrderid;
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

    public Integer getAutoTenderId() {
        return autoTenderId;
    }

    public void setAutoTenderId(Integer autoTenderId) {
        this.autoTenderId = autoTenderId;
    }
}
