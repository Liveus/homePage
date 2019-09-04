package com.liveus.domain;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "liveus")
public class Liveus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Pic")
    private String pic;

    @Column(name = "nickName")
    private String nickname;

    /**
     * Github
     */
    @Column(name = "Github")
    private String github;

    /**
     * 掘金
     */
    private String juejin;

    /**
     * CSDN
     */
    @Column(name = "CSDN")
    private String csdn;

    /**
     * 简书
     */
    private String jianshu;

    private String skills;

    private String moto;

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
     * @return Pic
     */
    public String getPic() {
        return pic;
    }

    /**
     * @param pic
     */
    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
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
     * 获取Github
     *
     * @return Github - Github
     */
    public String getGithub() {
        return github;
    }

    /**
     * 设置Github
     *
     * @param github Github
     */
    public void setGithub(String github) {
        this.github = github == null ? null : github.trim();
    }

    /**
     * 获取掘金
     *
     * @return juejin - 掘金
     */
    public String getJuejin() {
        return juejin;
    }

    /**
     * 设置掘金
     *
     * @param juejin 掘金
     */
    public void setJuejin(String juejin) {
        this.juejin = juejin == null ? null : juejin.trim();
    }

    /**
     * 获取CSDN
     *
     * @return CSDN - CSDN
     */
    public String getCsdn() {
        return csdn;
    }

    /**
     * 设置CSDN
     *
     * @param csdn CSDN
     */
    public void setCsdn(String csdn) {
        this.csdn = csdn == null ? null : csdn.trim();
    }

    /**
     * 获取简书
     *
     * @return jianshu - 简书
     */
    public String getJianshu() {
        return jianshu;
    }

    /**
     * 设置简书
     *
     * @param jianshu 简书
     */
    public void setJianshu(String jianshu) {
        this.jianshu = jianshu == null ? null : jianshu.trim();
    }

    /**
     * @return skills
     */
    public Object getSkills() {
        return skills;
    }

    /**
     * @param skills
     */
    public void setSkills(String skills) {
        this.skills = skills;
    }

    /**
     * @return moto
     */
    public String getMoto() {
        return moto;
    }

    /**
     * @param moto
     */
    public void setMoto(String moto) {
        this.moto = moto == null ? null : moto.trim();
    }

    @Override
    public String toString() {
        return "Liveus{" +
                "id=" + id +
                ", pic='" + pic + '\'' +
                ", nickname='" + nickname + '\'' +
                ", github='" + github + '\'' +
                ", juejin='" + juejin + '\'' +
                ", csdn='" + csdn + '\'' +
                ", jianshu='" + jianshu + '\'' +
                ", skills=" + skills +
                ", moto='" + moto + '\'' +
                '}';
    }

}