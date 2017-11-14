package com.tuodao.bp.model.business.traningcenter.input;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.constant.traningcenter.RechargeConstant;

import javax.validation.constraints.NotNull;

/**
 * 快捷充值
 * @author qingli.chen
 * @date 2017/10/16
 * @description
 */
public class FastRechargeInput extends BasePojo {


    private static final long serialVersionUID = 8471678549158779707L;

    private Integer id;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
