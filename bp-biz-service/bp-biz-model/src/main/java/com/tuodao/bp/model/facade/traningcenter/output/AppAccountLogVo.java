package com.tuodao.bp.model.facade.traningcenter.output;

import java.io.Serializable;

/**
 * @description: 手机端资金记录列表
 * @author: 王艳兵
 * @date: 2017/11/10
 * @time: 17:49
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class AppAccountLogVo implements Serializable {

    private static final long serialVersionUID = 450881129181009163L;

    /**
     * 投标标的
     */
    private String title;

    private String addTime;

    private String account;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
