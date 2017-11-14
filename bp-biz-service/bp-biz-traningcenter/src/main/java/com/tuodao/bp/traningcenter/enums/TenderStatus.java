package com.tuodao.bp.traningcenter.enums;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * @author qingli.chen
 * @date 2017/9/20
 * @description
 */
public enum TenderStatus {
    //投标中
    BIDING(0, "募集中"),
    //投标成功
    BID_SUCCESS(1, "募集中"),
    //还款中
    BACK_MONEY(2, "回款中"),
    //还款完成
    BACK_MONEY_OVER(5, "已还完"),
    //投标失败
    TENDER_FAILED(3, "投标失败"),
    //转让成功
    TRANSFER_SUCCESS(7, "转让成功");

    private int value;
    private String msg;
    TenderStatus(int value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public static String getMsg(int value) {
        Optional<TenderStatus> tenderStatus =
                Arrays.stream(TenderStatus.values())
                        .filter(v -> Objects.equals(v.value, value))
                        .findFirst();
        return tenderStatus.get().getMsg();
    }

    public int getValue() {
        return value;
    }
}
