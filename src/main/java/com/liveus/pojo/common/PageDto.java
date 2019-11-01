package com.liveus.pojo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Desc: 分页DTO
 * @author: Lenovo
 * @Time: 2019/11/1 10:14
 * @Copyright: © 2018 杭州凯立通信有限公司 版权所有
 * @Warning: 本内容仅限于公司内部传阅, 禁止外泄或用于其它商业目的
 */
@Data
@ApiModel("分页DTO")
public class PageDto {
    @ApiModelProperty("当前页")
    private int page = 1;
    @ApiModelProperty("每一页显示的条数")
    private int pageSize = 10;


}
