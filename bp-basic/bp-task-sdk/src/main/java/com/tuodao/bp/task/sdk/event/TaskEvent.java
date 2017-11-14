package com.tuodao.bp.task.sdk.event;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.tuodao.bp.task.sdk.domain.ErrorResult;

import java.util.List;

/**
 * taskEvent
 * 
 * @author hechuan
 *
 * @created 2017年6月8日
 *
 * @version 1.0.0
 */
public class TaskEvent {

	public TaskEvent() {

	}

	public TaskEvent(String taskId) {
		this.taskId = taskId;
	}

	private String taskId;

	private List<ErrorResult> resultList = Lists.newArrayList();

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public List<ErrorResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<ErrorResult> resultList) {
		this.resultList = resultList;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this).omitNullValues().add("taskId", taskId).add("resultList",resultList).toString();
	}

}
