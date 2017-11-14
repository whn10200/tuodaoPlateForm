package com.tuodao.bp.model.facade.traningcenter.output;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author qingli.chen
 * @date 2017/9/19
 * @description 回款计划 app
 */
@Data
@ToString
public class BackMoneyPlanAppVo implements Serializable {

    private static final long serialVersionUID = 5582010590245782270L;
    /**
     * 回款时间
     */
    private String collectionTime;

    /**
     * 回款利息
     */
    private String interest;

    /**
     * 回款本金
     */
    private String capital;

    /**
     * 回款状态
     */
    private String status;
}
