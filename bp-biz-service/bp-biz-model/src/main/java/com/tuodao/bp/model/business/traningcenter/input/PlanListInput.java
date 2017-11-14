package com.tuodao.bp.model.business.traningcenter.input;

import com.tuodao.bp.model.annotation.In;
import com.tuodao.bp.model.business.product.output.ProductOutput;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/10/30 0030.
 */
public class PlanListInput extends PlanNomalInput implements Serializable{

    private static final long serialVersionUID = 1215554586642347023L;
    private String key;

    //未还款期数
    private Integer period;

    //上期还款日期
    private Date lastRepayTime;

    //总期数
    private Integer periods;

    //0表示普通 1表示转让
    private Integer type;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Date getLastRepayTime() {
        return lastRepayTime;
    }

    public void setLastRepayTime(Date lastRepayTime) {
        this.lastRepayTime = lastRepayTime;
    }

    public Integer getPeriods() {
        return periods;
    }

    public void setPeriods(Integer periods) {
        this.periods = periods;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
