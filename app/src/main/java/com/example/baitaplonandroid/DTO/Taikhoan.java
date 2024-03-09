package com.example.baitaplonandroid.DTO;

public class Taikhoan {
    String uername;
    String password;


    public Taikhoan() {
    }
    public Taikhoan(String uername, String password) {
        this.uername = uername;
        this.password = password;

    }

    public String getUername() {
        return uername;
    }

    public void setUername(String uername) {
        this.uername = uername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
