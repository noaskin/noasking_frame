package com.noasking.scheduler.task.service.impl;

import com.noasking.scheduler.task.mapper.TaskFireLogMapper;
import com.noasking.scheduler.task.mapper.TaskGroupMapper;
import com.noasking.scheduler.task.mapper.TaskSchedulerMapper;
import com.noasking.scheduler.task.model.TaskFireLog;
import com.noasking.scheduler.task.model.TaskGroup;
import com.noasking.scheduler.task.model.TaskScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author ShenHuaJie
 * @version 2016年7月1日 上午11:34:59
 */
@Service
public class SchedulerService {

	@Autowired
	private TaskGroupMapper taskGroupMapper;
	@Autowired
	private TaskSchedulerMapper taskSchedulerMapper;
	@Autowired
	private TaskFireLogMapper logMapper;

	@Cacheable("taskGroup")
	public TaskGroup getGroupById(Integer id) {
		return taskGroupMapper.selectByPrimaryKey(id);
	}

	@Cacheable("taskScheduler")
	public TaskScheduler getSchedulerById(Integer id) {
		return taskSchedulerMapper.selectByPrimaryKey(id);
	}

	@Cacheable("taskFireLog")
	public TaskFireLog getFireLogById(Integer id) {
		return logMapper.selectByPrimaryKey(id);
	}

	@Transactional
	@CachePut("taskGroup")
	public TaskGroup updateGroup(TaskGroup record) {
		record.setEnable(true);
		if (record.getId() == null) {
			record.setCreateTime(new Date());
			taskGroupMapper.insert(record);
		} else {
			record.setUpdateTime(new Date());
			taskGroupMapper.updateByPrimaryKey(record);
		}
		return record;
	}

	@Transactional
	@CachePut("taskScheduler")
	public TaskScheduler updateScheduler(TaskScheduler record) {
		record.setEnable(true);
		if (record.getId() == null) {
			record.setCreateTime(new Date());
			taskSchedulerMapper.insert(record);
		} else {
			record.setUpdateTime(new Date());
			taskSchedulerMapper.updateByPrimaryKey(record);
		}
		return record;
	}

	@Transactional
	@CachePut("taskFireLog")
	public TaskFireLog updateLog(TaskFireLog record) {
		if (record.getId() == null) {
			logMapper.insert(record);
		} else {
			logMapper.updateByPrimaryKey(record);
		}
		return record;
	}

	public Tasj

}
