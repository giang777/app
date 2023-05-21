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
import com.example.appagile.dao.ThanhVienDao;
import com.example.appagile.elements.ThanhVien;
import com.google.android.material.textfield.TextInputLayout;

public class AddUser extends Dialog {
    private Context context;
    private TextInputLayout name,numberphone;
    private Button button;
    private Load_list loadList;
    public AddUser(@NonNull Context context,Load_list loadList) {
        super(context);
        this.context = context;
        this.loadList = loadList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_adduser);
        anh_xa();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTv = name.getEditText().getText().toString();
                String sđtTV = numberphone.getEditText().getText().toString();
                boolean check = true;
                if(nameTv.isEmpty()){
                    name.setError("Không được để chống tên !");
                    check = false;
                }else{
                    name.setError(null);
                    check = true;
                }

                if(sđtTV.isEmpty()){
                    numberphone.setError("Không được để chống số điện thoại !");
                    check = false;
                }else{
                    String reSđt = "^0[0-9]{9,10}$";
                    if(sđtTV.matches(reSđt)){
                        numberphone.setError(null);
                        check = true;
                    }else{
                        numberphone.setError("Không đúng định dạng số điện thoại !");
                        check = false;
                    }

                }

                if(check){
                    ThanhVienDao dao = new ThanhVienDao(context);
                    ThanhVien tv = new ThanhVien();
                    tv.setName(nameTv);
                    tv.setSđt(sđtTV);
                    long xet = dao.insert(tv);

                    if(xet >= 1){
                        Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        loadList.load_ds();
                        dismiss();
                    }else{
                        Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void anh_xa(){
        name = findViewById(R.id.editText_nameTv);
        numberphone = findViewById(R.id.editText_SdtTv);
        button= findViewById(R.id.btn_addUser);
    }
}
