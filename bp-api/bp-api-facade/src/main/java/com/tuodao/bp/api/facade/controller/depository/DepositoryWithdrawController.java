package com.tuodao.bp.api.facade.controller.depository;

import com.alibaba.fastjson.JSONObject;
import com.tuodao.bp.api.facade.client.depository.DepositoryWithdrawClient;
import com.tuodao.bp.model.constant.depository.BJFN;
import com.tuodao.bp.model.constant.depository.TDFN;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @description: 存管，标的相关接口
 * @author: 杨超凯
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@RequestMapping("/router")
@RestController
public class DepositoryWithdrawController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DepositoryWithdrawController.class);

	@Autowired
	private DepositoryWithdrawClient depositoryWithdrawClient;
	
	

	@ResponseBody
	@RequestMapping("beiJingBank/withdraw/notifyInvestor")
	public JSONObject notifyInvestor(HttpServletRequest request, HttpServletResponse response) {

		HashMap<String, String> paraMap = MapUtils.RequestParameterMap2Map(request.getParameterMap());
		paraMap.put(BJFN.requestUrl, request.getRequestURL().toString());
		
		HashMap<String, String> re = depositoryWithdrawClient.notifyInvester(paraMap);

		if (re.get(TDFN.success).equals("true")) {
			JSONObject obj = new JSONObject();
			obj.put("recode", "success");
			return obj;
		}
		return null;
	}

	

}