package com.tuodao.bp.useraccount.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.tuodao.bp.model.business.demo.input.demo.DemoDbInput;
import com.tuodao.bp.model.business.demo.input.demo.DemoInput;
import com.tuodao.bp.model.business.demo.input.demo.ParamInput;
import com.tuodao.bp.model.business.demo.output.DemoBizOutput;
import com.tuodao.bp.model.business.demo.output.DemoOuput;
import com.tuodao.bp.useraccount.future.DemoFuture;
import com.tuodao.bp.useraccount.interceptor.UnableValidate;
import com.tuodao.bp.useraccount.service.IDemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.jms.Queue;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/demo")
public class DemoController {
	
	private static final Logger logger = LoggerFactory.getLogger(DemoController.class);
	
	@Autowired
	private JmsMessagingTemplate jmsTemplate;
	
	@Autowired
	private Queue user2operation;
	
	@Autowired
	private IDemoService demoService;
	
	@Autowired
	private DemoFuture demoFuture;
	
	@Value("${server.port}")
	private String port;

	@UnableValidate
	@RequestMapping(value="/getDemo",method=RequestMethod.POST)
	public DemoOuput getDemo(@RequestParam(value="name") String name) {
		logger.info("name = {}",name);
		
		DemoOuput out = new DemoOuput();
		out.setOutName("fastJSON");
		out.setOutAddress("China-port:"+port);
		
		logger.info("out = {}", out);
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("sleep 2s---");
		return out;
	}
	
	@RequestMapping(value="/entity",method=RequestMethod.POST)
	@ResponseBody
	public DemoOuput getEntity(@RequestBody DemoInput demoInput) {
		logger.info("input = {}",demoInput);
		
		DemoOuput out = new DemoOuput();
		out.setOutName("entity-"+demoInput.getName());
		out.setOutAddress("entity-"+demoInput.getAddress()+"-port:"+port);
		
		logger.info("out = {}", out);
		return out;
	}
	
	@RequestMapping(value="/mq",method=RequestMethod.POST)
	@ResponseBody
	@UnableValidate
	public DemoOuput process(@RequestParam String address) {
		logger.info("address = {}",address);
		
		/*DemoOuput out = new DemoOuput();
		out.setOutName("queue-name");
		out.setOutAddress("queue-"+address+"-port:"+port);
		
		logger.info("out = {}", out);*/

		// 普通发消息
		//jmsTemplate.convertAndSend(user2operation, out);

		// 发消息后等到消息的接收
		// DemoOuput demoOuput = jmsTemplate.convertSendAndReceive("useraccount.to.operation", out, DemoOuput.class);
		// logger.info("demoOuput = {}",demoOuput);

		// 测试selector
		DemoOuput out = new DemoOuput();
		out.setOutName("queue-nameX");
		out.setOutAddress("queueX-"+address+"-port:"+port);

		logger.info("outX = {}", out);

		Map<String,Object> header = Maps.newHashMap();
		header.put("groupX","youareright");
		jmsTemplate.convertAndSend(user2operation,out,header);



		out = new DemoOuput();
		out.setOutName("queue-nameY");
		out.setOutAddress("queueY-"+address+"-port:"+port);

		logger.info("outY = {}", out);
		header.put("groupY","iamareright");
		jmsTemplate.convertAndSend(user2operation,out,header);
		return out;
	}

	
	@RequestMapping(value="/validate",method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@UnableValidate
	public DemoOuput validate(@RequestBody ParamInput input) {
		logger.info("input = {}",input);
		
		DemoOuput out = new DemoOuput();
		out.setOutName("chengdu");
		out.setOutAddress("china-port:"+port);
		
		logger.info("out = {}", out);
		
		return out;
	}
	
	
	/**
	 * demo detail-同步请求
	 * 
	 * @param input
	 *            输入参数
	 * @return
	 */
	@UnableValidate
	@RequestMapping(value = "/demoDetail", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public DemoBizOutput demoDetail(DemoDbInput input) {
		
		logger.info("DemoDbInput = [{}] ",input);
		
		// demo entity
		DemoBizOutput demoEntity = demoService.getDemoEntity(input);

		return demoEntity;
	}
	
	
	/**
	 * demo detail8-异步阻塞式请求
	 * 
	 * @param input
	 *            输入参数
	 * @return
	 */
	@UnableValidate
	@RequestMapping(value = "/demoDetailSync", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public DemoBizOutput demoDetailSync(DemoDbInput input) {
		
		logger.info("DemoDbInput = [{}] ",input);
		
		// demo entity
		DemoBizOutput demoEntity = demoFuture.getSyncDemoEntity(input).join();

		return demoEntity;
	}

	/**
	 * demo list
	 * 
	 * @param input
	 *            输入参数
	 * @return
	 */
	@UnableValidate
	@RequestMapping(value = "/demoList", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<DemoBizOutput> getDemoList(DemoDbInput input) {

		logger.info("DemoDbInput = [{}] ",input);
		
		// demo list
		List<DemoBizOutput> demoList = demoService.getDemoList(input);
		
		return demoList;
	}
	
	
	/**
	 * demo page list
	 * 
	 * @param input
	 *            输入参数
	 * @return
	 */
	@UnableValidate
	@RequestMapping(value = "/demoPageList", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public PageInfo<DemoBizOutput> getDemoPageList(DemoDbInput input) {

		logger.info("DemoDbInput = [{}] ",input);
		
		// demo page list
		PageInfo<DemoBizOutput> demoPageList = demoService.getDemoPageList(input);

		
		return demoPageList;
	}
	
	/**
	 * save demo
	 * 
	 * @param input
	 *            输入参数
	 * @return
	 */
	@UnableValidate
	@RequestMapping(value = "/saveDemo", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public void saveDemo(DemoDbInput input) {

		logger.info("DemoDbInput = [{}] ",input);
		
		demoService.saveDemo(input);
		
		logger.info("save Demo success!!");

	}
	
	
	/**
	 * update demo
	 * 
	 * @param input
	 *            输入参数
	 * @return
	 */
	@UnableValidate
	@RequestMapping(value = "/updateDemo", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public void updateDemo(DemoDbInput input) {

		logger.info("DemoDbInput = [{}] ",input);
		
		demoService.updateDemo(input);
		
		logger.info("update Demo success!!");

	}

	/**
	 * update demo
	 *
	 * @param input
	 *            输入参数
	 * @return
	 */
	@UnableValidate
	@RequestMapping(value = "/saveAndUpdate", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public void saveAndUpdate(DemoDbInput input) {

		logger.info("saveAndUpdate DemoInput = [{}] ",input);

		demoService.saveAndUpdate(input);

		logger.info("update Demo success!!");

	}
	
	
	/**
	 * test transactional
	 * 
	 * @param input
	 *            输入参数
	 * @return
	 */
	@UnableValidate
	@RequestMapping(value = "/transDemo", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public void transDemo(DemoDbInput input) {
		
		logger.info("DemoDbInput = [{}] ",input);

		demoService.testTransactional(input);

		logger.info("trans Demo success!!");
	}

	@UnableValidate
	@RequestMapping(value = "/resolveDemo", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public boolean resolveDemo(com.tuodao.bp.model.business.useraccount.input.DemoInput input){
		logger.info("com.tuodao.bp.model.business.useraccount.input.DemoInput input :[{}]",input);

		return demoService.resolveDemo(input);
	}

	@UnableValidate
	@RequestMapping(value = "/transMap", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public String transMap(@RequestBody Map<String,Object> inputMap){
		logger.info(" inputMap :[{}]",inputMap);
		return "SUCCESS";
	}
}
