package com.liveus.core.user.pojo.vo;

import io.swagger.annotations.ApiParam;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Desc:
 * @author: Lenovo
 * @Time: 2019/10/16 16:46
 * @Copyright: © 2018 杭州凯立通信有限公司 版权所有
 * @Warning: 本内容仅限于公司内部传阅, 禁止外泄或用于其它商业目的
 */
public class BlogtypeVo implements Serializable {

    @ApiParam(value = "id")
    private Integer id;

    @ApiParam(value = "分类名称")
    private String typeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "BlogtypeVo{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogtypeVo that = (BlogtypeVo) o;
        return Objects.equals(typeName, that.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeName);
    }

    public BlogtypeVo() {
    }

    public BlogtypeVo(String typeName) {
        this.typeName = typeName;
    }
}
