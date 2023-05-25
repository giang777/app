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

import java.util.ArrayList;
import java.util.List;

public class SpinnerLoaiSach extends ArrayAdapter<LoaiSach> {
    private Context context;
    private ArrayList<LoaiSach> list;
    TextView tvTenLoaiSach;

    public SpinnerLoaiSach(@NonNull Context context, ArrayList<LoaiSach> list) {
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
            v = inflater.inflate(R.layout.item_spinner_loai_sach,null);
        }

        final LoaiSach item = list.get(position);
        if(item != null){


            tvTenLoaiSach = v.findViewById(R.id.txt_loaisach);
            tvTenLoaiSach.setText(item.getNameLs());
        }



        return v;
    }
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_spinner_loai_sach,null);
        }

        final LoaiSach item = list.get(position);
        if(item != null){


            tvTenLoaiSach = v.findViewById(R.id.txt_loaisach);
            tvTenLoaiSach.setText(item.getNameLs());
        }

        return v;
    }
}
