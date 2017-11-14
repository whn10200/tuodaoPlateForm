package com.tuodao.bp.model.business.operation.input;

import com.tuodao.bp.model.BasePojo;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 用于投资
 * author hechuan
 * <p>
 * created on 2017/11/13
 * <p>
 * since V1.0.0
 */
@Data
@ToString
public class UserDiscountTenderInput extends BasePojo implements Serializable {

    private static final long serialVersionUID = -709108902054915881L;

    /**
     * 标的金额(元)
     */
    private Integer scaleMoney;

    /**
     * 标的期限(月)
     */
    private Integer scaleTimeLimit;

    /**
     * 精选计划、散标项目  根据输入"金额"和"标的期限"  返回可用加息券和抵用券
     * 债权转让 根据输入金额和标的期限  返回可用加息券 (债权转让不可以使用抵用券)
     *
     * 需要类型（1:加息券和抵用券都要,2:只要加息券，3：只要抵用券）
     */
    private Integer needType;


}
