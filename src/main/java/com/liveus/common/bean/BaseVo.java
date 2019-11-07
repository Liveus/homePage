package com.liveus.common.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Desc: basevo
 * @author: Lenovo
 * @Time: 2019/11/1 10:26
 * @Copyright: © 2018 杭州凯立通信有限公司 版权所有
 * @Warning: 本内容仅限于公司内部传阅, 禁止外泄或用于其它商业目的
 */
@Data
@ApiModel("公共Vo")
public class BaseVo implements Serializable {
    @ApiModelProperty("创建人")
    private String createName;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改人")
    private String modifyName;

    @ApiModelProperty("修改时间")
    private Date modifyTime;

    @ApiModelProperty("备注")
    private String remark;

}
