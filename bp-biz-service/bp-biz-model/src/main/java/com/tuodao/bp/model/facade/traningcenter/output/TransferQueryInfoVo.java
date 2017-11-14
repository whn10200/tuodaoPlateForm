package com.tuodao.bp.model.facade.traningcenter.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author qingli.chen
 * @date 2017/9/21
 * @description 债权转让详情
 */
@Data
@ToString
public class TransferQueryInfoVo implements Serializable {
    private static final long serialVersionUID = 8671724172764360397L;

    /**
     * 原始标的id
     */
    private Integer id;
    /**
     * 标的名称
     */
    private String title;
    /**
     * 标的编号
     */
    private String productCode;
    /**
     * 转让金额
     */
    private BigDecimal account;
    /**
     * 对应债权价值
     */
    private BigDecimal claimsValue;

    /**
     * 折价、溢价
     */
    private BigDecimal discount = BigDecimal.ZERO;

    /**
     * 手续费
     */
    private BigDecimal fee;

    /**
     * 年化率
     */
    private String apr;

    /**
     * 转让时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date addTime;

    /**
     * 到期时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    /**
     * 还款方式
     */
    private String repaymentType;

    /**
     * 状态
     * 0转让中、1 已转让
     */
    private Integer status;


}
