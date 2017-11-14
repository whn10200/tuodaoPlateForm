package com.tuodao.bp.model.business.traningcenter.output;

import java.io.Serializable;

/**
 * @description:查询理财计划加入记录
 * @author: wuzf
 * @date: 2017/9/18 0018.
 * @time: 16:19
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class TenderRecord implements Serializable{
    private static final long serialVersionUID = 9068100134339322712L;
    //投资时间
    private  String tenderTime;
    //投资人
    private String tenderUser;
    //投资金额
    private String tenderAccount;


    public String getTenderTime() {
        return tenderTime;
    }

    public void setTenderTime(String tenderTime) {
        this.tenderTime = tenderTime;
    }

    public String getTenderUser() {
        return tenderUser;
    }

    public void setTenderUser(String tenderUser) {
        this.tenderUser = tenderUser;
    }

    public String getTenderAccount() {
        return tenderAccount;
    }

    public void setTenderAccount(String tenderAccount) {
        this.tenderAccount = tenderAccount;
    }

}
