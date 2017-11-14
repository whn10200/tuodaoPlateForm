package com.tuodao.bp.depository.service.impl;

import com.tuodao.bp.cache.basic.useraccount.UserAccountCache;
import com.tuodao.bp.depository.service.WithdrawService;
import com.tuodao.bp.depository.sign.MapUtils;
import com.tuodao.bp.depository.util.BandUtil;
import com.tuodao.bp.depository.util.MapTransKeyUtil;
import com.tuodao.bp.model.constant.depository.BBC;
import com.tuodao.bp.model.constant.depository.BJFN;
import com.tuodao.bp.model.constant.depository.ONP;
import com.tuodao.bp.model.constant.depository.BJFN;
import com.tuodao.bp.model.constant.depository.key.WithdrawKey;
import com.tuodao.bp.utils.BDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
@Service("withdrawService")
public class WithdrawServiceImpl extends BaseServiceImpl implements WithdrawService {
	@Autowired
	private UserAccountCache userAccountCache;

	@Override
	public HashMap<String, String> withDrawApplyInvestor(HashMap<String, String> map) {

		map = new HashMap<String, String>(map);
		
		map.put(BJFN.platNo, BBC.PLAT_NO);
		BandUtil.setCommonPara(map);
		String depositNo = userAccountCache.getUserAccoutInfo("").getDepositNo();
		map.put(BJFN.platCust, depositNo);
		String orderNo = BandUtil.getNo(ONP.registe4Element);

		map.put(BJFN.totalNum, "1");
		map.put(BJFN.detailNo, BandUtil.getOneDetailNo(orderNo));
		map.put(BJFN.amt, new BDC(map.get(BJFN.amt)).fen2Yuan());
		map.put(BJFN.payCode, BBC.PAY_CODE_BOB_PAY);
		map.put(BJFN.notifyUrl, BBC.RECEIVE_URL_WITHDRAW_NOTIFY_INVESTOR);

		map = MapTransKeyUtil.TransTd2Bj(map);
		
		String date = MapUtils.map2jsonBean(map, WithdrawKey.applyWithdrawCashRequest);
		List<String> dateList = Arrays.asList(date);
		map.put(BJFN.data, MapUtils.map2jsonList(dateList));
		
		HashMap<String, String> rm = super.requestAndInsertDbCommon(map.get(BJFN.detailNo), map,
				BBC.REQUEST_URL_WITHDRAW_APPLY_INVESTOR, WithdrawKey.batchApplyWithdrawCashRequest);
		

		return map;
	}

}
