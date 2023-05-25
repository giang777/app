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
import com.example.appagile.elements.Sach;

import java.util.ArrayList;

public class SpinnerSach extends ArrayAdapter<Sach> {
    private Context context;
    private ArrayList<Sach> list;
    TextView tvTenSach;

    public SpinnerSach(@NonNull Context context, ArrayList<Sach> list) {
        super(context,0,list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v =convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_spinner_sach,null);
        }

        final Sach item = list.get(position);
        if(item != null){


            tvTenSach = v.findViewById(R.id.txt_sach);
            tvTenSach.setText(item.getName());
        }



        return v;
    }
    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;

        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_spinner_sach,null);
        }

        final Sach item = list.get(position);
        if(item != null){


            tvTenSach = v.findViewById(R.id.txt_sach);
            tvTenSach.setText(item.getName());
        }

        return v;
    }
}
