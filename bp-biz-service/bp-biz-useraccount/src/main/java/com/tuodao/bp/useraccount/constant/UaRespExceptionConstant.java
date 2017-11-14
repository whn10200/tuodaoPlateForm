package com.tuodao.bp.useraccount.constant;

/**
 * 用户账户异常常量类
 * @author hechuan
 *
 * @created 2017年8月30日
 *
 * @since 1.0.0
 */
public class UaRespExceptionConstant {

	/** 记录条数不唯一 */
	public static final int USER_ACCOUNT_RECORD_NOT_ONLY = 170001;

	/** 事物异常 */
	public static final int USER_ACCOUNT_TRANSACTION_EXCEPTION = 170002;
    /** 用户ID不能为空 */
	public static final int USER_ACCOUNT_USER_ID_CAN_NOT_BE_NULL = 170003;
	/** 用户不存在 */
    public static final int USER_ACCOUNT_USER_NOT_EXISTENCE = 170004;
    /** 号码已注册 */
    public static final int USER_ACCOUNT_MOBILE_REGISTERED = 170005;
    /** 用户已开通存管 */
    public static final int USER_HAS_OPENED_DEPOSIT = 170006;
    /** 用户名或密错误 */
    public static final int USER_NAME_OR_PW_ERROR = 170007;
    /** 旧密码错误 */
    public static final int OLD_PW_ERROR = 170008;
    /** 用户尚未注册*/
    public static final int USER_NOT_REGISTERED = 170009;

    /** 意见反馈添加失败*/
    public static final int FEEDBACK_ADD_ERROR = 170010;
    /** 数据添加异常*/
    public static final int ADD_ERROR = 170011;
    /** 账户累计收益详细明细类型不能为空*/
    public static final int INCOME_TYPE_NOT_BE_NULL = 170012;

    /** 数据不存在*/
    public static final int DATA_NOT_EXIT = 170013;

    /** 数据修改异常*/
    public static final int UPDATE_ERROR = 170014;

    /** 等级信息不存在 */
    public static final int LEVEL_INFO_NOT_EXIT = 170015;

    /** 短信模板未配置*/
    public static final int SMS_TEMPLATE_NOT_FIND = 170016;
    /** 用户详情不存在*/
    public static final int USER_DETAIL_NOT_EXIST = 170017;

    /** 存管开通中,请耐心等待*/
    public static final int OPENED_DEPOSIT_ING= 170018;
    /** 用户尚未开通存管*/
    public static final int USER_NOT_OPEN_DEPOSIT= 170019;

    /** 存管系统清算时间 */
    public static final int STORAGE_SYSTEM_SETTLEMENT_TIME = 170020;
}
