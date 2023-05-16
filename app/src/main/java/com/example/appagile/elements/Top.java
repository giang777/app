package com.example.appagile.elements;

import java.io.Serializable;

public class Top implements Serializable {
    private String ten_sach;
    private int so_luong;

    public Top() {
    }

    public Top(String ten_sach, int so_luong) {
        this.ten_sach = ten_sach;
        this.so_luong = so_luong;
    }

    public String getTen_sach() {
        return ten_sach;
    }

    public void setTen_sach(String ten_sach) {
        this.ten_sach = ten_sach;
    }

    public int getSo_luong() {
        return so_luong;
    }

    public void setSo_luong(int so_luong) {
        this.so_luong = so_luong;
    }
}

