package com.tuodao.bp.model.facade.traningcenter.input;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.constant.traningcenter.CreditAssignmentConstant;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 *
 * @author tookbra
 * @date 2017/11/3
 * @description
 */
@Data
public class TransferApplyParam extends BasePojo {
    private static final long serialVersionUID = 4908909497452038346L;

    /**
     * 投资id
     */
    @NotNull(message = CreditAssignmentConstant.TENDER_IS_NULL + "")
    private Integer tenderId;
}
