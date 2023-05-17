package com.example.appagile.elements;

import java.io.Serializable;

public class ThuThu implements Serializable {
   private String htTT,tkTT,mkTT;

    public ThuThu() {
    }

    public ThuThu( String htTT, String tkTT, String mkTT) {
        this.htTT = htTT;
        this.tkTT = tkTT;
        this.mkTT = mkTT;
    }

    public String getHtTT() {
        return htTT;
    }

    public void setHtTT(String htTT) {
        this.htTT = htTT;
    }

    public String getTkTT() {
        return tkTT;
    }

    public void setTkTT(String tkTT) {
        this.tkTT = tkTT;
    }

    public String getMkTT() {
        return mkTT;
    }

    public void setMkTT(String mkTT) {
        this.mkTT = mkTT;
    }
}
