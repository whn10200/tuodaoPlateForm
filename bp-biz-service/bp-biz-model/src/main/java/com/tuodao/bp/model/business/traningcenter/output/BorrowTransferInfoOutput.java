package com.tuodao.bp.model.business.traningcenter.output;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author qingli.chen
 * @date 2017/9/21
 * @description 债转投资 详情页
 */
@Data
@ToString
public class BorrowTransferInfoOutput extends BorrowTransferQueryOutput implements Serializable {
    private static final long serialVersionUID = 4122648477246919825L;

    /**
     * 转让结束时间
     */
    private Date endTime;

    /**
     * 可用余额
     */
    private BigDecimal balance;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 手续费
     */
    private BigDecimal fee;

}
