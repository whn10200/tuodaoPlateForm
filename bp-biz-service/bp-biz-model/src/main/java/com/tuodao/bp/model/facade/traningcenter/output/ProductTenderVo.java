package com.tuodao.bp.model.facade.traningcenter.output;

import java.io.Serializable;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/11/8
 * @time: 11:58
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class ProductTenderVo implements Serializable{

    private static final long serialVersionUID = 7798032854797490536L;
    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 投标金额
     */
    private String money;

    /**
     *
     */
    private String addTime;

    /**
     * 投资方式 0:普通方式投标 1:app投标 2 自动投标
     */
    private int type = 0;

    /**
     * 是否为首投
     */
    private boolean isFirst = false;

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ProductTenderVo{" +
                "mobile='" + mobile + '\'' +
                ", money='" + money + '\'' +
                ", addTime='" + addTime + '\'' +
                ", type=" + type +
                ", isFirst=" + isFirst +
                '}';
    }
}
