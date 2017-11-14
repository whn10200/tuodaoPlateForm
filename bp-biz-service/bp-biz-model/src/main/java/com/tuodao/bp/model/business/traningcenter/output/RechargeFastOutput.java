package com.tuodao.bp.model.business.traningcenter.output;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.constant.traningcenter.RechargeConstant;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 快捷充值 发送短信 出参
 * @author qingli.chen
 * @date 2017/9/13
 * @description
 */
@Data
public class RechargeFastOutput extends BasePojo {

    private static final long serialVersionUID = -3014474173798977306L;

    private String orderNo;

    private String phone;
}
