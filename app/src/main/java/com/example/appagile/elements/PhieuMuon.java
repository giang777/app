package com.example.appagile.elements;

import java.io.Serializable;

public class PhieuMuon implements Serializable {
    private int id;
    private String maTT;
    private int maTV;
    private int maSach;
    private int price;
    private String date;
    private int trangThai;


    public PhieuMuon() {
    }

    public PhieuMuon(int id, String maTT, int maTV, int maSach, int price, String date, int trangThai) {
        this.id = id;
        this.maTT = maTT;
        this.maTV = maTV;
        this.maSach = maSach;
        this.price = price;
        this.date = date;
        this.trangThai = trangThai;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
}
