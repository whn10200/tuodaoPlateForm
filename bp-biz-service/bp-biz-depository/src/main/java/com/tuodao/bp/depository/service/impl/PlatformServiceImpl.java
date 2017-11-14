package com.tuodao.bp.depository.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuodao.bp.cache.basic.useraccount.UserAccountCache;
import com.tuodao.bp.depository.service.PlatformService;
import com.tuodao.bp.depository.util.BandUtil;
import com.tuodao.bp.depository.util.MapTransKeyUtil;
import com.tuodao.bp.model.constant.depository.BBC;
import com.tuodao.bp.model.constant.depository.BJFN;
import com.tuodao.bp.model.constant.depository.BJFN;
import com.tuodao.bp.model.constant.depository.key.PlatFormKey;
import com.tuodao.bp.utils.BDC;

@Service("platformService")
public class PlatformServiceImpl extends BaseServiceImpl implements PlatformService {
	@Autowired
	private UserAccountCache userAccountCache;

	@Override
	public HashMap<String, String> fundFreeze(HashMap<String, String> map) {
		return freezeAndUnblock(map, "01");
	}

	@Override
	public HashMap<String, String> fundUnblock(HashMap<String, String> map) {
		return freezeAndUnblock(map, "02");
	}

	private HashMap<String, String> freezeAndUnblock(HashMap<String, String> map, String type) {
		map = new HashMap<String, String>(map);
		BandUtil.setCommonPara(map);
		String depositNo = userAccountCache.getUserAccoutInfo("").getDepositNo();
		map.put(BJFN.platCust, depositNo);
		map.put(BJFN.amount, new BDC(map.get(BJFN.amount)).fen2Yuan());
		map.put(BJFN.freezeFlg, type);
		map = MapTransKeyUtil.TransTd2Bj(map);

		HashMap<String, String> rm = super.requestAndInsertDbCommon(map.get(BJFN.orderNo), map,
				BBC.REQUEST_URL_PLATFORM_FUND_FREEZE, PlatFormKey.fundBlockAndDeblockRequest);
		/*HashMap<String, String> rsub = JsonUtil.json2List(rm.get(BJFN.data)).get(0);
		rsub = MapTransKeyUtil.TransBj2Td(rsub);
		map.putAll(rsub);*/
		map.put(BJFN.success, rm.get(BJFN.success));
		return map;
	}

	@Override
	public HashMap<String, String> platformConverse(HashMap<String, String> map) {
		map = new HashMap<String, String>(map);
		BandUtil.setCommonPara(map);
		String depositNo = userAccountCache.getUserAccoutInfo("").getDepositNo();
		map.put(BJFN.platCust, depositNo);
		map.put(BJFN.amt, new BDC(map.get(BJFN.amt)).fen2Yuan());
		map = MapTransKeyUtil.TransTd2Bj(map);
		HashMap<String, String> rm = super.requestAndInsertDbCommon(map.get(BJFN.orderNo), map,
				BBC.REQUEST_URL_PLATFORM_CONVERSE, PlatFormKey.platformConverseRequest);
		map.put(BJFN.success, rm.get(BJFN.success));
		return map;
	}

	@Override
	public HashMap<String, String> platformRecharge(HashMap<String, String> map) {
		map = new HashMap<String, String>(map);
		BandUtil.setCommonPara(map);
		map.put(BJFN.amount, new BDC(map.get(BJFN.amount)).fen2Yuan());
		map = MapTransKeyUtil.TransTd2Bj(map);
		HashMap<String, String> rm = super.requestAndInsertDbCommon(map.get(BJFN.orderNo), map,
				BBC.RECEIVE_URL_PLATFORM_RECHARGE, PlatFormKey.platformRechargeRequest);
		map.put(BJFN.success, rm.get(BJFN.success));
		return map;
	}

	@Override
	public HashMap<String, String> platformTransfer(HashMap<String, String> map) {
		map = new HashMap<String, String>(map);
		BandUtil.setCommonPara(map);
		String depositNo = userAccountCache.getUserAccoutInfo("").getDepositNo();
		map.put(BJFN.platCust, depositNo);
		map.put(BJFN.amt, new BDC(map.get(BJFN.amt)).fen2Yuan());
		map = MapTransKeyUtil.TransTd2Bj(map);
		HashMap<String, String> rm = super.requestAndInsertDbCommon(map.get(BJFN.orderNo), map,
				BBC.REQUEST_URL_PLATFORM_TRANSFER, PlatFormKey.platformTransferRequest);
		map.put(BJFN.success, rm.get(BJFN.success));
		return map;
	}

	@Override
	public HashMap<String, String> platformWithDraw(HashMap<String, String> map) {
		map = new HashMap<String, String>(map);
		BandUtil.setCommonPara(map);
		String depositNo = userAccountCache.getUserAccoutInfo("").getDepositNo();
		map.put(BJFN.platCust, depositNo);
		map.put(BJFN.amt, new BDC(map.get(BJFN.amt)).fen2Yuan());
		map = MapTransKeyUtil.TransTd2Bj(map);
		HashMap<String, String> rm = super.requestAndInsertDbCommon(map.get(BJFN.orderNo), map,
				BBC.REQUEST_URL_PLATFORM_WITHDRAW, PlatFormKey.platformWithDrawRequest);
		map.put(BJFN.success, rm.get(BJFN.success));
		return map;
	}

}
