package com.social.service.domain;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
    private String id;

    private String registrationid;

    private String name;

    private String password;

    private String img;

    private Integer sex;

    private String birthday;

    private Integer age;

    private String phone;

    private Date createTime;

    private Date updateTime;

    private String des;

    private static final SimpleDateFormat year = new SimpleDateFormat("yyyy");
    Date date = new Date();

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(String id, String name, String img, Integer sex, String birthday, Integer age, String phone, String des) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.sex = sex;
        this.birthday = birthday;
        if (!StringUtils.isBlank(birthday) && birthday.contains("-")) {
            this.age = Integer.valueOf(year.format(date)) - Integer.valueOf(birthday.split("-")[0]);
        }else {
            this.age = 0;
        }
        this.phone = phone;
        this.des = des;
    }

    public User(String id, String registrationid, String name, String password, String img, Integer sex, String birthday, Integer age, String phone, Date createTime, Date updateTime, String des) {
        this.id = id;
        this.registrationid = registrationid;
        this.name = name;
        this.password = password;
        this.img = img;
        this.sex = sex;
        this.birthday = birthday;
        if (!StringUtils.isBlank(birthday) && birthday.contains("-")) {
            this.age = Integer.valueOf(year.format(date)) - Integer.valueOf(birthday.split("-")[0]);
        }else {
            this.age = 0;
        }
        this.phone = phone;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.des = des;
    }

    public String getRegistrationid() {
        return registrationid;
    }

    public void setRegistrationid(String registrationid) {
        this.registrationid = registrationid;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    public User() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des == null ? null : des.trim();
    }
}
