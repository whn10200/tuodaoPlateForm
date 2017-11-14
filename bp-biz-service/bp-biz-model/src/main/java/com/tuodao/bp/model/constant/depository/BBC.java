package com.tuodao.bp.model.constant.depository;

import java.util.ResourceBundle;

import sun.misc.BASE64Decoder;

/**
 * bj bank constant</br>
 * 用词规范</br>
 * REQUEST：请求HTTP</br>
 * RESPONSE：请求HTTP所对应的返回</br>
 * RECEIVE：请求所对应的回调HTTP</br>
 */
public class BBC {

	private static ResourceBundle rb = ResourceBundle.getBundle("bjbank");

	public static final String PFX_PATH = rb.getString("pfx_path");

	public static final String PFX_PWD = initPfxPwd();

	private static String initPfxPwd() {
		try {
			String a = new String(new BASE64Decoder().decodeBuffer(rb.getString("pfx_pwd")), "UTF-8");
			Long b = Long.valueOf(a)+8L;
			return b.toString();
		} catch (Exception e) {
		}
		return null;
	}

	public static final String PFX_TYPE = rb.getString("pfx_type");

	public static final String CERT_PATH = rb.getString("cert_path");

	/**是否连接银行存管系统  */
	public static final String ACTIVE = rb.getString("active");
	
	/** 银行地址 */
	public static final String BANK_URL = rb.getString("bank_url");
	/** 前台回调用，我方网站地址， */
	public static final String LOCAL_WEB_URL = rb.getString("local_web_url");
	/** 后台回调用，我方服务器地址 */
	public static final String LOCAL_SERVER_URL = rb.getString("local_server_url");

	/** 平台编号 */
	public static final String PLAT_NO = rb.getString("plat_no");

	/** 北京银行代付，第三方支付编号 */
	public static final String PAY_CODE_BOB_PAY = rb.getString("pay_code_bob_pay");
	/** 中金，第三方支付编号 */
	public static final String PAY_CODE_ZHONG_JIN = rb.getString("pay_code_zhong_jin");
	/** 连连，第三方支付编号 */
	public static final String PAY_CODE_LIAN_LIAN = rb.getString("pay_code_lian_lian");
	/** 联动优势，第三方支付编号 */
	public static final String PAY_CODE_LIAN_DONG = rb.getString("pay_code_lian_dong");
	/** 易宝，第三方支付编号 */
	public static final String PAY_CODE_YI_BAO = rb.getString("pay_code_yi_bao");
	/** 翼支付，第三方支付编号 */
	public static final String PAY_CODE_YI_ZHI_FU = rb.getString("pay_code_yi_zhi_fu");
	/** 富友，第三方支付编号 */
	public static final String PAY_CODE_FU_YOU = rb.getString("pay_code_fu_you");
	/** 联动优势，第三方支付编号 */
	public static final String PAY_CODE_YIN_YING_TONG = rb.getString("pay_code_yin_ying_tong");
	/** 美付宝，第三方支付编号 */
	public static final String PAY_CODE_MEI_FU_BAO = rb.getString("pay_code_mei_fu_bao");
	/** 北京银行验卡，第三方支付编号 */
	public static final String PAY_CODE_BOB_VALIDATE_CARD = rb.getString("pay_code_bob_validate_card");
	/** 京东，第三方支付编号 */
	public static final String PAY_CODE_JING_DONG = rb.getString("pay_code_jing_dong");
	/** 默认，富有，第三方支付编号 */
	public static final String PAY_CODE = PAY_CODE_FU_YOU;

	/** 用户，四要素注册 */
	public static final String REQUEST_URL_USER_REGIST_4_ELE = BANK_URL + rb.getString("request_url_user_regist_4_ele");
	/** 用户，实名注册 */
	public static final String REQUEST_URL_USER_REGIST_REAL_NAME = BANK_URL
			+ rb.getString("request_url_user_regist_real_name");
	/** 用户，绑卡 */
	public static final String REQUEST_URL_USER_BOUND_CARD = BANK_URL + rb.getString("request_url_user_bound_card");
	/** 用户，解绑卡（原为换卡) */
	public static final String REQUEST_URL_USER_CHANGE_CARD = BANK_URL + rb.getString("request_url_user_change_card");
	/** 用户，短信绑卡申请 */
	public static final String REQUEST_URL_USER_MESSAGE_BOUND_CARD_APPLY = BANK_URL
			+ rb.getString("request_url_user_message_bound_card_apply");
	/** 用户，短信绑卡通知 */
	public static final String REQUEST_URL_USER_MESSAGE_BOUND_CARD_VALIDATE = BANK_URL
			+ rb.getString("request_url_user_message_bound_card_validate");
	/** 用户，信息修改 */
	public static final String REQUEST_URL_USER_UPDATE_INFO = BANK_URL + rb.getString("request_url_user_update_info");

