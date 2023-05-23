package com.example.appagile.elements;

import java.io.Serializable;

public class LoaiSach implements Serializable {
    private int id;
    private String nameLs;

    public LoaiSach() {
    }

    public LoaiSach(int id, String nameLs) {
        this.id = id;
        this.nameLs = nameLs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameLs() {
        return nameLs;
    }

    public void setNameLs(String nameLs) {
        this.nameLs = nameLs;
    }

    @Override
    public String toString() {
        return ""+id+"\n"+nameLs+"";
    }
}
