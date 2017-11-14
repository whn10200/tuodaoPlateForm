package com.tuodao.bp.depository.service;

import java.util.HashMap;

public interface RechargeService extends BaseService {

	public HashMap<String, String> gatewayRecharge(HashMap<String, String> map);

	public HashMap<String, String> rechargeReceive(HashMap<String, String> map);

	public HashMap<String, String> quickRechargeApply(HashMap<String, String> map);

	public HashMap<String, String> quickRechargeConfirm(HashMap<String, String> map);

	public HashMap<String, String> borrowCutRepay(HashMap<String, String> map);

}
