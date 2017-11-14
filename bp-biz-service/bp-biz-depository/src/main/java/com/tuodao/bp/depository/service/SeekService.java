package com.tuodao.bp.depository.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface SeekService extends BaseService {

	ArrayList<HashMap<String, String>> fundChangeDetail(HashMap<String, String> map);

	/** 4.6.2资金余额 */
	HashMap<String, String> fundBalance(HashMap<String, String> map);

	ArrayList<HashMap<String, String>> repayDetail(HashMap<String, String> map);

	ArrayList<HashMap<String, String>> biddingBidDetail(HashMap<String, String> map);

	HashMap<String, String> biddingInfo(HashMap<String, String> map);

	/** 4.6.6账户余额明细查询 */
	HashMap<String, String> accountBalanceDetail(HashMap<String, String> map);

	HashMap<String, String> orderStatus(HashMap<String, String> map);

	HashMap<String, String> biddingBalance(HashMap<String, String> map);

	HashMap<String, String> platBalanceDetail(HashMap<String, String> map);

	HashMap<String, String> rechargeOrderStatus(HashMap<String, String> map);
}
