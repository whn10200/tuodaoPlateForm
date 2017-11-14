package com.tuodao.bp.api.facade.controller.common;

import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.client.PushService;
import com.tuodao.bp.api.facade.common.BaseController;
import com.tuodao.bp.model.annotation.AccessToken;
import com.tuodao.bp.model.business.common.input.PushInput;
import com.tuodao.bp.model.enums.AccessType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/router/push")
@RestController
public class FacadePushController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(FacadePushController.class);

	@Autowired
	private PushService pushService;
	
//	@Autowired
//	private RestTemplate rest;
	
//	@RequestMapping(value="/pushMessage",method=RequestMethod.POST)
//	public RespResult<String> pushMessage(PushInput input){
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//		HttpEntity<String> requestEntity = new HttpEntity<String>(JSON.toJSONString(input), headers);
//
//		ResponseEntity<String> entity = rest.exchange("http://localhost:15001/push/pushMessage", HttpMethod.POST,
//				requestEntity, String.class);
//
//		logger.info("entity.getBody : {},statusValue : {}",entity.getBody(),entity.getStatusCodeValue());
//
//		return RespResult.<String>create().setContent(entity.getBody());
//	}

	@RequestMapping(value="/pushMessage",method=RequestMethod.POST)
	@AccessToken(checkAccess=false ,access = {AccessType.PC, AccessType.APP})
	public RespResult<String> pushMessage(PushInput input){
		pushService.pushMessage(input);
		return RespResult.<String>create();
	}
}
