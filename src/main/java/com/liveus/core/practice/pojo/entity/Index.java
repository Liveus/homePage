package com.liveus.core.practice.pojo.entity;

import com.liveus.core.practice.pojo.dto.BaseSQLDto;
import lombok.Data;

/**
 * @Package: com.liveus.core.practice.pojo.entity
 * @Author: shen2
 * @Description:
 * @Date: 2020/9/18 14:05
 */
@Data
public class Index {

    private String table;

    private String nonUnique;

    private String keyName;

    private String seqInIndex;

    private String columnName;

    private String collation;

    private String cardinality;

    private String subPart;

    private String packed;

    private String isNull;

    private String indexType;

    private String comment;

    private String indexComment;
}
