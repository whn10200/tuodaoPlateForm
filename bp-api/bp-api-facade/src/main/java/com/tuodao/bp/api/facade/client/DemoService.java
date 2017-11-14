package com.tuodao.bp.api.facade.client;

import com.tuodao.bp.model.business.demo.input.demo.DemoInput;
import com.tuodao.bp.model.business.demo.input.demo.ParamInput;
import com.tuodao.bp.model.business.demo.output.DemoOuput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="BIZ-USER-ACCOUNT",fallback=DemoServiceFallback.class)
public interface DemoService {
	
	@RequestMapping(value="/demo/getDemo",method=RequestMethod.POST)
	public DemoOuput getDemo(@RequestParam(value="name") String name);
	
	@RequestMapping(value="/demo/entity",method=RequestMethod.POST)
	public DemoOuput getEntity(@RequestBody DemoInput demoInput);
	
	@RequestMapping(value="/demo/mq",method=RequestMethod.POST)
	public DemoOuput process(@RequestParam(value="address") String address);
	
	@RequestMapping(value="/demo/validate",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public DemoOuput validate(@RequestBody ParamInput input);
}
