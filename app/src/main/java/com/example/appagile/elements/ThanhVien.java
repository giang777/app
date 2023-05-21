package com.example.appagile.elements;

import java.io.Serializable;

public class ThanhVien implements Serializable {
    private int id;
    private String name;
    private String sđt;

    public ThanhVien() {
    }

    public ThanhVien(int id, String name, String sđt) {
        this.id = id;
        this.name = name;
        this.sđt = sđt;
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

    public String getSđt() {
        return sđt;
    }

    public void setSđt(String sđt) {
        this.sđt = sđt;
    }

    @Override
    public String toString() {
        return ""+id+"\n"+name+"";
    }
}
