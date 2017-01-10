package com.noasking.scheduler.task.api;

import com.noasking.page.PageInfo;
import com.noasking.scheduler.task.bean.TaskScheduled;
import com.noasking.scheduler.task.bean.TaskSchedulerBean;
import com.noasking.scheduler.task.model.TaskFireLog;
import com.noasking.scheduler.task.model.TaskGroup;
import com.noasking.scheduler.task.model.TaskScheduler;

import java.util.List;
import java.util.Map;

/**
 * Created by MaJing on 2017/1/9.
 */
public interface ISchedulerFSV {

    /** 获取所有任务 */
    public List<TaskScheduled> getAllTaskDetail();

    /** 执行任务 */
    public boolean execTask(String taskGroup, String taskName);

    /** 启停 */
    public boolean openCloseTask(String taskGroup, String taskName, String status);

    public TaskGroup getGroupById(Integer id);

    public TaskGroup updateGroup(TaskGroup record);

    public PageInfo<TaskGroup> queryGroup(Map<String, Object> params);

    public TaskScheduler getSchedulerById(Integer id);

    public TaskScheduler updateScheduler(TaskScheduler record);

    public TaskFireLog updateLog(TaskFireLog record);

    public PageInfo<TaskSchedulerBean> queryScheduler(Map<String, Object> params);

    public TaskFireLog getFireLogById(Integer id);

    public PageInfo<TaskFireLog> queryLog(Map<String, Object> params);

}
