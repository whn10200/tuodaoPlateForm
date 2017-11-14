package com.tuodao.bp.depository.service.impl;

import com.tuodao.bp.cache.basic.useraccount.UserAccountCache;
import com.tuodao.bp.depository.service.RechargeService;
import com.tuodao.bp.depository.util.BandUtil;
import com.tuodao.bp.depository.util.MapTransKeyUtil;
import com.tuodao.bp.model.constant.depository.BBC;
import com.tuodao.bp.model.constant.depository.ONP;
import com.tuodao.bp.model.constant.depository.BJFN;
import com.tuodao.bp.model.constant.depository.key.RechargeKey;
import com.tuodao.bp.utils.BDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
@Service("rechargeService")
public class RechargeServiceImpl extends BaseServiceImpl implements RechargeService{
	@Autowired
	private UserAccountCache userAccountCache;

	@Override
	public HashMap<String, String> gatewayRecharge(HashMap<String, String> map) {
		map = new HashMap<String, String>(map);
		
		map.put(BJFN.platNo, BBC.PLAT_NO);
		BandUtil.setCommonPara(map);
		String depositNo = userAccountCache.getUserAccoutInfo("").getDepositNo();
		map.put(BJFN.platCust, depositNo);
		String orderNo = BandUtil.getNo(ONP.registe4Element);

		map.put(BJFN.type, "");
		map.put(BJFN.chargeType, "");
		map.put(BJFN.bankcode, "");
		map.put(BJFN.cardType, "");
		map.put(BJFN.currencyCode, "");
		map.put(BJFN.cardNo, "");
		map.put(BJFN.amt, new BDC(map.get(BJFN.amt)).fen2Yuan());
		map.put(BJFN.returnUrl, "");
		map.put(BJFN.notifyUrl, BBC.RECEIVE_URL_WITHDRAW_NOTIFY_INVESTOR);
		map.put(BJFN.payCode, BBC.PAY_CODE_BOB_PAY);
		map.put(BJFN.partnerUserid, "");
		map.put(BJFN.userInfoIdentifyType, "");
		map.put(BJFN.userInfoIdentifyState, "");
		map.put(BJFN.userInfoDtRegister, "");
		map.put(BJFN.userInfoDtIp, "");
		map.put(BJFN.idKind, "");
		map.put(BJFN.idNo, "");
		map.put(BJFN.clientName, "");

		map = MapTransKeyUtil.TransTd2Bj(map);
		
		HashMap<String, String> rm = super.requestAndInsertDbCommon(map.get(BJFN.detailNo), map,
				BBC.REQUEST_URL_RECHARGE_GATEWAY, RechargeKey.gatewayRechargeRequest);
		

		return map;
	}

	@Override
	public HashMap<String, String> rechargeReceive(HashMap<String, String> map) {
		map = new HashMap<String, String>(map);

		map.put(BJFN.platNo, BBC.PLAT_NO);
		BandUtil.setCommonPara(map);
		String depositNo = userAccountCache.getUserAccoutInfo("").getDepositNo();
		map.put(BJFN.platCust, depositNo);
		String orderNo = BandUtil.getNo(ONP.registe4Element);

		map.put(BJFN.orderAmt, new BDC(map.get(BJFN.orderAmt)).fen2Yuan());
		map.put(BJFN.transDate, "");
		map.put(BJFN.transTime, "");
		map.put(BJFN.hostReqSerialNo, "");
		map.put(BJFN.payFinishDate, "");
		map.put(BJFN.payFinishTime, "");
		map.put(BJFN.orderStatus, "");
		map.put(BJFN.payAmt, new BDC(map.get(BJFN.payAmt)).fen2Yuan());
		map.put(BJFN.errorInfo, "");

		map = MapTransKeyUtil.TransTd2Bj(map);

		HashMap<String, String> rm = super.requestAndInsertDbCommon(map.get(BJFN.detailNo), map,
				BBC.REQUEST_URL_RECHARGE_GATEWAY, RechargeKey.rechargeReceiveRequest);

		return map;
	}

