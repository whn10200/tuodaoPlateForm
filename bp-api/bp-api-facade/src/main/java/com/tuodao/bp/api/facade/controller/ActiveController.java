package com.tuodao.bp.api.facade.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.client.DemoService;
import com.tuodao.bp.api.facade.common.BaseController;
import com.tuodao.bp.api.facade.domain.ResultInput;
import com.tuodao.bp.api.facade.domain.ResultOutput;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.demo.input.demo.DemoInput;
import com.tuodao.bp.model.business.demo.input.demo.ParamInput;
import com.tuodao.bp.model.business.demo.output.DemoOuput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RequestMapping("/router")
@RestController
public class ActiveController extends BaseController{
	
	
	private static final Logger logger = LoggerFactory.getLogger(ActiveController.class);
	
	private RestTemplate rest;
	
	@Autowired
	private DemoService demoService;
	
	@RequestMapping(value="/active",method=RequestMethod.POST)
	public RespResult<String> queryActive(){
		logger.info("query active start.....");
		
		ResponseEntity<String> entity = rest.getForEntity("http://RIBBON-MEMBER/member/getActive", String.class);
		
		logger.info("entity.getBody : {},statusValue : {}",entity.getBody(),entity.getStatusCodeValue());
		
		logger.info("query active end.....");
		
		return RespResult.<String>create().setContent(entity.getBody());
	}
	
	
	@RequestMapping(value="/p",method=RequestMethod.POST)
	public RespResult<String> p(@RequestParam String userNo){
		logger.info("query p start.....，userNo = {}",userNo);
		
		
		logger.info("query p end.....");
		
		return RespResult.<String>create().setContent("SUCCESS");
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public RespResult<ResultOutput> test(ResultInput input) {
		long start = System.currentTimeMillis();
		logger.info("input:{}", input);
		
		ResultOutput out = new ResultOutput();
		
		System.out.println(System.currentTimeMillis() - start);
		
		return RespResult.<ResultOutput> create().setContent(out);
	}

	@ResponseBody
	@RequestMapping(value = "/demo", method = RequestMethod.POST)
	@HystrixCommand(
			fallbackMethod="testDemo",
			commandProperties = {
					@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
					@HystrixProperty(name = "execution.isolation.strategy",value = "THREAD")
			},
			threadPoolProperties = {
					@HystrixProperty(name = "coreSize", value = "30"),
					@HystrixProperty(name = "maxQueueSize", value = "101"),
					@HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
					@HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
					@HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
					@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440")
			}
	)
	public RespResult<String> test(BasePojo input) {
		long start = System.currentTimeMillis();

		DemoOuput demo = demoService.getDemo("张三");

		System.out.println(System.currentTimeMillis() - start);

		return RespResult.<String>create().setContent(demo.toString());
	}

	@ResponseBody
	public RespResult<String> testDemo(BasePojo input) {
		long start = System.currentTimeMillis();

		DemoOuput demo = new DemoOuput();
		demo.setOutAddress("fallback address");
		demo.setOutName("fallback name");

		System.out.println(System.currentTimeMillis() - start);

		return RespResult.<String> create().setContent(demo.toString());
	}
	
	@ResponseBody
	@RequestMapping(value = "/entity", method = RequestMethod.POST)
	public RespResult<String> entity(DemoInput input) {
		long start = System.currentTimeMillis();
		
		logger.info("input = {}",input);
		
		DemoOuput demo = demoService.getEntity(input);
		
		System.out.println(System.currentTimeMillis() - start);
		
		return RespResult.<String> create().setContent(demo.toString());
	}
	
	@ResponseBody
	@RequestMapping(value = "/mq", method = RequestMethod.POST)
	public RespResult<String> mq(@RequestParam String address) {
		long start = System.currentTimeMillis();
		
		logger.info("address = {}",address);
		
		DemoOuput demo = demoService.process(address);
		
		System.out.println(System.currentTimeMillis() - start);
		
		return RespResult.<String> create().setContent(demo.toString());
	}

	
	@ResponseBody
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public RespResult<String> validate(@RequestParam String address) {
		long start = System.currentTimeMillis();
		
		logger.info("address = {}",address);
		
		ParamInput input = new ParamInput();
		input.setKey("key-param");
		input.setValue("value - param");
		input.setAge(0);
		
		DemoOuput demo = demoService.validate(input);
		
		System.out.println(System.currentTimeMillis() - start);
		
		return RespResult.<String> create().setContent(demo.toString());
	}
}
