package com.tuodao.bp.model.business.traningcenter.output;

import com.tuodao.bp.model.BasePojo;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qingli.chen
 * @date 2017/10/31
 * @description
 */
@Data
public class RechargeConfirmOutput extends BasePojo {

    private static final long serialVersionUID = -591808195779400477L;

    private String msg;

    private boolean success;

    private Integer code;

}
