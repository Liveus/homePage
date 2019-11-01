package com.liveus.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Desc: Recommended blog vo
 * @author: Lenovo
 * @Time: 2019/11/1 17:11
 * @Copyright: © 2018 杭州凯立通信有限公司 版权所有
 * @Warning: 本内容仅限于公司内部传阅, 禁止外泄或用于其它商业目的
 */

@Data
@ApiModel("recommended blog vo")
public class BlogRecommendVo {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("title")
    private String title;
}
