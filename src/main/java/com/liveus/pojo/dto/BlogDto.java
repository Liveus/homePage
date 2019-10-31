package com.liveus.pojo.dto;

import lombok.Data;

import java.util.List;

/**
 * @Desc: search dto
 * @author: Lenovo
 * @Time: 2019/10/23 15:06
 * @Copyright: © 2018 杭州凯立通信有限公司 版权所有
 * @Warning: 本内容仅限于公司内部传阅, 禁止外泄或用于其它商业目的
 */
@Data
public class BlogDto {
    private String searchContent;
    private String blogType;
    private List blogClass;
}
