package com.tuodao.bp.depository.service.impl;

import com.tuodao.bp.cache.basic.useraccount.UserAccountCache;
import com.tuodao.bp.depository.service.UserService;
import com.tuodao.bp.depository.sign.MapUtils;
import com.tuodao.bp.depository.util.BandUtil;
import com.tuodao.bp.depository.util.BeanStructure;
import com.tuodao.bp.depository.util.IdCardUtils;
import com.tuodao.bp.depository.util.MapTransKeyUtil;
import com.tuodao.bp.model.constant.depository.*;
import com.tuodao.bp.model.constant.depository.key.UserKey;
import com.tuodao.bp.utils.JsonUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userSerivce")
public class UserServiceImpl extends BaseServiceImpl implements UserService {
	@Autowired
	private UserAccountCache userAccountCache;

	@Override
	public HashMap<String, String> regist4Ele(HashMap<String, String> map) {
		HashMap<String, String> bj = new HashMap<String, String>();

		BandUtil.setCommonPara(bj);

		// String depositNo =
		// userAccountCache.getUserAccoutInfo(map.get(TDFN.userId)).getDepositNo();

		String mobile = BandUtil.getUserIdMobile(map.get(TDFN.subbean + CFN._eleln_(0) + TDFN.userId));
		String orderNo = BandUtil.getNo(ONP.registe4Element + mobile);
		bj.put(BJFN.orderNo, orderNo);

		// String orderNo = "1508327708909";
		// map.put(BJFN.orderNo, "1508327708909");

		bj.put(BJFN.totalNum, "1");
		bj.put(BJFN.data + CFN._lisize, "1");
		bj.put(BJFN.data + CFN._eleln_(0) + BJFN.detailNo, BandUtil.getOneDetailNo(orderNo));
		bj.put(BJFN.data + CFN._eleln_(0) + BJFN.name, map.get(TDFN.subbean + CFN._eleln_(0) + TDFN.realName));
		bj.put(BJFN.data + CFN._eleln_(0) + BJFN.idType, BBDD.CERT_TYPE_ID);
		bj.put(BJFN.data + CFN._eleln_(0) + BJFN.idCode, map.get(TDFN.subbean + CFN._eleln_(0) + TDFN.idCard));
		bj.put(BJFN.data + CFN._eleln_(0) + BJFN.mobile, map.get(TDFN.subbean + CFN._eleln_(0) + TDFN.mobile));
		// bj.put(BJFN.data + CFN._eleln_(0) + BJFN.email, null);
		bj.put(BJFN.data + CFN._eleln_(0) + BJFN.sex,
				IdCardUtils.getSex(bj.get(BJFN.data + CFN._eleln_(0) + BJFN.idCode)));
		bj.put(BJFN.data + CFN._eleln_(0) + BJFN.cusType, BBDD.CUSTOMER_TYPE_PERSONAL);
		bj.put(BJFN.data + CFN._eleln_(0) + BJFN.birthday,
				IdCardUtils.getBirthday(bj.get(BJFN.data + CFN._eleln_(0) + BJFN.idCode)));
		bj.put(BJFN.data + CFN._eleln_(0) + BJFN.cardNo, map.get(TDFN.subbean + CFN._eleln_(0) + TDFN.bankNum));
		bj.put(BJFN.data + CFN._eleln_(0) + BJFN.cardType, BBDD.BANK_CARD_TYPE_DEBIT);
		bj.put(BJFN.data + CFN._eleln_(0) + BJFN.preMobile, map.get(TDFN.subbean + CFN._eleln_(0) + TDFN.reservationMobile));
		bj.put(BJFN.data + CFN._eleln_(0) + BJFN.payCode, BBC.PAY_CODE);
		bj.put(BJFN.data + CFN._eleln_(0) + BJFN.remark, map.get(TDFN.subbean + CFN._eleln_(0) + TDFN.remark));

		BandUtil.putList2JsonWithoutNUll(bj, BJFN.data, new Integer(bj.get(BJFN.data + CFN._lisize)),
				UserKey.regist4EleRequest);

		HashMap<String, String> rm = super.requestAndInsertDbCommon(bj.get(BJFN.data + CFN._eleln_(0) + BJFN.detailNo),
				bj, BBC.REQUEST_URL_USER_REGIST_4_ELE, UserKey.batchRegist4EleRequest);

		List<BeanStructure> responseBss = new ArrayList<BeanStructure>();
		responseBss.add(new BeanStructure(BJFN.successData, CFN._elelx_));
		responseBss.add(new BeanStructure(BJFN.errorData, CFN._elelx_));
		BandUtil.analyze(rm, responseBss);
		BandUtil.getSuccessForSingle(rm);
		// rm = MapTransKeyUtil.TransBj2Td(map);
		map.put(TDFN.success, rm.get(BJFN.success));
		map.put(TDFN.subbean + CFN._eleln_(0) + TDFN.depositNo, rm.get(BJFN.successData + CFN._eleln_(0) + BJFN.platCust));

		if (rm.get(BJFN.errorData + CFN._eleln_(0) + BJFN.errorNo) != null) {
			map.put(TDFN.recode, rm.get(BJFN.errorData + CFN._eleln_(0) + BJFN.errorNo));
			map.put(TDFN.remsg, rm.get(BJFN.errorData + CFN._eleln_(0) + BJFN.remsg));
		}

		if (rm.get(BJFN.errorData + CFN._eleln_(0) + BJFN.errorNo) != null
				&& rm.get(BJFN.errorData + CFN._eleln_(0) + BJFN.errorNo).equals(BBDD.error45000)
				&& rm.get(BJFN.success).equals(BBDD.xfalse)) {
			map.put(TDFN.success, BBDD.xtrue);
			map.put(TDFN.subbean + CFN._eleln_(0) + TDFN.bankNum, rm.get(BJFN.errorData + CFN._eleln_(0) + BJFN.cardNo));
			map.put(TDFN.subbean + CFN._eleln_(0) + TDFN.reservationMobile,
					rm.get(BJFN.errorData + CFN._eleln_(0) + BJFN.preMobile));
			map.put(TDFN.subbean + CFN._eleln_(0) + TDFN.depositNo,
					rm.get(BJFN.errorData + CFN._eleln_(0) + BJFN.platCust));
		}

		return map;
	}

