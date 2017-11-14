package com.tuodao.bp.operation.persistence.model.biz;

import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * @description: 运营活动信息
 * @author: mif
 * @date: 2017/9/28
 * @time: 18:40
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class BizOpWelfareActivity implements Serializable {

    private static final long serialVersionUID = -8591689624183041169L;

    /**
     * 福利活动CODE
     */
    private String welfareActivityCode;
    /**
     * 活动名称
     */
    private String welfareActivityName;

    /**
     * 生效日期
     */
    private Date effectDate;

    /**
     * 失效日期
     */
    private Date invalidDate;

    /**
     * 数量
     */
    private Integer quantity;

    /**
     * 券ID
     */
    private Integer couponId;

    /**
     * 标题
     */
    private String discountTitle;

    /**
     * 类型(1:抵用券,2:加息券;3:提现次数)
     */
    private Integer discountType;

    /**
     * 额度(抵用券就是多少钱，加息券就是百分比，提现为次数)
     */
    private String discountAvailable;

    /**
     * 金额限制(如:5000元以上的标的)
     */
    private Integer moneyLimit;

    /**
     * 时长限制(如:12个月及以上)
     */
    private Integer dateLimit;

    /**
     * 有效期(天)
     */
    private Integer effectDay;

    /**
     * 备注
     */
    private String remark;

    public String getWelfareActivityCode() {
        return welfareActivityCode;
    }

    public void setWelfareActivityCode(String welfareActivityCode) {
        this.welfareActivityCode = welfareActivityCode;
    }

    public String getWelfareActivityName() {
        return welfareActivityName;
    }

    public void setWelfareActivityName(String welfareActivityName) {
        this.welfareActivityName = welfareActivityName;
    }

    public Date getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(Date effectDate) {
        this.effectDate = effectDate;
    }

    public Date getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getDiscountTitle() {
        return discountTitle;
    }

    public void setDiscountTitle(String discountTitle) {
        this.discountTitle = discountTitle;
    }

    public Integer getDiscountType() {
        return discountType;
    }

    public void setDiscountType(Integer discountType) {
        this.discountType = discountType;
    }

    public String getDiscountAvailable() {
        return discountAvailable;
    }

    public void setDiscountAvailable(String discountAvailable) {
        this.discountAvailable = discountAvailable;
    }

    public Integer getMoneyLimit() {
        return moneyLimit;
    }

    public void setMoneyLimit(Integer moneyLimit) {
        this.moneyLimit = moneyLimit;
    }

    public Integer getDateLimit() {
        return dateLimit;
    }

    public void setDateLimit(Integer dateLimit) {
        this.dateLimit = dateLimit;
    }

    public Integer getEffectDay() {
        return effectDay;
    }

    public void setEffectDay(Integer effectDay) {
        this.effectDay = effectDay;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("welfareActivityCode", welfareActivityCode)
                .add("welfareActivityName", welfareActivityName)
                .add("effectDate", effectDate)
                .add("invalidDate", invalidDate)
                .add("quantity", quantity)
                .add("couponId", couponId)
                .add("discountTitle", discountTitle)
                .add("discountType", discountType)
                .add("discountAvailable", discountAvailable)
                .add("moneyLimit", moneyLimit)
                .add("dateLimit", dateLimit)
                .add("effectDay", effectDay)
                .add("remark", remark)
                .toString();
    }
}
