package com.liveus.core.practice.service;

import com.liveus.core.practice.pojo.dto.BaseSQLDto;
import com.liveus.core.practice.pojo.dto.ExplainDto;
import com.liveus.core.practice.pojo.dto.IndexDto;
import com.liveus.core.practice.pojo.dto.OptimizerTraceDto;
import com.liveus.core.practice.pojo.entity.Index;
import com.liveus.core.practice.pojo.entity.Variable;
import com.liveus.core.practice.pojo.vo.ExplainVo;
import com.liveus.core.practice.pojo.vo.OptimizerTraceVo;

import java.util.List;

/**
 * @Package: com.liveus.core.practice.service
 * @Author: shen2
 * @Description:
 * @Date: 2020/9/15 15:48
 */
public interface MySQLPracticeService {

    /**
     * 查询数据库变量
     * @param dto 入参
     * @return
     */
    List<Variable> selectVariables(BaseSQLDto dto);

    /**
     * 查询索引
     * @param dto
     * @return
     */
    List<Index> selectIndex(IndexDto dto);

    /**
     * explain 分析
     * @param dto
     * @return
     */
    List<ExplainVo> explainMySQL(ExplainDto dto);

    /**
     * OptimizerTraceDto 分析
     * @param dto
     * @return
     */
    List<OptimizerTraceVo> OptimizerTraceMySQL(OptimizerTraceDto dto);
}
