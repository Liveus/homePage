package com.liveus.core.practice.pojo.entity;

import lombok.Data;

/**
 * @Package: com.liveus.core.practice.pojo.entity
 * @Author: shen2
 * @Description:
 * @Date: 2020/9/15 17:48
 */
@Data
public class OptimizerTrace {

    private String query;

    private String trace;

    private String MISSING_BYTES_BEYOND_MAX_MEM_SIZE;

    private String INSUFFICIENT_PRIVILEGES;
}
