package com.liveus.common.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Desc: 分页DTO
 * @author: Lenovo
 * @Time: 2019/11/1 10:14

 */
@Data
@ApiModel("分页DTO")
public class PageDto {
    @ApiModelProperty("当前页")
    private int page = 1;
    @ApiModelProperty("每一页显示的条数")
    private int pageSize = 10;


}
