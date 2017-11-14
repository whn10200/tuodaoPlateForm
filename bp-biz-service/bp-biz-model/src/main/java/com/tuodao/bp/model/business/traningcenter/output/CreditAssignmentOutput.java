package com.tuodao.bp.model.business.traningcenter.output;

import com.tuodao.bp.model.PagePojo;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author qingli.chen
 * @date 2017/9/14
 * @description
 */
@Data
@ToString
public class CreditAssignmentOutput extends PagePojo {

    private static final long serialVersionUID = 7067033468404151629L;

    /**
     * 产品名称
     */
    private String productTitle;

    /**
     * 期数
     */
    private Integer productPeriod;

    /**
     * 剩余期数
     */
    private Integer remaining;

    /**
     * 投标id
     */
    private Integer tenderId;

    /**
     * 标的id
     */
    private Integer borrowId;

    /**
     * 产品编号
     */
    private String productCode;

    /**
     * 年化率
     */
    private BigDecimal borrowApr;

    /**
     * 可转让金额
     */
    private BigDecimal recoverCapital;

}
