package com.tuodao.bp.activemq.constant;

public class DepositoryMqConstant {
	
	public static final String DEPOSITORY_HEAD_KEY = "depository_head_key";

	/** 订单状态查询，入队列 */
	public static final String DE_IN_ORDER_STATUS = "de_in_order_status";
	/** 订单状态查询 ，出队列*/
	public static final String DE_OUT_ORDER_STATUS = "de_out_order_status";

	/** 四要素注册，入队列 */
	public static final String DE_IN_USER_REGIST_4_ELE = "de_in_user_regist_4_ele";
	/** 四要素注册，出队列 */
	public static final String DE_OUT_USER_REGIST_4_ELE = "de_out_user_regist_4_ele";
	/**选择器数值*/
	public static final String DE_SELECTOR_VALUE_USER_REGIST_4_ELE = "de_op_user_regist_4_ele";
	/**选择器*/
	public static final String DE_SELECTOR_USER_REGIST_4_ELE = DEPOSITORY_HEAD_KEY + "='" + DE_SELECTOR_VALUE_USER_REGIST_4_ELE+ "'";
	
	/**实名注册，入队列 */
	public static final String DE_IN_USER_REGIST_REAL_NAME = "de_in_user_regist_real_name";
	/**实名注册，出队列*/
	public static final String DE_OUT_USER_REGIST_REAL_NAME = "de_out_user_regist_real_name";
	/**绑卡，入队列*/
	public static final String DE_IN_USER_BOUND_CARD = "de_in_user_bound_card";
	/**绑卡，出队列 */
	public static final String DE_OUT_USER_BOUND_CARD = "de_out_user_bound_card";
	/**换卡，入队列*/
	public static final String DE_IN_USER_CHANGE_CARD = "de_in_user_change_card";
	/**换卡，出队列 */
	public static final String DE_OUT_USER_CHANGE_CARD = "de_out_user_change_card";
	/**短验绑卡（可包含开户）申请，入队列*/
	public static final String DE_IN_USER_MESSAGE_BOUND_CARD_APPLY = "de_in_user_message_bound_card_apply";
	/**短验绑卡（可包含开户）申请，出队列 */
	public static final String DE_OUT_USER_MESSAGE_BOUND_CARD_APPLY = "de_out_user_message_bound_card_apply";
	/**短验绑卡（可包含开户）确认，入队列*/
	public static final String DE_IN_USER_MESSAGE_BOUND_CARD_VALIDATE = "de_in_user_message_bound_card_validate";
	/**短验绑卡（可包含开户）确认，出队列 */
	public static final String DE_OUT_USER_MESSAGE_BOUND_CARD_VALIDATE = "de_out_user_message_bound_card_validate";
	 /**客户信息变更，入队列*/
	public static final String DE_IN_USER_UPDATE_INFO = "de_in_user_update_info";
	 /**客户信息变更，出队列 */
	public static final String DE_OUT_USER_UPDATE_INFO = "de_out_user_update_info";

	/**借款人，还款，入队列*/
	public static final String DE_IN_BORROWER_BATCH_PAY_BACK = ("de_in_borrower_batch_pay_bak");
	/**借款人，还款，出队列 */
	public static final String DE_OUT_BORROWER_BATCH_PAY_BACK = ("de_out_borrower_batch_pay_bak");
	 /**借款人，标的代偿（委托）还款，入队列*/
	public static final String DE_IN_BORROWER_BIDDING_COMPENSATION = ("de_in_borrower_bidding_compensation");
	/**借款人，标的代偿（委托）还款，出队列 */
	public static final String DE_OUT_BORROWER_BIDDING_COMPENSATION = ("de_out_borrower_bidding_compensation");
	 /**借款人，借款人还款代偿（委托），入队列*/
	public static final String DE_IN_BORROWER_BORROWER_COMPENSATION = ("de_in_borrower_borrower_compensation");
	/**借款人，借款人还款代偿（委托），出队列 */
	public static final String DE_OUT_BORROWER_BORROWER_COMPENSATION = ("de_out_borrower_borrower_compensation");

