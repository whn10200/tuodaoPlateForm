package com.tuodao.bp.model.business.traningcenter.output;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author qingli.chen
 * @date 2017/9/19
 * @description
 */
@Data
@ToString
public class BorrowCollectionCapitalOutput implements Serializable {
    private static final long serialVersionUID = -5206555669415385631L;

    /**
     * 预计回款本金
     */
    private BigDecimal preCapital;
    /**
     * 实际回款本金
     */
    private BigDecimal capital;

    private Integer sumCount;

    private BigDecimal interest;

    /**
     * 未获得利息
     */
    private BigDecimal unInterest;

    private Integer tenderId;

    private Date lastRepayTime;

    /**
     * 最早一笔应还利息
     */
    private BigDecimal repayInterest;

    /**
     * 当期应收利息
     */
    private BigDecimal currentInterest;


}
