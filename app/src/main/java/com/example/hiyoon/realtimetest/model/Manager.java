package com.example.hiyoon.realtimetest.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Manager {
    private String loginType;
    private String name;
    private String phone;
    private String token;

    public Manager() {
    }

    public Manager(String loginType, String name, String phone, String token) {
        this.loginType = loginType;
        this.name = name;
        this.phone = phone;
        this.token = token;
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
}
