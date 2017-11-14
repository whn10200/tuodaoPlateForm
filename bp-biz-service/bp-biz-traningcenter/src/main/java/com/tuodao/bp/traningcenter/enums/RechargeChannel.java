package com.tuodao.bp.traningcenter.enums;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * @author qingli.chen
 * @date 2017/9/12
 * @description 充值渠道
 */
public enum RechargeChannel {
    //富有
    FY(0, "fuyou"),
    //京东
    JD(1, "jd");

    private int value;

    private String code;

    RechargeChannel(int value, String code) {
        this.value = value;
        this.code = code;
    }

    public int getValue() {
        return value;
    }

    public static RechargeChannel getCode(String code) {
        Optional<RechargeChannel> rechargeChannel =
                Arrays.stream(RechargeChannel.values())
                        .filter(v -> Objects.equals(v.code, code))
                        .findFirst();
        return rechargeChannel.get();
    }

    public static RechargeChannel getCode(int value) {
        Optional<RechargeChannel> rechargeChannel =
                Arrays.stream(RechargeChannel.values())
                        .filter(v -> Objects.equals(v.value, value))
                        .findFirst();
        return rechargeChannel.get();
    }

    public String getCode() {
        return code;
    }



    public static void main(String[] args) {
        System.out.println(RechargeChannel.FY.getCode());
    }
}
