package com.tuodao.bp.model.facade.traningcenter.output;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 投资债权 vo对象
 * @author qingli.chen
 * @date 2017/11/7
 * @description
 */
@Data
@ToString
public class TenderTransferInfoVo implements Serializable {

    /**
     * 项目名称
     */
    private String title;
    /**
     * 投资金额
     */
    private BigDecimal account;
    /**
     * 投资收益
     */
    private BigDecimal interest;
    /**
     * 优惠券
     */
    private String voucher;
}
