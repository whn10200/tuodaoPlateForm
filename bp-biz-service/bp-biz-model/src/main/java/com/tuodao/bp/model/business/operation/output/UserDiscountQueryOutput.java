package com.tuodao.bp.model.business.operation.output;

import lombok.Data;

import java.io.Serializable;

/**
 * 提供给交易中心调用的用户优惠券查询
 * author hechuan
 * <p>
 * created on 2017/10/25
 * <p>
 * since V1.0.0
 */
@Data
public class UserDiscountQueryOutput implements Serializable {
    private static final long serialVersionUID = -820109170392470742L;
    /**
     * 券ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     *类型(1:抵用券,2:加息券)
     */
    private Integer discountType;

    /**
     * 额度(抵用券就是多少钱，加息券就是百分比)
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

}
