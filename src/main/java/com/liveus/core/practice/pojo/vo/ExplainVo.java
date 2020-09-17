package com.liveus.core.practice.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Package: com.liveus.core.practice.pojo.vo
 * @Author: shen2
 * @Description: explain 出参
 * @Date: 2020/9/15 15:51
 */
@Data
public class ExplainVo {
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
