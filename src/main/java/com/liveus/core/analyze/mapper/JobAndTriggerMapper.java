package com.liveus.core.analyze.mapper;

/**
 * @Desc:
 * @author: Lenovo
 * @Time: 2019/11/28 15:14
 * @Copyright: © Liveus
 * @Warning: for fun
 */
import com.liveus.core.analyze.pojo.entity.JobAndTrigger;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface JobAndTriggerMapper {
    public List<JobAndTrigger> getJobAndTriggerDetails();
}
