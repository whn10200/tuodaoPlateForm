package com.tuodao.bp.model.business.traningcenter.output;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: wuzf
 * @date: 2017/9/18 0018.
 * @time: 15:43
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class TenderTraRecordOutput implements Serializable {
    private static final long serialVersionUID = 465848377387566750L;

    //捡漏用户
    private String picker;

    //投资最多用户
    private String maxEr;

    //投资列表
//    private  List<TenderRecord> tenderRecordList;


    public String getPicker() {
        return picker;
    }

    public void setPicker(String picker) {
        this.picker = picker;
    }

    public String getMaxEr() {
        return maxEr;
    }

    public void setMaxEr(String maxEr) {
        this.maxEr = maxEr;
    }

//    public List<TenderRecord> getTenderRecordList() {
//        return tenderRecordList;
//    }
//
//    public void setTenderRecordList(List<TenderRecord> tenderRecordList) {
//        this.tenderRecordList = tenderRecordList;
//    }
}
