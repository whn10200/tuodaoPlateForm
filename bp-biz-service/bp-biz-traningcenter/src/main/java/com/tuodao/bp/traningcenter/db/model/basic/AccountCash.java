package com.tuodao.bp.traningcenter.db.model.basic;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AccountCash implements Serializable {
    /**
     * 主键<br>
     * 表 : account_cash<br>
     * 对应字段 : id<br>
     */
    private Integer id;

    /**
     * 用户id<br>
     * 表 : account_cash<br>
     * 对应字段 : user_id<br>
     */
    private String userId;

    /**
     * 提现订单号(唯一)<br>
     * 表 : account_cash<br>
     * 对应字段 : order_no<br>
     */
    private String orderNo;

    /**
     * 银行卡表id<br>
     * 表 : account_cash<br>
     * 对应字段 : bank_num<br>
     */
    private String bankNum;

    /**
     * 提现状态:0:申请中:1提现成功2:提现失败<br>
     * 表 : account_cash<br>
     * 对应字段 : status<br>
     */
    private Integer status;

    /**
     * 提现金额<br>
     * 表 : account_cash<br>
     * 对应字段 : account<br>
     */
    private BigDecimal account;

    /**
     * 提现手续费<br>
     * 表 : account_cash<br>
     * 对应字段 : fee<br>
     */
    private BigDecimal fee;

    /**
     * 实际到账金额<br>
     * 表 : account_cash<br>
     * 对应字段 : real_account<br>
     */
    private BigDecimal realAccount;

    /**
     * 提现申请时间<br>
     * 表 : account_cash<br>
     * 对应字段 : add_time<br>
     */
    private Date addTime;

    /**
     * 提现更新时间<br>
     * 表 : account_cash<br>
     * 对应字段 : update_time<br>
     */
    private Date updateTime;

    /**
     * 审核备注<br>
     * 表 : account_cash<br>
     * 对应字段 : verify_remarks<br>
     */
    private String verifyRemarks;

    /**
     * 安存保存号<br>
     * 表 : account_cash<br>
     * 对应字段 : cash_num<br>
     */
    private String cashNum;

    /**
     * 提现ip<br>
     * 表 : account_cash<br>
     * 对应字段 : add_ip<br>
     */
    private String addIp;

    /**
     * 提现来源0:pc,1:ios,2:android,3:h5,4:后台<br>
     * 表 : account_cash<br>
     * 对应字段 : source<br>
     */
    private Integer source;

    /**
     * 备注信息<br>
     * 表 : account_cash<br>
     * 对应字段 : remarks<br>
     */
    private String remarks;

    /**
     * 本次提现免费部分的金额
     */
    private BigDecimal feeAccount;

    /**
     * 备注信息<br>
     *     表 : account_cash<br>
     *         对应字段 : use_free<br>
     * 本次提现是否使用免费提现次数 0:没有使用 1:使用
     */
    private Integer useFree;

    public Integer getUseFree() {
        return useFree;
    }

    public void setUseFree(Integer useFree) {
        this.useFree = useFree;
    }

    public BigDecimal getFeeAccount() {
        return feeAccount;
    }

    public void setFeeAccount(BigDecimal feeAccount) {
        this.feeAccount = feeAccount;
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
     * @return 提现订单号(唯一)
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * @param orderNo  提现订单号(唯一)
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getBankNum() {
        return bankNum;
    }

    public void setBankNum(String bankNum) {
        this.bankNum = bankNum;
    }

    /**
     * @return 提现状态:0:申请中:1提现成功2:提现失败
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status  提现状态:0:申请中:1提现成功2:提现失败
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return 提现金额
     */
    public BigDecimal getAccount() {
        return account;
    }

    /**
     * @param account  提现金额
     */
    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    /**
     * @return 提现手续费
     */
    public BigDecimal getFee() {
        return fee;
    }

    /**
     * @param fee  提现手续费
     */
    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    /**
     * @return 实际到账金额
     */
    public BigDecimal getRealAccount() {
        return realAccount;
    }

    /**
     * @param realAccount  实际到账金额
     */
    public void setRealAccount(BigDecimal realAccount) {
        this.realAccount = realAccount;
    }

    /**
     * @return 提现申请时间
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * @param addTime  提现申请时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * @return 提现更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime  提现更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return 审核备注
     */
    public String getVerifyRemarks() {
        return verifyRemarks;
    }

    /**
     * @param verifyRemarks  审核备注
     */
    public void setVerifyRemarks(String verifyRemarks) {
        this.verifyRemarks = verifyRemarks == null ? null : verifyRemarks.trim();
    }

    /**
     * @return 安存保存号
     */
    public String getCashNum() {
        return cashNum;
    }

    /**
     * @param cashNum  安存保存号
     */
    public void setCashNum(String cashNum) {
        this.cashNum = cashNum == null ? null : cashNum.trim();
    }

    /**
     * @return 提现ip
     */
    public String getAddIp() {
        return addIp;
    }

    /**
     * @param addIp  提现ip
     */
    public void setAddIp(String addIp) {
        this.addIp = addIp == null ? null : addIp.trim();
    }

    /**
     * @return 提现来源0:pc,1:ios,2:android,3:h5,4:后台
     */
    public Integer getSource() {
        return source;
    }

    /**
     * @param source  提现来源0:pc,1:ios,2:android,3:h5,4:后台
     */
    public void setSource(Integer source) {
        this.source = source;
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

    @Override
    public String toString() {
        return "AccountCash{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", bankNum='" + bankNum + '\'' +
                ", status=" + status +
                ", account=" + account +
                ", fee=" + fee +
                ", realAccount=" + realAccount +
                ", addTime=" + addTime +
                ", updateTime=" + updateTime +
                ", verifyRemarks='" + verifyRemarks + '\'' +
                ", cashNum='" + cashNum + '\'' +
                ", addIp='" + addIp + '\'' +
                ", source=" + source +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}