	/** 借款人，批量还款 */
	public static final String REQUEST_URL_BORROWER_BATCH_REPAY = BANK_URL + rb.getString("request_url_borrower_batch_repay");
	/** 借款人，标的代偿 */
	public static final String REQUEST_URL_BORROWER_BIDDING_COMPENSATION = BANK_URL + rb.getString("request_url_borrower_bidding_compensation");
	/** 借款人，还款代偿 */
	public static final String REQUEST_URL_BORROWER_REPAY_COMPENSATION = BANK_URL + rb.getString("request_url_borrower_repay_compensation");

	
	/** 标的，发布 */
	public static final String REQUEST_URL_BIDDING_PUBLISH = BANK_URL + rb.getString("request_url_bidding_publish");
	/** 标的，成废 */
	public static final String REQUEST_URL_BIDDING_RESULT = BANK_URL + rb.getString("request_url_bidding_result");
	/** 标的，投标 */
	public static final String REQUEST_URL_BIDDING_BID = BANK_URL + rb.getString("request_url_bidding_bid");
	/** 标的，出账 */
	public static final String REQUEST_URL_BIDDING_CHARGE_OFF = BANK_URL
			+ rb.getString("request_url_bidding_charge_off");
	/** 标的，债权转让 */
	public static final String REQUEST_URL_BIDDING_TRANSFER_DEBT = BANK_URL
			+ rb.getString("request_url_bidding_transfer_debt");
	/** 标的，批量债权转让 */
	public static final String REQUEST_URL_BIDDING_REPAY = BANK_URL + rb.getString("request_url_bidding_repay");
	/** 标的，还款 */
	public static final String REQUEST_URL_BIDDING_REPAY_PLANE_UPDATE = BANK_URL
			+ rb.getString("request_url_bidding_repay_plane_update");
	/** 标的，还款计划更新 */
	public static final String REQUEST_URL_BIDDING_CHARGE_OFF_CHANGE = BANK_URL
			+ rb.getString("request_url_bidding_charge_off_change");
	/** 标的，出账通知 */
	public static final String RECEIVE_URL_BIDDING_CHARGE_OFF = LOCAL_SERVER_URL
			+ rb.getString("receive_url_bidding_charge_off");

	/** 充值，网关充值*/
	public static final String REQUEST_URL_RECHARGE_GATEWAY = BANK_URL + rb.getString("request_url_recharge_gateway");
	/** 充值，网关 充值回调 */
	public static final String RECEIVE_URL_RECHARGE_GATEWAY = LOCAL_SERVER_URL + rb.getString("receive_url_recharge_gateway");
	/** 充值，快捷充值回调 */
	public static final String RECEIVE_URL_RECHARGE_QUICK = LOCAL_SERVER_URL + rb.getString("receive_url_recharge_quick");
	/** 充值，快捷充值申请 */
	public static final String REQUEST_URL_RECHARGE_QUICK_APPLY = BANK_URL
			+ rb.getString("request_url_recharge_quick_apply");
	/** 充值，快捷充值确认 */
	public static final String REQUEST_URL_RECHARGE_QUICK_CONFIRM = BANK_URL
			+ rb.getString("request_url_recharge_quick_confirm");
	/** 充值，线下自动还款 */
	public static final String REQUEST_URL_BORROWER_OFFLINE_REPAY_AUTO = BANK_URL + rb.getString("request_url_borrower_offline_repay");
	/** 充值，线下手动还款 */
	public static final String REQUEST_URL_BORROWER_OFFLINE_REPAY_MANUAL = BANK_URL + rb.getString("request_url_borrower_offline_repay");	
	/** 充值，线下自动还款确认 */
	public static final String RECEIVE_URL_BORROWER_OFFLINE_REPAY_AUTO = LOCAL_SERVER_URL + rb.getString("receive_url_borrower_offline_repay_auto");
	/** 充值，线下手动还款确认 */
	public static final String RECEIVE_URL_BORROWER_OFFLINE_REPAY_MANUAL = LOCAL_SERVER_URL + rb.getString("receive_url_borrower_offline_repay_manual");

