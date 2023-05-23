package com.example.appagile.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.appagile.R;
import com.example.appagile.activity.Load_list;
import com.example.appagile.dao.LoaiSachDao;
import com.example.appagile.elements.LoaiSach;
import com.google.android.material.textfield.TextInputLayout;

public class AddLoaiSach extends Dialog {
    private Context context;
    private Load_list listLoaiSach;
    private TextInputLayout nameLs;
    private Button btnAddLs;

    public AddLoaiSach(@NonNull Context context,Load_list listLoaiSach) {
        super(context);
        this.context = context;
        this.listLoaiSach = listLoaiSach;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_loaisach);
        anh_xa();
        btnAddLs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameLS = nameLs.getEditText().getText().toString();
                boolean check = true;
                if(nameLS.isEmpty()){
                    nameLs.setError("Không được để trống tên ! ");
                    check = false;
                }else {
                    nameLs.setError(null);
                }

                if(check){
                    LoaiSachDao loaiSachDao =  new LoaiSachDao(context);
                    LoaiSach ls = new LoaiSach();
                    ls.setNameLs((nameLS));
                    long xet = loaiSachDao.insert(ls);

                    if(xet >= 1){
                        Toast.makeText(context, "Thêm thành công ", Toast.LENGTH_SHORT).show();
                        listLoaiSach.load_ds();
                        dismiss();
                    }else{
                        Toast.makeText(context, "Thêm thất bại ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    private void anh_xa(){
        nameLs = findViewById(R.id.editText_nameLs);
        btnAddLs = findViewById(R.id.btn_addLs);
    }
}
