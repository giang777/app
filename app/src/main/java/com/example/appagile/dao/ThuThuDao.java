package com.example.appagile.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appagile.elements.ThuThu;

import java.util.ArrayList;
import java.util.List;



public class ThuThuDao {
    private SQLiteDatabase db;

    public ThuThuDao(Context context) {
        Data dbHelper = new Data(context);
        db = dbHelper.getWritableDatabase();
    }


    public long insert(ThuThu odj){
        ContentValues values = new ContentValues();
        values.put("hoTen",odj.getHtTT());
        values.put("taikhoanTT",odj.getTkTT());
        values.put("matKhau",odj.getMkTT());
        return db.insert("ThuThu",null,values);
    }


    public long update(ThuThu odj){
        ContentValues values = new ContentValues();
        values.put("hoTen",odj.getHtTT());
        values.put("taikhoanTT",odj.getTkTT());
        values.put("matKhau",odj.getMkTT());
        return db.update("ThuThu",values,"taiKhoanTT=?",new String[]{String.valueOf(odj.getTkTT())});
    }

    public int delete(String id){
        return db.delete("ThuThu","maTT=?",new String[]{id});
    }

    public List<ThuThu> getAll(){
        String sql = "SELECT * FROM ThuThu";
        return getData(sql);
    }

    public ThuThu getID(String id){
        String sql = "SELECT * FROM ThuThu WHERE taikhoanTT=?";
        List<ThuThu> list = getData(sql,id);
        return list.get(0);
    }

    @SuppressLint("Range")
    private List<ThuThu> getData(String sql, String...selectionArgs){
        List<ThuThu> list = new ArrayList<ThuThu>();
        Cursor c = db.rawQuery(sql,selectionArgs);
        while (c.moveToNext()){
            ThuThu obj = new ThuThu();
            obj.setHtTT(c.getString(c.getColumnIndex("hoTen")));
            obj.setTkTT(c.getString(c.getColumnIndex("taikhoanTT")));
            obj.setMkTT(c.getString(c.getColumnIndex("matKhau")));
            list.add(obj);
        }
        return list;
    }

    public int checkLogin(String id, String password){
        String sql = "SELECT * FROM ThuThu WHERE taikhoanTT=? AND matKhau=?";
        List<ThuThu> list = getData(sql,id,password);
        if (list.size()==0){
            return -1;
        }
        return 1;
    }
}
