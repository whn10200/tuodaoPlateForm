package com.tuodao.bp.model.business.operation.input;

import com.google.common.base.MoreObjects;
import com.tuodao.bp.model.PagePojo;
import com.tuodao.bp.model.annotation.In;
import com.tuodao.bp.model.constant.operation.OperationResponseConstant;
import com.tuodao.bp.model.constant.operation.UserDiscountConstant;

import java.io.Serializable;


/**
 * 我的优惠券input
 * author hechuan
 * <p>
 * created on 2017/9/20
 * <p>
 * since V1.0.0
 */
public class UserDiscountsInput extends PagePojo implements Serializable{
    /**
     * 类型(1:抵用券,2:加息券)
     */
    @In(value = UserDiscountConstant.DISCOUNT_TYPE_ALL,required = true,message = OperationResponseConstant.PARAM_USER_DISCOUNT_TYPE_ERROR+"")
    private Integer discountType;

    /**
     * 优惠券状态（1：可使用，2：已使用，3：已过期）
     */
    @In(value = UserDiscountConstant.DISCOUNT_STATUS_ALL,required = true,message = OperationResponseConstant.PARAM_USER_DISCOUNT_STATUS_ERROR+"")
    private Integer discountStatus;

    /**
     * 是否锁定(1:正常，2：锁定)
     */
    @In(value = UserDiscountConstant.DISCOUNT_LOCK_ALL,message = OperationResponseConstant.PARAM_USER_DISCOUNT_LOCK_ERROR+"")
    private Integer discountLock;

    /**
     * 标的金额(元)
     */
    private Integer scaleMoney;

    /**
     * 标的期限(月)
     */
    private Integer scaleTimeLimit;

    public Integer getDiscountType() {
        return discountType;
    }

    public void setDiscountType(Integer discountType) {
        this.discountType = discountType;
    }

    public Integer getDiscountStatus() {
        return discountStatus;
    }

    public void setDiscountStatus(Integer discountStatus) {
        this.discountStatus = discountStatus;
    }

    public Integer getDiscountLock() {
        return discountLock;
    }

    public void setDiscountLock(Integer discountLock) {
        this.discountLock = discountLock;
    }

    public Integer getScaleMoney() {
        return scaleMoney;
    }

    public void setScaleMoney(Integer scaleMoney) {
        this.scaleMoney = scaleMoney;
    }

    public Integer getScaleTimeLimit() {
        return scaleTimeLimit;
    }

    public void setScaleTimeLimit(Integer scaleTimeLimit) {
        this.scaleTimeLimit = scaleTimeLimit;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
                .add("discountType",discountType)
                .add("discountStatus",discountStatus)
                .add("discountLock",discountLock)
                .add("scaleMoney",scaleMoney)
                .add("scaleTimeLimit",scaleTimeLimit)
                .toString();
    }
}
