package com.noasking.scheduler.task.bean;

import com.noasking.scheduler.task.model.TaskScheduler;

import java.io.Serializable;


/**
 * 计划任务信息
 */
@SuppressWarnings("serial")
public class TaskSchedulerBean extends TaskScheduler implements Serializable {

	private String groupName;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
