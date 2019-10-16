package com.liveus.pojo.entity;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**标题*/
    private String title;

    /*** 内容*/
    private String content;

    /*** html内容*/
    private String html;

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
    private Integer create_by;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 更新人
     */
    private Integer update_by;

    /**
     * 更新时间
     */
    private Date update_time;

    /*** 已删除？1：0*/
    private Byte deleted;

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", html='" + html + '\'' +
                ", theme='" + theme + '\'' +
                ", blogtag='" + blogtag + '\'' +
                ", blogclass='" + blogclass + '\'' +
                ", blogtype='" + blogtype + '\'' +
                ", sourcename='" + sourcename + '\'' +
                ", sourcelink='" + sourcelink + '\'' +
                ", isfinished=" + isfinished +
                ", create_by=" + create_by +
                ", create_time=" + create_time +
                ", update_by=" + update_by +
                ", update_time=" + update_time +
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

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
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

    public Integer getCreate_by() {
        return create_by;
    }

    public void setCreate_by(Integer create_by) {
        this.create_by = create_by;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Integer getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(Integer update_by) {
        this.update_by = update_by;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public Byte getDeleted() {
        return deleted;
    }

    public void setDeleted(Byte deleted) {
        this.deleted = deleted;
    }
}