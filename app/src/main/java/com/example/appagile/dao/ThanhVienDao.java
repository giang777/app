package com.example.appagile.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appagile.elements.ThanhVien;

import java.util.ArrayList;
import java.util.List;



public class ThanhVienDao {
    private SQLiteDatabase db;

    public ThanhVienDao(Context context) {
        Data data = new Data(context);
        db = data.getWritableDatabase();
    }


    public long insert(ThanhVien tv){
        ContentValues values = new ContentValues();
        values.put("hoTen",tv.getName());
        values.put("namSinh",tv.getDate());
        return db.insert("ThanhVien",null,values);
    }

    public long update(ThanhVien tv){
        ContentValues values = new ContentValues();
        values.put("hoTen",tv.getName());
        values.put("namSinh",tv.getDate());
        return db.update("ThanhVien",values,"maTV=?",new String[]{String.valueOf(tv.getId())});
    }

    public int delete(String id){
        return db.delete("ThanhVien","maTV=?",new String[]{id});
    }

    public int delete_all(){
        return db.delete("ThanhVien",null,null);
    }

    public List<ThanhVien> getAll(){
        String sql = "SELECT * FROM ThanhVien";
        return getData(sql);
    }

    public ThanhVien getID(String id){
        String sql = "SELECT * FROM ThanhVien WHERE maTV=?";
        List<ThanhVien> list = getData(sql,id);
        return list.get(0);
    }

    private List<ThanhVien> getData(String sql, String...selectionArgs){
        List<ThanhVien> list = new ArrayList<ThanhVien>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            ThanhVien tv = new ThanhVien();
            tv.setId(Integer.parseInt(c.getString(0)));
            tv.setName(c.getString(1));
            tv.setDate(c.getString(2));
            list.add(tv);
        }
        return list;
    }
}
