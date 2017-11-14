package com.tuodao.bp.useraccount.future;

import java.util.concurrent.CompletableFuture;

import com.tuodao.bp.useraccount.service.IDemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tuodao.bp.model.business.demo.input.demo.DemoDbInput;
import com.tuodao.bp.model.business.demo.output.DemoBizOutput;

@Component
public class DemoFuture {

	/** 日志 */
	private static final Logger logger = LoggerFactory.getLogger(DemoFuture.class);

	@Autowired
	private IDemoService demoService;

	public CompletableFuture<DemoBizOutput> getSyncDemoEntity(DemoDbInput input) {
		return CompletableFuture.<DemoBizOutput>supplyAsync(()->{
			return demoService.getDemoEntity(input);
		});
	}
	
	

}
