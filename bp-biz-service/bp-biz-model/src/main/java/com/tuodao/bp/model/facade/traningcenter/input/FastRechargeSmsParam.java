package com.tuodao.bp.model.facade.traningcenter.input;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.constant.traningcenter.CreditAssignmentConstant;
import com.tuodao.bp.model.constant.traningcenter.RechargeConstant;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 快捷充值 发送短信参数
 * @author qingli.chen
 * @date 2017/10/16
 * @description
 */
public class FastRechargeSmsParam extends BasePojo {
    private static final long serialVersionUID = 9025093853477011801L;


    /**
     * 充值金额
     */
    @NotNull(message = RechargeConstant.MONEY_IS_NULL + "")
    @DecimalMin(message = CreditAssignmentConstant.TRANSFER_MONEY_ERROR + "", value = "100")
    private BigDecimal money;

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
