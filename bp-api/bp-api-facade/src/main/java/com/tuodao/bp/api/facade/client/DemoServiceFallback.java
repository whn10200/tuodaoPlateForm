package com.tuodao.bp.api.facade.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tuodao.bp.model.business.demo.input.demo.DemoInput;
import com.tuodao.bp.model.business.demo.input.demo.ParamInput;
import com.tuodao.bp.model.business.demo.output.DemoOuput;

@Component
public class DemoServiceFallback implements DemoService {

	private static final Logger logger = LoggerFactory.getLogger(DemoServiceFallback.class);

	@Override
	public DemoOuput getDemo(String name) {
		logger.info("----fallback name = {}", name);

		DemoOuput out = new DemoOuput();
		out.setOutAddress("fallbackAddress");
		out.setOutName("fallbackName");
		return out;
	}

	@Override
	public DemoOuput getEntity(DemoInput demoInput) {
		logger.info("----fallback demoInput = {}", demoInput);

		DemoOuput out = new DemoOuput();
		out.setOutAddress("fallbackAddress--input");
		out.setOutName("fallbackName--input");
		return out;
	}

	@Override
	public DemoOuput process(String address) {

		logger.info("----fallback address = {}", address);

		DemoOuput out = new DemoOuput();
		out.setOutAddress("fallbackAddress--input-address-queue");
		out.setOutName("fallbackName--input-address-queue");
		return out;
	}

	@Override
	public DemoOuput validate(ParamInput input) {
		logger.info("----fallback input = {}", input);

		DemoOuput out = new DemoOuput();
		out.setOutAddress("fallbackAddress--input-validate");
		out.setOutName("fallbackName--input-validate");
		return out;
	}
}
