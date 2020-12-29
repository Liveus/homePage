package com.liveus.core.practice.mapper;

import com.liveus.core.practice.pojo.dto.BaseSQLDto;
import com.liveus.core.practice.pojo.dto.ExplainDto;
import com.liveus.core.practice.pojo.dto.IndexDto;
import com.liveus.core.practice.pojo.dto.OptimizerTraceDto;
import com.liveus.core.practice.pojo.entity.Explain;
import com.liveus.core.practice.pojo.entity.Index;
import com.liveus.core.practice.pojo.entity.OptimizerTrace;
import com.liveus.core.practice.pojo.entity.Variable;
import com.liveus.core.user.pojo.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface MySQLMapper {
    void openOptimizerTrace();

    void closeOptimizerTrace();

    /**
     * 查询sql
     * @param dto 入参
     * @return 结果
     */
    List<OptimizerTrace> doQuery(OptimizerTraceDto dto);

    /**
     * explain
     * @param dto 入参
     * @return 结果
     */
    List<Explain> explainQuery(ExplainDto dto);

    /**
     * optimizerTrace
     * @param dto 入参
     * @return 结果
     */
    List<OptimizerTrace> optimizerTraceQuery(OptimizerTraceDto dto);

    /**
     * 查询变量
     * @param baseSQLDto 入参
     * @return 结果
     */
    List<Variable> selectVariables(BaseSQLDto baseSQLDto);

    /**
     * 查询索引
     * @param indexDto
     * @return
     */
    List<Index> selectIndex(IndexDto indexDto);
}