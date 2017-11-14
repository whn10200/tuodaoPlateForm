package com.tuodao.bp.api.facade.controller.depository;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.client.depository.DepositoryRechargeClient;
import com.tuodao.bp.model.business.useraccount.input.OpenDepositInput;

/**
 * @description: 存管，标的相关接口
 * @author: 杨超凯
 * @cmap yright: 拓道金服 Cmap yright (c) 2017
 */
@RequestMapping("/router")
@RestController
public class DepositoryRechargeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DepositoryRechargeController.class);

	@Autowired
	private DepositoryRechargeClient depositoryRechargeClient;

	@RequestMapping("depository/recharge/gatewayRecharge")
	public RespResult<HashMap> gatewayRecharge(HashMap<String,String> map) throws Exception {

		HashMap res = depositoryRechargeClient.gatewayRecharge(map );

		return RespResult.<HashMap>create().setContent(res);
	}

	@RequestMapping("depository/recharge/rechargeReceive")
	public RespResult<HashMap> rechargeReceive(HashMap<String,String> map) throws Exception {

		HashMap res = depositoryRechargeClient.rechargeReceive(map );

		return RespResult.<HashMap>create().setContent(res);
	}

	@RequestMapping("depository/recharge/quickRechargeApply")
	public RespResult<HashMap> quickRechargeApply(HashMap<String,String> map) throws Exception {

		HashMap res = depositoryRechargeClient.quickRechargeApply(map );

		return RespResult.<HashMap>create().setContent(res);
	}

	@RequestMapping("depository/recharge/quickRechargeConfirm")
	public RespResult<HashMap> quickRechargeConfirm(HashMap<String,String> map) throws Exception {

		HashMap res = depositoryRechargeClient.quickRechargeConfirm(map );

		return RespResult.<HashMap>create().setContent(res);
	}

	@RequestMapping("depository/recharge/borrowCutRepay")
	public RespResult<HashMap> borrowCutRepay(HashMap<String,String> map) throws Exception {

		HashMap res = depositoryRechargeClient.borrowCutRepay(map );

		return RespResult.<HashMap>create().setContent(res);
	}

}