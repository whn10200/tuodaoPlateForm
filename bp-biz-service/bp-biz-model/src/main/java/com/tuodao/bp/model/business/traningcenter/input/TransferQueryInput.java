package com.tuodao.bp.model.business.traningcenter.input;

import com.tuodao.bp.model.PagePojo;
import lombok.Data;

import java.util.Date;

/**
 * @author qingli.chen
 * @date 2017/9/21
 * @description 债转列表查询
 */
@Data
public class TransferQueryInput extends PagePojo {
    private static final long serialVersionUID = 724117805661443450L;

    private Integer beginPeriod = 0;

    private Integer endPeriod = 0;

    private Integer transferId;
}
