package com.tuodao.bp.model.facade.traningcenter.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 投资详情
 * @author qingli.chen
 * @date 2017/11/10
 * @description
 */
@Data
@ToString
public class TransferableInfoVo implements Serializable {

    /**
     * 投资id
     */
    private Integer tenderId;
    /**
     * 产品标题
     */
    private String title;
    /**
     * 产品编号
     */
    private String productCode;
    /**
     * 投资金额
     */
    private BigDecimal account;
    /**
     * 优惠券
     */
    private String voucher;
    /**
     * 年利率
     */
    private String borrowApr;
    /**
     * 奖励
     */
    private BigDecimal reward;
    /**
     * 利息
     */
    private BigDecimal interest;
    /**
     * 投资时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date addTime;
    /**
     * 到期时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
    /**
     * 等额本息 按月付息
     */
    private String styleType;
    /**
     * 借款协议
     */
    private String protocol;
    /**
     * 安存
     */
    private String ancun;
    /**
     * 状态
     */
    private Integer status = 0;

    /**
     * 回款计划
     */
    private List<BackMoneyPlanAppVo> backPlan;
}
