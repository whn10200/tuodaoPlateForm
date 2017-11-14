package com.tuodao.bp.model.business.operation.output;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

/**
 * 抽奖查询output
 * author hechuan
 * <p>
 * created on 2017/9/28
 * <p>
 * since V1.0.0
 */
public class InverstmentQueryOutput implements Serializable {

    private static final long serialVersionUID = 7583239912778094852L;

    /**
     * 主键ID,积分抽奖规则ID
     */
    private Long id;

    /**
     * 抽奖种类（1：10积分，2：100积分）
     */
    private Integer drawType;

    /**
     * 奖品名称
     */
    private String prizeName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDrawType() {
        return drawType;
    }

    public void setDrawType(Integer drawType) {
        this.drawType = drawType;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id",id)
                .add("drawType",drawType)
                .add("prizeName",prizeName)
                .toString();
    }
}
