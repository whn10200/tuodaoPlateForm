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
public class BorrowTransferQueryOutput implements Serializable {
    private static final long serialVersionUID = -4090729033372776548L;

    //债权
    private Integer id;

    //产品名称
    private String borrowName;

    //剩余期限
    private Integer period;

    //期限类型
    private Integer periodType;

    //转让金额
    private BigDecimal account;

    //对应债权价值
    private BigDecimal claimsValue;

    //奖励
    private BigDecimal reward;

    //原始标的id
    private Integer preBorrowId;

    //剩余可投
    private BigDecimal lastAccount;

    //年化率
    private BigDecimal apr;

    //还款方式
    private String repaymentType;

    //用户id
    private String userId;

    /**
     * 是否已抢完
     */
    private boolean finished;

    /**
     * 计息方式
     */
    private String interestType = "满标复审计息";
}