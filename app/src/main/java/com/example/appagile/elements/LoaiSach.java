package com.example.appagile.elements;

import java.io.Serializable;

public class LoaiSach implements Serializable {
    private int id;
    private String name;
    private String loai;

    public LoaiSach() {
    }

    public LoaiSach(int id, String name,String loai) {
        this.id = id;
        this.name = name;
        this.loai = loai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    @Override
    public String toString() {
        return ""+id+"\n"+name+"";
    }
}
