package com.tuodao.bp.model.business.traningcenter.input;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.constant.traningcenter.RechargeConstant;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 快捷充值 发送短信 入参
 * @author qingli.chen
 * @date 2017/9/13
 * @description
 */
@Data
public class RechargeFastCodeInput extends BasePojo {


    private static final long serialVersionUID = -9161450019720887941L;
    /**
     * 充值金额
     */
    @NotNull(message = RechargeConstant.MONEY_IS_NULL + "")
    private BigDecimal money;

    /**
     * 银行id
     */
    private String bankCode;
    /**
     * 银行卡号
     */
    private String bankNum;

    /**
     * 姓名
     */
    private String realName;
    /**
     * 证件号
     */
    private String cardId;
}
