package com.tuodao.bp.depository.service.impl;

import com.tuodao.bp.cache.basic.useraccount.UserAccountCache;
import com.tuodao.bp.depository.service.BiddingService;
import com.tuodao.bp.depository.sign.MapUtils;
import com.tuodao.bp.depository.util.BandUtil;
import com.tuodao.bp.depository.util.IdCardUtils;
import com.tuodao.bp.depository.util.MapTransKeyUtil;
import com.tuodao.bp.model.constant.depository.*;
import com.tuodao.bp.model.constant.depository.key.BiddingKey;
import com.tuodao.bp.model.constant.depository.key.UserKey;
import com.tuodao.bp.utils.BDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service("biddingSerivce")
public class BiddingServiceImpl extends BaseServiceImpl implements BiddingService {
	@Autowired
	private UserAccountCache userAccountCache;

	@Override
	public HashMap<String, String> publish(HashMap<String, String> map) {
		map.put(BJFN.platNo, BBC.PLAT_NO);
		BandUtil.setCommonPara(map);
		String depositNo = userAccountCache.getUserAccoutInfo("").getDepositNo();
		map.put(TDFN.depositNo, depositNo);
		String orderNo = BandUtil.getNo(ONP.registe4Element);
		map.put(BJFN.orderNo, orderNo);

		// map.put(BJFN.orderNo, "1508327708909");

		map.put(TDFN.productId, "1");
		map.put(TDFN.productTitle, "");
		map.put(TDFN.productType, "");
		map.put(BJFN.totalLimit, "");
		map.put(BJFN.valueType, "");
		map.put(BJFN.createType, "");
		map.put(BJFN.sellDate, "");
		map.put(BJFN.valueDate, "");
		map.put(BJFN.expireDate, "");
		map.put(BJFN.cycle, "");
		map.put(BJFN.cycleUnit, "");
		map.put(BJFN.istYear, "");
		map.put(BJFN.repayType, "");
		map.put(BJFN.financingInfoList, "");
		map.put(BJFN.riskLvl, "");
		map.put(BJFN.prodInfo, "");
		map.put(BJFN.buyerNumLimit, "");
		map.put(BJFN.overTotalLimit, "");
		map.put(BJFN.compensation, "");

		map = MapTransKeyUtil.TransTd2Bj(map);

		HashMap<String, String> rm = super.requestAndInsertDbCommon(map.get(BJFN.detailNo), map,
				BBC.REQUEST_URL_BIDDING_PUBLISH, BiddingKey.publishRequest);
		return map;
	}

	public HashMap<String, String> complate(HashMap<String, String> map) {
		
		map.put(BJFN.repayPlanList+CFN._eleln_(0)+BJFN.repayAmt, "");
		map.put(BJFN.repayPlanList+CFN._eleln_(0)+BJFN.repayNum, "");
		map.put(BJFN.repayPlanList+CFN._eleln_(1)+BJFN.repayAmt, "");
		map.put(BJFN.repayPlanList+CFN._eleln_(1)+BJFN.repayNum, "");
		
		
		return result(map, "2");
	}

	public HashMap<String, String> cancel(HashMap<String, String> map) {
		return result(map, "3");
	}

	private HashMap<String, String> result(HashMap<String, String> map, String type) {
		map.put(BJFN.platNo, BBC.PLAT_NO);
		BandUtil.setCommonPara(map);
		String depositNo = userAccountCache.getUserAccoutInfo("").getDepositNo();
		map.put(BJFN.platCust, depositNo);
		String orderNo = BandUtil.getNo(ONP.registeRealName);
		map.put(BJFN.orderNo, orderNo);
		map.put(BJFN.flag,type);

		map.put(BJFN.totalNum, "1");
		map.put(BJFN.detailNo, BandUtil.getOneDetailNo(orderNo));
		map.put(BJFN.idType, BBDD.CERT_TYPE_ID);
		map.put(BJFN.sex, IdCardUtils.getSex(map.get(BJFN.cardNo)));
		map.put(BJFN.cusType, BBDD.CUSTOMER_TYPE_PERSONAL);
		map.put(BJFN.birthday, IdCardUtils.getBirthday(map.get(BJFN.cardNo)));
		map.put(BJFN.payCode, BBC.PAY_CODE);

		map = MapTransKeyUtil.TransTd2Bj(map);

		String date = MapUtils.map2jsonBean(map, UserKey.registRealnameRequest);
		List<String> dateList = Arrays.asList(date);
		map.put(BJFN.data, MapUtils.map2jsonList(dateList));

		HashMap<String, String> rm = super.requestAndInsertDbCommon(map.get(BJFN.detailNo), map,
				BBC.REQUEST_URL_BIDDING_RESULT, BiddingKey.resultRequest);

		return map;
	}