	/** 提现，借款人 */
	public static final String REQUEST_URL_WITHDRAW_APPLY_INVESTOR = BANK_URL + rb.getString("request_url_withdraw_apply");
	/** 提现，还款人 */
	public static final String REQUEST_URL_WITHDRAW_APPLY_BORROWER = BANK_URL + rb.getString("request_url_withdraw_apply");
	/** 提现，借款人通知 */
	public static final String RECEIVE_URL_WITHDRAW_NOTIFY_INVESTOR = LOCAL_SERVER_URL + rb.getString("receive_url_withdraw_notify_investor");
	/** 提现，还款人通知 */
	public static final String RECEIVE_URL_WITHDRAW_NOTIFY_BORROWER = LOCAL_SERVER_URL + rb.getString("receive_url_withdraw_notify_borrower");

	/** 查询，资金变动明细 */
	public static final String REQUEST_URL_SEEK_FUND_CHANGE_DETAIL = BANK_URL
			+ rb.getString("request_url_seek_fund_change_detail");
	/** 查询，资金余额 */
	public static final String REQUEST_URL_SEEK_FUND_BALANCE = BANK_URL + rb.getString("request_url_seek_fund_balance");
	/** 查询，还款明细 */
	public static final String REQUEST_URL_SEEK_REPAY_DETAIL = BANK_URL + rb.getString("request_url_seek_repay_detail");
	/** 查询，投标明细 */
	public static final String REQUEST_URL_SEEK_BIDDING_BID_DETAIL = BANK_URL
			+ rb.getString("request_url_seek_bidding_bid_detail");
	
	
	/** 查询，标的信息 */
	public static final String REQUEST_URL_SEEK_BIDDING_INFO = BANK_URL + rb.getString("request_url_seek_bidding_info");
	/** 查询，账户余额明细 */
	public static final String REQUEST_URL_SEEK_ACCOUNT_BALANCE_DETAIL = BANK_URL
			+ rb.getString("request_url_seek_account_balance_detail");

	/** 查询，标标的余额明细 */
	public static final String REQUEST_URL_SEEK_BIDDING_BALANCE = BANK_URL
			+ rb.getString("request_url_seek_bidding_balance");
	/** 查询，订单状态 */
	public static final String REQUEST_URL_SEEK_ORDER_STATUS = BANK_URL + rb.getString("request_url_seek_order_status");
	/** 查询，充值订单状态*/
	public static final String REQUEST_URL_SEEK_RECHARGE_ORDER_STATUS = BANK_URL
			+ rb.getString("request_url_seek_recharge_order_status");

	/** 平台，冻结 */
	public static final String REQUEST_URL_PLATFORM_FUND_FREEZE = BANK_URL
			+ rb.getString("request_url_platform_fund_freeze_and_unlock");
	/** 平台，解冻 */
	public static final String REQUEST_URL_PLATFORM_FUND_UNLOCK = BANK_URL
			+ rb.getString("request_url_platform_fund_freeze_and_unlock");
	/** 平台，平台转个人 */
	public static final String REQUEST_URL_PLATFORM_TRANSFER = BANK_URL + rb.getString("request_url_platform_transfer");
	/** 平台，平台子账号互转 */
	public static final String REQUEST_URL_PLATFORM_CONVERSE = BANK_URL + rb.getString("request_url_platform_converse");
	/** 平台，平台提现 */
	public static final String REQUEST_URL_PLATFORM_RECHARGE = BANK_URL + rb.getString("request_url_platform_recharge");
	/** 平台，平台提现通知 */
	public static final String RECEIVE_URL_PLATFORM_RECHARGE = LOCAL_SERVER_URL + rb.getString("receive_url_platform_recharge");
	/** 平台，平台充值 */
	public static final String REQUEST_URL_PLATFORM_WITHDRAW = BANK_URL + rb.getString("request_url_platform_withdraw");
	/** 平台，平台充值通知 */
	public static final String RECEIVE_URL_PLATFORM_WITHDRAW = LOCAL_SERVER_URL + rb.getString("receive_url_platform_withdraw");
	
	/**平台，平台银行账户余额查询 */
	//public static final String REQUEST_URL_PLATFORM_BALANCE_DETAIL = BANK_URL + rb.getString("request_url_platform_balance_detail");
	/**垫付人账号*/
	public static final String ADVANCE_ACCOUNT = rb.getString("advance_account");
	/** 4.8.2 */
	//public static final String REQUEST_URL_ACCOUNTCHECK_BALANCE_INFO = BANK_URL + rb.getString("request_url_accountcheck_balance_info");
}
