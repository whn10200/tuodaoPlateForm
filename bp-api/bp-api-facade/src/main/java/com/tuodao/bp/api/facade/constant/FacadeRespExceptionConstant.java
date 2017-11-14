package com.tuodao.bp.api.facade.constant;

/**
 * @description: 聚合层业务异常代码
 * @author: mif
 * @date: 2017/9/12
 * @time: 15:48
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class FacadeRespExceptionConstant {
    /** 验证短信发送太频繁，请稍后再请求*/
    public static final int SMS_SEND_TOO_OFTEN = 199001;
    /** 验证码不存在或已失效，请重新请求发送*/
    public static final int SMS_CODE_IS_NON_EXISTENT = 199002;
    /** 短信验证码不匹配*/
    public static final int SMS_CODE_NOT_MATCH = 199003;
    /** 短信模板未配置*/
    public static final int SMS_TEMPLATE_NOT_FIND = 199004;
    /** 头像上传失败*/
    public static final int AVATAR_UPLOAD_FAILED = 199005;
    /** 文件不存在，请重新上传 */
    public static final int FILE_NOT_EXIST = 199006;
    /** 文件格式错误，请上传jpg、png、gif 格式的文件*/
    public static final int AVATAR_FORMAT_ERROR = 199007;
    /** 文件超出大小*/
    public static final int AVATAR_BEYOND_MAX_SIZE = 199008;
    /** 用户不存在 */
    public static final int USER_ACCOUNT_USER_NOT_EXISTENCE = 199009;
    /** 用户尚未开通存管*/
    public static final int USER_NOT_OPEN_DEPOSIT = 199010;
}