	@Override
	public HashMap<String, String> bid(HashMap<String, String> map) {
		map.put(BJFN.platNo, BBC.PLAT_NO);
		BandUtil.setCommonPara(map);
		String depositNo = userAccountCache.getUserAccoutInfo("").getDepositNo();
		map.put(BJFN.platCust, depositNo);
		String orderNo = BandUtil.getNo(ONP.changeCard);
		map.put(BJFN.orderNo, orderNo);

		map.put(BJFN.totalNum, "1");
		map.put(BJFN.prodId, "");
		map.put(BJFN.detailNo, BandUtil.getOneDetailNo(orderNo));
		map.put(BJFN.transAmt, new BDC(map.get(BJFN.transAmt)).fen2Yuan());
		map.put(BJFN.experienceAmt, new BDC(map.get(BJFN.experienceAmt)).fen2Yuan());
		map.put(BJFN.couponAmt, new BDC(map.get(BJFN.couponAmt)).fen2Yuan());
		map.put(BJFN.selfAmt, new BDC(map.get(BJFN.selfAmt)).fen2Yuan());
		map.put(BJFN.inInterest, "");
		map.put(BJFN.subjectPriority, "");
		map.put(BJFN.commission, "");

		map = MapTransKeyUtil.TransTd2Bj(map);

		String date = MapUtils.map2jsonBean(map, BiddingKey.BidRequest);
		List<String> dateList = Arrays.asList(date);
		map.put(BJFN.data, MapUtils.map2jsonList(dateList));

		HashMap<String, String> rm = super.requestAndInsertDbCommon(map.get(BJFN.orderNo), map,
				BBC.REQUEST_URL_BIDDING_BID, BiddingKey.BidRequest);

		String remark = BandUtil.getResponseSingleSubbean(rm, BJFN.errorData, BJFN.errorInfo);
		map.put(BJFN.remark, remark);
		return map;
	}

	@Override
	public HashMap<String, String> chargeOff(HashMap<String, String> map) {
		map.put(BJFN.platNo, BBC.PLAT_NO);
		BandUtil.setCommonPara(map);
		String depositNo = userAccountCache.getUserAccoutInfo("").getDepositNo();
		map.put(BJFN.platCust, depositNo);
		String orderNo = BandUtil.getNo(ONP.registe4Element);
		map.put(BJFN.orderNo, orderNo);
		map.put(BJFN.prodId, "");
		map.put(BJFN.chargeOffList, "");
		map.put(BJFN.notifyUrl, BBC.RECEIVE_URL_WITHDRAW_NOTIFY_INVESTOR);
		map.put(BJFN.payCode, BBC.PAY_CODE_BOB_PAY);

		map = MapTransKeyUtil.TransTd2Bj(map);

		HashMap<String, String> rm = super.requestAndInsertDbCommon(map.get(BJFN.orderNo), map,
				BBC.REQUEST_URL_BIDDING_CHARGE_OFF, BiddingKey.chargeOffRequest);

		String remark = BandUtil.getResponseSingleSubbean(rm, BJFN.errorData, BJFN.errorInfo);
		map.put(BJFN.remark, remark);
		return map;
	}

	@Override
	public HashMap<String, String> chargeOffReceive(HashMap<String, String> map) {
		BandUtil.setCommonPara(map);
		String depositNo = userAccountCache.getUserAccoutInfo("").getDepositNo();
		map.put(BJFN.platCust, depositNo);
		String orderNo = BandUtil.getNo(ONP.messageBoundCardValidate);
		map.put(BJFN.orderNo, orderNo);
		map.put(BJFN.transShare, "");
		map.put(BJFN.prodId, "");
		map.put(BJFN.transAmt, new BDC(map.get(BJFN.transAmt)).fen2Yuan());
		map.put(BJFN.dealAmount, new BDC(map.get(BJFN.dealAmount)).fen2Yuan());
		map.put(BJFN.couponAmt, new BDC(map.get(BJFN.couponAmt)).fen2Yuan());
		map.put(BJFN.dealPlatcust, "");
		map.put(BJFN.commission, "");
		map.put(BJFN.commissionExt, "");
		map.put(BJFN.publishDate, "");
		map.put(BJFN.transDate, "");
		map.put(BJFN.transferIncome, "");
		map.put(BJFN.incomeAcct, "");
		map.put(BJFN.relatedProdIds, "");
		map.put(BJFN.subjectPriority, "");

		map = MapTransKeyUtil.TransTd2Bj(map);

		HashMap<String, String> rm = super.requestAndInsertDbCommon(map.get(BJFN.orderNo), map,
				BBC.REQUEST_URL_BIDDING_CHARGE_OFF, BiddingKey.chargeOffReceiveRequest);
		return map;
	}

