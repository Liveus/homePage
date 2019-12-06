package com.liveus.core.analyze.service.impl;

/**
 * @Desc:
 * @author: Lenovo
 * @Time: 2019/11/28 15:12
 * @Copyright: © Liveus
 * @Warning: for fun
 */
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.liveus.common.bean.BaseJob;
import com.liveus.common.exception.BusinessException;
import com.liveus.core.analyze.controller.JobController;
import com.liveus.core.analyze.mapper.JobAndTriggerMapper;
import com.liveus.core.analyze.pojo.entity.JobAndTrigger;
import com.liveus.core.analyze.service.IJobAndTriggerService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service
public class JobAndTriggerImpl implements IJobAndTriggerService {

    private static Logger log = LoggerFactory.getLogger(JobController.class);

    @Autowired
    private JobAndTriggerMapper jobAndTriggerMapper;

    //加入Qulifier注解，通过名称注入bean
    @Autowired
    @Qualifier("Scheduler")
    private Scheduler scheduler;

    public void addJob(String jobClassName, String jobGroupName, String cronExpression){
        // 启动调度器
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        //构建job信息
        JobDetail jobDetail = null;
        try {
            jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass()).withIdentity(jobClassName, jobGroupName).build();
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        //表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        //按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName).withSchedule(scheduleBuilder).build();
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            log.info("------------创建定时任务失败"+e);
            throw new BusinessException("创建定时任务失败");
        }
    }

    public void jobPause(String jobClassName, String jobGroupName)  {
        try {
            scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void jobresume(String jobClassName, String jobGroupName)  {
        try {
            scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void jobreschedule(String jobClassName, String jobGroupName, String cronExpression)  {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            System.out.println("更新定时任务失败"+e);
            throw new BusinessException("更新定时任务失败");
        }
    }

    public void jobdelete(String jobClassName, String jobGroupName)  {
        try {
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
            scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<JobAndTrigger> list = jobAndTriggerMapper.getJobAndTriggerDetails();
        PageInfo<JobAndTrigger> page = new PageInfo<JobAndTrigger>(list);
        return page;
    }

    private static BaseJob getClass(String classname) throws InstantiationException, IllegalAccessException, IllegalArgumentException, NoSuchMethodException, SecurityException, ClassNotFoundException, InvocationTargetException {
        Class<?> class1 = Class.forName(classname);
        return (BaseJob)class1.getDeclaredConstructor().newInstance();
    }
}
