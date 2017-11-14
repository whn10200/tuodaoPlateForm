package com.tuodao.bp.model.admin;

import com.tuodao.bp.model.annotation.PhoneNum;
import com.tuodao.bp.model.constant.RegexpConstant;
import com.tuodao.bp.model.constant.useraccount.UaParamExceptionConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @description: 换卡
 * @author: mif
 * @date: 2017/11/10
 * @time: 16:05
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class ChangeBankInput extends AdminPojo implements Serializable {
    private static final long serialVersionUID = -771130467476275179L;

    /**
     * 银行卡编码
     */
    @NotBlank(message = UaParamExceptionConstant.BANK_CODE_CAN_NOT_BE_BLANK + "")
    private String bankCode;

    /**
     * 卡号
     */
    @NotBlank(message = UaParamExceptionConstant.BANK_NUM_CAN_NOT_BE_BLANK + "")
    @Pattern(regexp = RegexpConstant.PATTERN_BANK_NUM_REGEXP, message = UaParamExceptionConstant.BANK_NUM_FORMAT_ERROR + "")
    private String bankNum;

    /**
     * 预留号码
     */
    @NotBlank(message = UaParamExceptionConstant.RESERVATION_MOBILE_CAN_NOT_BE_NULL + "")
    @PhoneNum(message = UaParamExceptionConstant.RESERVATION_MOBILE_FORMAT_ERROR + "")
    private String reservationMobile;

    /**
     * 老的卡号
     */
    @NotBlank(message = UaParamExceptionConstant.BANK_NUM_CAN_NOT_BE_BLANK + "")
    @Pattern(regexp = RegexpConstant.PATTERN_BANK_NUM_REGEXP, message = UaParamExceptionConstant.BANK_NUM_FORMAT_ERROR + "")
    private String oldBankNum;

}