	@Override
	public HashMap<String, String> registRealname(HashMap<String, String> map) {
		map.put(BJFN.platNo, BBC.PLAT_NO);
		BandUtil.setCommonPara(map);
		String orderNo = BandUtil.getNo(ONP.registeRealName);
		map.put(BJFN.orderNo, orderNo);

		map.put(BJFN.totalNum, "1");
		map.put(BJFN.data + CFN._eleln_(0) + BJFN.detailNo, BandUtil.getOneDetailNo(orderNo));
		map.put(BJFN.data + CFN._eleln_(0) + BJFN.idType, BBDD.CERT_TYPE_ID);
		map.put(BJFN.data + CFN._eleln_(0) + BJFN.sex, IdCardUtils.getSex(map.get(BJFN.cardNo)));
		map.put(BJFN.data + CFN._eleln_(0) + BJFN.cusType, BBDD.CUSTOMER_TYPE_PERSONAL);
		map.put(BJFN.data + CFN._eleln_(0) + BJFN.birthday, IdCardUtils.getBirthday(map.get(BJFN.cardNo)));
		map.put(BJFN.data + CFN._eleln_(0) + BJFN.payCode, BBC.PAY_CODE);

		HashMap<String, String> requestPara = MapTransKeyUtil.TransTd2Bj(map);

		List<BeanStructure> requestBss = new ArrayList<BeanStructure>();
		requestBss.add(new BeanStructure(BJFN.data, CFN._elelx_, UserKey.regist4EleRequest));
		BandUtil.structure(requestPara, requestBss);

		HashMap<String, String> rm = super.requestAndInsertDbCommon(
				requestPara.get(BJFN.data + CFN._eleln_(0) + BJFN.detailNo), requestPara,
				BBC.REQUEST_URL_USER_REGIST_REAL_NAME, UserKey.batchRegistRealnameRequest);
		List<BeanStructure> responseBss = new ArrayList<BeanStructure>();
		responseBss.add(new BeanStructure(BJFN.successData, CFN._elelx_));
		responseBss.add(new BeanStructure(BJFN.errorData, CFN._elelx_));
		BandUtil.analyze(rm, responseBss);
		BandUtil.getSuccessForSingle(rm);
		map.put(BJFN.success, rm.get(BJFN.success));
		map.put(BJFN.data + CFN._eleln_(0) + BJFN.platCust, rm.get(BJFN.successData + CFN._eleln_(0) + BJFN.platCust));

		if (rm.get(BJFN.errorData + CFN._eleln_(0) + BJFN.errorNo) != null) {
			map.put(BJFN.recode, rm.get(BJFN.errorData + CFN._eleln_(0) + BJFN.errorNo));
			map.put(BJFN.remsg, rm.get(BJFN.errorData + CFN._eleln_(0) + BJFN.remsg));
		}

		if (rm.get(BJFN.errorData + CFN._eleln_(0) + BJFN.errorNo) != null
				&& rm.get(BJFN.errorData + CFN._eleln_(0) + BJFN.errorNo).equals(BBDD.error45000)
				&& rm.get(BJFN.success).equals(BBDD.xfalse)) {
			map.put(BJFN.success, BBDD.xtrue);
			map.put(BJFN.data + CFN._eleln_(0) + BJFN.cardNo, rm.get(BJFN.data + CFN._eleln_(0) + BJFN.cardNo));
			map.put(BJFN.data + CFN._eleln_(0) + BJFN.cardNo, rm.get(BJFN.data + CFN._eleln_(0) + BJFN.cardNo));
			map.put(BJFN.data + CFN._eleln_(0) + BJFN.preMobile, rm.get(BJFN.data + CFN._eleln_(0) + BJFN.preMobile));
			map.put(BJFN.data + CFN._eleln_(0) + BJFN.platCust, rm.get(BJFN.data + CFN._eleln_(0) + BJFN.platCust));
		}

		return map;
	}

