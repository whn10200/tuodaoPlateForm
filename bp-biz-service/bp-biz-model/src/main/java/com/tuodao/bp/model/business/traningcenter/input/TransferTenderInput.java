package com.tuodao.bp.model.business.traningcenter.input;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.constant.traningcenter.CreditAssignmentConstant;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * 债权投资入参
 * @author qingli.chen
 * @date 2017/10/20
 * @description
 */
@Data
@ToString
public class TransferTenderInput extends BasePojo {

    /**
     * 债权id
     */
    @NotNull(message = CreditAssignmentConstant.TRANSFER_IS_NULL + "")
    private Integer transferId;

    /**
     * 支付密码
     */
    @NotNull(message = CreditAssignmentConstant.PAY_PWD_IS_NULL + "")
    private String payPassword;

    /**
     * 优惠券id
     */
    private long voucherId;

    /**
     * 加息券利息
     */
    private BigDecimal discountAvailable;

    /**
     * 投资金额
     */
    @NotNull(message = CreditAssignmentConstant.ACCOUNT_IS_NULL + "")
    @DecimalMin(message = CreditAssignmentConstant.TRANSFER_MONEY_ERROR + "", value = "100")
    private BigDecimal money;
}
