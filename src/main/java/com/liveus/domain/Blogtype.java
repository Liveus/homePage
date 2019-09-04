package com.liveus.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "blogtype")
public class Blogtype {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 类型名称
     */
    @Id
    private String typename;

    /**
     * 该类型blog总数
     */
    private Integer blogsum;

    /**
     * 已删除？1：0
     */
    private Byte deleted;

    private Date createdtime;

    private Date deletedtime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取类型名称
     *
     * @return typename - 类型名称
     */
    public String getTypename() {
        return typename;
    }

    /**
     * 设置类型名称
     *
     * @param typename 类型名称
     */
    public void setTypename(String typename) {
        this.typename = typename == null ? null : typename.trim();
    }

    /**
     * 获取该类型blog总数
     *
     * @return blogsum - 该类型blog总数
     */
    public Integer getBlogsum() {
        return blogsum;
    }

    /**
     * 设置该类型blog总数
     *
     * @param blogsum 该类型blog总数
     */
    public void setBlogsum(Integer blogsum) {
        this.blogsum = blogsum;
    }

    /**
     * 获取已删除？1：0
     *
     * @return deleted - 已删除？1：0
     */
    public Byte getDeleted() {
        return deleted;
    }

    /**
     * 设置已删除？1：0
     *
     * @param deleted 已删除？1：0
     */
    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }

    /**
     * @return createdtime
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * @param createdtime
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * @return deletedtime
     */
    public Date getDeletedtime() {
        return deletedtime;
    }

    /**
     * @param deletedtime
     */
    public void setDeletedtime(Date deletedtime) {
        this.deletedtime = deletedtime;
    }
}