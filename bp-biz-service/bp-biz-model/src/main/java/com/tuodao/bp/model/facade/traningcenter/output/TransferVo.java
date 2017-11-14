package com.tuodao.bp.model.facade.traningcenter.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 债权详情
 * @author qingli.chen
 * @date 2017/11/10
 * @description
 */
@Data
@ToString
public class TransferVo extends TransferQueryVo {
    private static final long serialVersionUID = -4972655326072327843L;

    /**
     * 还款方式
     */
    private String repaymentType;

    /**
     * 用户手机号
     */
    private String userPhone;

    /**
     * 转让结束时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    /**
     * 转让剩余时间
     */
    private String remainingTime;

    /**
     * 可用余额
     */
    private BigDecimal balance;

    /**
     * 是否存管清算时间
     */
    private boolean cleanTime = false;

    /**
     * 状态
     * 0转让中、1 已转让
     */
    private Integer status;

    /**
     * 剩余时间
     */
    private long timeLeft;
}
