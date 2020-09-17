package com.liveus.core.practice.pojo.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Package: com.liveus.core.practice.pojo.entity
 * @Author: shen2
 * @Description:
 * @Date: 2020/9/15 15:54
 */
@Data
public class Explain {
    @ApiModelProperty("ID")
    private Integer id;
    @ApiModelProperty("查询类型")
    private String selectType;
    @ApiModelProperty("表")
    private String table;
    private String partitions;
    private String type;
    private String possibleKeys;
    private String key;
    private String keyLen;
    private String ref;
    private Integer rows;
    private Integer filtered;
    private String extra;
}
