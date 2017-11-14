package com.tuodao.bp.cache.constant;

/**
 * redis相关常量
 * 
 * @author hechuan
 *
 * @created 2017年6月1日
 *
 * @version 1.0.0
 */
public class RedisConstans {

	/** 基础数据缓存 */
	public static final String CACHE_NAME_BASIC_DATA_BUSINESS = "BASIC_DATA_BUSINESS";

	/** IP白名单 */
	public static final String CACHE_NAME_BASIC_DATA_IPLIMIT = "BASIC_DATA_IPLIMIT";

	/** 银行 */
	public static final String CACHE_NAME_BANKS = "CACHE_NAME_BANKS";

	/** 网银 */
	public static final String CACHE_NAME_ONLINE_BANKS = "ONLINE_BANKS";

	/** 快捷充值 */
	public static final String CACHE_NAME_FAST_BANKS = "FAST_BANKS";

	/** 省份 */
	public static final String CACHE_NAME_PROVINCES = "CACHE_NAME_PROVINCES";

	/** 城市 */
	public static final String CACHE_NAME_CITIES = "CACHE_NAME_CITIES";

	/** 地区 */
	public static final String CACHE_NAME_AREAS = "CACHE_NAME_AREAS";
	
	/** 业务提示 */
	public static final String BASIC_DATA_MODULE_BUSINESS = "business";
	
	/** ip白名单限制 */
	public static final String BASIC_DATA_MODULE_IPLIMIT = "iplimit";

	/** 数据字典缓存*/
	public static final String CACHE_NAME_CONFIG_DICTIONARY_CACHE = "CONFIG_DICTIONARY_CACHE_";

	/** 用户账户基础信息缓存*/
	public static final String CACHE_NAME_USER_ACCOUNT_INFO = "USER_ACCOUNT_INFO_";

	/** 用户accessId,accessKey缓存 */
	public static final String CACHE_NAME_ACCESS_INFO = "ACCESS_INFO_";

	/** 用户accessId,accessKey APP缓存 */
	public static final String CACHE_NAME_ACCESS_INFO_APP = "ACCESS_INFO_APP_";

	/** 短信验证码缓存*/
	public static final String CACHE_NAME_SMS_CODE = "SMS_CODE_";
	/**短信模版缓存 */
	public static final String CACHE_NAME_SMS_TEMPLATE = "SMS_TEMPLATE_";
	/** 推送模版缓存 */
	public static final String CACHE_NAME_PUSH_TEMPLATE = "PUSH_TEMPLATE_";
    /**
     * 提现加锁
     */
    public static final String LOCK_PREFIX = "cash_";

    /**
     * 提现异步回调
     */
    public static final String CASH_ASYNC_PREFIX = "cashAsync_";

    /**
     * 更新资金与资金日志
     */
    public static final String ACCOUNT_LOG_PREFIX = "accountLog_";

	/**
	 * 加入理财计划加锁
	 */
	public static final String JOIN_PLAN_PREFIX = "joinPlan_";





	/** 自定义标种类型缓存*/
	public static final String CACHE_BORROW_DEFINE_TYPE = "BORROW_DEFINE_TYPE_";

	/** 前端散标缓存*/
	public static final String CACHE_BORROW = "CACHE_BORROW_";

	/** 前端理财计划缓存*/
	public static final String CACHE_PLAN = "CACHE_PLAN_";

    /** MQ同步数据错误次数 */
	public static final String MQ_ERROR_NUMBER = "MQ_ERROR_NUMBER_";

	/** 积分任务缓存 */
	public static final String OP_SCORE_TASK = "OP_SCORE_TASK";
	/** VIP等级缓存 */
	public static final String BASE_VIP_LEVEL_INFO = "BASE_VIP_LEVEL_INFO";

	/** 理财计划产品剩余金额缓存 */
	public static final String TC_PROJECT = "TC_PROJECT_";

	/** 返回信息缓存（如理财计划） */
	public static final String TC_RESULT = "TC_RESULT_";

	/** 更新理财计划下accountstatus */
	public static final String TC_ACCOUNT_STATUS = "TC_ACCOUNT_STATUS_";
	
	/** 存管订单号 */
	public static final String DEPOSITORY_ORDER_NO = "DEPOSITORY_ORDER_NO_";
	

	/** 债转解冻 */
	public static final String TRANSFER_UNFREEZE = "TRANSFER_UNFREEZE_";

	/** 债权转让 */
	public static final String TRANSFER_DEBT = "TRANSFER_DEBT_";

	/** 系统内部银行表缓存 */
	public static final String CACHE_ACCOUNT_BANK = "CACHE_ACCOUNT_BANK";
	
	/**
	 * 标的还款加锁
	 */
 	public static final String PRODUCT_REPAYMENT_LOCK = "PRODUCT_REPAYMENT_LOCK";

	/**
	 * 债转投标锁
	 */
	public static final String TENDER_TRANSFER_LOCK = "TENDER_TRANSFER_LOCK_";

    /**
     * 模拟session缓存
     */
    public static final String SESSION_ID = "SESSION_ID_";
}
