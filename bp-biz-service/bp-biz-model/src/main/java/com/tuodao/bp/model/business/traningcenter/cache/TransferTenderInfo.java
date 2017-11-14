package com.tuodao.bp.model.business.traningcenter.cache;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 债权投标
 * @author qingli.chen
 * @date 2017/10/24
 * @description
 */
@Data
@ToString
public class TransferTenderInfo implements Serializable {

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 金额
     */
    private BigDecimal account;

    /**
     * 是否成功
     */
    private boolean success = false;
}
