package com.tuodao.bp.model.business.traningcenter.input;

import com.tuodao.bp.model.PagePojo;

/**
 * @author qingli.chen
 * @date 2017/9/22
 * @description 投标记录查询
 */
public class TenderQueryInput extends PagePojo {
    private static final long serialVersionUID = -3050450787496807214L;

    //标的id
    private Integer borrowId;

    //投标类型 0普通1债权
    private Integer tenderType;

    public Integer getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }

    public Integer getTenderType() {
        return tenderType;
    }

    public void setTenderType(Integer tenderType) {
        this.tenderType = tenderType;
    }
}
