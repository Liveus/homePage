package com.liveus.core.user.pojo.vo;

import com.liveus.common.bean.BaseVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Desc: blog vo
 * @author: Lenovo
 * @Time: 2019/11/1 10:25

 */
@EqualsAndHashCode(callSuper=false)
@Data
@ApiModel("blog列表VO")
public class BlogVo extends BaseVo {

    /*** 主键*/
    private Integer id;

    /*** 标题 */
    private String title;

    /*** 文本内容*/
    private String text;

    /*** 文章标签*/
    private String blogtag;

    /**
     * 分类专栏
     */
    private String blogclass;

    /**
     * 文章类型
     */
    private String blogtype;
}
