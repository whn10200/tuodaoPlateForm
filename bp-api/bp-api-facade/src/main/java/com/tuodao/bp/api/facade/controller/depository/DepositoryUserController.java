package com.tuodao.bp.api.facade.controller.depository;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.client.depository.DepositoryUserClient;
import com.tuodao.bp.model.annotation.AccessToken;
import com.tuodao.bp.model.business.useraccount.input.OpenDepositCallbackInput;
import com.tuodao.bp.model.business.useraccount.input.OpenDepositInput;
import com.tuodao.bp.model.enums.AccessType;

/**
 * @description: 存管，标的相关接口
 * @author: 杨超凯
 * @cmap yright: 拓道金服 Cmap yright (c) 2017
 */
@RequestMapping("/router")
@RestController
public class DepositoryUserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DepositoryUserController.class);

	@Autowired
	private DepositoryUserClient depositoryUserClient;

	@RequestMapping("depository/user/regist4Ele")
	@AccessToken(access = { AccessType.BANK, AccessType.PC })
	public RespResult<HashMap<String, String>> regist4Ele(HttpServletRequest request) throws Exception {

		HashMap<String, String> map = MapUtils.RequestParameterMap2Map(request.getParameterMap());
		OpenDepositInput a = new OpenDepositInput ();
		a.setUserId(map.get("user_id"));
		a.setRealName(map.get("real_name"));
		a.setMobile(map.get("mobile"));
		a.setReservationMobile(map.get("reservation_mobile"));
		a.setBankNum(map.get("bank_num"));
		a.setIdCard(map.get("id_card"));
		
		
		map = depositoryUserClient.regist4Ele(a.toHashMap());

		OpenDepositCallbackInput b= new OpenDepositCallbackInput();
		b.fromHashMap(map);
		
		return RespResult.<HashMap<String, String>>create().setContent(map);
	}

	@RequestMapping("depository/user/registRealname")
	@AccessToken(access = { AccessType.BANK, AccessType.PC })
	public RespResult<HashMap> registRealname(HashMap<String, String> map) throws Exception {

		HashMap res = depositoryUserClient.registRealname(map);

		return RespResult.<HashMap>create().setContent(res);
	}

	@RequestMapping("depository/user/changeCard")
	@AccessToken(access = { AccessType.BANK, AccessType.PC })
	public RespResult<HashMap> changeCard(HashMap<String, String> map) throws Exception {

		HashMap res = depositoryUserClient.changeCard(map);

		return RespResult.<HashMap>create().setContent(res);
	}

	@RequestMapping("depository/user/messageBoundCardApply")
	@AccessToken(access = { AccessType.BANK, AccessType.PC })
	public RespResult<HashMap> messageBoundCardApply(HashMap<String, String> map) throws Exception {

		HashMap res = depositoryUserClient.messageBoundCardApply(map);

		return RespResult.<HashMap>create().setContent(res);
	}

	@RequestMapping("depository/user/messageBoundCardValidate")
	@AccessToken(access = { AccessType.BANK, AccessType.PC })
	public RespResult<HashMap> messageBoundCardValidate(HashMap<String, String> map) throws Exception {

		HashMap res = depositoryUserClient.messageBoundCardValidate(map);

		return RespResult.<HashMap>create().setContent(res);
	}

	@RequestMapping("depository/user/boundCard")
	@AccessToken(access = { AccessType.BANK, AccessType.PC })
	public RespResult<HashMap> boundCard(HashMap<String, String> map) throws Exception {

		HashMap res = depositoryUserClient.boundCard(map);

		return RespResult.<HashMap>create().setContent(res);
	}

	@RequestMapping("depository/user/updateInfo")
	@AccessToken(access = { AccessType.BANK, AccessType.PC })
	public RespResult<HashMap> updateInfo(HashMap<String, String> map) throws Exception {

		HashMap res = depositoryUserClient.updateInfo(map);

		return RespResult.<HashMap>create().setContent(res);
	}

}