package com.example.appagile.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.appagile.R;
import com.example.appagile.activity.Load_list;
import com.example.appagile.adapter.SpinnerLoaiSach;
import com.example.appagile.dao.LoaiSachDao;
import com.example.appagile.dao.SachDao;
import com.example.appagile.elements.LoaiSach;
import com.example.appagile.elements.Sach;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class AddSach extends Dialog {

    private Context context;
    private Load_list listSach;
    private TextInputLayout nameSach,priceSach;
    private Spinner spinnerLoai;
    private Button btnAddSach;
    private SpinnerLoaiSach adapterSpinner;
    private LoaiSachDao loaiSachDao;
    ArrayList<LoaiSach> listLoaiSach;
    private Integer maloai;

    public AddSach(@NonNull Context context, Load_list listSach) {
        super(context);
        this.context = context;
        this.listSach = listSach;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_sach);
        anh_xa();

        listLoaiSach = new ArrayList<LoaiSach>();
        loaiSachDao = new LoaiSachDao(context);
        listLoaiSach = (ArrayList<LoaiSach>) loaiSachDao.getAll();
        adapterSpinner = new SpinnerLoaiSach(context,listLoaiSach);
        spinnerLoai.setAdapter(adapterSpinner);
        spinnerLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maloai = listLoaiSach.get(i).getId();
                Toast.makeText(context, "Chon" +listLoaiSach.get(i).getNameLs(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnAddSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tv_name = nameSach.getEditText().getText().toString();
                String tv_price = priceSach.getEditText().getText().toString();
                boolean check = true;
                if(tv_name.isEmpty()){
                    nameSach.setError("Không được để trống tên ! ");
                    check = false;
                }else {
                    nameSach.setError(null);
                }
                if(tv_price.isEmpty()){
                    priceSach.setError("Không được để trống giá ! ");
                    check = false;
                }else {
                    priceSach.setError(null);
                }

                if(check){
                    SachDao sachDao =  new SachDao(context);
                    Sach sach = new Sach();
                    sach.setName((tv_name));
                    sach.setPrice(Integer.parseInt(tv_price));
                    sach.setMaloai(maloai);
                    long xet = sachDao.insert(sach);

                    if(xet >= 1){
                        Toast.makeText(context, "Thêm thành công ", Toast.LENGTH_SHORT).show();
                        listSach.load_ds();
                        dismiss();
                    }else{
                        Toast.makeText(context, "Thêm thất bại ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
    private void anh_xa(){
        nameSach = findViewById(R.id.editText_nameSach);
        priceSach = findViewById(R.id.editText_priceSach);
        spinnerLoai = findViewById(R.id.spinner_loaisach);
        btnAddSach = findViewById(R.id.btn_addSach);
    }


}
