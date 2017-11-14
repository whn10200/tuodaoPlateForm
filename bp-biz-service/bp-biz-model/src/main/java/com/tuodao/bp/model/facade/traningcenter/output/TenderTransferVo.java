package com.tuodao.bp.model.facade.traningcenter.output;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**I
 * @author qingli.chen
 * @date 2017/9/22
 * @description 债转投资记录
 */
@Data
@ToString
public class TenderTransferVo implements Serializable {

    private static final long serialVersionUID = -1280814137118421265L;

    /**
     * 投资时间
     */
    private Date addTime;

    /**
     * 投资用户
     */
    private String mobile;

    /**
     * 投资金额
     */
    private BigDecimal account;

    /**
     * 是否app投资
     */
    private boolean isApp = false;


}
