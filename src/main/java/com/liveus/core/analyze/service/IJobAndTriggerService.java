package com.liveus.core.analyze.service;

import com.github.pagehelper.PageInfo;
import com.liveus.core.analyze.pojo.entity.JobAndTrigger;

/**
 * @Desc:
 * @author: Lenovo
 * @Time: 2019/11/28 15:08
 * @Copyright: © Liveus
 * @Warning: for fun
 */
public interface IJobAndTriggerService {

    void addJob(String jobClassName, String jobGroupName, String cronExpression);

    void jobPause(String jobClassName, String jobGroupName);

    void jobresume(String jobClassName, String jobGroupName);

    void jobreschedule(String jobClassName, String jobGroupName, String cronExpression);

    void jobdelete(String jobClassName, String jobGroupName);

    PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize);
}
