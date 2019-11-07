package com.liveus.core.user.pojo.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;
import javax.persistence.*;

@Table(name = "message")
public class Message {
    @Id
    @TableId(type= IdType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ip;

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", messagetimestamp=" + messagetimestamp +
                ", context='" + context + '\'' +
                '}';
    }

    /**
     * 留言时间
     */
    private Date messagetimestamp;

    private String context;

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
     * @return ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * 获取留言时间
     *
     * @return messagetimestamp - 留言时间
     */
    public Date getMessagetimestamp() {
        return messagetimestamp;
    }

    /**
     * 设置留言时间
     *
     * @param messagetimestamp 留言时间
     */
    public void setMessagetimestamp(Date messagetimestamp) {
        this.messagetimestamp = messagetimestamp;
    }

    /**
     * @return context
     */
    public String getContext() {
        return context;
    }

    /**
     * @param context
     */
    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }
}