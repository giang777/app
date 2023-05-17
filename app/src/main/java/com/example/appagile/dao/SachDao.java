package com.example.appagile.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appagile.elements.Sach;

import java.util.ArrayList;
import java.util.List;



public class SachDao {
    private SQLiteDatabase db;

    public SachDao(Context context) {
        Data data = new Data(context);
        db = data.getWritableDatabase();
    }


    public long insert(Sach ls){
        ContentValues values = new ContentValues();
        values.put("tenSach",ls.getName());
        values.put("giaThue",ls.getPrice());
        values.put("maLoai",ls.getMaloai());
        return db.insert("Sach",null,values);
    }

    public long update(Sach ls){
        ContentValues values = new ContentValues();
        values.put("tenSach",ls.getName());
        values.put("giaThue",ls.getPrice());
        values.put("maLoai",ls.getMaloai());
        return db.update("Sach",values,"maSach=?",new String[]{String.valueOf(ls.getId())});
    }

    public int delete(String id){
        return db.delete("Sach","maSach=?",new String[]{id});
    }

    public int delete_all(){
        return db.delete("Sach",null,null);
    }

    public List<Sach> getAll(){
        String sql = "SELECT * FROM Sach";
        return getData(sql);
    }

    public Sach getID(String id){
        String sql = "SELECT * FROM Sach WHERE maSach=?";
        List<Sach> list = getData(sql,id);
        return list.get(0);
    }

    private List<Sach> getData(String sql, String...selectionArgs){
        List<Sach> list = new ArrayList<Sach>();
        Cursor c = db.rawQuery(sql,selectionArgs);

        while (c.moveToNext()){
            Sach ls = new Sach();
            ls.setId(Integer.parseInt(c.getString(0)));
            ls.setName(c.getString(1));
            ls.setPrice(Integer.parseInt(c.getString(2)));
            ls.setMaloai(Integer.parseInt(c.getString(3)));
            list.add(ls);
        }

        return list;
    }
}
