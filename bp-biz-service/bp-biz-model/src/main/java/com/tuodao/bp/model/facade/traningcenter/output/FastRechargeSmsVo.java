package com.tuodao.bp.model.facade.traningcenter.output;

import java.io.Serializable;

/**
 * 快捷充值 发送短信 返回对象
 * @author qingli.chen
 * @date 2017/10/16
 * @description
 */
public class FastRechargeSmsVo implements Serializable {
    private static final long serialVersionUID = -8020629557401427829L;

    private String orderNo;

    private String phone;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
