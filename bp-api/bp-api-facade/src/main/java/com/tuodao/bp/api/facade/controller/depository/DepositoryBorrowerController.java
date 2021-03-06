package com.tuodao.bp.api.facade.controller.depository;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.client.depository.DepositoryBorrowerClient;

/**
 * @description: 存管，标的相关接口
 * @author: 杨超凯
 * @cmap yright: 拓道金服 Cmap yright (c) 2017
 */
@RequestMapping("/router")
@RestController
public class DepositoryBorrowerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DepositoryBorrowerController.class);

	@Autowired
	private DepositoryBorrowerClient depositoryBorrowerClient;

	@RequestMapping("depository/borrower/payBak")
	public RespResult<HashMap> payBack(HashMap<String,String> map) throws Exception {

		HashMap res = depositoryBorrowerClient.payBack(map );

		return RespResult.<HashMap>create().setContent(res);
	}
	

}