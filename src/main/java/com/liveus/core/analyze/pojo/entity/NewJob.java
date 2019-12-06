package com.liveus.core.analyze.pojo.entity;

/**
 * @Desc:
 * @author: Lenovo
 * @Time: 2019/11/28 15:29
 * @Copyright: © Liveus
 * @Warning: for fun
 */
import java.util.Date;

import com.liveus.common.bean.BaseJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class NewJob implements BaseJob {

    private static Logger _log = LoggerFactory.getLogger(NewJob.class);

    public NewJob() {

    }

    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        _log.info("New Job执行时间: " + new Date());

    }
}
