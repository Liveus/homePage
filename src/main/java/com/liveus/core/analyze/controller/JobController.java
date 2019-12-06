package com.liveus.core.analyze.controller;



import java.util.HashMap;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.liveus.core.analyze.pojo.entity.JobAndTrigger;
import com.liveus.core.analyze.service.IJobAndTriggerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Desc:
 * @author: Lenovo
 * @Time: 2019/11/28 15:02
 * @Copyright: © Liveus
 * @Warning: for fun
 */
@RestController
@RequestMapping(value="/job")
public class JobController {

    private static Logger log = LoggerFactory.getLogger(JobController.class);

    private final IJobAndTriggerService iJobAndTriggerService;

    @Autowired
    public JobController(IJobAndTriggerService iJobAndTriggerService) {
        this.iJobAndTriggerService = iJobAndTriggerService;
    }

    /**
    * @Desc:  新增任务
    * @author: shenliqiang
    * @Time: 2019/11/28 17:06
    * @param:
    * @return:
    */

    @PostMapping(value="/addjob")
    public void addjob(@RequestParam(value="jobClassName")String jobClassName, @RequestParam(value="jobGroupName")String jobGroupName,
                       @RequestParam(value="cronExpression")String cronExpression) {
        log.info("--------------------add new job");
        this.iJobAndTriggerService.addJob(jobClassName, jobGroupName, cronExpression);
    }

    /**
    * @Desc:  暂停任务
    * @author: shenliqiang
    * @Time: 2019/11/28 17:05
    * @param: jobClassName,jobGroupName
    * @return:
    */

    @PostMapping(value="/pausejob")
    public void pausejob(@RequestParam(value="jobClassName")String jobClassName, @RequestParam(value="jobGroupName")String jobGroupName) {
        this.iJobAndTriggerService.jobPause(jobClassName, jobGroupName);
    }

    /**
    * @Desc:  恢复任务
    * @author: shenliqiang
    * @Time: 2019/11/28 17:05
    * @param: jobClassName,jobGroupName
    * @return:
    */

    @PostMapping(value="/resumejob")
    public void resumejob(@RequestParam(value="jobClassName")String jobClassName, @RequestParam(value="jobGroupName")String jobGroupName) {
        this.iJobAndTriggerService.jobresume(jobClassName, jobGroupName);
    }

    /**
    * @Desc:  重新设置任务
    * @author: shenliqiang
    * @Time: 2019/11/28 17:05
    * @param: jobClassName,jobGroupName,cronExpression
    * @return:
    */

    @PostMapping(value="/reschedulejob")
    public void rescheduleJob(@RequestParam(value="jobClassName")String jobClassName,
                              @RequestParam(value="jobGroupName")String jobGroupName,
                              @RequestParam(value="cronExpression")String cronExpression) {
        this.iJobAndTriggerService.jobreschedule(jobClassName, jobGroupName, cronExpression);
    }


    /**
    * @Desc:  删除任务
    * @author: shenliqiang
    * @Time: 2019/11/28 17:04
    * @param: jobClassName,jobGroupName
    * @return:
    */

    @PostMapping(value="/deletejob")
    public void deletejob(@RequestParam(value="jobClassName")String jobClassName, @RequestParam(value="jobGroupName")String jobGroupName) {
        this.iJobAndTriggerService.jobdelete(jobClassName, jobGroupName);
    }


    /**
    * @Desc:  查询任务
    * @author: shenliqiang
    * @Time: 2019/11/28 17:04
    * @param: pageNum,pageSize
    * @return:
    */

    @GetMapping(value="/queryjob")
    public Map<String, Object> queryjob(@RequestParam(value="pageNum")Integer pageNum, @RequestParam(value="pageSize")Integer pageSize) {
        PageInfo<JobAndTrigger> jobAndTrigger = iJobAndTriggerService.getJobAndTriggerDetails(pageNum, pageSize);
        Map<String, Object> map = new HashMap<>();
        map.put("JobAndTrigger", jobAndTrigger);
        map.put("number", jobAndTrigger.getTotal());
        return map;
    }
}


