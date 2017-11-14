package com.tuodao.bp.model.business.operation.output;

import com.tuodao.bp.model.business.operation.input.TenderVoucherInput;

import java.io.Serializable;

/**
 * author hechuan
 * <p>
 * created on 2017/10/18
 * <p>
 * since V1.0.0
 */
public class TenderVoucherOutput extends TenderVoucherInput implements Serializable {

    private static final long serialVersionUID = -4194333612312102098L;

    /**
     * 标题
     */
    private String discountTitle;

    /**
     * 类型(1:抵用券,2:加息券,3:免费提现次数)
     */
    private Integer discountType;

    /**
     * 额度(抵用券就是多少钱，加息券就是百分比，免费提现次数就是次数)
     */
    private String discountAvailable;

    /**
     * 优惠券状态（1：可使用，2：已使用，3：已过期）
     */
    private Integer disStatus;

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

    public Integer getDisStatus() {
        return disStatus;
    }

    public void setDisStatus(Integer disStatus) {
        this.disStatus = disStatus;
    }
}
