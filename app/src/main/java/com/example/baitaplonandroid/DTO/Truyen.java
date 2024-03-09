package com.example.baitaplonandroid.DTO;

public class Truyen {
    int id;
    String tentruyen;
    String noidung;
    byte[] imagetruyen;

    public Truyen() {
    }

    public Truyen(int id, String tentruyen, String noidung, byte[] imagetruyen) {
        this.id = id;
        this.tentruyen = tentruyen;
        this.noidung = noidung;
        this.imagetruyen = imagetruyen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTentruyen() {
        return tentruyen;
    }

    public void setTentruyen(String tentruyen) {
        this.tentruyen = tentruyen;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public byte[] getImagetruyen() {
        return imagetruyen;
    }

    public void setImagetruyen(byte[] imagetruyen) {
        this.imagetruyen = imagetruyen;
    }
}
