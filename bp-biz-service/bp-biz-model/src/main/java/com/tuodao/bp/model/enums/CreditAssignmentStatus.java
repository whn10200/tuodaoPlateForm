package com.tuodao.bp.model.enums;

/**
 * 债权转让状态
 * @author qingli.chen
 * @date 2017/10/20
 * @description
 */
public enum CreditAssignmentStatus {
    //可转让
    TRANSFERABLE(0),
    //转让中
    TRANSFER(1),
    //已转让
    TRANSFERRED(2),
    //已受让
    COMPROMISED(3);

    private int value;

    CreditAssignmentStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
