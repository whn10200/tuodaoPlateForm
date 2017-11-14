package com.tuodao.bp.depository.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuodao.bp.cache.basic.useraccount.UserAccountCache;
import com.tuodao.bp.depository.service.SeekService;
import com.tuodao.bp.depository.util.BandUtil;
import com.tuodao.bp.depository.util.MapTransKeyUtil;
import com.tuodao.bp.model.constant.depository.BBC;
import com.tuodao.bp.model.constant.depository.BBDD;
import com.tuodao.bp.model.constant.depository.BJFN;
import com.tuodao.bp.model.constant.depository.ONP;
import com.tuodao.bp.model.constant.depository.TDFN;
import com.tuodao.bp.model.constant.depository.key.PlatFormKey;
import com.tuodao.bp.model.constant.depository.key.SeekKey;
import com.tuodao.bp.utils.BDC;
import com.tuodao.bp.utils.JsonUtil;
@Service("seekService")
public class SeekServiceImpl extends BaseServiceImpl implements SeekService {

	@Autowired
	private UserAccountCache userAccountCache;

	@Override
	public ArrayList<HashMap<String, String>> fundChangeDetail(HashMap<String, String> map) {
		map = new HashMap<String, String>(map);
		map.put(TDFN.platNo, BBC.PLAT_NO);
		String orderNo = BandUtil.getNo(ONP.seekFundChangeDetail);
		map.put(TDFN.orderNo, orderNo);
		
		map = MapTransKeyUtil.TransTd2Bj(map);	
		HashMap<String, String> rm = super.requestAndInsertDbCommon(map.get(TDFN.orderNo), map,
				BBC.REQUEST_URL_SEEK_FUND_CHANGE_DETAIL, SeekKey.fundChangeDetailRequest);
		ArrayList<HashMap<String, String>> r = JsonUtil.json2List(rm.get(BJFN.data));
		ArrayList<HashMap<String, String>> rTd = JsonUtil.json2List(rm.get(BJFN.data));
		for (HashMap<String, String> m : r) {
			rTd.add(MapTransKeyUtil.TransBj2Td(m));
		}

		return rTd;
	}

	@Override
	public HashMap<String, String> fundBalance(HashMap<String, String> map) {
		map = new HashMap<String, String>(map);
		map.put(TDFN.platNo, BBC.PLAT_NO);
		String depositNo = userAccountCache.getUserAccoutInfo("").getDepositNo();
		//map.put(TDFN.platCust, depositNo);
		String orderNo = BandUtil.getNo(ONP.seekFundBalance);
		map.put(TDFN.orderNo, orderNo);

		map = MapTransKeyUtil.TransTd2Bj(map);
		HashMap<String, String> rm = super.requestAndInsertDbCommon(map.get(TDFN.orderNo), map,
				BBC.REQUEST_URL_SEEK_FUND_BALANCE, SeekKey.fundBalanceRequest);
		HashMap<String, String> rsub = JsonUtil.json2List(rm.get(BJFN.data)).get(0);
		rsub = MapTransKeyUtil.TransBj2Td(rsub);

		return rsub;
	}

	@Override
	public ArrayList<HashMap<String, String>> repayDetail(HashMap<String, String> map) {
		map = new HashMap<String, String>(map);
		map.put(TDFN.platNo, BBC.PLAT_NO);
		String depositNo = userAccountCache.getUserAccoutInfo("").getDepositNo();
		//map.put(TDFN.platCust, depositNo);
		String orderNo = BandUtil.getNo(ONP.seekRepayDetail);
		map.put(TDFN.orderNo, orderNo);
		
		
		map = MapTransKeyUtil.TransTd2Bj(map);
		HashMap<String, String> rm = super.requestAndInsertDbCommon(map.get(TDFN.orderNo), map,
				BBC.REQUEST_URL_SEEK_REPAY_DETAIL, SeekKey.repayDetailRequest);
		ArrayList<HashMap<String, String>> r = JsonUtil.json2List(rm.get(BJFN.data));
		ArrayList<HashMap<String, String>> rTd = JsonUtil.json2List(rm.get(BJFN.data));
		for (HashMap<String, String> m : r) {
			rTd.add(MapTransKeyUtil.TransBj2Td(m));
		}

		return rTd;
	}

	@Override
	public ArrayList<HashMap<String, String>> biddingBidDetail(HashMap<String, String> map) {
		map = new HashMap<String, String>(map);
		map.put(TDFN.platNo, BBC.PLAT_NO);
		String depositNo = userAccountCache.getUserAccoutInfo("").getDepositNo();
		//map.put(TDFN.platCust, depositNo);
		String orderNo = BandUtil.getNo(ONP.seekBiddingBidDetail);
		map.put(TDFN.orderNo, orderNo);
		
		
		map = MapTransKeyUtil.TransTd2Bj(map);
		HashMap<String, String> rm = super.requestAndInsertDbCommon(map.get(TDFN.orderNo), map,
				BBC.REQUEST_URL_SEEK_BIDDING_BID_DETAIL, SeekKey.biddingBidDetailRequest);
		ArrayList<HashMap<String, String>> r = JsonUtil.json2List(rm.get(BJFN.data));
		ArrayList<HashMap<String, String>> rTd = JsonUtil.json2List(rm.get(BJFN.data));
		for (HashMap<String, String> m : r) {
			rTd.add(MapTransKeyUtil.TransBj2Td(m));
		}

		return rTd;
	}