	@Override
	public HashMap<String, String> changeCard(HashMap<String, String> map) {
		map.put(BJFN.platNo, BBC.PLAT_NO);
		BandUtil.setCommonPara(map);
		String depositNo = userAccountCache.getUserAccoutInfo(map.get(TDFN.userId)).getDepositNo();
		map.put(BJFN.platCust, depositNo);
		String orderNo = BandUtil.getNo(ONP.changeCard);
		map.put(BJFN.orderNo, orderNo);
		map.put(BJFN.totalNum, "1");
		map.put(BJFN.data + CFN._eleln_(0) + BJFN.detailNo, BandUtil.getOneDetailNo(orderNo));
		map.put(BJFN.data + CFN._eleln_(0) + BJFN.cardType, BBDD.CERT_TYPE_ID);
		map.put(BJFN.data + CFN._eleln_(0) + BJFN.type, BBDD.CUSTOMER_TYPE_PERSONAL);
		map.put(BJFN.data + CFN._eleln_(0) + BJFN.payCode, BBC.PAY_CODE_FU_YOU);

		HashMap<String, String> requestPara = MapTransKeyUtil.TransTd2Bj(map);

		HashMap<String, String> rm = super.requestAndInsertDbCommon(
				requestPara.get(BJFN.data + CFN._eleln_(0) + BJFN.detailNo), requestPara,
				BBC.REQUEST_URL_USER_CHANGE_CARD, UserKey.batchChangeCardRequest);

		List<BeanStructure> responseBss = new ArrayList<BeanStructure>();
		responseBss.add(new BeanStructure(BJFN.successData, CFN._elelx_));
		responseBss.add(new BeanStructure(BJFN.errorData, CFN._elelx_));
		BandUtil.analyze(rm, responseBss);
		BandUtil.getSuccessForSingle(rm);
		map.put(BJFN.success, rm.get(BJFN.success));
		map.put(BJFN.recode, rm.get(BJFN.errorNo));
		map.put(BJFN.remsg, rm.get(BJFN.remsg));
		return map;
	}

