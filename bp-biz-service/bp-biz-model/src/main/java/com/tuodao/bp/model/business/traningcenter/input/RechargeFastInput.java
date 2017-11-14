package com.tuodao.bp.model.business.traningcenter.input;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.constant.traningcenter.RechargeConstant;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * 快捷充值 入参
 * @author qingli.chen
 * @date 2017/9/13
 * @description
 */
@Data
public class RechargeFastInput extends BasePojo {


    /**
     * 订单号
     */
    @NotNull(message = RechargeConstant.COMMIT_ORDER + "")
    private String orderNo;

    /**
     * 短信验证码
     */
    @NotNull(message = RechargeConstant.SMS_CODE_IS_NULL + "")
    @Size(min = 4, message = RechargeConstant.SMS_CODE_IS_ERROR + "")
    private String smsCode;

}
