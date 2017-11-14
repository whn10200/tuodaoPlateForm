package com.tuodao.bp.model.traningcenter.input;

import com.tuodao.bp.model.BasePojo;

import java.math.BigDecimal;

/**
 * @author qingli.chen
 * @date 2017/9/25
 * @description 投资债权
 */
public class TenderCreditAssignmentInput extends BasePojo {
    private static final long serialVersionUID = -4545766263940709623L;

    private BigDecimal money;

    private String payPassword;

    private Integer voucherId;

    private Integer transferId;

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public Integer getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(Integer voucherId) {
        this.voucherId = voucherId;
    }

    public Integer getTransferId() {
        return transferId;
    }

    public void setTransferId(Integer transferId) {
        this.transferId = transferId;
    }
}
