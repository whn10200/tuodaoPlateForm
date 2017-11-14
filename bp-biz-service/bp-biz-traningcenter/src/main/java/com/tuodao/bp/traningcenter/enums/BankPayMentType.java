package com.tuodao.bp.traningcenter.enums;

/**
 * 充值 银行类型
 * @author qingli.chen
 * @date 2017/10/18
 * @description
 */
public enum BankPayMentType {
    //网银
    ONLINE_BANK(0),
    //快捷充值
    FAST_BANK(1);

    private int value;

    BankPayMentType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
