package com.tuodao.bp.traningcenter.db.model.basic;

/**
 * @author qingli.chen
 * @date 2017/9/18
 * @description 受让
 */
public class TransfereeBean extends BorrowTender {
    private static final long serialVersionUID = 464742136435814049L;


    private Integer productStatus;

    public Integer getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }
}
