package com.tuodao.bp.model.facade.traningcenter.input;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.constant.traningcenter.RechargeConstant;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 快捷充值
 * @author qingli.chen
 * @date 2017/10/16
 * @description
 */
public class FastRechargeParam extends BasePojo {


    private static final long serialVersionUID = -5698539511681243311L;
    /**
     * 订单号
     */
    @NotNull()
    private String orderNo;

    /**
     * 短信验证码
     */
    @NotNull(message = RechargeConstant.SMS_CODE_IS_NULL + "")
    private String smsCode;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }
}