	@Override
	public HashMap<String, String> messageBoundCardApply(HashMap<String, String> map) {
		map.put(BJFN.platNo, BBC.PLAT_NO);
		BandUtil.setCommonPara(map);
		String depositNo = userAccountCache.getUserAccoutInfo(map.get(TDFN.userId)).getDepositNo();
		map.put(BJFN.platCust, depositNo);
		map.put(BJFN.idType, BBDD.CERT_TYPE_ID);
		map.put(BJFN.cardType, BBDD.BANK_CARD_TYPE_DEBIT);
		map.put(BJFN.payCode, BBC.PAY_CODE);

		HashMap<String, String> requestPara = MapTransKeyUtil.TransTd2Bj(map);

		HashMap<String, String> rm = super.requestAndInsertDbCommon(requestPara.get(BJFN.orderNo), requestPara,
				BBC.REQUEST_URL_USER_MESSAGE_BOUND_CARD_APPLY, UserKey.messageBoundCardApplyRequest);

		List<BeanStructure> responseBss = new ArrayList<BeanStructure>();
		responseBss.add(new BeanStructure(BJFN.successData, CFN._swb_));
		responseBss.add(new BeanStructure(BJFN.errorData, CFN._swb_));
		BandUtil.analyze(rm, responseBss);
		BandUtil.getSuccessForSingle(rm);
		map.put(BJFN.success, rm.get(BJFN.success));

		return map;
	}

	@Override
	public HashMap<String, String> messageBoundCardValidate(HashMap<String, String> map) {
		BandUtil.setCommonPara(map);
		String depositNo = userAccountCache.getUserAccoutInfo("").getDepositNo();
		map.put(BJFN.platCust, depositNo);
		String orderNo = BandUtil.getNo(ONP.messageBoundCardValidate);
		map.put(BJFN.orderNo, orderNo);
		map.put(BJFN.cardType, BBDD.BANK_CARD_TYPE_DEBIT);

		map = MapTransKeyUtil.TransTd2Bj(map);

		HashMap<String, String> rm = super.requestAndInsertDbCommon(map.get(BJFN.orderNo), map,
				BBC.REQUEST_URL_USER_MESSAGE_BOUND_CARD_VALIDATE, UserKey.messageBoundCardValidateRequest);
		BandUtil.getSuccessForSingle(rm);
		map.put(BJFN.success, rm.get(BJFN.success));

		return map;
	}

	@Override
	public HashMap<String, String> boundCard(HashMap<String, String> map) {
		BandUtil.setCommonPara(map);
		String depositNo = userAccountCache.getUserAccoutInfo(map.get(TDFN.userId)).getDepositNo();
		map.put(BJFN.platCust, depositNo);
		String orderNo = BandUtil.getNo(ONP.boundCard);
		map.put(BJFN.orderNo, orderNo);
		map.put(BJFN.type, BBDD.CUSTOMER_TYPE_PERSONAL);
		map.put(BJFN.idType, BBDD.CERT_TYPE_ID);
		map.put(BJFN.cardType, BBDD.BANK_CARD_TYPE_DEBIT);
		map.put(BJFN.payCode, BBC.PAY_CODE_FU_YOU);

		HashMap<String, String> requestPara = MapTransKeyUtil.TransTd2Bj(map);

		HashMap<String, String> rm = super.requestAndInsertDbCommon(requestPara.get(BJFN.orderNo), requestPara,
				BBC.REQUEST_URL_USER_BOUND_CARD, UserKey.boundCardRequest);
		BandUtil.getSuccessForSingle(rm);
		map.put(BJFN.success, rm.get(BJFN.success));
		map.put(BJFN.recode, rm.get(BJFN.errorNo));
		map.put(BJFN.remsg, rm.get(BJFN.remsg));
		return map;
	}

	@Override
	public HashMap<String, String> updateInfo(HashMap<String, String> map) {
		BandUtil.setCommonPara(map);
		String depositNo = userAccountCache.getUserAccoutInfo(map.get(TDFN.userId)).getDepositNo();
		map.put(BJFN.platCust, depositNo);
		String orderNo = BandUtil.getNo(ONP.userInfoChange);
		map.put(BJFN.orderNo, orderNo);

		HashMap<String, String> requestPara = MapTransKeyUtil.TransTd2Bj(map);

		HashMap<String, String> rm = super.requestAndInsertDbCommon(requestPara.get(BJFN.orderNo), requestPara,
				BBC.REQUEST_URL_USER_UPDATE_INFO, UserKey.updateInfoRequest);
		BandUtil.getSuccessForSingle(rm);
		map.put(BJFN.success, rm.get(BJFN.success));
		map.put(BJFN.recode, rm.get(BJFN.errorNo));
		map.put(BJFN.remsg, rm.get(BJFN.remsg));
		return map;
	}

}
