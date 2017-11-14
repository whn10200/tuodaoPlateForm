package com.tuodao.bp.traningcenter.enums;

/**
 * 债转状态
 * @author qingli.chen
 * @date 2017/10/20
 * @description
 */
public enum TransferStatus {
    //转让中
    TRANSFER(0),
    //转让成功
    TRANSFER_SUCCESS(1),
    //流标
    FLOW_MARK(2);

    private int value;

    TransferStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
