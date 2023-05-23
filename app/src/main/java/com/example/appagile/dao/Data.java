package com.example.appagile.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class Data extends SQLiteOpenHelper {
    private static final String NAME_DATABASE = "thu_vien.db";
    private static final int VERSION = 2;

    public Data(@Nullable Context context) {
        super(context, NAME_DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableThuThu="create table ThuThu (" +
                "taikhoanTT text PRIMARY KEY UNIQUE NOT NULL, " +
                "hoTen text NOT NULL, " +
                "matKhau text NOT NULL)";
        db.execSQL(createTableThuThu);

        String createTableThanhVien="create table ThanhVien (" +
                "maTV INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "hoTen text NOT NULL, " +
                "sđt text NOT NULL)";
        db.execSQL(createTableThanhVien);

        String createTableLoaiSach="create table LoaiSach (" +
                "maLoai INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "tenLoai text NOT NULL)";
        db.execSQL(createTableLoaiSach);

        String createTableSach="create table Sach (" +
                "maSach INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tenSach text NOT NULL, " +
                "giaThue INTEGER NOT NULL, " +
                "maLoai INTEGER REFERENCES LoaiSach(maLoai))";
        db.execSQL(createTableSach);

        String createTablePhieuMuon="create table PhieuMuon (" +
                "maPM INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "taikhoanTT text REFERENCES ThuThu(taikhoanTT), " +
                "maTV INTEGER REFERENCES ThanhVien(maTV), " +
                "maSach INTEGER REFERENCES Sach(maSach), " +
                "tienThue INTEGER NOT NULL, " +
                "ngay DATE NOT NULL, " +
                "traSach INTEGER NOT NULL )";
        db.execSQL(createTablePhieuMuon);

        String admin = "INSERT INTO ThuThu VALUES('ADMIN','Nguyễn Minh Giang','123456')";
        db.execSQL(admin);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String dropTableThuThu = "drop table if exists ThuThu";
        db.execSQL(dropTableThuThu);
        String dropTableThanhVien = "drop table if exists ThanhVien";
        db.execSQL(dropTableThanhVien);
        String dropTableLoaiSach = "drop table if exists LoaiSach";
        db.execSQL(dropTableLoaiSach);
        String dropTableSach = "drop table if exists Sach";
        db.execSQL(dropTableSach);
        String dropTablePhieuMuon = "drop table if exists PhieuMuon";
        db.execSQL(dropTablePhieuMuon);

        onCreate(db);
    }
}
