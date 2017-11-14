package com.tuodao.bp.traningcenter.enums;

/**
 * 投标类型
 * @author qingli.chen
 * @date 2017/10/20
 * @description
 */
public enum  TenderType {
    //普通投标
    ORDINARG_TENDER(0),
    //债权投标
    TRANSFER_TENDER(1),
    //精选计划普通
    SELECTION_PLAN_ORDINARY(2),
    //精选计划转让
    SELECTION_PLAN_TRANSFER(3);

    private int value;

    TenderType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
