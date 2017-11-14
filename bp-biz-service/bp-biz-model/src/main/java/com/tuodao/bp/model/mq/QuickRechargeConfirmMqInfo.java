package com.tuodao.bp.model.mq;

import com.tuodao.bp.model.depository.DepositoryPara;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 存管 快捷充值确认
 * @author qingli.chen
 * @date 2017/10/18
 * @description
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class QuickRechargeConfirmMqInfo extends DepositoryPara {

    private static final long serialVersionUID = 3073582472019469952L;


    /**
     * 短信验证码
     */
    private String identifyingCode;

    /**
     * 原快捷充值订单号
     */
    private String originOrderNo;

    /**
     * 支付通道
     */
    private String payCode;
    /**
     * 备注
     */
    private String remark;

    /**
     * 订单状态0:已接受, 1:处理中,2:处理成功,3:处理失败
     */
    private int orderStatus;

    /**
     * 平台流水号
     */
    private String queryId;

    
}
