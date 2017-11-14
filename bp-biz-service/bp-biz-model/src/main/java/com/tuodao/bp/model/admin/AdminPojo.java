package com.tuodao.bp.model.admin;

import com.tuodao.bp.model.constant.common.CommonExceptionConstant;
import com.tuodao.bp.model.constant.facade.FacadeExceptionConstant;
import com.tuodao.bp.model.constant.useraccount.UaParamExceptionConstant;
import com.tuodao.bp.model.depository.DepositoryParaPojo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @description: 大后台请求基础POJO
 * @author: mif
 * @date: 2017/11/10
 * @time: 16:07
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
class AdminPojo extends DepositoryParaPojo implements Serializable {
    private static final long serialVersionUID = 9111924942003290912L;

    /**
     * 用户ID
     */
    @NotBlank(message = FacadeExceptionConstant.USER_ID_CAN_NOT_BE_NULL + "")
    private String userId;

    /**
     * 修改人
     */
    @NotBlank(message = FacadeExceptionConstant.GMT_MODIFIER_CAN_NOT_BE_NULL + "")
    @Size(max = 32, message = CommonExceptionConstant.PARAM_BEYOND_THE_LENGTH + "")
    private String gmtModifier;


}
