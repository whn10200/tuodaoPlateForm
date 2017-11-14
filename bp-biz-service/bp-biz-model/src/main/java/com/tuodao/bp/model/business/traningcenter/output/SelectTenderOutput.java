package com.tuodao.bp.model.business.traningcenter.output;

/**
 * @description:
 * @author: wuzf
 * @date: 2017/9/20 0020.
 * @time: 11:31
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class SelectTenderOutput {

    //加入时间
    private String addTime;

    //加入金额
    private String preAccount;

    //用户id
    private String userId;

    //理财计划产品id
    private Integer borrowId;

    //产品名称
    private String productName;

    //产品编号
    private String borrowNid;

    //预计收益
    private String preAccountInterest;

    //加息劵id
    private Integer voucherId;

    //抵用券id
    private Integer voucherCouponId;

    //状态
    private String  status;

    //加入id
    private String tenderId;

    //优惠卷使用情况
    private String useVoucherRemark;

    public String getUseVoucherRemark() {
        return useVoucherRemark;
    }

    public void setUseVoucherRemark(String useVoucherRemark) {
        this.useVoucherRemark = useVoucherRemark;
    }

    public String getBorrowNid() {
        return borrowNid;
    }

    public void setBorrowNid(String borrowNid) {
        this.borrowNid = borrowNid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPreAccountInterest() {
        return preAccountInterest;
    }

    public void setPreAccountInterest(String preAccountInterest) {
        this.preAccountInterest = preAccountInterest;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getPreAccount() {
        return preAccount;
    }

    public void setPreAccount(String preAccount) {
        this.preAccount = preAccount;
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

    public Integer getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Integer voucherId) {
        this.voucherId = voucherId;
    }

    public Integer getVoucherCouponId() {
        return voucherCouponId;
    }

    public void setVoucherCouponId(Integer voucherCouponId) {
        this.voucherCouponId = voucherCouponId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTenderId() {
        return tenderId;
    }

    public void setTenderId(String tenderId) {
        this.tenderId = tenderId;
    }
}
