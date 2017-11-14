package com.tuodao.bp.model.mq;

import com.tuodao.bp.model.depository.DepositoryPara;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 网关充值 请求 存管参数
 * @author qingli.chen
 * @date 2017/10/16
 * @description
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class GatewayRechargeMqInfo extends DepositoryPara {

    private static final long serialVersionUID = -8005914326518771955L;

    /**
     * 充值类型（1-用户充值）
     */
    private Integer type = 1;

    /**
     * 1-投资账户 2-融资账户 必填项
     */
    private Integer chargeType = 1;

    /**
     * 银行编码
     */
    private String bankCode;

    /**
     * 卡号
     */
    private String cardNo;

    /**
     * 金额
     */
    private BigDecimal amt;

    /**
     * 同步回调地址
     */
    private String returnUrl;

    /**
     * 异步回调地址
     */
    private String notifyUrl;

    /**
     * 支付通道
     */
    private String payCode;

    /**
     * 订单时间
     */
    private String transTime;
    /**
     * 支付渠道流水号
     */
    private String hostReqSerialNo;
    /**
     * 支付完成日期
     */
    private String payFinishDate;
    /**
     * 支付完成时间
     */
    private String payFinishTime;
    /**
     * 订单状态 2:处理成功,3:处理失败
     */
    private Integer orderStatus;
    /**
     * 支付金额
     */
    private BigDecimal payAmt;

}
