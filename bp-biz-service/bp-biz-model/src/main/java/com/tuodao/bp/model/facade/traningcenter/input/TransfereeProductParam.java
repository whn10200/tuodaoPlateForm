package com.tuodao.bp.model.facade.traningcenter.input;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.constant.traningcenter.CreditAssignmentConstant;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 我的受让详情-产品
 * @author qingli.chen
 * @date 2017/10/24
 * @description
 */
@Data
public class TransfereeProductParam extends BasePojo {

    @NotNull(message = CreditAssignmentConstant.TENDER_IS_NULL + "")
    private Integer tenderId;
}
