package com.social.service.domain;

import java.util.Date;

public class User {
    private Integer id;

    private String name;

    private String password;

    private String phone;

    private String des;

    private Date create_time;

    private Date update_time;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(String name, String password, String phone, String des) {
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.des = des;
    }

    public User(Integer id, String name, String phone, String des) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.des = des;
    }

    public User(Integer id, String name, String password, String phone, Date create_time, Date update_time, String des) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.create_time = create_time;
        this.update_time = update_time;
        this.des = des;
    }

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des == null ? null : des.trim();
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }
}
