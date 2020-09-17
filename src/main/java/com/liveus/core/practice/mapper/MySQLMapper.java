package com.liveus.core.practice.mapper;

import com.liveus.core.practice.pojo.dto.ExplainDto;
import com.liveus.core.practice.pojo.dto.OptimizerTraceDto;
import com.liveus.core.practice.pojo.entity.Explain;
import com.liveus.core.practice.pojo.entity.OptimizerTrace;
import com.liveus.core.user.pojo.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface MySQLMapper {

    List<Explain> explainQuery(ExplainDto dto);

    List<OptimizerTrace> optimizerTraceQuery(OptimizerTraceDto dto);

}