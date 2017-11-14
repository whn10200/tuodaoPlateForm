package com.tuodao.bp.model.facade.traningcenter.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author qingli.chen
 * @date 2017/9/18
 * @description 受让详情 app
 */
@Data
public class TransfereeAppVo implements Serializable {

    private static final long serialVersionUID = -3187390402728123022L;
    /**
     * 产品标题
     */
    private String title;
    /**
     * 利息
     */
    private BigDecimal interest;

    /**
     * 奖励
     */
    private BigDecimal reward;

    /**
     * 年利率
     */
    private String borrowApr;

    /**
     * 投资金额
     */
    private BigDecimal amount;

    /**
     * 到期时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date repayLastTime;

    /**
     * 等额本息 按月付息
     */
    private String styleType;

    /**
     * 加息券
     */
    private String voucher;
    /**
     * 状态
     * 1募集中 2回款中 3已回款
     */
    private Integer status;
    /**
     * 转让协议
     */
    private String protocol;
    /**
     * 投资时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date tenderTime;

    private List<BackMoneyPlanAppVo> backPlan;
}
