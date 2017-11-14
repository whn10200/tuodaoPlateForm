package com.tuodao.bp.model.constant.facade;

/**
 * @description: 聚合层异常抛出
 * @author: mif
 * @date: 2017/9/12
 * @time: 14:45
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class FacadeExceptionConstant {
    /** 手机号码不能为空 */
    public static final int MOBILE_CAN_NOT_BE_NULL = 191001;
    /** 手机号码格式 */
    public static final int MOBILE_FORMAT_ERROR = 191002;
    /** 短信类型不能为空 */
    public static final int SMS_TYPE_CAN_NOT_BE_BLANK = 191003;
    /** 短信类型传值错误*/
    public static final int SMS_TYPE_VALUE_ERROR = 191004;
    /** 短信验证码不能为空 */
    public static final int SMS_CODE_CAN_NOT_BE_BLANK = 191005;
    /** 邀请码不能为空 */
    public static final int INVITE_CODE_CAN_NOT_BE_BLANK = 191006;
    /** 邀请码超出长度限制 */
    public static final int INVITE_CODE_BEYOND_THE_LENGTH = 191007;
    /** ID不能为空 */
    public static final int ID_CAN_NOT_BE_BLANK = 191008;
    /** 用户编码不能为空 */
    public static final int USER_ID_CAN_NOT_BE_NULL = 191009;
    /** 修改人不能为空 */
    public static final int GMT_MODIFIER_CAN_NOT_BE_NULL = 191010;
}
