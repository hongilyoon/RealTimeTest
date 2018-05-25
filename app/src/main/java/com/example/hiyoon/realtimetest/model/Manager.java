package com.example.hiyoon.realtimetest.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Manager {
    private String loginType;
    private String name;
    private String phone;
    private String token;
    private String id;
    private String loginTypeAndId;

    public Manager() {
    }

    public Manager(String loginType, String name, String phone, String token, String id) {
        this.loginType = loginType;
        this.name = name;
        this.phone = phone;
        this.token = token;
        this.id = id;
        this.loginTypeAndId = String.format("%s_%s", loginType, id);
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginTypeAndId() {
        return loginTypeAndId;
    }

    public void setLoginTypeAndId(String loginTypeAndId) {
        this.loginTypeAndId = loginTypeAndId;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "loginType='" + loginType + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", token='" + token + '\'' +
                ", id='" + id + '\'' +
                ", loginTypeAndId='" + loginTypeAndId + '\'' +
                '}';
    }
}
