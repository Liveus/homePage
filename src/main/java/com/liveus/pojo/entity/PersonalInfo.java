package com.liveus.pojo.entity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "personalinfo")
public class PersonalInfo implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nickName")
    private String nickname;

    private String introduce;

    private String motto;

    private Byte sex;

    private Integer age;

    @Column(name = "GithubAddress")
    private String githubaddress;

    @Column(name = "CSDNAddress")
    private String csdnaddress;

    @Column(name = "JuejinAddress")
    private String juejinaddress;

    private String passwd;

    private String icon;

    private String skills;

    private String email;

    /**
     * @return ID
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
     * @return nickName
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * @return introduce
     */
    public String getIntroduce() {
        return introduce;
    }

    /**
     * @param introduce
     */
    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    /**
     * @return motto
     */
    public String getMotto() {
        return motto;
    }

    /**
     * @param motto
     */
    public void setMotto(String motto) {
        this.motto = motto == null ? null : motto.trim();
    }

    /**
     * @return sex
     */
    public Byte getSex() {
        return sex;
    }

    /**
     * @param sex
     */
    public void setSex(Byte sex) {
        this.sex = sex;
    }

    /**
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * @return GithubAddress
     */
    public String getGithubaddress() {
        return githubaddress;
    }

    /**
     * @param githubaddress
     */
    public void setGithubaddress(String githubaddress) {
        this.githubaddress = githubaddress == null ? null : githubaddress.trim();
    }

    /**
     * @return CSDNAddress
     */
    public String getCsdnaddress() {
        return csdnaddress;
    }

    /**
     * @param csdnaddress
     */
    public void setCsdnaddress(String csdnaddress) {
        this.csdnaddress = csdnaddress == null ? null : csdnaddress.trim();
    }

    /**
     * @return JuejinAddress
     */
    public String getJuejinaddress() {
        return juejinaddress;
    }

    /**
     * @param juejinaddress
     */
    public void setJuejinaddress(String juejinaddress) {
        this.juejinaddress = juejinaddress == null ? null : juejinaddress.trim();
    }

    /**
     * @return passwd
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * @param passwd
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    /**
     * @return icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    /**
     * @return skills
     */
    public String getSkills() {
        return skills;
    }

    /**
     * @param skills
     */
    public void setSkills(String skills) {
        this.skills = skills == null ? null : skills.trim();
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
}