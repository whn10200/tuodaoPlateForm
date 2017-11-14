package com.tuodao.bp.model.business.useraccount.output;

import com.tuodao.bp.model.business.useraccount.UserAccountInfo;

import java.io.Serializable;

/**
 * @description: 聚合层用户帐号信息
 * @author: mif
 * @date: 2017/10/27
 * @time: 11:02
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class UserAccountInfoOutput extends UserAccountInfo implements Serializable {
    private static final long serialVersionUID = 206943377861040224L;

    /**
     * VIP等级
     */
    private Integer vipLevel;

    public Integer getVipLevel() {
        return vipLevel;
    }

    public void setVipLevel(Integer vipLevel) {
        this.vipLevel = vipLevel;
    }
}