	@Override
	public HashMap<String, String> transferDebt(HashMap<String, String> map) {
		BandUtil.setCommonPara(map);
		String depositNo = userAccountCache.getUserAccoutInfo("").getDepositNo();
		map.put(BJFN.platCust, depositNo);
		String orderNo = BandUtil.getNo(ONP.messageBoundCardValidate);
		map.put(BJFN.orderNo, orderNo);
		map.put(BJFN.transShare, "");
		map.put(BJFN.prodId, "");
		map.put(BJFN.transAmt, new BDC(map.get(BJFN.transAmt)).fen2Yuan());
		map.put(BJFN.dealAmount, new BDC(map.get(BJFN.dealAmount)).fen2Yuan());
		map.put(BJFN.couponAmt, new BDC(map.get(BJFN.couponAmt)).fen2Yuan());
		map.put(BJFN.dealPlatcust, "");
		map.put(BJFN.commission, "");
		map.put(BJFN.commissionExt, "");
		map.put(BJFN.publishDate, "");
		map.put(BJFN.transDate, "");
		map.put(BJFN.transferIncome, "");
		map.put(BJFN.incomeAcct, "");
		map.put(BJFN.relatedProdIds, "");
		map.put(BJFN.subjectPriority, "");

		map = MapTransKeyUtil.TransTd2Bj(map);

		HashMap<String, String> rm = super.requestAndInsertDbCommon(map.get(BJFN.orderNo), map,
				BBC.REQUEST_URL_BIDDING_TRANSFER_DEBT, BiddingKey.transferDebtRequest);
		return map;
	}

	@Override
	public HashMap<String, String> repay(HashMap<String, String> map) {
		BandUtil.setCommonPara(map);
		String depositNo = userAccountCache.getUserAccoutInfo("").getDepositNo();
		map.put(BJFN.platCust, depositNo);
		String orderNo = BandUtil.getNo(ONP.userInfoChange);
		map.put(BJFN.orderNo, orderNo);
		map.put(BJFN.prodId, "");
		map.put(BJFN.repayNum, "");
		map.put(BJFN.isPayoff, "");
		map.put(BJFN.transAmt, new BDC(map.get(BJFN.transAmt)).fen2Yuan());
		map.put(BJFN.repayFlag, "");
		map.put(BJFN.data, "");

		map = MapTransKeyUtil.TransTd2Bj(map);

		HashMap<String, String> rm = super.requestAndInsertDbCommon(map.get(BJFN.orderNo), map,
				BBC.REQUEST_URL_BIDDING_REPAY, BiddingKey.repayRequest);
		return map;
	}

	@Override
	public HashMap<String, String> repayPlaneUpdate(HashMap<String, String> map) {
		BandUtil.setCommonPara(map);
		String depositNo = userAccountCache.getUserAccoutInfo("").getDepositNo();
		map.put(BJFN.platCust, depositNo);
		String orderNo = BandUtil.getNo(ONP.userInfoChange);
		map.put(BJFN.orderNo, orderNo);
		map.put(BJFN.prodId, "");
		map.put(BJFN.repayPlanList, "");

		map = MapTransKeyUtil.TransTd2Bj(map);

		HashMap<String, String> rm = super.requestAndInsertDbCommon(map.get(BJFN.orderNo), map,
				BBC.REQUEST_URL_BIDDING_REPAY_PLANE_UPDATE, BiddingKey.repayPlaneUpdateRequest);
		return map;
	}

	@Override
	public HashMap<String, String> chargeOffChange(HashMap<String, String> map) {
		BandUtil.setCommonPara(map);
		String depositNo = userAccountCache.getUserAccoutInfo("").getDepositNo();
		map.put(BJFN.platCust, depositNo);
		String orderNo = BandUtil.getNo(ONP.userInfoChange);
		map.put(BJFN.orderNo, orderNo);
		map.put(BJFN.prodId, "");
		map.put(BJFN.openBranch, "");
		map.put(BJFN.withdrawAccount, "");
		map.put(BJFN.accountType, "");
		map.put(BJFN.payeeName, "");

		map = MapTransKeyUtil.TransTd2Bj(map);

		HashMap<String, String> rm = super.requestAndInsertDbCommon(map.get(BJFN.orderNo), map,
				BBC.REQUEST_URL_BIDDING_CHARGE_OFF_CHANGE, BiddingKey.chargeOffChangeRequest);
		return map;
	}

}
