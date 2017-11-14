package com.tuodao.bp.model.enums;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * @author qingli.chen
 * @date 2017/9/12
 * @description
 */
public enum  ChannelType {
    PC(0, "PC"),
    APP(1, "APP"),
    BACKSTAGE(4, "");

    private int value;
    private String desc;

    ChannelType(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public static ChannelType getValue(String desc) {
        Optional<ChannelType> rechargeChannel = Arrays.stream(ChannelType.values())
                        .filter(v -> Objects.equals(v.desc, desc))
                        .findFirst();
        return rechargeChannel.orElse(ChannelType.PC);
    }
}
