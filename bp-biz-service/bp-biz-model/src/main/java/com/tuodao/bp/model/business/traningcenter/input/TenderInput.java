package com.tuodao.bp.model.business.traningcenter.input;

import java.io.Serializable;

/**
 * @author qingli.chen
 * @date 2017/9/21
 * @description
 */
public class TenderInput implements Serializable {

    private static final long serialVersionUID = 6815801501686882997L;

    private Integer tenderId;

    public Integer getTenderId() {
        return tenderId;
    }

    public void setTenderId(Integer tenderId) {
        this.tenderId = tenderId;
    }
}
