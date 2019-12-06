package com.liveus.core.user.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Desc: Recommended blog vo
 * @author: Lenovo
 * @Time: 2019/11/1 17:11

 */

@Data
@ApiModel("recommended blog vo")
public class BlogRecommendVo {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("title")
    private String title;
}
