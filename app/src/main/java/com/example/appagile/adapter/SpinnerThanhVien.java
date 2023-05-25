package com.example.appagile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appagile.R;
import com.example.appagile.elements.LoaiSach;
import com.example.appagile.elements.ThanhVien;

import java.util.ArrayList;

public class SpinnerThanhVien extends ArrayAdapter<ThanhVien> {
    private Context context;
    private ArrayList<ThanhVien> list;
    TextView tvTen;

    public SpinnerThanhVien(@NonNull Context context, ArrayList<ThanhVien> list) {
        super(context, 0,list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v =convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_spinner_thanh_vien,null);
        }

        final ThanhVien item = list.get(position);
        if(item != null){


            tvTen = v.findViewById(R.id.txt_thnahvien);
            tvTen.setText(item.getName());
        }



        return v;
    }
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_spinner_thanh_vien,null);
        }

        final ThanhVien item = list.get(position);
        if(item != null){
            tvTen = v.findViewById(R.id.txt_thnahvien);
            tvTen.setText(item.getName());
        }

        return v;
    }
}
