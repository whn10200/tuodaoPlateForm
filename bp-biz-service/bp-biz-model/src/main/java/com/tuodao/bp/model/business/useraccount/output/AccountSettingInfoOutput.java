package com.tuodao.bp.model.business.useraccount.output;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @description: 帐号设置信息
 * @author: mif
 * @date: 2017/11/6
 * @time: 16:18
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Data
@ToString
public class AccountSettingInfoOutput implements Serializable {

    /**
     * 是否已开通存管账户（0:未开通；1:已开通;2:开通中;3开通失败）
     */
    private Integer isOpenDeposit;


    /**
     * 是否已绑定银行卡（0：未绑；1：已绑定）
     */
    private Integer isBindBank;

    /**
     * 银行卡号
     */
    private String bankNum;

    /**
     * 银行名称
     */
    private String bankName;
    /**
     * 密码安级别（0：弱；1：强；2：最高）
     */
    private Integer pwSecurityLevel;

    /**
     * 是否有收件人信息（0:无；1：有）
     */
    private Integer hasConsigneeInfo;
    /**
     * 收件人
     */
    private String consignee;
    /**
     * 收件人联系电话
     */
    private String consigneeMobile;
    /**
     * 收件地址
     */
    private String consigneeAddress;
}
