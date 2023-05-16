package com.example.appagile.elements;

import java.io.Serializable;

public class Sach implements Serializable {
    private int id;
    private String name;
    private int price;
    private int maloai;

    public Sach() {
    }

    public Sach(int id, String name, int price, int maloai) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.maloai = maloai;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }

    @Override
    public String toString() {
        return ""+id+"\n"+name+"";
    }
}
