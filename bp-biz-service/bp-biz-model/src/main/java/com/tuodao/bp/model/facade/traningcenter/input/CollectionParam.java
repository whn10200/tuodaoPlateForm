package com.tuodao.bp.model.facade.traningcenter.input;

import com.tuodao.bp.model.PagePojo;
import com.tuodao.bp.model.constant.traningcenter.TraningCenterExceptionConstant;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/9/30
 * @time: 14:21
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class CollectionParam extends PagePojo implements Serializable{

    /**
     * 查询回款的日期 yyyy-mm-dd 或 yyyy-mm
     */
    @NotNull(message = TraningCenterExceptionConstant.TENDER_CALENDAR_IS_NULL + "")
    private String day;

    /**
     * 查询类型 0:按月查询 1:按天查询默认 月查询 即如果按月 上述只会以yyyy-mm为条件进行查询 否则以yyyy-mm-dd查询
     */
    private int type = 0;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "CollectionParam{" +
                "day='" + day + '\'' +
                ", type=" + type +
                '}';
    }
}
