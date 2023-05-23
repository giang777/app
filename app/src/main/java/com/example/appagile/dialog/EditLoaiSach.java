package com.example.appagile.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.appagile.R;
import com.example.appagile.activity.Load_list;
import com.example.appagile.dao.LoaiSachDao;
import com.example.appagile.dao.ThanhVienDao;
import com.example.appagile.elements.LoaiSach;
import com.example.appagile.elements.ThanhVien;
import com.google.android.material.textfield.TextInputLayout;

public class EditLoaiSach extends Dialog {
    private Context context;
    private TextInputLayout nameLS;
    private Button button;
    private LoaiSach loaiSach;
    private Load_list loadList;

    public EditLoaiSach(Context context, LoaiSach loaiSach, Load_list loadList) {
        super(context);
        this.context = context;
        this.loaiSach = loaiSach;
        this.loadList = loadList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_edit_loaisach);
        anh_xa();
        nameLS.getEditText().setText(loaiSach.getNameLs());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameLs = nameLS.getEditText().getText().toString();
                boolean check = true;
                if(nameLs.isEmpty()){
                    nameLS.setError("Không được để chống tên !");
                    check = false;
                }else{
                    nameLS.setError(null);
                    check = true;
                }

                if(check){
                    LoaiSachDao dao = new LoaiSachDao(context);
                    loaiSach.setNameLs(nameLs);
                    int xet = (int) dao.update(loaiSach);

                    if(xet >= 1){
                        Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
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
        nameLS = findViewById(R.id.editText_nameLs);
        button= findViewById(R.id.btn_editLs);
    }
}
