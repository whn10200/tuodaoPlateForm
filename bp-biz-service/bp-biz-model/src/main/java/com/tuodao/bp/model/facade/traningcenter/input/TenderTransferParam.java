package com.tuodao.bp.model.facade.traningcenter.input;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.constant.traningcenter.CreditAssignmentConstant;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author qingli.chen
 * @date 2017/9/22
 * @description 投资债转
 */
public class TenderTransferParam extends BasePojo {

    private static final long serialVersionUID = -3524925162695028256L;


    /**
     * 债转id
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
     * 投资金额
     */
    @NotNull(message = CreditAssignmentConstant.ACCOUNT_IS_NULL + "")
    @DecimalMin(message = CreditAssignmentConstant.TENDER_TRANSFER_MONEY_ERROR + "", value = "100")
    private BigDecimal money;


    public Integer getTransferId() {
        return transferId;
    }

    public void setTransferId(Integer transferId) {
        this.transferId = transferId;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public long getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(long voucherId) {
        this.voucherId = voucherId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
