package com.tuodao.bp.task.server.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.MoreObjects;

@Configuration
@ConfigurationProperties(prefix = "bp.task")
public class TaskProperties {

	/** 时长 */
	private String durtion;

	/** 间隔时间 */
	private String interval;

	public String getDurtion() {
		return durtion;
	}

	public void setDurtion(String durtion) {
		this.durtion = durtion;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("durtion", durtion)
				.add("interval", interval)
				.toString();
	}

}
