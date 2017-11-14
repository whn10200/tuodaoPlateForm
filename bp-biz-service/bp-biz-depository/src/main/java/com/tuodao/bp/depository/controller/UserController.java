package com.tuodao.bp.depository.controller;

import com.tuodao.bp.activemq.constant.DepositoryMqConstant;
import com.tuodao.bp.depository.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.jms.Queue;
import java.util.HashMap;

@RestController
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Resource(name = "deInUserRegist4Ele")
	private Queue deInUserRegist4Ele;

	@Resource
	private JmsMessagingTemplate jmsMessagingTemplate;

	/*@RequestMapping(value = "depository/user/regist4Ele",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public HashMap<String, String> regist4Ele(@RequestBody HashMap<String,String> requestMap) throws Exception {

		//HashMap<String,String> requestMapx = MapUtils.RequestParameterMap2Map(request.getParameterMap());
		HashMap<String, Object> header = new HashMap<String, Object>();
		header.put(DepositoryMqConstant.DEPOSITORY_HEAD_KEY, DepositoryMqConstant.DE_SELECTOR_VALUE_USER_REGIST_4_ELE);
		jmsMessagingTemplate.convertAndSend(deInUserRegist4Ele, userService.regist4Ele(requestMap), header);

		return null;
	}*/

	
	@RequestMapping(value = "depository/user/regist4Ele",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public HashMap<String, String> regist4Ele(@RequestBody HashMap<String,String> requestMap) {
		return userService.regist4Ele(requestMap);
	}
	 

	@RequestMapping(value = "depository/user/registRealname", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public HashMap<String, String> registRealname(@RequestBody HashMap<String, String> requestMap) {
		return userService.registRealname(requestMap);
	}

	@RequestMapping(value = "depository/user/changeCard", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public HashMap<String, String> changeCard(@RequestBody HashMap<String, String> requestMap) {
		return userService.changeCard(requestMap);
	}

	@RequestMapping(value = "depository/user/messageBoundCardApply", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public HashMap<String, String> messageBoundCardApply(@RequestBody HashMap<String, String> requestMap) {
		return userService.messageBoundCardApply(requestMap);
	}

	@RequestMapping(value = "depository/user/messageBoundCardValidate", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public HashMap<String, String> messageBoundCardValidate(@RequestBody HashMap<String, String> requestMap) {
		return userService.messageBoundCardValidate(requestMap);
	}

	@RequestMapping(value = "depository/user/boundCard", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public HashMap<String, String> boundCard(@RequestBody HashMap<String, String> requestMap) {
		return userService.boundCard(requestMap);
	}

	@RequestMapping(value = "depository/user/updateInfo", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public HashMap<String, String> updateInfo(@RequestBody HashMap<String, String> requestMap) {
		return userService.updateInfo(requestMap);
	}
}
