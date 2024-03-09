package com.example.baitaplonandroid.DTO;

public class Truyenlv {
    private int idTruyen;
    private String username;  // Thêm thuộc tính username để lưu thông tin về người dùng
    private String nameTr;
    private byte[] image;

    public Truyenlv() {
        // Hàm khởi tạo mặc định
    }

    public Truyenlv(int idTruyen, String username, String nameTr, byte[] image) {
        this.idTruyen = idTruyen;
        this.username = username;
        this.nameTr = nameTr;
        this.image = image;
    }

    public int getIdTruyen() {
        return idTruyen;
    }

    public void setIdTruyen(int idTruyen) {
        this.idTruyen = idTruyen;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNameTr() {
        return nameTr;
    }

    public void setNameTr(String nameTr) {
        this.nameTr = nameTr;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
