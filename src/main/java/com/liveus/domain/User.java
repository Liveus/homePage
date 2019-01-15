package com.liveus.domain;

import javax.persistence.*;

@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User() {
    }

    public User(String name, String password, String skill, String profession, String phone, String email, String publicemail) {
        this.name = name;
        this.password = password;
        this.skill = skill;
        this.profession = profession;
        this.phone = phone;
        this.email = email;
        this.publicemail = publicemail;
    }

    /**
     * 登录名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 技术
     */
    private String skill;

    /**
     * 专业
     */
    private String profession;

    /**
     * 手机
     */
    private String phone;

    /**
     * 私有邮箱
     */
    private String email;

    /**
     * 公开邮箱
     */
    @Column(name = "publicEmail")
    private String publicemail;

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
     * 获取登录名
     *
     * @return name - 登录名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置登录名
     *
     * @param name 登录名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取技术
     *
     * @return skill - 技术
     */
    public String getSkill() {
        return skill;
    }

    /**
     * 设置技术
     *
     * @param skill 技术
     */
    public void setSkill(String skill) {
        this.skill = skill == null ? null : skill.trim();
    }

    /**
     * 获取专业
     *
     * @return profession - 专业
     */
    public String getProfession() {
        return profession;
    }

    /**
     * 设置专业
     *
     * @param profession 专业
     */
    public void setProfession(String profession) {
        this.profession = profession == null ? null : profession.trim();
    }

    /**
     * 获取手机
     *
     * @return phone - 手机
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机
     *
     * @param phone 手机
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 获取私有邮箱
     *
     * @return email - 私有邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置私有邮箱
     *
     * @param email 私有邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取公开邮箱
     *
     * @return publicEmail - 公开邮箱
     */
    public String getPublicemail() {
        return publicemail;
    }

    /**
     * 设置公开邮箱
     *
     * @param publicemail 公开邮箱
     */
    public void setPublicemail(String publicemail) {
        this.publicemail = publicemail == null ? null : publicemail.trim();
    }
}