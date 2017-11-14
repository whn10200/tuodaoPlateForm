package com.tuodao.bp.model.constant.traningcenter;

/**
 * @description: 平台账户类型
 * @author: 王艳兵
 * @date: 2017/9/20
 * @time: 17:04
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public enum  PlatformAccountType {

    ZYZZH(1, "自有子账户"),
    JLJZZH(2, "奖励金子账号"),
    XJDFZZH(3, "现金垫付子账户"),
    ZTDFZZH(4, "在途垫付子账户"),
    PTZJQSZH(5, "平台资金清算账户"),
    PTZYZJZH(6, "平台自有资金账户"),
    PTBZJZH(7, "平台保证金账户"),
    SSFZZH(9, "手续费子账户"),
    TYJZZH(10, "体验金子账户"),
    DYJZZH(11, "抵用金子账户"),
    JXJZZH(12, "加息金子账户"),
    FXZBJZH(13, "风险准备金账户");

    private int id;

    private String name;

    PlatformAccountType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
