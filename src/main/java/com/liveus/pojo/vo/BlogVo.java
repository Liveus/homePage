package com.liveus.pojo.vo;

import com.liveus.pojo.common.BaseVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Desc: blog vo
 * @author: Lenovo
 * @Time: 2019/11/1 10:25
 * @Copyright: © 2018 杭州凯立通信有限公司 版权所有
 * @Warning: 本内容仅限于公司内部传阅, 禁止外泄或用于其它商业目的
 */
@Data
@ApiModel("blog列表VO")
public class BlogVo extends BaseVo {

    /*** 主键*/
    private Integer id;

    /*** 标题 */
    private String title;

    /*** html内容*/
    private String content;

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
