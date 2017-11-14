package com.tuodao.bp.model.business.traningcenter.input;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.constant.traningcenter.RechargeConstant;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author qingli.chen
 * @date 2017/9/13
 * @description 充值入参
 */
@Data
public class RechargeGatewayInput extends BasePojo {
    private static final long serialVersionUID = 3985841671592745590L;

    private String orderNo;

    /**
     * 充值金额
     */
    @NotNull(message = RechargeConstant.MONEY_IS_NULL + "")
    private BigDecimal money;

    /**
     * 银行id
     */
    @NotNull(message = RechargeConstant.MONEY_IS_NULL + "")
    private String bankId;

    /**
     * 银行卡号
     */
    private String cardNo;

    private String remark;

    private String type;

    
    
}
