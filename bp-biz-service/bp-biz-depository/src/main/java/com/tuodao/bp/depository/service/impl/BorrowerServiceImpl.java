package com.tuodao.bp.depository.service.impl;

import com.tuodao.bp.cache.basic.useraccount.UserAccountCache;
import com.tuodao.bp.depository.service.BorrowerService;
import com.tuodao.bp.depository.sign.MapUtils;
import com.tuodao.bp.depository.util.BandUtil;
import com.tuodao.bp.depository.util.MapTransKeyUtil;
import com.tuodao.bp.model.constant.depository.BBC;
import com.tuodao.bp.model.constant.depository.BJFN;
import com.tuodao.bp.model.constant.depository.ONP;
import com.tuodao.bp.model.constant.depository.BJFN;
import com.tuodao.bp.model.constant.depository.key.BorrowerKey;
import com.tuodao.bp.utils.BDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service("borrowerSerivce")
public class BorrowerServiceImpl extends BaseServiceImpl implements BorrowerService {
	@Autowired
	private UserAccountCache userAccountCache;

	@Override
	public HashMap<String, String> payBak(HashMap<String, String> map) {

		map.put(BJFN.platNo, BBC.PLAT_NO);
		BandUtil.setCommonPara(map);
		String depositNo = userAccountCache.getUserAccoutInfo("").getDepositNo();
		map.put(BJFN.platCust, depositNo);
		String orderNo = BandUtil.getNo(ONP.registe4Element);
		map.put(BJFN.orderNo, orderNo);

		// map.put(BJFN.orderNo, "1508327708909");

		map.put(BJFN.totalNum, "1");
		map.put(BJFN.detailNo, BandUtil.getOneDetailNo(orderNo));
		map.put(BJFN.feeAmt,new BDC(map.get(BJFN.feeAmt)).mul100().toS0Bd().toString());
		map.put(BJFN.prodId,"");
		map.put(BJFN.realRepayAmt,new BDC(map.get(BJFN.realRepayAmt)).mul100().toS0Bd().toString());
		map.put(BJFN.realRepayDate, "");
		map.put(BJFN.repayAmt,new BDC(map.get(BJFN.repayAmt)).mul100().toS0Bd().toString() );
		map.put(BJFN.repayDate,"" );
		map.put(BJFN.repayNum,"" );
		map.put(BJFN.transAmt, new BDC(map.get(BJFN.transAmt)).mul100().toS0Bd().toString());

		map = MapTransKeyUtil.TransTd2Bj(map);

		String date = MapUtils.map2jsonBean(map, BorrowerKey.PayBakRequest);
		List<String> dateList = Arrays.asList(date);
		map.put(BJFN.data, MapUtils.map2jsonList(dateList));

		HashMap<String, String> rm = super.requestAndInsertDbCommon(map.get(BJFN.orderNo),map, BBC.REQUEST_URL_BORROWER_BATCH_REPAY,
				BorrowerKey.batchPayBakRequest);
		BandUtil.isSuccessSingle(rm, map);		


		return map;
	}

	@Override
	public HashMap<String, String> biddingCompensation(HashMap<String, String> map) {
		map.put(BJFN.platNo, BBC.PLAT_NO);
		BandUtil.setCommonPara(map);
		String depositNo = userAccountCache.getUserAccoutInfo("").getDepositNo();
		map.put(BJFN.platCust, depositNo);
		String orderNo = BandUtil.getNo(ONP.registeRealName);
		map.put(BJFN.orderNo, orderNo);

		map.put(BJFN.repayNum, "1");
		map.put(BJFN.prodId,"" );
		map.put(BJFN.repayDate,"" );
		map.put(BJFN.repayAmt,new BDC(map.get(BJFN.repayAmt)).mul100().toS0Bd().toString()  );
		map.put(BJFN.realRepayDate,"" );
		map.put(BJFN.realRepayAmt, new BDC(map.get(BJFN.realRepayAmt)).mul100().toS0Bd().toString() );
		//map.put(BJFN.compensationPlatCust, "");
		map.put(BJFN.transAmt, new BDC(map.get(BJFN.transAmt)).mul100().toS0Bd().toString() );
		map.put(BJFN.feeAmt,new BDC(map.get(BJFN.feeAmt)).mul100().toS0Bd().toString() );
		map.put(BJFN.repayType, "");

		map = MapTransKeyUtil.TransTd2Bj(map);

		HashMap<String, String> rm = super.requestAndInsertDbCommon(map.get(BJFN.orderNo),map,BBC.REQUEST_URL_BORROWER_BIDDING_COMPENSATION,
				BorrowerKey.biddingCompensationRequest);
		BandUtil.isSuccessSingle(rm, map);		

		return map;
	}

	@Override
	public HashMap<String, String> borrowerCompensation(HashMap<String, String> map) {
		map.put(BJFN.platNo, BBC.PLAT_NO);
		BandUtil.setCommonPara(map);
		String depositNo = userAccountCache.getUserAccoutInfo("").getDepositNo();
		map.put(BJFN.platCust, depositNo);
		String orderNo = BandUtil.getNo(ONP.changeCard);
		map.put(BJFN.orderNo, orderNo);

		map.put(BJFN.prodId,"");
		map.put(BJFN.repayAmt,new BDC(map.get(BJFN.repayAmt)).mul100().toS0Bd().toString());
		//map.put(BJFN.compensationPlatCust, "");

		map = MapTransKeyUtil.TransTd2Bj(map);


		HashMap<String, String> rm = super.requestAndInsertDbCommon(map.get(BJFN.orderNo),map, BBC.REQUEST_URL_BORROWER_REPAY_COMPENSATION,
				BorrowerKey.borrowerCompensationRequest);

		String remark = BandUtil.getResponseSingleSubbean(rm, BJFN.errorData, BJFN.errorInfo);

		map.put(BJFN.remark, remark);

		BandUtil.isSuccessBatch(rm, map);

		return map;
	}


}
