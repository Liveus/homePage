package com.liveus.pojo.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 总结
     */
    private String summary;

    private Date uploadtime;

    /**
     * blog类型
     */
    private String type;

    /**
     * 阅读数
     */
    private String readsum;

    /**
     * 完成？1：0
     */
    private Byte isfinished;

    /**
     * 更新blog
     */
    private Date updatetime;

    /**
     * 已删除？1：0
     */
    private Byte deleted;

    private Date deletedtime;

    /**
     * 内容
     */
    private String content;

    /**
     * html内容
     */
    private String html;

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
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取总结
     *
     * @return summary - 总结
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 设置总结
     *
     * @param summary 总结
     */
    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    /**
     * @return uploadtime
     */
    public Date getUploadtime() {
        return uploadtime;
    }

    /**
     * @param uploadtime
     */
    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }

    /**
     * 获取blog类型
     *
     * @return type - blog类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置blog类型
     *
     * @param type blog类型
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取阅读数
     *
     * @return readsum - 阅读数
     */
    public String getReadsum() {
        return readsum;
    }

    /**
     * 设置阅读数
     *
     * @param readsum 阅读数
     */
    public void setReadsum(String readsum) {
        this.readsum = readsum == null ? null : readsum.trim();
    }

    /**
     * 获取完成？1：0
     *
     * @return isfinished - 完成？1：0
     */
    public Byte getIsfinished() {
        return isfinished;
    }

    /**
     * 设置完成？1：0
     *
     * @param isfinished 完成？1：0
     */
    public void setIsfinished(Byte isfinished) {
        this.isfinished = isfinished;
    }

    /**
     * 获取更新blog
     *
     * @return updatetime - 更新blog
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * 设置更新blog
     *
     * @param updatetime 更新blog
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
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

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 获取html内容
     *
     * @return html - html内容
     */
    public String getHtml() {
        return html;
    }

    /**
     * 设置html内容
     *
     * @param html html内容
     */
    public void setHtml(String html) {
        this.html = html == null ? null : html.trim();
    }
}