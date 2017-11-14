package com.tuodao.bp.traningcenter.enums.depository;

/**
 * 快捷充值 状态
 * @author qingli.chen
 * @date 2017/10/19
 * @description
 */
public enum  FastRechargeStatus {
    //接受成功
    APPLY(0),
    //处理中
    PROCESS(1),
    //成功
    SUCCESS(2),
    //失败
    FAILED(3);

    private int value;

    FastRechargeStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
