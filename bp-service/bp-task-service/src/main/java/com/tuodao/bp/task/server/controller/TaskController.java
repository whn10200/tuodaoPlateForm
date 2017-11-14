package com.tuodao.bp.task.server.controller;

import com.tuodao.bp.task.server.service.IBusinessNotifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 任务中心
 * 
 * @author HECHUAN
 * 
 * @created 2017年5月18日
 *
 * @version 1.0.0
 *
 * @since 1.0.0
 */
@RequestMapping("/task")
@RestController
public class TaskController {
	
	/** 日志 */
	private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

	@Autowired
	private IBusinessNotifyService businessNotifyService;
	

	@RequestMapping(value="/callBack",method=RequestMethod.GET)
	public String callBack(int taskId) {
		return businessNotifyService.callBack(taskId);
	}
}
