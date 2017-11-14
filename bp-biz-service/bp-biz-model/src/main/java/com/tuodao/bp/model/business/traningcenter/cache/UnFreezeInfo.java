package com.tuodao.bp.model.business.traningcenter.cache;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 债转解冻
 * @author qingli.chen
 * @date 2017/10/24
 * @description
 */
@Data
@ToString
public class UnFreezeInfo implements Serializable {

    /**
     * 债转id
     */
    private String transferId;

    /**
     * 投标
     */
    private List<TransferTenderInfo> tenderInfoList;

    /**
     * 是否解冻成功
     */
    private boolean success;
}
