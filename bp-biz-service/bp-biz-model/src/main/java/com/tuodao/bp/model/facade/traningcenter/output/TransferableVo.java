package com.tuodao.bp.model.facade.traningcenter.output;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 可转让列表
 * @author qingli.chen
 * @date 2017/11/10
 * @description
 */
@Data
@ToString
public class TransferableVo implements Serializable {

    /**
     * 原始标的id
     */
    private Integer id;
    /**
     * 标的名称
     */
    private String title;
    /**
     * 标的编号
     */
    private String productCode;
    /**
     * 金额
     */
    private BigDecimal amount;
    /**
     * 剩余期数
     */
    private Integer remaining;
    /**
     * 年化率
     */
    private String apr;
}
