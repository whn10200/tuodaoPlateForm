package com.tuodao.bp.model.facade.traningcenter.input;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.constant.traningcenter.CreditAssignmentConstant;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * @author qingli.chen
 * @date 2017/9/21
 * @description 债转申请 入参
 */
public class SaveTransferParam extends BasePojo {
    private static final long serialVersionUID = -8826884396393108867L;

    /**
     * 投标id
     */
    @NotNull(message = CreditAssignmentConstant.TENDER_IS_NULL + "")
    private Integer tenderId;

    /**
     * 支付密码
     */
    @NotEmpty(message = CreditAssignmentConstant.PAY_PWD_IS_NULL + "")
    private String payPassword;

    public Integer getTenderId() {
        return tenderId;
    }

    public void setTenderId(Integer tenderId) {
        this.tenderId = tenderId;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }
}
