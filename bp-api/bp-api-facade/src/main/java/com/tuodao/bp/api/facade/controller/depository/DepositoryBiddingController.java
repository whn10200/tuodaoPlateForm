package com.tuodao.bp.api.facade.controller.depository;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.client.depository.DepositoryBiddingClient;
import com.tuodao.bp.model.annotation.AccessToken;
import com.tuodao.bp.model.enums.AccessType;


/**
 * @description: 存管，标的相关接口
 * @author: 杨超凯
 * @cmapyright: 拓道金服 Cmapyright (c) 2017
 */
@RequestMapping("/router")
@RestController
public class DepositoryBiddingController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DepositoryBiddingController.class);

	@Autowired
	private DepositoryBiddingClient depositoryBiddingClient;


	@RequestMapping(value = "depository/bidding/publish", produces = MediaType.APPLICATION_JSON_VALUE)
	@AccessToken(access = { AccessType.BANK, AccessType.PC })
	HashMap<String, String> publish(HashMap<String, String> map) {
		HashMap<String, String> re = depositoryBiddingClient.publish(map);
		return re;
	}


	@RequestMapping("depository/bidding/complate")
	public RespResult<HashMap> complate(HashMap<String, String> map) throws Exception {

		HashMap<String, String> re = depositoryBiddingClient.complate(map);

		return RespResult.<HashMap>create().setContent(re);
	}
	
	@RequestMapping("depository/bidding/cancel")
	public RespResult<HashMap> cancel(HashMap<String, String> map) throws Exception {

		HashMap<String, String> re = depositoryBiddingClient.cancel(map);

		return RespResult.<HashMap>create().setContent(re);
	}

	@RequestMapping("depository/bidding/bid")
	public RespResult<HashMap> bid(HashMap<String, String> map) throws Exception {

		HashMap<String, String> re = depositoryBiddingClient.bid(map);

		return RespResult.<HashMap>create().setContent(re);
	}

	@RequestMapping("depository/bidding/chargeOff")
	public RespResult<HashMap> chargeOff(HashMap<String, String> map) throws Exception {

		HashMap<String, String> re = depositoryBiddingClient.chargeOff(map);

		return RespResult.<HashMap>create().setContent(re);
	}

	@RequestMapping("depository/bidding/chargeOffReceive")
	public RespResult<HashMap> chargeOffReceive(HashMap<String, String> map) throws Exception {

		HashMap<String, String> re = depositoryBiddingClient.chargeOffReceive(map);

		return RespResult.<HashMap>create().setContent(re);
	}

	@RequestMapping("depository/bidding/transferDebt")
	public RespResult<HashMap> transferDebt(HashMap<String, String> map) throws Exception {

		HashMap<String, String> re = depositoryBiddingClient.transferDebt(map);

		return RespResult.<HashMap>create().setContent(re);
	}

	@RequestMapping("depository/bidding/repay")
	public RespResult<HashMap> repay(HashMap<String, String> map) throws Exception {

		HashMap<String, String> re = depositoryBiddingClient.repay(map);

		return RespResult.<HashMap>create().setContent(re);
	}

	@RequestMapping("depository/bidding/repayPlaneUpdate")
	public RespResult<HashMap> repayPlaneUpdate(HashMap<String, String> map) throws Exception {

		HashMap<String, String> re = depositoryBiddingClient.repayPlaneUpdate(map);

		return RespResult.<HashMap>create().setContent(re);
	}

	@RequestMapping("depository/bidding/chargeOffChange")
	public RespResult<HashMap> chargeOffChange(HashMap<String, String> map) throws Exception {

		HashMap<String, String> re = depositoryBiddingClient.chargeOffChange(map);

		return RespResult.<HashMap>create().setContent(re);
	}



}