	/**标的，发标，入队列*/
	public static final String DE_IN_BIDDING_PUBLISH = "de_in_bidding_publish";
	/**标的，发标，出队列 */
	public static final String DE_OUT_BIDDING_PUBLISH = "de_out_bidding_publish";
	/**标的，成标，入队列*/
	public static final String DE_IN_BIDDING_COMPLATE = "de_in_bidding_complate";
	/**标的，成标，出队列 */
	public static final String DE_OUT_BIDDING_COMPLATE = "de_out_bidding_complate";
	/**标的，成标，入队列*/
	public static final String DE_IN_BIDDING_CANCEL = "de_in_bidding_cancel";
	/**标的，成标，出队列 */
	public static final String DE_OUT_BIDDING_CANCEL = "de_out_bidding_cancel";
	/**标的，投标，入队列*/
	public static final String DE_IN_BIDDING_BID = "de_in_bidding_bid";
	/**标的，投标，出队列 */
	public static final String DE_OUT_BIDDING_BID = "de_out_bidding_bid";	
	/**标的，投标，选择器， 值     */
    public static final String TENDER_IN_VALUE = "tender_in_value";
    /**标的，投标，选择器*/
    public static final String TENDER_IN_VALUE_SELECTOR = DEPOSITORY_HEAD_KEY + "='" + TENDER_IN_VALUE+ "'";
	
	
	/**标的，成标出账，入队列*/
	public static final String DE_IN_BIDDING_CHARGE_OFF = "de_in_bidding_charge_off";
	/**标的，成标出账，出队列 */
	public static final String DE_OUT_BIDDING_CHARGE_OFF = "de_out_bidding_charge_off";
	
	/**标的，标的转让，入队列*/
	public static final String DE_IN_BIDDING_TRANSFER_DEBT = "de_in_bidding_transfer_debt";
	/**标的，标的转让，出队列 */
	public static final String DE_OUT_BIDDING_TRANSFER_DEBT = "de_out_bidding_transfer_debt";
	/**标的，标的转让，普通债权转让，选择器数值 */
	public static final String DE_IN_BIDDING_TRANSFER_DEBT_TRANSFER_VALUE = "transfer";
	/**标的，标的转让，普通债权转让，选择器 */
	public static final String DE_IN_BIDDING_TRANSFER_DEBT_TRANSFER_SELECTOR = DEPOSITORY_HEAD_KEY + "='" + DE_IN_BIDDING_TRANSFER_DEBT_TRANSFER_VALUE+ "'";
	/**标的，标的转让，精选计划，选择器数值 */
	public static final String DE_IN_BIDDING_TRANSFER_PLAN_TRANSFER_VALUE = "plan";
	/**标的，标的转让，精选计划，选择器 */
	public static final String DE_IN_BIDDING_TRANSFER_PLAN_TRANSFER_SELECTOR = DEPOSITORY_HEAD_KEY + "='" + DE_IN_BIDDING_TRANSFER_PLAN_TRANSFER_VALUE+ "'";

	/**标的，批量标的转让，入队列*/
	public static final String DE_IN_BIDDING_BATCH_TRANSFER_DEBT = "de_in_bidding_batch_transfer_debt";
	/**标的，批量标的转让，出队列 */
	public static final String DE_OUT_BIDDING_BATCH_TRANSFER_DEBT = "de_out_bidding_batch_transfer_debt";

	/**标的，还款，入队列*/
	public static final String DE_IN_BIDDING_REPAY = "de_in_bidding_repay";
	/**标的，还款，出队列 */
	public static final String DE_OUT_BIDDING_REPAY = "de_out_bidding_repay";
	/**标的，还款计划更新，入队列*/
	public static final String DE_IN_BIDDING_REPAY_PLANE_UPDATE = "de_in_bidding_repay_plane_update";
	/**标的，还款计划更新，出队列 */
	public static final String DE_OUT_BIDDING_REPAY_PLANE_UPDATE = "de_out_bidding_repay_plane_update";
	/**标的，出账信息修改，入队列*/
	public static final String DE_IN_BIDDING_CHARGE_OFF_CHANGE = "de_in_bidding_charge_off_change";
	/**标的，出账信息修改，出队列 */
	public static final String DE_OUT_BIDDING_CHARGE_OFF_CHANGE = "de_out_bidding_charge_off_change";
	