	@Override
	public HashMap<String, String> biddingInfo(HashMap<String, String> map) {
		map = new HashMap<String, String>(map);
		map.put(TDFN.platNo, BBC.PLAT_NO);
		String orderNo = BandUtil.getNo(ONP.seekBiddingInfo);
		map.put(TDFN.orderNo, orderNo);
		

		map = MapTransKeyUtil.TransTd2Bj(map);
		HashMap<String, String> rm = super.requestAndInsertDbCommon(map.get(TDFN.orderNo), map,
				BBC.REQUEST_URL_SEEK_BIDDING_INFO, SeekKey.biddingInfoRequest);
		HashMap<String, String> rsub = JsonUtil.json2List(rm.get(BJFN.data)).get(0);
		rsub = MapTransKeyUtil.TransBj2Td(rsub);

		return rsub;
	}

	@Override
	public HashMap<String, String> accountBalanceDetail(HashMap<String, String> map) {
		map = new HashMap<String, String>(map);
		map.put(TDFN.platNo, BBC.PLAT_NO);
		String orderNo = BandUtil.getNo(ONP.seekAccountBalanceDetail);
		map.put(TDFN.orderNo, orderNo);

		map = MapTransKeyUtil.TransTd2Bj(map);
		HashMap<String, String> rm = super.requestAndInsertDbCommon(map.get(TDFN.orderNo), map,
				BBC.REQUEST_URL_SEEK_ACCOUNT_BALANCE_DETAIL, SeekKey.accountBalanceDetailRequest);
		HashMap<String, String> rsub = JsonUtil.json2List(rm.get(BJFN.data)).get(0);
		rsub = MapTransKeyUtil.TransBj2Td(rsub);

		return rsub;
	}

	@Override
	public HashMap<String, String> orderStatus(HashMap<String, String> map) {
		map = new HashMap<String, String>(map);
		BandUtil.setCommonPara(map);
		String orderNo = BandUtil.getNo(ONP.orderStatus);
		map.put(TDFN.orderNo, orderNo);

		map = MapTransKeyUtil.TransTd2Bj(map);
		HashMap<String, String> rm = super.requestAndInsertDbCommon(map.get(TDFN.orderNo), map,
				BBC.REQUEST_URL_SEEK_ACCOUNT_BALANCE_DETAIL, SeekKey.orderStatusRequest);
		
		map.put(TDFN.success, rm.get(TDFN.success));		
		return map;
	}

	@Override
	public HashMap<String, String> biddingBalance(HashMap<String, String> map) {
		map = new HashMap<String, String>(map);
		map.put(TDFN.platNo, BBC.PLAT_NO);
		String orderNo = BandUtil.getNo(ONP.seekBiddingInfo);
		map.put(TDFN.orderNo, orderNo);
		

		map = MapTransKeyUtil.TransTd2Bj(map);
		HashMap<String, String> rm = super.requestAndInsertDbCommon(map.get(TDFN.orderNo), map,
				BBC.REQUEST_URL_SEEK_BIDDING_INFO, SeekKey.biddingInfoRequest);
		HashMap<String, String> rsub = JsonUtil.json2List(rm.get(BJFN.data)).get(0);
		rsub = MapTransKeyUtil.TransBj2Td(rsub);
		
		return rsub;
	}

	@Override
	public HashMap<String, String> platBalanceDetail(HashMap<String, String> map) {
		/*map = new HashMap<String, String>(map);
		map.put(TDFN.platNo, BBC.PLAT_NO);
		String orderNo = BandUtil.getNo(ONP.seekPlatBalanceDetail);
		map.put(TDFN.orderNo, orderNo);

		map = MapTransKeyUtil.TransTd2Bj(map);
		HashMap<String, String> rm = super.requestAndInsertDbCommon(map.get(TDFN.orderNo), map,
				BBC.REQUEST_URL_SEEK_PLAT_BALANCE_DETAIL, SeekKey.platBalanceDetailRequest);
		HashMap<String, String> rsub = JsonUtil.json2List(rm.get(BJFN.data)).get(0);
		rsub = MapTransKeyUtil.TransBj2Td(rsub);
		return rsub;*/
		
		return null;
	}

	@Override
	public HashMap<String, String> rechargeOrderStatus(HashMap<String, String> map) {
		map = new HashMap<String, String>(map);
		BandUtil.setCommonPara(map);
		String orderNo = BandUtil.getNo(ONP.seekRechargeOrderStatus);
		map.put(TDFN.orderNo, orderNo);

		map = MapTransKeyUtil.TransTd2Bj(map);
		HashMap<String, String> rm = super.requestAndInsertDbCommon(map.get(TDFN.orderNo), map,
				BBC.REQUEST_URL_SEEK_RECHARGE_ORDER_STATUS, SeekKey.rechargeOrderStatusRequest);
		
		map.put(TDFN.success, "false");		
		if(rm.get(BJFN.recode).equals(BBDD.success10000)){
			String status = JsonUtil.json2List(map.get(BJFN.data)).get(0).get(BJFN.status);
			if(status.equals(BBDD.tradeSuccess)){
				map.put(TDFN.success, "false");
			}
		}
		return map;
	}

}
