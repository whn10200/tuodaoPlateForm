package com.tuodao.bp.model.facade.traningcenter.output;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author tookbra
 * @date 2017/9/19
 * @description 债权转让详情
 */
@Data
public class CreditAssignmentInfoVo implements Serializable {

    private static final long serialVersionUID = -60595371875112875L;

    /**
     * 可转金额
     */
    private BigDecimal account;

    /**
     * 债转价值
     */
    private BigDecimal transferWorth;

    /**
     * 转让手续费
     */
    private BigDecimal fee;

    /**
     * 剩余期数
     */
    private Integer period;

    /**
     * 利率
     */
    private BigDecimal borrowApr;

    /**
     * 产品名称
     */
    private String productTitle;

    /**
     * 奖励利率
     */
    private BigDecimal awardScale;

    /**
     * 转让亏盈
     */
    private BigDecimal profit;

    /**
     * 产品编号
     */
    private String productCode;
    /**
     * 投资id
     */
    private Integer tenderId;
    /**
     * 原始标的id
     */
    private Integer borrowId;

}
