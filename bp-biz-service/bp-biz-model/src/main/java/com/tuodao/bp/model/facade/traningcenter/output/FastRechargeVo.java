package com.tuodao.bp.model.facade.traningcenter.output;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 充值信息
 * @author qingli.chen
 * @date 2017/10/16
 * @description
 */
@Data
public class FastRechargeVo implements Serializable {
    private static final long serialVersionUID = 7274732089298902648L;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 单笔限额
     */
    private BigDecimal limitOneTime;

    /**
     * 最小充值金额
     */
    private int minRechargeMoney = 100;

    /**
     * 银行卡号
     */
    private String account;

    /**
     * 账户余额
     */
    private BigDecimal money;

    /**
     * 是否清算时间
     */
    private boolean cleanTime = false;
    /**
     * 图片地址
     */
    private String bankUrl;
}
