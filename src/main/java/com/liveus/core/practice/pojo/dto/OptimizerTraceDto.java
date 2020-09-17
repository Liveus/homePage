package com.liveus.core.practice.pojo.dto;

import lombok.Data;

/**
 * @Package: com.liveus.core.practice.pojo.dto
 * @Author: shen2
 * @Description: optimizerTrace 入参
 * @Date: 2020/9/15 17:47
 */
@Data
public class OptimizerTraceDto {
    /**
     * 待分析的sql
     */
    private String sql;
}
