package com.tuodao.bp.model.constant.depository;

/**bj bank data dictionary*/
public class BBDD {	
	
	/**证件类型	1	身份证*/
	public final static String xtrue = "true";
	
	/**证件类型	1	身份证*/
	public final static String xfalse = "false";
	
	/**证件类型	1	身份证*/
	public final static String CERT_TYPE_ID = "1";
	/**性别	0	男*/
	public final static String SEX_MAN = "0";
	/**性别	1	女*/
	public final static String SEX_WOMAN = "1";
	/**客户类型	1	个人客户*/
	public final static String CUSTOMER_TYPE_PERSONAL = "1";
	/**客户类型	2	企业客户*/
	public final static String CUSTOMER_TYPE_CORPORATE = "2";
	/**卡类型	1	借记卡*/
	public final static String BANK_CARD_TYPE_DEBIT = "1";
	/**卡类型	2	信用卡*/
	public final static String BANK_CARD_TYPE_CREDIT = "2";
	/**订单状态	0	已接受*/
	public final static String ORDER_STATUS_ACCEPTED = "0";
	/**订单状态	1	处理中*/
	public final static String ORDER_STATUS_HANDLING = "1";
	/**订单状态	2	处理成功*/
	public final static String ORDER_STATUS_SUCCESS = "2";
	/**订单状态	3	处理失败*/
	public final static String ORDER_STATUS_FAIL = "3";
	/**婚姻状态	0	已婚*/
	public final static String MARITAL_MARRIED = "0";
	/**婚姻状态	1	未婚*/
	public final static String MARITAL_SINGLE = "1";
	/**有无	0	有*/
	public final static String EXIST = "0";
	/**有无	1	无*/
	public final static String NONE = "1";
	/**产品类型	0	周期性产品*/
	public final static String PRODUCT_TYPE_PERIODIC = "0";
	/**产品类型	1	活期产品*/
	public final static String PRODUCT_TYPE_CURRENT = "1";
	/**产品起息方式	0	满额起息*/
	public final static String CARRY_INTEREST_TYPE_FULL_AMOUNT = "0";
	/**产品起息方式	1	成立起息*/
	public final static String CARRY_INTEREST_TYPE_COMPLETE = "1";
	/**产品起息方式	2	投标起息*/
	public final static String CARRY_INTEREST_TYPE_BID = "2";
	/**产品起息方式	3	成立审核后起息*/
	public final static String CARRY_INTEREST_TYPE_AUDIT = "3";
	/**成立方式	0	满额成立*/
	public final static String ACHIEVEMENT_TYPE_FULL_AMOUNT = "0";
	/**成立方式	1	成立日成立*/
	public final static String ACHIEVEMENT_TYPE_COMPLETE = "1";
	/**成立方式	2	活期方式*/
	public final static String ACHIEVEMENT_TYPE_CURRENT = "2";
	/**周期单位	1	日*/
	public final static String PERIOD_UNIT_DAY = "1";
	/**周期单位	2	周*/
	public final static String PERIOD_UNIT_WEEK = "2";
	/**周期单位	3	月*/
	public final static String PERIOD_UNIT_MONTH = "3";
	/**周期单位	4	季*/
	public final static String PERIOD_UNIT_SEASON = "4";
	/**周期单位	5	年*/
	public final static String PERIOD_UNIT_YEAR = "5";
	/**还款方式	0	按月等额本金 average capital*/
	public final static String REPAY_TYPE_MONTH_AC = "0";
	/**还款方式	1	按月等额本息  average capital plus interest*/
	public final static String REPAY_TYPE_MONTH_ACPI = "1";
	/**还款方式	2	按季等额本金 average capital*/
	public final static String REPAY_TYPE_SEASON_AC = "2";
	/**还款方式	3	按季等额本息 average capital plus interest*/
	public final static String REPAY_TYPE_SEASON_ACPI = "3";
	/**还款方式	4	到期日一次性还款 */
	public final static String REPAY_TYPE_ONE_TIME = "4";
	/**还款方式	5	按期还息到期还本 period interest finish principal*/
	public final static String REPAY_TYPE_PIFP = "5";
	/**分红方式	0	成立分红*/
	public final static String DIVIDENDS_TYPE_COMPLETE = "0";
	/**分红方式	1	还款分红*/
	public final static String DIVIDENDS_TYPE_REPAY = "1";
	/**是否	0	是*/
	public final static String YES = "0";
	/**是否	1	否*/
	public final static String NO = "1";
	/**自动出账	0	自动出账*/
	public final static String ATUO_OFF_CHARGE = "0";
	/**自动出账	1	手动出账*/
	public final static String MANUAL_OFF_CHARGE = "1";
	/**成废标记	2	成标*/
	public final static String ACHIEVEMENT_BIDDING = "2";
	/**成废标记	3	废标*/
	public final static String WITHDROW_BIDDING = "3";
	/**科目优先级	0	可提优先*/
	public final static String PRIORITY_TYPE_WITHDROW = "0";
	/**科目优先级	1	可投优先*/
	public final static String PRIORITY_TYPE_BID = "1";
	/**科目优先级	2	均分 average  withdrow & bid*/
	public final static String PRIORITY_TYPE_AWD = "2";
	/**充值类型	0	用户充值*/
	public final static String RECHARGE_TYPE_USER = "0";
	/**充值类型	1	平台充值*/
	public final static String RECHARGE_TYPE_PLATFORM = "1";
	/**平台子账户	1	自有子账户*/
	public final static String PLATFORM_SUBACCOUNT_OWN = "1";
	/**平台子账户	3	自有子账户*/
	public final static String PLATFORM_SUBACCOUNT_FEE = "3";
	/**平台子账户	4	体验金子账户*/
	public final static String PLATFORM_SUBACCOUNT_EXPERIENCE = "4";
	/**平台子账户	5	抵用金子账户*/
	public final static String PLATFORM_SUBACCOUNT_DEDUCTIBLE = "5";
	/**平台子账户	6	加息金子账户*/
	public final static String PLATFORM_SUBACCOUNT_RAISE_INTEREST = "6";
	/**平台子账户	7	保证金子账户*/
	public final static String PLATFORM_SUBACCOUNT_DEPOSITORY = "7";
	/**平台子账户	9	奖励金*/
	public final static String PLATFORM_SUBACCOUNT_AWARD = "9";
	/**平台子账户	10	现金垫付*/
	public final static String PLATFORM_SUBACCOUNT_CASH_ADVANCE = "10";
	/**平台子账户	11	在途垫付*/
	public final static String PLATFORM_SUBACCOUNT_FLOAT_ADVANCE = "11";
	
