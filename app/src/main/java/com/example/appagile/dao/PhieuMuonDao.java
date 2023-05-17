package com.example.appagile.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appagile.elements.PhieuMuon;

import java.util.ArrayList;
import java.util.List;



public class PhieuMuonDao {
    private SQLiteDatabase db;

    public PhieuMuonDao(Context context) {
        Data data = new Data(context);
        db = data.getWritableDatabase();
    }


    public long insert(PhieuMuon phieuMuon){
        ContentValues values = new ContentValues();
        values.put("maTT",phieuMuon.getMaTT());
        values.put("maTV",phieuMuon.getMaTV());
        values.put("maSach",phieuMuon.getMaSach());
        values.put("tienThue",phieuMuon.getPrice());
        values.put("ngay",phieuMuon.getDate());
        values.put("traSach",phieuMuon.getTrangThai());
        return db.insert("PhieuMuon",null,values);
    }

    public long update(PhieuMuon phieuMuon){
        ContentValues values = new ContentValues();
        values.put("maTT",phieuMuon.getMaTT());
        values.put("maTV",phieuMuon.getMaTV());
        values.put("maSach",phieuMuon.getMaSach());
        values.put("tienThue",phieuMuon.getPrice());
        values.put("ngay",phieuMuon.getDate());
        values.put("traSach",phieuMuon.getTrangThai());
        return db.update("PhieuMuon",values,"maPM=?",new String[]{String.valueOf(phieuMuon.getId())});
    }

    public int delete(String id){
        return db.delete("PhieuMuon","maPM=?",new String[]{id});
    }

    public int delete_all(){
        return db.delete("PhieuMuon",null,null);
    }

    public List<PhieuMuon> getAll(){
        String sql = "SELECT * FROM PhieuMuon";
        return getData(sql);
    }

    public PhieuMuon getID(String id){
        String sql = "SELECT * FROM PhieuMuon WHERE maPM=?";
        List<PhieuMuon> list = getData(sql,id);
        return list.get(0);
    }

    private List<PhieuMuon> getData(String sql, String...selectionArgs){
        List<PhieuMuon> list = new ArrayList<PhieuMuon>();
        Cursor c = db.rawQuery(sql,selectionArgs);

        while (c.moveToNext()){
            PhieuMuon phieuMuon = new PhieuMuon();
            phieuMuon.setId(Integer.parseInt(c.getString(0)));
            phieuMuon.setMaTT(c.getString(1));
            phieuMuon.setMaTV(Integer.parseInt(c.getString(2)));
            phieuMuon.setMaSach(Integer.parseInt(c.getString(3)));
            phieuMuon.setPrice(Integer.parseInt(c.getString(4)));
            phieuMuon.setDate(c.getString(5));
            phieuMuon.setTrangThai(Integer.parseInt(c.getString(6)));
            list.add(phieuMuon);
        }

        return list;
    }
}
