package com.tuodao.bp.model.facade.traningcenter.input;

import com.tuodao.bp.model.PagePojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qingli.chen
 * @date 2017/9/21
 * @description 债转查询
 */
public class TransferQueryParam extends PagePojo {
    private static final long serialVersionUID = 8180639003838616043L;

    /**
     * 1 3个月以内
     * 2 3-6个月
     * 3 7-12个月
     * 4 12个月以上
     */
    private Integer type;

    private Integer beginPeriod = 0;

    private Integer endPeriod = 0;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public Integer getBeginPeriod() {
        if(type == 1) {
            return 0;
        } else if(type == 2) {
            return 3;
        } else if(type == 3) {
            return 7;
        } else if(type == 4) {
            return 12;
        } else {
            return 0;
        }
    }

    public Integer getEndPeriod() {
        if(type == 1) {
            return 3;
        } else if(type == 2) {
            return 6;
        } else if(type == 3) {
            return 12;
        } else if(type == 4) {
            return 0;
        } else {
            return 0;
        }
    }
}
