package com.liveus.core.user.pojo.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "user")
public class UserEntity implements Serializable {
    @Id
    @TableId(type= IdType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String password;

    private String skill;

    private String profession;

    private String phone;

    private String email;

    /**
     * publicEmail
     */
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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * @return skill
     */
    public String getSkill() {
        return skill;
    }

    /**
     * @param skill
     */
    public void setSkill(String skill) {
        this.skill = skill == null ? null : skill.trim();
    }

    /**
     * @return profession
     */
    public String getProfession() {
        return profession;
    }

    /**
     * @param profession
     */
    public void setProfession(String profession) {
        this.profession = profession == null ? null : profession.trim();
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
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

    /**
     * 获取publicEmail
     *
     * @return publicemail - publicEmail
     */
    public String getPublicemail() {
        return publicemail;
    }

    /**
     * 设置publicEmail
     *
     * @param publicemail publicEmail
     */
    public void setPublicemail(String publicemail) {
        this.publicemail = publicemail == null ? null : publicemail.trim();
    }

    public UserEntity() {
    }

    public UserEntity(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", skill='" + skill + '\'' +
                ", profession='" + profession + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", publicemail='" + publicemail + '\'' +
                '}';
    }
}