	/**网关充值，入队列*/
	public static final String DE_IN_RECHARGE_GATEWAY = "de_in_recharge_gateway";
	/**网关充值，出队列 */
	public static final String DE_OUT_RECHARGE_GATEWAY = "de_out_recharge_gateway";
	public static final String DE_IN_RECHARGE_GATEWAY_VALUE = "recharge_gateway";
	public static final String DE_IN_RECHARGE_GATEWAY_SELECTOR = DEPOSITORY_HEAD_KEY + "='" + DE_IN_RECHARGE_GATEWAY_VALUE+ "'";

    /**
     * 提现请求银行
     */
    public static final String DE_IN_CASH_APPLY_VALUE = "de_in_cash_apply_value";

    /**
     * 提现请求银行 选择器
     */
    public static final String DE_IN_CASH_APPLY_VALUE_SELECTOR = DEPOSITORY_HEAD_KEY + "='" + DE_IN_CASH_APPLY_VALUE+ "'";

	/*public static final String DE_IN_RECHARGE_QUICK = "de_in_recharge_quick";
	public static final String DE_OUT_RECHARGE_QUICK = "de_out_recharge_quick";
	public static final String DE_IN_RECHARGE_QUICK_SELECTOR = DEPOSITORY_HEAD_KEY + "='quick'";*/
	
	/**快捷充值申请，入队列*/
	public static final String DE_IN_RECHARGE_QUICK_APPLY = "de_in_recharge_quick_apply";
	/**快捷充值申请，出队列 */
	public static final String DE_OUT_RECHARGE_QUICK_APPLY = "de_out_recharge_quick_apply";
	public static final String DE_IN_RECHARGE_QUICK_APPLY_VALUE = "quick_apply";
	public static final String DE_IN_RECHARGE_QUICK_APPLY_SELECTOR = DEPOSITORY_HEAD_KEY + "='" + DE_IN_RECHARGE_QUICK_APPLY_VALUE+ "'";
	
	/**快捷充值确认，入队列*/
	public static final String DE_IN_RECHARGE_QUICK_CONFIRM = "de_in_recharge_quick_confirm";
	/**快捷充值确认，出队列 */
	public static final String DE_OUT_RECHARGE_QUICK_CONFIRM = "de_out_recharge_quick_confirm";
	public static final String DE_IN_RECHARGE_QUICK_CONFIRM_VALUE = "quick_confirm";
	public static final String DE_IN_RECHARGE_QUICK_CONFIRM_SELECTOR = DEPOSITORY_HEAD_KEY + "='" + DE_IN_RECHARGE_QUICK_CONFIRM_VALUE+ "'";
	
	
	 /**借款人线下还款，入队列*/
	public static final String DE_IN_BORROW_CUT_REPAY = "de_in_borrow_cut_repay";
	 /**借款人线下还款，出队列 */
	public static final String DE_OUT_BORROW_CUT_REPAY = "de_out_borrow_cut_repay";

