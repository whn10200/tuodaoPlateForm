package com.tuodao.bp.api.facade.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.client.DemoClient;
import com.tuodao.bp.api.facade.service.useraccount.IDemoService;
import com.tuodao.bp.model.business.demo.input.demo.DemoDbInput;
import com.tuodao.bp.model.business.demo.output.DemoBizOutput;
import com.tuodao.bp.model.business.useraccount.input.DemoInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * demo controller
 * 
 * @author hechuan
 *
 * @created 2017年8月30日
 *
 * @since 1.0.0
 */
@RequestMapping("/router")
@RestController
public class DemoApiController {

	/** 日志 */
	private static final Logger logger = LoggerFactory.getLogger(DemoApiController.class);
	
	@Autowired
	private DemoClient demoClient;

	@Autowired
	private IDemoService demoService;
	
	
	/**
	 * demo detail
	 * 
	 * @param input
	 *            输入参数
	 * @return
	 */
	@RequestMapping(value = "/demoDetail", method = RequestMethod.POST)
	public RespResult<DemoBizOutput> demoDetail(DemoDbInput input) {
		
		logger.info("facade DemoDbInput = [{}] ",input);
		
		// demo entity
		DemoBizOutput demoEntity = demoClient.demoDetail(input);

		return RespResult.<DemoBizOutput>create().setContent(demoEntity);
	}

	/**
	 * demo list
	 * 
	 * @param input
	 *            输入参数
	 * @return
	 */

	@RequestMapping(value = "/demoList", method = RequestMethod.POST)
	public RespResult<List<DemoBizOutput>> getDemoList(DemoDbInput input) {

		logger.info("facade DemoDbInput = [{}] ",input);
		
		//input.setPhone("");
		
		// demo list
		List<DemoBizOutput> demoList = demoClient.getDemoList(input);
		
		return RespResult.<List<DemoBizOutput>>create().setContent(demoList);
	}
	
	
	/**
	 * demo page list
	 * 
	 * @param input
	 *            输入参数
	 * @return
	 */

	@RequestMapping(value = "/demoPageList", method = RequestMethod.POST)
	public RespResult<PageInfo<DemoBizOutput>> getDemoPageList(DemoDbInput input) {

		logger.info("facade DemoDbInput = [{}] ",input);
		
		// demo page list
		PageInfo<DemoBizOutput> demoPageList = demoClient.getDemoPageList(input);

		
		return RespResult.<PageInfo<DemoBizOutput>>create().setContent(demoPageList);
	}
	
	/**
	 * save demo
	 * 
	 * @param input
	 *            输入参数
	 * @return
	 */

	@RequestMapping(value = "/saveDemo", method = RequestMethod.POST)
	public RespResult<String> saveDemo(DemoDbInput input) {

		logger.info("facade DemoDbInput = [{}] ",input);
		
		demoClient.saveDemo(input);
		
		logger.info("facade save Demo success!!");
		
		return RespResult.<String>create();
	}
	
	
	/**
	 * update demo
	 * 
	 * @param input
	 *            输入参数
	 * @return
	 */

	@RequestMapping(value = "/updateDemo", method = RequestMethod.POST)
	public RespResult<String> updateDemo(DemoDbInput input) {

		logger.info("facade DemoDbInput = [{}] ",input);
		
		demoClient.updateDemo(input);
		
		logger.info("facade update Demo success!!");
		
		return RespResult.<String>create();
	}

	/**
	 * update demo
	 *
	 * @param input
	 *            输入参数
	 * @return
	 */

	@RequestMapping(value = "/saveAndUpdate", method = RequestMethod.POST)
	public RespResult<String> saveAndUpdate(DemoDbInput input) {

		logger.info("facade saveAndUpdate DemoDbInput = [{}] ",input);

		demoClient.saveAndUpdate(input);

		logger.info("facade saveAndUpdate Demo success!!");

		return RespResult.<String>create();
	}
	
	
	/**
	 * test transactional
	 * 
	 * @param input
	 *            输入参数
	 * @return
	 */

	@RequestMapping(value = "/transDemo", method = RequestMethod.POST)
	public RespResult<String> transDemo(DemoDbInput input) {
		
		logger.info("facade DemoDbInput = [{}] ",input);

		demoClient.transDemo(input);

		logger.info("facade trans Demo success!!");
		
		return RespResult.<String>create();
	}


	@RequestMapping(value = "/paramDemo", method = RequestMethod.POST)
	public RespResult<String> paramDemo(DemoDbInput input, HttpServletRequest request) {

		logger.info("facade DemoDbInput = [{}] ",input);

		String remoteAddr = request.getRemoteAddr();
		logger.info("remoteAddr = {}",remoteAddr);

		logger.info("facade trans Demo success!!");

		return RespResult.<String>create();
	}

	@RequestMapping(value = "/resolveDemo", method = RequestMethod.POST)
	public RespResult<String> resolveDemo(DemoInput input) {

		logger.info("facade DemoDbInput = [{}] ",input);

		boolean result = demoService.getResult(input);

		logger.info("facade trans Demo success!!");

		return RespResult.<String>create().setSuccess(result);
	}

	@RequestMapping(value = "/transMap", method = RequestMethod.POST)
	public RespResult<String> transMap(DemoInput input){
		logger.info("facade DemoDbInput = [{}] ",input);

		Map<String,Object> inputMap = Maps.newHashMap();
		inputMap.put("mobile",input.getMobile());
		inputMap.put("userId",input.getUserId());
		String result = demoClient.transMap(inputMap);

		logger.info("facade trans transMap success!!");

		return RespResult.<String>create().setContent(result);
	}
	
}
