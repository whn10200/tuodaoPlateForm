package com.tuodao.bp.model.constant.depository;

/** 参数字段名称 field name */
public class BJFN {

	/** 账户编号 */
	public static final String account = "account";
	/** 卡类型(1-个人 2-企业) */
	public static final String accountType = "account_type";
	/**
	 * 平台收入账户(平台子账户：1-自有子账户；3手续费子账户；4-体验金子账户；5-抵用金子账户；6-加息金子账户；7-保证金子账户)
	 * ；默认自有子账户。
	 */
	public static final String accoutType = "accout_type";
	/** 账户名称，对公客户必填 */
	public static final String acctName = "acct_name";
	/** 账号，对公客户必填 */
	public static final String acctNo = "acct_no";
	/**
	 * 平台（1-自有子账户；3手续费子账户；4-体验金子账户；5-抵用金子账户；6-加息金子账户；7-保证金子账户， 11--在途垫付） 个人（投资账户
	 * 12、融资 13）
	 */
	public static final String acctType = "acct_type";
	/** 交易金额 */
	public static final String amount = "amount";
	/** 转账金额(1.00) */
	public static final String amt = "amt";
	/** 资金变动类型 */
	public static final String amtType = "amt_type";
	/** 余额信息 */
	public static final String balance = "balance";
	/** 银行编号（对公出账必填） */
	public static final String bankId = "bank_id";
	/** 社会信用代码证（企业客户，营业执照编号、社会信用代码证要二选一） */
	public static final String bankLicense = "bank_license";
	/** 银行卡 */
	public static final String bankNo = "bank_no";
	/** 银行编码 */
	public static final String bankcode = "bankcode";
	/** 开始时间(预对账必输：yyyyMMddHHmmss) */
	public static final String beginTime = "begin_time";
	/** 出生日期 */
	public static final String birthday = "birthday";
	/** 开户支行名称 */
	public static final String brabankName = "brabank_name";
	/** 北京银行或其他银行标识，1：北京银行，2:其他银行（暂不使用） */
	public static final String branchFlag = "branch_flag";
	/** 营业执照编号（企业客户，营业执照编号、社会信用代码证要二选一） */
	public static final String businessLicense = "business_license";
	/** 北京限制购买人数 */
	public static final String buyerNumLimit = "buyer_num_limit";
	/** 卡号，个人客户必填 */
	public static final String cardNo = "card_no";
	/** 原卡号 */
	public static final String cardNoOld = "card_no_old";
	/** 卡类型(1:借记卡) */
	public static final String cardType = "card_type";
	/** 原卡类型 */
	public static final String cardTypeOld = "card_type_old";
	/** 原因 */
	public static final String cause = "cause";
	/** 原因类型（平台需提前报备，存管配置后返回类型） */
	public static final String causeType = "cause_type";
	/** 资金出账明细（一个标的对应一个融资人） */
	public static final String chargeOffList = "charge_off_list";
	/** 1-投资账户 2-融资账户 必填项 */
	public static final String chargeType = "charge_type";
	/** 出账标示：0、成标后自动出账至借款人融资账户1、调用成标出账接口，出账至标的对应收款账户 */
	public static final String chargeoffAuto = "chargeOff_auto";
	/** 城市编码（富友必填） */
	public static final String cityCode = "city_code";
	/** 客户姓名，连连wap必填 */
	public static final String clientName = "client_name";
	/** 公私标示，0-个人 1-公司； 默认个人 */
	public static final String clientProperty = "client_property";
	/** 1-入账成功 2-入账失败 */
	public static final String code = "code";
	/** 出让人手续费说明 */
	public static final String commission = "commission";
	/** 受让人手续费说明 */
	public static final String commissionExt = "commission_ext";
	/** 代偿（委托）账户列表 */
	public static final String compensation = "compensation";
	/** 代偿人平台客户编号 */
	public static final String compensationPlatcust = "compensation_platcust";
	/** 抵用劵金额 */
	public static final String couponAmt = "coupon_amt";
	/** 成立方式0满额成立 1成立日成立 2活期方式 */
	public static final String createType = "create_type";
	/** 币种（默认CNY） */
	public static final String currencyCode = "currency_code";
	/** 客户类型（1:个人客户，2:企业客户，不传参数则默认为”个人客户“） */
	public static final String cusType = "cus_type";
	/** 融资人的平台客户编号 */
	public static final String custNo = "cust_no";
	/***/
	public static final String custrepaylist = "custRepayList";
	/** 周期，例如：3个月的标的传 3 */
	public static final String cycle = "cycle";
	/** 周期单位 1日 2周 3月 4季 5年 */
	public static final String cycleUnit = "cycle_unit";
	/** JsonArray，批量明细数据 */
	public static final String data = "data";
	/** 自费价格 */
	public static final String dealAmount = "deal_amount";
	/** 成交账号（受让人平台客户编号） */
	public static final String dealPlatcust = "deal_platcust";
	/** 目标账户(平台子账户：1-自有子账户；3手续费子账户；4-体验金子账户；5-抵用金子账户；6-加息金子账户；7-保证金子账户；9-奖励金；10-现金垫付；11-在途垫付) */
	public static final String destAccount = "dest_account";
	/** 明细订单号 */
	public static final String detailNo = "detail_no";
	/** 电子邮箱 */
	public static final String email = "email";
	/** 查询结束时间(YYYY-MM-DD HH:mm:ss) */
	public static final String endDate = "end_date";
	/** 结束时间(预对账必输：yyyyMMddHHmmss) */
	public static final String endTime = "end_time";
	/** JsonArray失败信息（有处理失败的数据时，则为必填参数；其中error_no、error_info为必填信息） */
	public static final String errorData = "error_data";
	/** 错误描述 */
	public static final String errorInfo = "error_info";
	/** 失败响应码 */
	public static final String errorNo = "error_no";
	/** 体验金金额 */
	public static final String experienceAmt = "experience_amt";
	/** 到期日同上(YYYY-MM-DD HH:mm:ss) */
	public static final String expireDate = "expire_date";
	/** 手续费金额 */
	public static final String feeAmt = "fee_amt";
	/** 手续费（eg：0.01） */
	public static final String feeInt = "fee_int";
	/** 手续费类型 */
	public static final String feeType = "fee_type";
	/** 融资金额[N(19,2)] */
	public static final String financAmt = "financ_amt";
	/** 融资利息（eg：0.12） */
	public static final String financInt = "financ_int";
	/** 借款用途[北京限制100字符以内] */
	public static final String financPurpose = "financ_purpose";
	/** 融资信息列表,仅允许一个融资人 */
	public static final String financingInfoList = "financing_info_list";
	/** 处理完成时间(批量订单受理成功时，则必填) */
	public static final String finishDatetime = "finish_datetime";
	/** 成标、废标标记 2 成标 3废标 */
	public static final String flag = "flag";
	/** 冻结解冻标示：01、冻结，02、解冻 */
	public static final String freezeFlg = "freeze_flg";
	/** 冻结平台流水号，如果是解冻操作则必填 */
	public static final String freezeOrderNo = "freeze_order_no";
	/** 针对个人（现金01、在途02） */
	public static final String fundType = "fund_type";
	/** 成标时候需要进行的分佣说明 */
	public static final String funddata = "funddata";
	/** 支付通道流水号 */
	public static final String hostReqSerialNo = "host_req_serial_no";
	/** 流水编号 */
	public static final String id = "id";
	/** 证件号码 */
	public static final String idCode = "id_code";
	/** 证件类型（0-身份证） 连连wap必填 */
	public static final String idKind = "id_kind";
	/** 证件号码，连连wap必填 */
	public static final String idNo = "id_no";
	/** 证件类型（1：身份证） */
	public static final String idType = "id_type";
	/** 短信验证码 */
	public static final String identifyingCode = "identifying_code";
	/** 加息 eg:1=" 传 0.01 */
	public static final String inInterest = "in_interest";
	/** 收益出资方账户 */
	public static final String incomeAcct = "income_acct";
	/** 是否垫资:(1-不垫资， 2-垫资)；默认不垫付。 */
	public static final String isAdvance = "is_advance ";
	/** 是否整个标的还清：0-是，1-否； */
	public static final String isPayoff = "is_payoff";
	/** 年利率 */
	public static final String istYear = "ist_year";
	/** 0:未完成，1:完成 */
	public static final String liquidationFlag = "liquidation_flag";
	/** 手机号码 */
	public static final String mobile = "mobile";
	/** 用户姓名 */
	public static final String name = "name";
	/** 暂停或启动标识：1:暂停，2：启动 */
	public static final String noticeType = "notice_type";
	/** 异步通知地址，企业客户必填 */
	public static final String notifyUrl = "notify_url";
	/** 发生金额 */
	public static final String occurBalance = "occur_balance";
	/** 原交易订单号 */
	public static final String oldOrderNo = "old_order_no";
	/** 开户行 */
	public static final String openBranch = "open_branch";
	/** 订单金额 */
	public static final String orderAmt = "order_amt";
	/** 订单处理信息(批量订单受理成功时，则必填) */
	public static final String orderInfo = "order_info";
	/** 订单号 */
	public static final String orderNo = "order_no";
	/** 订单状态0:已接受, 1:处理中,2:处理成功,3:处理失败 */
	public static final String orderStatus = "order_status";
	/** 企业名称（企业客户必填） */
	public static final String orgName = "org_name";
	/** 组织机构代码，对公客户必填 */
	public static final String orgNo = "org_no";
	/** 原绑卡申请订单号 */
	public static final String originOrderNo = "origin_order_no";
	/** 原充值订单号 */
	public static final String originalSerialNo = "original_serial_no";
	/** 出账金额 */
	public static final String outAmt = "out_amt";
	/** 超额北京限制总额度，北京限制该标的允许超额投标的总额度 */
	public static final String overTotalLimit = "over_total_limit";
	/** 超额北京限制 0-不北京限制 1-北京限制 */
	public static final String overlimit = "overLimit";
	/** 页码：从1开始 */
	public static final String pagenum = "pagenum";
	/** 分页大小 */
	public static final String pagesize = "pagesize  ";
	/** 商户交易日期 */
	public static final String partnerTransDate = "partner_trans_date";
	/** 商户交易时间 */
	public static final String partnerTransTime = "partner_trans_time";
	/** 商户用户编号，连连必填 */
	public static final String partnerUserid = "partner_userid";
	/** 支付金额 */
	public static final String payAmt = "pay_amt";
	/** 支付通道 */
	public static final String payCode = "pay_code";
	/** 支付完成日期 */
	public static final String payFinishDate = "pay_finish_date";
	/** 支付完成时间 */
	public static final String payFinishTime = "pay_finish_time";
	/** 支付订单号 */
	public static final String payOrderNo = "pay_order_no";
	/** 对账日期(yyyyMMdd) */
	public static final String paycheckDate = "paycheck_date";
	/** 产品收款人户名 */
	public static final String payeeName = "payee_name";
	/** 手续费固定金额 */
	public static final String payoutAmt = "payout_amt";
	/** 分配平台类型(01-现金 11-在途) */
	public static final String payoutPlatType = "payout_plat_type";
	/** 平台账户(01 为平台账户，从平台自有资金转账) */
	public static final String platAccount = "plat_account";
	/** 平台名称 */
	public static final String platName = "plat_name";
	/** 平台编号 */
	public static final String platNo = "plat_no";
	/**
	 * 收费类型(01-现金 11-在途) ；01：表示扣除用户现金账户，转账至平台手续费子账户；11：表示扣除用户在途账户，转账至平台在途垫付子账户
	 */
	public static final String platType = "plat_type";
	/** 交易客户号 */
	public static final String platCust = "platcust";
	/** 平台客户入账列表 */
	public static final String platcustlist = "platcustList";
	/** 融资人平台客户编号 */
	public static final String platucst = "platucst";
	/** 预留手机号 */
	public static final String preMobile = "pre_mobile";
	/** 是否预对账0-不是，1-是 */
	public static final String precheckFlag = "precheck_flag";
	/** 系统处理日期(yyyyMMddHHmmss)) */
	public static final String processDate = "process_date";
	/** 标的账户 */
	public static final String prodAccount = "prod_account";
	/** 标的编号 */
	public static final String prodId = "prod_id";
	/** 产品介绍 */
	public static final String prodInfo = "prod_info";
	/** 产品名称 */
	public static final String prodName = "prod_name";
	/** 标的状态 */
	public static final String prodState = "prod_state";
	/** 产品类型（0周期性产品、1不定期出账产品） */
	public static final String prodType = "prod_type";
	/** 发布时间(YYYY-MM-DD HH:mm:ss) */
	public static final String publishDate = "publish_date";
	/** 平台流水号 */
	public static final String queryId = "query_id";
	/** 查询的订单编号 */
	public static final String queryOrderNo = "query_order_no";
	/** 加息金 */
	public static final String ratesAmt = "rates_amt";
	/** 实际还款本金 */
	public static final String realRepayAmount = "real_repay_amount";
	/** 实际还款金额 */
	public static final String realRepayAmt = "real_repay_amt";
	/** 实际还款日期(yyyyMMdd) */
	public static final String realRepayDate = "real_repay_date";
	/** 实际还款利息repay_fee 手续费 */
	public static final String realRepayVal = "real_repay_val";
	/** 返回码，10000为整个批量订单受理成功，具体单条数据的处理结果，要查看成功信息或失败信息 */
	public static final String recode = "recode";
	/** 申请日期（YYYY-MM-DD） */
	public static final String regDate = "reg_date";
	/** 申请时间（HH:mm:ss） */
	public static final String regTime = "reg_time";
	/** 涉及的标的编号，不同标的之间用逗号分隔(eg:P0001,P0002) */
	public static final String relatedProdIds = "related_prod_ids";
	/** 剩余份额 */
	public static final String remainLimit = "remain_limit";
	/** 备注 */
	public static final String remark = "remark";
	/** 返回结果描述 */
	public static final String remsg = "remsg";
	/** 计划还款金额 */
	public static final String repayAmt = "repay_amt";
	/** 计划还款日期(yyyyMMdd) */
	public static final String repayDate = "repay_date";
	/** 手续费 */
	public static final String repayFee = "repay_fee";
	/** 本期已还清：0-是，1-否 */
	public static final String repayFlag = "repay_flag";
	/** 还款期数，如果一次性还款，repay_num为1 */
	public static final String repayNum = "repay_num";
	/** 还款计划表，标的成立时必填，该计划为借款人还款计划 */
	public static final String repayPlanList = "repay_plan_list";
	/** 还款类型 0-代偿还款 1-委托还款 */
	public static final String repayType = "repay_type";
	/** 错误信息 */
	public static final String respmsg = "respMsg";
	/** 处理结果 */
	public static final String returnMsg = "return_msg";
	/** 同步回调地址 */
	public static final String returnUrl = "return_url";
	/** 风险等级 */
	public static final String riskLvl = "risk_lvl";
	/** 已卖份额 */
	public static final String saledLimit = "saled_limit";
	/** 自费金额 */
	public static final String selfAmt = "self_amt";
	/** 产品发行日(YYYY-MM-DD HH:mm:ss) */
	public static final String sellDate = "sell_date";
	/** 性别（0:男性，1:女性） */
	public static final String sex = "sex";
	/** 签名数据 */
	public static final String signdata = "signdata";
	/** 来源账户(平台子账户：1-自有子账户；3手续费子账户；4-体验金子账户；5-抵用金子账户；6-加息金子账户；7-加息金子账户；9-奖励金；10-现金垫付；11-在途垫付) */
	public static final String sourceAccount = "source_account";
	/** 查询起始时间(YYYY-MM-DD HH:mm:ss) */
	public static final String startDate = "start_date";
	/** 状态 */
	public static final String status = "status";
	/** 科目优先级0-可提优先1可投优先 */
	public static final String subjectPriority = "subject_priority";
	/** 成功信息（有处理成功的数据时，则为必填参数；其中detail_no、mobile、platcust为必填信息） */
	public static final String successData = "success_data";
	/** 成功数量(批量订单受理成功时，则必填) */
	public static final String successNum = "success_num";
	/** 令牌号（待补充） */
	public static final String tokenid = "tokenid";
	/** 总份额 */
	public static final String totalLimit = "total_limit";
	/** 总数量 */
	public static final String totalNum = "total_num";
	/** 交易金额 */
	public static final String transAmt = "trans_amt";
	/** 成交时间(YYYY-MM-DD HH:mm:ss) */
	public static final String transDate = "trans_date";
	/** 交易名称(提现、标的出账、债权转让、标的成立、标的废弃、标的出账、标的还款、借款人还款、充值、投融资转换、转账、平台不同账户转换) */
	public static final String transName = "trans_name";
	/** 转让份额 */
	public static final String transShare = "trans_share";
	/** 交易时间 */
	public static final String transTime = "trans_time";
	/** JsonArray多个受让人 */
	public static final String transferData = "transfer_data ";
	/** 明细编号 */
	public static final String transferDetailNo = "transfer_detail_no";
	/** 转让收益 */
	public static final String transferIncome = "transfer_income";
	/** 交易日期 */
	public static final String trasnDate = "trasn_date";
	/** 0-代偿还款 1-委托还款 */
	public static final String type = "type";
	/** 用款日期（YYYY-MM-DD） */
	public static final String useDate = "use_date";
	/** 用户IP地址，连连必填 */
	public static final String userInfoDtIp = "user_info_dt_ip";
	/** 用户注册时间，连连必填 */
	public static final String userInfoDtRegister = "user_info_dt_register";
	/** 实名认证状态（0-认证 1-未认证），连连必填 */
	public static final String userInfoIdentifyState = "user_info_identify_state";
	/** 实名认证类型（1 银行卡认证,2 现场认证,3 身份证远程认证,4 其他认证），连连必填 */
	public static final String userInfoIdentifyType = "user_info_identify_type";
	/** 起息日如起息方式为成立起息，为必选项(YYYY-MM-DD HH:mm:ss) */
	public static final String valueDate = "value_date";
	/** 产品起息方式 0满额起息1成立起息2投标起息 3 成立审核后起息 */
	public static final String valueType = "value_type";
	/** 交易份额 */
	public static final String vol = "vol";
	/** 产品收款人银行卡号 */
	public static final String withdrawAccount = "withdraw_account";
	/** 提现的账户类型：0投资账户1融资账户 */
	public static final String withdrawType = "withdraw_type";

	public static final String success = "success";
	public static final String error = "error";
	public static final String requestUrl = "request_url";
	
	

}
