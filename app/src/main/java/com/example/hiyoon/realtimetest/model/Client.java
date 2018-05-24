package com.example.hiyoon.realtimetest.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Client {
    private String phone;
    private String token;

    public Client() {

    }

    public Client(String phone, String token) {
        this.phone = phone;
        this.token = token;
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
