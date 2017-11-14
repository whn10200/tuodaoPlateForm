package com.tuodao.bp.model.facade.traningcenter.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 受让列表
 * @author qingli.chen
 * @date 2017/11/10
 * @description
 */
@Data
@ToString
public class TransferCompromisedVo implements Serializable {
    private static final long serialVersionUID = 2490591780941177382L;

    /**
     * 债转id
     */
    private Integer id;
    /**
     * 标题
     */
    private String title;

    /**
     * 标的编码
     */
    private String productCode;
    /**
     * 金额
     */
    private BigDecimal amount;
    /**
     * 状态 1募集中 2还款中 3已还款
     */
    private Integer status;
    /**
     * 利息
     */
    private BigDecimal interest = BigDecimal.ZERO;
    /**
     * 投资时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date addTime;
    /**
     * 结束时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
}
