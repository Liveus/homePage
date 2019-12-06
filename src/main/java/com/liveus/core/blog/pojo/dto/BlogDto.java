package com.liveus.core.blog.pojo.dto;

import com.liveus.common.bean.PageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Desc: search dto
 * @author: Lenovo
 * @Time: 2019/10/23 15:06

 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("分页查询dto")
public class BlogDto extends PageDto {

    @ApiModelProperty("搜索内容")
    private String searchContent;

    @ApiModelProperty("blog类型")
    private String blogType;

    @ApiModelProperty("分类专栏")
    private List blogClass;

    @ApiModelProperty("状态")
    private  Integer isfinished;

}
