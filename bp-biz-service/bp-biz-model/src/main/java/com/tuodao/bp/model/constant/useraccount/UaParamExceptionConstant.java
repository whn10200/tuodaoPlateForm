package com.tuodao.bp.model.constant.useraccount;

/**
 * 参数异常常量
 * @author hechuan
 *
 * @created 2017年8月31日
 *
 * @since 1.0.0
 */
public class UaParamExceptionConstant {

	/**	手机号码不能为空 */
	public static final int MOBILE_IS_NULL = 171000;
	/**	手机号码格式错误 */
	public static final int MOBILE_FORMAT_ERROR = 171001;
	/** 用户名不能为空 */
	public static final int USER_NAME_CANT_BE_BLANK = 171002;
	/** 参数超出长度限制*/
	public static final int PARAM_BEYOND_THE_LENGTH = 171003;
	/** 参数传值范围错误*/
	public static final int PARAM_VALUE_ERROR = 171004;
    /** 登录密码不能为空*/
	public static final int LOGIN_PASSWORD_CAN_NOT_BE_BLANK = 171005;
	/** 请输入6-16位密码*/
	public static final int LOGIN_PASSWORD_ERORR = 171006;
	/** 登录来源不能为空*/
	public static final int LOGIN_SOURCE_CAN_NOT_BE_NULL = 171007;
	/** 密码安级别不能为空 */
	public static final int PW_SECURITY_LEVEL_CAN_NOT_BE_NULL = 171008;
	/** 支付密码不能为空*/
	public static final int PAY_PASSWORD_CAN_NOT_BE_BLANK = 171009;
	/** 支付密码必须为6位整数*/
	public static final int PAY_PASSWORD_MUST_BE_SIX_NUM = 171010;
	/** 头像地址不能为空*/
	public static final int AVATAR_URL_CAN_NOT_BE_BLANK = 171011;
	/** 注册来源不能为空*/
	public static final int REGISTER_SOURCE_CAN_NOT_BE_NULL = 171012;
	/** IP地址格式错误*/
	public static final int IP_FORMAT_ERROR = 171013;
	/** 真实姓名不能为空*/
	public static final int REAL_NAME_CAN_NOT_BE_BLANK = 171014;
	/** 身份证号码不能为空*/
	public static final int ID_CARD_CAN_NOT_BE_BLANK = 171015;
	/** 身份证号码格式错误*/
	public static final int ID_CARD_FORMAT_ERROR = 171016;
	/** 银行编号不能为空*/
	public static final int BANK_CODE_CAN_NOT_BE_BLANK = 171017;
	/** 银行卡号不能为空*/
	public static final int BANK_NUM_CAN_NOT_BE_BLANK = 171018;
    /** 银行卡号格式错误*/
	public static final int BANK_NUM_FORMAT_ERROR = 171019;
    /** 预留手机号码不能为空*/
	public static final int RESERVATION_MOBILE_CAN_NOT_BE_NULL = 171020;
    /**预留手机号码格式错误*/
	public static final int RESERVATION_MOBILE_FORMAT_ERROR = 171021;
	/** 存管编号不能为空*/
	public static final int DEPOSIT_NO_CAN_NOT_BE_BLANK = 171022;
	/** 旧密码不能为空 */
	public static final int OLD_PW_CAN_NOT_BE_BLANK = 171023;
	/** 新密码不能为空 */
	public static final int NEW_PW_CAN_NOT_BE_BLANK = 171024;
	/** 短信验证吗不能为空*/
	public static final int SMS_CODE_CANT_BE_BLANK = 171025;

	/** 收件人不能为空 */
	public static final int CONSIGNEE_CAN_NOT_BE_NULL = 171026;
	/** 收件人手机号不能为空 */
	public static final int CONSIGNEE_MOBILE_CAN_NOT_BE_NULL = 171027;
	/** 收件地址不能为空 */
	public static final int CONSIGNEE_ADDRESS_CAN_NOT_BE_NULL = 171028;
	/** 密码必须为MD5格式 */
	public static final int PASS_WORD_MUST_BE_MD5 = 171029;

	/** ---------------------- add zkai ------------------------- */
	/** 意见反馈内容不能为空*/
	public static final int FEEDBACK_CONTENT_NOT_BE_BLANK = 171030;
}