	@Override
	public HashMap<String, String> quickRechargeApply(HashMap<String, String> map) {
		map = new HashMap<String, String>(map);

		map.put(BJFN.platNo, BBC.PLAT_NO);
		BandUtil.setCommonPara(map);
		String depositNo = userAccountCache.getUserAccoutInfo("").getDepositNo();
		map.put(BJFN.platCust, depositNo);
		String orderNo = BandUtil.getNo(ONP.registe4Element);

		map.put(BJFN.name, "");
		map.put(BJFN.cardNo, "");
		map.put(BJFN.cardType, "");
		map.put(BJFN.currencyCode, "");
		map.put(BJFN.idType, "");
		map.put(BJFN.idCode, "");
		map.put(BJFN.mobile, "");
		map.put(BJFN.email, "");
		map.put(BJFN.amt, new BDC(map.get(BJFN.amt)).fen2Yuan());
		map.put(BJFN.payCode, "");
		map.put(BJFN.accountType, "");
		map.put(BJFN.branchFlag, "");
		map.put(BJFN.notifyUrl, BBC.RECEIVE_URL_WITHDRAW_NOTIFY_INVESTOR);

		map = MapTransKeyUtil.TransTd2Bj(map);

		HashMap<String, String> rm = super.requestAndInsertDbCommon(map.get(BJFN.detailNo), map,
				BBC.REQUEST_URL_RECHARGE_QUICK_APPLY, RechargeKey.quickRechargeApplyRequest);

		return map;
	}

	@Override
	public HashMap<String, String> quickRechargeConfirm(HashMap<String, String> map) {
		map = new HashMap<String, String>(map);

		map.put(BJFN.platNo, BBC.PLAT_NO);
		BandUtil.setCommonPara(map);
		String depositNo = userAccountCache.getUserAccoutInfo("").getDepositNo();
		map.put(BJFN.platCust, depositNo);
		String orderNo = BandUtil.getNo(ONP.registe4Element);

		map.put(BJFN.identifyingCode, "");
		map.put(BJFN.originOrderNo, "");

		map = MapTransKeyUtil.TransTd2Bj(map);

		HashMap<String, String> rm = super.requestAndInsertDbCommon(map.get(BJFN.detailNo), map,
				BBC.REQUEST_URL_RECHARGE_QUICK_CONFIRM, RechargeKey.quickRechargeConfirmRequest);

		return map;
	}

	@Override
	public HashMap<String, String> borrowCutRepay(HashMap<String, String> map) {
		map = new HashMap<String, String>(map);

		map.put(BJFN.platNo, BBC.PLAT_NO);
		BandUtil.setCommonPara(map);
		String depositNo = userAccountCache.getUserAccoutInfo("").getDepositNo();
		map.put(BJFN.platCust, depositNo);
		String orderNo = BandUtil.getNo(ONP.registe4Element);

		map.put(BJFN.orderNo, "");
		map.put(BJFN.notifyUrl, BBC.RECEIVE_URL_WITHDRAW_NOTIFY_INVESTOR);
		map.put(BJFN.amt, new BDC(map.get(BJFN.amt)).fen2Yuan());
		//map.put(BJFN.platcustList, "");
		map.put(BJFN.platCust, "");
		map.put(BJFN.amt, "");


		map = MapTransKeyUtil.TransTd2Bj(map);
		// TODO: 2017/10/26  BBC.REQUEST_URL_RECHARGE_QUICK_CONFIRM 有误
		HashMap<String, String> rm = super.requestAndInsertDbCommon(map.get(BJFN.detailNo), map,
				BBC.REQUEST_URL_RECHARGE_QUICK_CONFIRM, RechargeKey.borrowCutRepayRequest);

		return map;
	}


}
