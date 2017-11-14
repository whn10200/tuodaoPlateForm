package com.tuodao.bp.traningcenter.enums;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * @author qingli.chen
 * @date 2017/9/21
 * @description 还款方式枚举
 */
public enum TransferRepayType {

    //等额本息
    MTROAI(0, "等额本息"),
    //按月付息到期还本
    MONTHLY_INTEREST_ON_REPAYMENT(1, "按月付息到期还本");

    private int value;
    private String msg;

    TransferRepayType(int value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public int getValue() {
        return value;
    }

    public static String getMsg(int value) {
        Optional<TransferRepayType> transferRepayType =
                Arrays.stream(TransferRepayType.values())
                        .filter(v -> Objects.equals(v.value, value))
                        .findFirst();
        return transferRepayType.get().msg;
    }
}