	/**交易状态	0	成功*/
	public final static String SUCCESS = "0";
	/**交易状态	1	失败*/
	public final static String FAILE = "1";
	/**冻结解冻标示	0	冻结*/
	public final static String FROZEN = "0";
	/**冻结解冻标示	1	解冻*/
	public final static String UNFROZEN = "1";
	/**是否预对账	0	否*/
	public final static String PRE_RECONCILIATION_NO = "0";
	/**是否预对账	1	是*/
	public final static String PRE_RECONCILIATION_YES = "1";
	/**提现审核	0	允许提现*/
	public final static String WITHDROW_AUDIT_APPLY = "0";
	/**提现审核	1	拒绝提现*/
	public final static String WITHDROW_AUDIT_REFUSE = "1";

	/**成功*/
	public final static String success10000 = "10000";
	/**存管服务关闭*/
	public final static String error90909 = "90909";
	/**存管服务关闭 提示语*/
	public final static String error90909msg = "银行存管系统关闭";
	
	/**参数格式不匹配*/
	public final static String error10002 = "10002";
	/** 缺少必要参数 */
	public final static String error20001 = "20001";
	/** 参数错误 */
	public final static String error20002 = "20002";
	/** 签名错误 */
	public final static String error20003 = "20003";
	/** appid为空 */
	public final static String error20004 = "20004";
	/** appid错误 */
	public final static String error20005 = "20005";
	/** 平台编号为空 */
	public final static String error20014 = "20014";
	/** 平台编号错误 */
	public final static String error20015 = "20015";
	/** 订单号重复 */
	public final static String error20016 = "20016";
	/** 原订单号错误 */
	public final static String error20017 = "20017";
	/** 原订单未处理成功（针对确认操作时） */
	public final static String error20018 = "20018";
	/** 参数长度过长 */
	public final static String error21000 = "21000";
	/** 未知错误 */
	public final static String error30001 = "30001";
	/** 数据库操作异常 */
	public final static String error30002 = "30002";
	/** 空指针异常 */
	public final static String error30003 = "30003";
	/** IO操作异常 */
	public final static String error30004 = "30004";
	/** 数学运算异常 */
	public final static String error30005 = "30005";
	/** 数组越界 */
	public final static String error30006 = "30006";
	/** 方法参数错误 */
	public final static String error30007 = "30007";
	/** 类转换异常 */
	public final static String error30008 = "30008";
	/** 违背安全原则 */
	public final static String error30009 = "30009";
	/** SQL操作异常 */
	public final static String error30010 = "30010";
	/** 运行异常 */
	public final static String error30011 = "30011";
	/** 方法名不存在 */
	public final static String error30012 = "30012";
	/** 服务器繁忙 */
	public final static String error30013 = "30013";
	/** 远程调用端错误 */
	public final static String error31111 = "31111";
	/** 远程未知状况 */
	public final static String error32222 = "32222";
	/** 未查询到订单信息 */
	public final static String error34401 = "34401";
	/** 无效的产品id */
	public final static String error40001 = "40001";
	/** 用户密码错误 */
	public final static String error40002 = "40002";
	/** 用户名重复 */
	public final static String error40003 = "40003";
	/** 无效的open_id */
	public final static String error40004 = "40004";
	/** 无效的邀请码 */
	public final static String error40005 = "40005";
	/** 实名认证失败 */
	public final static String error40006 = "40006";
	/** 账号无效或不存在 */
	public final static String error40007 = "40007";
	/** 重复的产品id */
	public final static String error40008 = "40008";
	/** 无效的转让编号 */
	public final static String error40009 = "40009";
	/** 无效的平台编号 */
	public final static String error40010 = "40010";
	/** 标的已满额 */
	public final static String error40011 = "40011";
	/** 标的状态不许购买 */
	public final static String error40012 = "40012";
	/** 用户在黑名单中 */
	public final static String error40013 = "40013";
	/** 标的未成标 */
	public final static String error40014 = "40014";
	/** 请求账户与融资人提取账户信息是否一致 */
	public final static String error40015 = "40015";
	/** 不满足转让条件 */
	public final static String error40016 = "40016";
	/** 不在误差范围内 */
	public final static String error40017 = "40017";
	/** 出账金额不足 */
	public final static String error40018 = "40018";
	/** 标的未满额 */
	public final static String error40019 = "40019";
	/** 金额有误 */
	public final static String error40020 = "40020";
	/** 标的已成标 */
	public final static String error40021 = "40021";
	/** 还款失败 */
	public final static String error41000 = "41000";
	/** 已注册 */
	public final static String error45000 = "45000";
	/** 未注册 */
	public final static String error45001 = "45001";
	/** 已绑卡 */
	public final static String error46000 = "46000";
	/** 用户无权进行此操作 */
	public final static String error47000 = "47000";
	/** 充值金额不合法 */
	public final static String error50001 = "50001";
	/** 支付失败 */
	public final static String error50002 = "50002";
	/** 网络异常 */
	public final static String error50003 = "50003";
	/** 用户此渠道下未绑定卡 */
	public final static String error50004 = "50004";
	/** bindid不存在 */
	public final static String error50005 = "50005";
	/** 绑卡失败 */
	public final static String error50006 = "50006";
	/** 解绑失败 */
	public final static String error50007 = "50007";
	/** 获取验证码失败 */
	public final static String error50008 = "50008";
	/** 没有渠道信息 */
	public final static String error50009 = "50009";
	/** 未知渠道 */
	public final static String error50010 = "50010";
	/** 已经绑过卡，不要重复绑卡 */
	public final static String error50011 = "50011";
	/** 实名认证失败 */
	public final static String error50012 = "50012";
	/** 未绑卡 */
	public final static String error50013 = "50013";
	/** 余额不足 */
	public final static String error50014 = "50014";
	/** 提现失败 */
	public final static String error50015 = "50015";
	/** 余额查询失败 */
	public final static String error50016 = "50016";
	/** 查询绑卡信息失败 */
	public final static String error50017 = "50017";
	/** 下载对账文件失败 */
	public final static String error50018 = "50018";
	/** 转账原因不合法 */
	public final static String error50019 = "50019";
	/** 超出转账额度 */
	public final static String error50020 = "50020";
	/** 超出月转账额度 */
	public final static String error50021 = "50021";
	/** 活期标暂不支持垫付出账 */
	public final static String error50022 = "50022";
	
	public final static String proccessing = "0";
	public final static String tradeSuccess = "1";
	public final static String tradeFail = "2";
	public final static String unknow = "3";
	public final static String requestSuccess = "11";
	public final static String requestFail = "12";
	public final static String requestUnknow = "13";
	public final static String confirmSuccess = "21";
	public final static String confirmFail = "22";
	public final static String confirmUnknow = "23";


}
