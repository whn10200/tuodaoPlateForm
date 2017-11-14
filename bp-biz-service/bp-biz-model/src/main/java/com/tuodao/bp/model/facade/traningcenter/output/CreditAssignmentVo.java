package com.tuodao.bp.model.facade.traningcenter.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author qingli.chen
 * @date 2017/9/20
 * @description
 */
@Data
public class CreditAssignmentVo implements Serializable {

    private static final long serialVersionUID = 3058143063264219184L;
    //产品名称
    private String productTitle;

    //期数
    private Integer productPeriod;

    //剩余期数
    private Integer remaining;

    //投标id
    private Integer tenderId;

    //产品编号
    private String productCode;

    //年化率
    private BigDecimal borrowApr;

    //可转让金额
    private BigDecimal recoverCapital;

    //手续费
    private BigDecimal fee;

    //状态
    private Integer status;

    private String statusMsg;

    //对应债权价值
    private BigDecimal claimsValue;

    //折价
    private BigDecimal discount = BigDecimal.ZERO;

    //转让时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date addTime;

    //利息
    private BigDecimal interest;

    //奖励
    private BigDecimal reward;

    /**
     * 到期时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

}
