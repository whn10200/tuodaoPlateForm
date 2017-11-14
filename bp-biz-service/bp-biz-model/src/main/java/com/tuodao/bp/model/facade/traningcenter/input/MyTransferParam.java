package com.tuodao.bp.model.facade.traningcenter.input;

import com.tuodao.bp.model.PagePojo;
import lombok.Data;
import lombok.ToString;

/**
 * app 我的债权转让列表
 * @author qingli.chen
 * @date 2017/11/10
 * @description
 */
@Data
@ToString
public class MyTransferParam extends PagePojo {

    /**
     * 0 可转让 1转让 2受让
     */
    private Integer status;
}
