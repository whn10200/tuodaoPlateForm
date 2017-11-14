package com.tuodao.bp.depository.service;

import java.util.HashMap;

public interface BiddingService extends BaseService {

	public HashMap<String, String> publish(HashMap<String, String> map);

	public HashMap<String, String> complate(HashMap<String, String> map) ;

	public HashMap<String, String> cancel(HashMap<String, String> map);

	public HashMap<String, String> bid(HashMap<String, String> map);

	public HashMap<String, String> chargeOff(HashMap<String, String> map);

	public HashMap<String, String> chargeOffReceive(HashMap<String, String> map);

	public HashMap<String, String> transferDebt(HashMap<String, String> map);

	public HashMap<String, String> repay(HashMap<String, String> map);

	public HashMap<String, String> repayPlaneUpdate(HashMap<String, String> map);

	public HashMap<String, String> chargeOffChange(HashMap<String, String> map);

}
