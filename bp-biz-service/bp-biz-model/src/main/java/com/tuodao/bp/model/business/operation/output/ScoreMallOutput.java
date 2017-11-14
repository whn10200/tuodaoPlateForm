package com.tuodao.bp.model.business.operation.output;

import java.io.Serializable;

/**
 * 积分商城-积分兑换output
 *
 * author hechuan
 * <p>
 * created on 2017/9/25
 * <p>
 * since V1.0.0
 */
public class ScoreMallOutput implements Serializable {

    private static final long serialVersionUID = 4785866938614496859L;

    /**
     * 积分商城ID,主键，唯一标识
     */
    private Long id;

    /**
     * 类型(1:加息券,2:免费提现次数)
     */
    private Integer type;

    /**
     * 类型名称
     */
    private String typeName;

    /**
     * 所需积分
     */
    private Integer needScore;

    /**
     * 对应值(加息券是百分比的数值,提现数量是次数)-如：3
     */
    private Integer numValue;

    /**
     * 对应值显示- 如：3%
     */
    private String numValueView;

    /**
     * 有效期天数（加息券才有，提现次数没有）
     */
    private Integer effectDay;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getNeedScore() {
        return needScore;
    }

    public void setNeedScore(Integer needScore) {
        this.needScore = needScore;
    }

    public Integer getNumValue() {
        return numValue;
    }

    public void setNumValue(Integer numValue) {
        this.numValue = numValue;
    }

    public String getNumValueView() {
        return numValueView;
    }

    public void setNumValueView(String numValueView) {
        this.numValueView = numValueView;
    }

    public Integer getEffectDay() {
        return effectDay;
    }

    public void setEffectDay(Integer effectDay) {
        this.effectDay = effectDay;
    }
}
