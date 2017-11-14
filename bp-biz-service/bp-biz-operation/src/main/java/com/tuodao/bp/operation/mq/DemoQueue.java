package com.tuodao.bp.operation.mq;

import com.google.common.collect.Lists;
import com.tuodao.bp.model.business.demo.output.DemoOuput;
import com.tuodao.bp.utils.TimeUtils;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DemoQueue {
	
	private static final Logger logger = LoggerFactory.getLogger(DemoQueue.class);

	private static int num = 0;

	private static List<String> list = Lists.newArrayList();

	@JmsListener(destination="useraccount.to.operation-normal")
	public void process(DemoOuput out) {
		String sysTimeSLong = TimeUtils.getSysTimeSLong();
		list.add(sysTimeSLong);
		num ++;
		logger.info("demoQueue.process.out = {}",out);
		
		if(StringUtils.isBlank(out.toString())) {
			logger.info("out is blank...");
		}else {
			logger.info("out = {}",out);
		}
		
		logger.info("process completely....");
		logger.info("num = [{}]",num);
		if(num>6){
			num = 1;
			logger.info("num back to 1");
			logger.info(list.toString());
			list.clear();
		}else{
			throw new RuntimeException("test mail send....and replicated number....");
		}
	}


	@JmsListener(destination="useraccount.to.operation-master-slave-cluster",selector = "groupX='youareright'")
	@Async(value = "cosumerTaskExecutor")
	public DemoOuput processmx(ActiveMQObjectMessage message,DemoOuput out) {
		logger.info("messageX = {}",message);
		logger.info("outX = {}",out);
		logger.info("processX completely....");

		return out;
	}
	@JmsListener(destination="useraccount.to.operation-master-slave-cluster",selector = "groupY='iamareright'")
	@Async(value = "cosumerTaskExecutor")
	public DemoOuput processmy(ActiveMQObjectMessage message,DemoOuput out) {
		logger.info("messageY = {}",message);
		logger.info("outY = {}",out);
		logger.info("processY completely....");

		return out;
	}


	@JmsListener(destination="useraccount.to.operation-master-slave-cluster")
	@Async(value = "cosumerTaskExecutor")
	public DemoOuput processmall(ActiveMQObjectMessage message,DemoOuput out) {
		logger.info("messageALL = {}",message);
		logger.info("outALL = {}",out);
		logger.info("processALL completely....");

		return out;
	}


	@JmsListener(destination="useraccount.to.operation-master-slave-clusterException")
	@Async(value = "cosumerTaskExecutor")
	public DemoOuput processException(ActiveMQObjectMessage message,DemoOuput out) {
		logger.info("message = {}",message);
		logger.info("out = {}",out);

		String sysTimeSLong = TimeUtils.getSysTimeSLong();
		list.add(sysTimeSLong);
		num ++;
		logger.info("num = [{}]",num);
		if(num>6){
			num = 1;
			logger.info("num back to 1");
			logger.info(list.toString());
			list.clear();
		}else{
			throw new RuntimeException("test mail send....and replicated number....");
		}

		logger.info("process completely....");

		return out;
	}

}
