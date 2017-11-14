package com.tuodao.bp.model.facade.traningcenter.input;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.constant.traningcenter.RechargeConstant;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 网银充值 请求参数
 * @author qingli.chen
 * @date 2017/10/16
 * @description
 */
public class OnlineBankParam extends BasePojo {

    private static final long serialVersionUID = 8029883061858606306L;
    /**
     * 充值金额
     */
    @NotNull(message = RechargeConstant.MONEY_IS_NULL + "")
    private BigDecimal money;

    /**
     * 银行id
     */
    @NotNull(message = RechargeConstant.BANK_ID_IS_NULL + "")
    private String bankId;

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

}
