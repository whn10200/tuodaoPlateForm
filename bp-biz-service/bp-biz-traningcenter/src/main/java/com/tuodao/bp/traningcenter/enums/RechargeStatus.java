package com.tuodao.bp.traningcenter.enums;

/**
 * @author qingli.chen
 * @date 2017/9/12
 * @description
 */
public enum RechargeStatus {
    //待处理
    WAIT(0),
    //成功
    SUCCESS(1),
    //失败
    FAILED(2);

    private int value;

    RechargeStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
