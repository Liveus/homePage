package com.liveus.common.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Desc: base entity
 * @author: Lenovo
 * @Time: 2019/11/7 17:00

 */
@Data
public class BaseEntity implements Serializable {

    @ApiModelProperty(value = "创建人",hidden = true)
    private String createBy;

    @ApiModelProperty(value = "创建时间",hidden = true)
    private Date createTime;

    @ApiModelProperty(value = "修改人",hidden = true)
    private String modifyBy;

    @ApiModelProperty(value = "修改时间",hidden = true)
    private Date modifyTime;

    @ApiModelProperty(value = "状态",hidden = true)
    private String status;
}
