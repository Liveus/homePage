package com.liveus.core.practice.pojo.dto;

import lombok.Data;

/**
 * @Package: com.liveus.core.practice.pojo.dto
 * @Author: shen2
 * @Description:
 * @Date: 2020/9/18 10:59
 */
@Data
public class BaseSQLDto {
    /**
     *待分析的sql
     */
    private String sql;

    /**
     * 变量
     */
    private String variables;
    
}
