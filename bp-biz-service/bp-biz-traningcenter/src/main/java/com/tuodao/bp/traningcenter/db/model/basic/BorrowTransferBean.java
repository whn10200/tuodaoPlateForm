package com.tuodao.bp.traningcenter.db.model.basic;


/**
 * @author qingli.chen
 * @date 2017/9/15
 * @description
 */
public class BorrowTransferBean extends BorrowTransfer {

    private static final long serialVersionUID = 796659223549482659L;

    /**
     * 产品id
     */
    private String borrowId;


    public String getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(String borrowId) {
        this.borrowId = borrowId;
    }
}
