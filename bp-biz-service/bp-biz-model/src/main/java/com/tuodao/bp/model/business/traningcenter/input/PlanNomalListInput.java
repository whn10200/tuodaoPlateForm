package com.tuodao.bp.model.business.traningcenter.input;

import com.tuodao.bp.model.annotation.In;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: wuzf
 * @date: 2017/9/18 0018.
 * @time: 10:54
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class PlanNomalListInput implements Serializable{
    private static final long serialVersionUID = 8438187068546767748L;

    private  List<PlanNomalInput> inputs;

    private String key;

    private Integer borrowPeriod;

    public Integer getBorrowPeriod() {
        return borrowPeriod;
    }

    public void setBorrowPeriod(Integer borrowPeriod) {
        this.borrowPeriod = borrowPeriod;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<PlanNomalInput> getInputs() {
        return inputs;
    }

    public void setInputs(List<PlanNomalInput> inputs) {
        this.inputs = inputs;
    }
}
