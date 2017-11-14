package com.tuodao.bp.traningcenter.enums;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * @author qingli.chen
 * @date 2017/9/25
 * @description 回款状态
 */
public enum CollectionStatus {
    //未回款
    WAIT(0, "回款中"),
    //已回款
    SUCCESS(1, "已回款"),
    //已回款
    ADVANCE_SUCCESS(2, "提前回款"),
    //已转让
    TRANSFERED(7, "已转让");

    private int value;
    private String msg;

    CollectionStatus(int value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public int getValue() {
        return value;
    }

    public static CollectionStatus getValue(int value) {
        Optional<CollectionStatus> collectionStatus =
                Arrays.stream(CollectionStatus.values())
                        .filter(v -> Objects.equals(v.value, value))
                        .findFirst();
        return collectionStatus.get();
    }

    public String getMsg() {
        return msg;
    }
}
