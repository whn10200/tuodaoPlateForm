package com.tuodao.bp.model.business.traningcenter.output;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author qingli.chen
 * @date 2017/9/19
 * @description 回款计划
 */
@Data
@ToString
public class BackMoneyPlanOutput implements Serializable {
    private static final long serialVersionUID = -1639300001799183598L;

    /**
     * 产品id
     */
    private Integer borrowId;

    /**
     * 状态 0未回款 1已回款 7 已转让
     */
    private Integer status;

    /**
     * 状态说明
     */
    private String statusMsg;

    /**
     * 当前期数
     */
    private Integer period;

    /**
     * 总期数
     */
    private Integer periods;

    /**
     * 回款时间 实际回款时间
     */
    private Date collectionTime;

    /**
     * 利息 实际回款利息
     */
    private BigDecimal interest;

    /**
     * 本金 实际回款本金
     */
    private BigDecimal capital;
    /**
     * 预计回款时间
     */
    private Date preCollectionTime;

    /**
     * 预计回款利息总利息 当期
     */
    private BigDecimal preInterest;

    /**
     * 预计回款本金
     */
    private BigDecimal preCapital;

    /**
     * 加息券利息 当期
     */
    private BigDecimal couponAccount;

    /**
     * 平台加息利息 当期
     */
    private BigDecimal platformAccount;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 回款id
     */
    private Integer id;

}
