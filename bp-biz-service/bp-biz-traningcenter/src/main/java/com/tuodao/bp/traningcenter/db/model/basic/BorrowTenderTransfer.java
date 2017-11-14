package com.tuodao.bp.traningcenter.db.model.basic;

/**
 * @author qingli.chen
 * @date 2017/11/3
 * @description
 */
public class BorrowTenderTransfer extends BorrowTender {

    private Integer transferStatus;

    public Integer getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(Integer transferStatus) {
        this.transferStatus = transferStatus;
    }
}