	/** 4.5.1 */
	// public static final String DE_IN_WITHDRAW_APPLY = "de_in_withdraw_apply";
	/** 4.5.1 */
	// public static final String DE_OUT_WITHDRAW_APPLY =
	// "de_out_withdraw_apply";
	/**投资人提现申请，入队列*/
	public static final String DE_IN_WITHDRAW_APPLY_INVESTOR = "de_in_withdraw_apply_investor";
	/**投资人提现申请，出队列 */
	public static final String DE_OUT_WITHDRAW_APPLY_INVESTOR = "de_out_withdraw_apply_investor";
	/**借款人提现，入队列*/
	public static final String DE_IN_WITHDRAW_APPLY_BORROWER = "de_in_withdraw_apply_borrower";
	/** 4.5.1 */
	public static final String DE_OUT_WITHDRAW_APPLY_BORROWER = "de_out_withdraw_apply_borrower";
	/** 投资人提现通知，入队列 */
	public static final String DE_IN_WITHDRAW_NOTIFY_INVESTOR = "de_in_withdraw_notify_investor";
	/** 投资人提现通知，出队列  */
	public static final String DE_OUT_WITHDRAW_NOTIFY_INVESTOR = "DE_OUT_withdraw_notify_investor";
	/** 4.5.2 */
	public static final String DE_IN_WITHDRAW_NOTIFY_BORROWER = "de_in_withdraw_notify_borrower";
	/** 4.5.2 */
	public static final String DE_OUT_WITHDRAW_NOTIFY_BORROWER = "DE_OUT_withdraw_notify_borrower";

	/**资金变动明细查询，入队列*/
	public static final String DE_IN_SEEK_FUND_CHANGE_DETAIL = "de_in_seek_fund_change_detail";
	/**资金变动明细查询，出队列 */
	public static final String DE_OUT_SEEK_FUND_CHANGE_DETAIL = "de_out_seek_fund_change_detail";
	/** 资金余额查询，入队列 */
	public static final String DE_IN_SEEK_FUND_BALANCE = "de_in_seek_fund_balance";
	/** 资金余额查询 ，出队列 */
	public static final String DE_OUT_SEEK_FUND_BALANCE = "de_out_seek_fund_balance";
	/** 还款明细查询 ，入队列 */
	public static final String DE_IN_SEEK_REPAY_DETAIL = "de_in_seek_repay_detail";
	/** 还款明细查询，出队列  */
	public static final String DE_OUT_SEEK_REPAY_DETAIL = "de_out_seek_repay_detail";
	/** 标的投资明细查询 ，入队列 */
	public static final String DE_IN_SEEK_BIDDING_BID_DETAIL = "de_in_seek_bidding_bid_detail";
	/** 标的投资明细查询 ，出队列 */
	public static final String DE_OUT_SEEK_BIDDING_BID_DETAIL = "de_out_seek_bidding_bid_detail";
	/** 标的信息查询，入队列  */
	public static final String DE_IN_SEEK_BIDDING_INFO = "de_in_seek_bidding_info";
	/** 标的信息查询，出队列  */
	public static final String DE_OUT_SEEK_BIDDING_INFO = "de_out_seek_bidding_info";
	/** 账户余额明细查询，入队列  */
	public static final String DE_IN_SEEK_ACCOUNT_BALANCE_DETAIL = "de_in_seek_account_balance_detail";
	/** 账户余额明细查询，出队列  */
	public static final String DE_OUT_SEEK_ACCOUNT_BALANCE_DETAIL = "de_out_seek_account_balance_detail";
	/** 4.6.6 */
	public static final String DE_IN_SEEK_PLAT_BALANCE_DETAIL = "de_in_seek_plat_balance_detail";
	/** 4.6.6 */
	public static final String DE_OUT_SEEK_PLAT_BALANCE_DETAIL = "de_out_seek_plat_balance_detail";
	/** 标的账户余额查询，入队列  */
	public static final String DE_IN_SEEK_BIDDING_BALANCE = "de_in_seek_bidding_balance";
	/** 标的账户余额查询 ，出队列 */
	public static final String DE_OUT_SEEK_BIDDING_BALANCE = "de_out_seek_bidding_balance";
	
	/** 充值订单状态查询，入队列  */
	public static final String DE_IN_SEEK_RECHARGE_ORDER_STATUS = "de_in_seek_recharge_order_status";
	/** 充值订单状态查询，出队列  */
	public static final String DE_OUT_SEEK_RECHARGE_ORDER_STATUS = "de_out_seek_recharge_order_status";

