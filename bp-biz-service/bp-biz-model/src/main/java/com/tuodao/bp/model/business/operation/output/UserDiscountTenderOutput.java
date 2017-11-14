package com.tuodao.bp.model.business.operation.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.Joiner;
import com.tuodao.bp.utils.TimeUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 用于投资
 * author hechuan
 * <p>
 * created on 2017/11/13
 * <p>
 * since V1.0.0
 */
public class UserDiscountTenderOutput implements Serializable {

    private static final long serialVersionUID = 4999539075499656989L;

    /**
     * 优惠券ID
     */
    private Long id;

    /**
     * 标题
     */
    private String discountTitle;

    /**
     *类型(1:抵用券,2:加息券)
     */
    private Integer discountType;

    /**
     * 生效日期
     */
    @JsonFormat(pattern="yyyy/MM/dd")
    private Date effectDate;

    /**
     * 有效期(天)
     */
    private Integer effectDay;

    /**
     * 失效日期
     */
    @JsonFormat(pattern="yyyy/MM/dd")
    private Date invalidDate;

    /**
     * 来源(如：新手福利，存管福利)
     */
    private String source;

    /**
     * 生效-失效说明
     */
    private String dateExplain;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiscountTitle() {
        return discountTitle;
    }

    public void setDiscountTitle(String discountTitle) {
        this.discountTitle = discountTitle;
    }

    public Integer getDiscountType() {
        return discountType;
    }

    public void setDiscountType(Integer discountType) {
        this.discountType = discountType;
    }

    public Date getEffectDate() {
        return effectDate;
    }

    public void setEffectDate(Date effectDate) {
        this.effectDate = effectDate;
    }

    public Integer getEffectDay() {
        return effectDay;
    }

    public void setEffectDay(Integer effectDay) {
        this.effectDay = effectDay;
    }

    public Date getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDateExplain() {
        // 开始日期
        String beginDate = TimeUtils.format(this.effectDate, "yyyy/MM/dd");

        // 结束日期
        String endDate = TimeUtils.format(this.invalidDate, "yyyy/MM/dd");

        dateExplain = Joiner.on("-").join(beginDate, endDate);
        return dateExplain;
    }

    public void setDateExplain(String dateExplain) {
        this.dateExplain = dateExplain;
    }
}
