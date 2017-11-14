package com.tuodao.bp.model.facade.traningcenter.output;

import java.io.Serializable;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/11/10
 * @time: 15:02
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class AutoTenderAppVo implements Serializable{
    /**
     * 已开启自动投标的总人数
     */
    private long total;

    /**
     * 是否已开通存管
     */
    private boolean isOpen;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
