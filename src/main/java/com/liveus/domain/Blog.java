package com.liveus.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String summary;

    @Column(name = "uploadTime")
    private Date uploadtime;

    /**
     * 文章类别
     */
    private String type;

    /**
     * 阅读数
     */
    @Column(name = "readSum")
    private Integer readsum;

    /**
     * 完成？1：0
     */
    private Byte isfinished;

    private String content;

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
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * @return summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary
     */
    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    /**
     * @return uploadTime
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
     * 获取文章类别
     *
     * @return type - 文章类别
     */
    public String getType() {
        return type;
    }

    /**
     * 设置文章类别
     *
     * @param type 文章类别
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 获取阅读数
     *
     * @return readSum - 阅读数
     */
    public Integer getReadsum() {
        return readsum;
    }

    /**
     * 设置阅读数
     *
     * @param readsum 阅读数
     */
    public void setReadsum(Integer readsum) {
        this.readsum = readsum;
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
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * @return html
     */
    public String getHtml() {
        return html;
    }

    /**
     * @param html
     */
    public void setHtml(String html) {
        this.html = html == null ? null : html.trim();
    }
}