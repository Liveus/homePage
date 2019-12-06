package com.liveus.common.bean;

/**
 * @Desc:
 * @author: Lenovo
 * @Time: 2019/11/28 15:03
 * @Copyright: © Liveus
 * @Warning: for fun
 */

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public interface BaseJob extends Job{
    public void execute(JobExecutionContext context) throws JobExecutionException;
}
