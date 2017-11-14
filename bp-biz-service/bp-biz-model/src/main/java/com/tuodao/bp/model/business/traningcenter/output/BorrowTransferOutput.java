package com.tuodao.bp.model.business.traningcenter.output;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author qingli.chen
 * @date 2017-09-15
 * @description
 */
@Data
@ToString
public class BorrowTransferOutput implements Serializable {
    private static final long serialVersionUID = -4090729033372776548L;

    /**
     * 产品id
     */
    private Integer borrowId;

    /**
     * 转让时间
     */
    private Date addTime;
    /**
     * 到期时间
     */
    private Date endTime;

    /**
     * 剩余期限
     */
    private Integer period;

    /**
     * 转让金额
     */
    private BigDecimal account = BigDecimal.ZERO;

    /**
     * 对应债权价值
     */
    private BigDecimal claimsValue = BigDecimal.ZERO;

    /**
     * 折价
     */
    private BigDecimal discount = BigDecimal.ZERO;

    /**
     * 手续费
     */
    private BigDecimal fee = BigDecimal.ZERO;

    /**
     * 利息
     */
    private BigDecimal interest;

    /**
     * 奖励
     */
    private BigDecimal reward;

    /**
     * 状态
     */
    private Integer status;
    /**
     * 状态说明
     */
    private String statusMsg;

    private Integer preBorrowId;
    /**
     * 债转id
     */
    private Integer transferId;

}