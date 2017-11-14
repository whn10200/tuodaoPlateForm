package com.tuodao.bp.model.facade.traningcenter.input;

import com.tuodao.bp.model.PagePojo;
import com.tuodao.bp.model.constant.traningcenter.CreditAssignmentConstant;

import javax.validation.constraints.NotNull;

/**
 * @author qingli.chen
 * @date 2017/9/21
 * @description
 */
public class TransferQueryInfoParam extends PagePojo {

    private static final long serialVersionUID = 7325155666144081723L;

    /**
     * 债权id
     */
    @NotNull(message = CreditAssignmentConstant.TRANSFER_IS_NULL + "")
    private Integer transferId;

    public Integer getTransferId() {
        return transferId;
    }

    public void setTransferId(Integer transferId) {
        this.transferId = transferId;
    }
}
