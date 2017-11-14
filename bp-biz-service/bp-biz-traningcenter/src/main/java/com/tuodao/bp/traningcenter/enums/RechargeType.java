package com.tuodao.bp.traningcenter.enums;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * @author qingli.chen
 * @date 2017/9/12
 * @description 充值方式
 */
public enum RechargeType {
    //网关
    GATEWAY(0, "网关充值"),
    //快捷
    FAST(1, "快捷充值");

    private int value;

    private String description;

    RechargeType(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public static RechargeType getValue(int value) {
        Optional<RechargeType> rechargeType =
                Arrays.stream(RechargeType.values())
                        .filter(v -> Objects.equals(v.value, value))
                        .findFirst();
        return rechargeType.get();
    }

    public String getDescription() {
        return description;
    }

}
