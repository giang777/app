package com.example.appagile.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appagile.elements.LoaiSach;

import java.util.ArrayList;
import java.util.List;



public class LoaiSachDao {
    private SQLiteDatabase db;

    public LoaiSachDao(Context context) {
        Data data = new Data(context);
        db = data.getWritableDatabase();
    }


    public long insert(LoaiSach ls){
        ContentValues values = new ContentValues();
        values.put("tenLoai",ls.getName());
        values.put("nhaSX",ls.getLoai());
        return db.insert("LoaiSach",null,values);
    }

    public long update(LoaiSach ls){
        ContentValues values = new ContentValues();
        values.put("tenLoai",ls.getName());
        values.put("nhaSX",ls.getLoai());
        return db.update("LoaiSach",values,"maLoai=?",new String[]{String.valueOf(ls.getId())});
    }

    public int delete(String id){
        return db.delete("LoaiSach","maLoai=?",new String[]{id});
    }

    public int delete_all(){
        return db.delete("LoaiSach",null,null);
    }

    public List<LoaiSach> getAll(){
        String sql = "SELECT * FROM LoaiSach";
        return getData(sql);
    }

    public LoaiSach getID(String id){
        String sql = "SELECT * FROM LoaiSach WHERE maLoai=?";
        List<LoaiSach> list = getData(sql,id);
        return list.get(0);
    }

    private List<LoaiSach> getData(String sql, String...selectionArgs){
        List<LoaiSach> list = new ArrayList<LoaiSach>();
        Cursor c = db.rawQuery(sql,selectionArgs);

        while (c.moveToNext()){
           LoaiSach ls = new LoaiSach();
           ls.setId(Integer.parseInt(c.getString(0)));
           ls.setLoai(c.getString(1));
           ls.setName(c.getString(2));
           list.add(ls);
        }

        return list;
    }
}
