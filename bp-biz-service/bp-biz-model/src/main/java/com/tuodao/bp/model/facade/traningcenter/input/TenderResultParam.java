package com.tuodao.bp.model.facade.traningcenter.input;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.constant.traningcenter.TraningCenterExceptionConstant;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/11/6
 * @time: 15:52
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class TenderResultParam extends BasePojo implements Serializable {

    private static final long serialVersionUID = 145853587210140561L;

    /**
     * 投标结果查询 唯一key
     */
    @NotEmpty(message = TraningCenterExceptionConstant.JUST_ID_IS_NULL + "")
    private String tenderKey;

    /**
     * 查询结果次数 默认3次为最大上限
     */
    private Integer num = 1;

    public String getTenderKey() {
        return tenderKey;
    }

    public void setTenderKey(String tenderKey) {
        this.tenderKey = tenderKey;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