	/**资金冻结，入队列 */
	public static final String DE_IN_PLATFORM_FUND_FREEZE = "de_in_platform_fund_freeze";
	/**资金冻结，出队列 */
	public static final String DE_OUT_PLATFORM_FUND_FREEZE = "de_out_platform_fund_freeze";
	public static final String DE_IN_PLATFORM_FUND_FREEZE_TRANSFER_VALUE = "transfer";
	public static final String DE_IN_PLATFORM_FUND_FREEZE_TRANSFER_SELECTOR = DEPOSITORY_HEAD_KEY + "='" + DE_IN_PLATFORM_FUND_FREEZE_TRANSFER_VALUE+ "'";
	
	 /**资金解冻，入队列 */
	public static final String DE_IN_PLATFORM_FUND_UNBLOCK = "de_in_platform_fund_unblock";
	 /**资金解冻，出队列 */
	public static final String DE_OUT_PLATFORM_FUND_UNBLOCK = "de_out_platform_fund_unblock";
	
	public static final String DE_IN_PLATFORM_FUND_UNBLOCK_TRANSFER_VALUE = "transfer";
	public static final String DE_IN_PLATFORM_FUND_UNBLOCK_TRANSFER_REVOKED_VALUE = "transfer_revoked";
	public static final String DE_IN_PLATFORM_FUND_UNBLOCK_TRANSFER_SELECTOR = DEPOSITORY_HEAD_KEY + "='" + DE_IN_PLATFORM_FUND_UNBLOCK_TRANSFER_VALUE+ "'";
	public static final String DE_IN_PLATFORM_FUND_UNBLOCK_TRANSFER_REVOKED_SELECTOR = DEPOSITORY_HEAD_KEY + "='" + DE_IN_PLATFORM_FUND_UNBLOCK_TRANSFER_VALUE+ "'";

	
	
	 /**平台账户转个人账户，入队列 */
	public static final String DE_IN_PLATFORM_TRANSFER = "de_in_platform_transfer";
	 /**平台账户转个人账户，出队列 */
	public static final String DE_OUT_PLATFORM_TRANSFER = "de_out_platform_transfer";

	public static final String DE_IN_PLATFORM_TRANSFER_TRANSFER_VALUE = "transfer";
	public static final String DE_IN_PLATFORM_TRANSFER_TRANSFER_SELECTOR = DEPOSITORY_HEAD_KEY + "='" + DE_IN_PLATFORM_TRANSFER_TRANSFER_VALUE+ "'";

	//理财计划平账
	public static final String DE_IN_PLATFORM_TRANSFER_EXPIRED_VALUE = "expired";
	public static final String DE_IN_PLATFORM_TRANSFER_EXPIRED__SELECTOR = DEPOSITORY_HEAD_KEY + "='" + DE_IN_PLATFORM_TRANSFER_EXPIRED_VALUE+ "'";

	/**平台自有账户的不同子账户转账，入队列 */
	public static final String DE_IN_PLATFORM_CONVERSE = "de_in_platform_converse";
	/**平台自有账户的不同子账户转账，出队列 */
	public static final String DE_OUT_PLATFORM_CONVERSE = "de_out_platform_converse";
	/**平台充值，入队列 */
	public static final String DE_IN_PLATFORM_RECHARGE = "de_in_platform_recharge";
	/**平台充值，出队列 */
	public static final String DE_OUT_PLATFORM_RECHARGE = "de_out_platform_recharge";
	/**平台提现，入队列 */
	public static final String DE_IN_PLATFORM_WITHDRAW = "de_in_platform_withdraw";
	/**平台提现，出队列 */
	public static final String DE_OUT_PLATFORM_WITHDRAW = "de_out_platform_withdraw";
	
	/** 对账文件账户余额数据 ，入队列 */
	public static final String DE_IN_ACCOUNTCHECK_BALANCE_INFO = "de_in_accountcheck_balance_info";
	/** 对账文件账户余额数据，出队列  */
	public static final String DE_OUT_ACCOUNTCHECK_BALANCE_INFO = "de_out_accountcheck_balance_info";

}
