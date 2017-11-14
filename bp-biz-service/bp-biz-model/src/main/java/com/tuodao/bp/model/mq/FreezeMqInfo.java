package com.tuodao.bp.model.mq;

import com.tuodao.bp.model.depository.DepositoryPara;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author tookbra
 * @date 2017/10/20
 * @description
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class FreezeMqInfo extends DepositoryPara {

    /**
     * 金额
     */
    private BigDecimal amount;
    /**
     * 01冻结，02解冻
     */
    private String freezeFlg;
    /**
     * 冻结订单号
     */
    private String freezeOrderNo;
    /**
     *
     */
    private Integer borrowId;

}
