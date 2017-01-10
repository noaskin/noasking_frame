package com.noasking.scheduler.task.api.impl;

import com.noasking.common.dubbo.spring.annotation.DubboService;
import com.noasking.scheduler.task.api.ISchedulerFSV;
import com.noasking.scheduler.task.bean.TaskScheduled;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by MaJing on 2017/1/9.
 */
@DubboService(interfaceClass = ISchedulerFSV.class)
public class SchedulerFSVImpl implements ISchedulerFSV {

    @Autowired
    private org.ibase4j.scheduler.manager.SchedulerManager schedulerManager;

    // 获取所有作业
    public List<TaskScheduled> getAllTaskDetail() {
        return schedulerManager.getAllJobDetail();
    }

    // 执行作业
    public boolean execTask(String taskGroup, String taskName) {
        TaskScheduled taskScheduler = new TaskScheduled();
        taskScheduler.setTaskGroup(taskGroup);
        taskScheduler.setTaskName(taskName);
        return schedulerManager.runJob(taskScheduler);
    }

    // 暂停/恢复作业
    public boolean openCloseTask(String taskGroup, String taskName, String status) {
        TaskScheduled taskScheduler = new TaskScheduled();
        taskScheduler.setTaskGroup(taskGroup);
        taskScheduler.setTaskName(taskName);
        if ("start".equals(status)) {
            return schedulerManager.resumeJob(taskScheduler);
        } else if ("stop".equals(status)) {
            return schedulerManager.stopJob(taskScheduler);
        }
        return false;
    }

}
