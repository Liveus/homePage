package com.liveus.pojo.dto;

import com.liveus.pojo.common.PageDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Desc: search dto
 * @author: Lenovo
 * @Time: 2019/10/23 15:06
 * @Copyright: © 2018 杭州凯立通信有限公司 版权所有
 * @Warning: 本内容仅限于公司内部传阅, 禁止外泄或用于其它商业目的
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

}
