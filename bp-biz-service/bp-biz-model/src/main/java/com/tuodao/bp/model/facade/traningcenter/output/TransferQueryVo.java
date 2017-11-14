package com.tuodao.bp.model.facade.traningcenter.output;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author qingli.chen
 * @date 2017/9/21
 * @description 债转投资 页面对象
 */
@Data
public class TransferQueryVo implements Serializable {
    private static final long serialVersionUID = -5510662940208408651L;

    //债权
    @JsonProperty("transferId")
    private Integer id;

    //产品名称
    @JsonProperty("name")
    private String borrowName;

    /**
     * 剩余期限
     */
    private Integer period;

    /**
     * 期限类型
     */
    private Integer periodType;

    /**
     * 转让金额
     */
    private BigDecimal account;

    /**
     * 对应债权价值
     */
    private BigDecimal claimsValue;

    /**
     * 奖励
     */
    private BigDecimal reward;

    /**
     * 原始标的id
     */
    private Integer preBorrowId;

    /**
     * 原标编号
     */
    private String productCode;

    /**
     * 剩余可投
     */
    private BigDecimal lastAccount;

    /**
     * 年化率
     */
    private BigDecimal apr;

    /**
     * 是否抢完
     */
    private boolean finished;

    /**
     * 计息方式
     */
    private String interestType;
}
