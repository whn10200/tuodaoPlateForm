package com.tuodao.bp.model.business.traningcenter.output;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author qingli.chen
 * @date 2017/9/20
 * @description 债权转让
 */
@Data
@ToString
public class TenderTransferOutput implements Serializable {

    private static final long serialVersionUID = 8191805560710209417L;


    private Integer id;

    /**
     * 原标的id
     */
    private Integer preBorrowId;

    private String borrowName;

    /**
     * 状态 0转让中 1转让成功 2流标
     */
    private Integer status;

    private String userId;

    private BigDecimal account;

    private BigDecimal accountYes;

    /**
     * 转让结束时间
     */
    private Date endTime;
}
