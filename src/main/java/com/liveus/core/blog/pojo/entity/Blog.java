package com.liveus.core.blog.pojo.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "blog")
public class Blog implements Serializable {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @TableId(type= IdType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**标题*/
    private String title;

    /*** 内容*/
    private String content;

    /*** html内容*/
    private String text;

    /**
     * 代码主题样式
     */
    private String theme;

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

    /**
     * 资源名称
     */
    private String sourcename;

    /**
     * 资源路径
     */
    private String sourcelink;

    /*** 完成？1：0*/
    private Byte isfinished;

    /**
     * 创建人
     */
    private Integer createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private Integer updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /*** 已删除？1：0*/
    private Byte deleted;

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", text='" + text + '\'' +
                ", theme='" + theme + '\'' +
                ", blogtag='" + blogtag + '\'' +
                ", blogclass='" + blogclass + '\'' +
                ", blogtype='" + blogtype + '\'' +
                ", sourcename='" + sourcename + '\'' +
                ", sourcelink='" + sourcelink + '\'' +
                ", isfinished=" + isfinished +
                ", createBy=" + createBy +
                ", createTime=" + createTime +
                ", updateBy=" + updateBy +
                ", updateTime=" + updateTime +
                ", deleted=" + deleted +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getText() {
        return text;
    }

    public void setHtml(String text) {
        this.text = text;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getBlogtag() {
        return blogtag;
    }

    public void setBlogtag(String blogtag) {
        this.blogtag = blogtag;
    }

    public String getBlogclass() {
        return blogclass;
    }

    public void setBlogclass(String blogclass) {
        this.blogclass = blogclass;
    }

    public String getBlogtype() {
        return blogtype;
    }

    public void setBlogtype(String blogtype) {
        this.blogtype = blogtype;
    }

    public String getSourcename() {
        return sourcename;
    }

    public void setSourcename(String sourcename) {
        this.sourcename = sourcename;
    }

    public String getSourcelink() {
        return sourcelink;
    }

    public void setSourcelink(String sourcelink) {
        this.sourcelink = sourcelink;
    }

    public Byte getIsfinished() {
        return isfinished;
    }

    public void setIsfinished(Byte isfinished) {
        this.isfinished = isfinished;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }
}