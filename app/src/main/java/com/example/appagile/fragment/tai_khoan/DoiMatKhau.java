package com.example.appagile.fragment.tai_khoan;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appagile.R;
import com.example.appagile.dao.ThuThuDao;
import com.example.appagile.elements.ThuThu;


public class DoiMatKhau extends Fragment {

    private EditText oldpass,newpass,renewpass;
    private Button btnUpdate;
    private ThuThu thuThu;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_doi_mat_khau,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.oldpass = view.findViewById(R.id.FRAGMENT_CHANGE_PASSWORD_ETEXT_OLDPASS);
        this.newpass = view.findViewById(R.id.FRAGMENT_CHANGE_PASSWORD_ETEXT_NEWPASS);
        this.renewpass = view.findViewById(R.id.FRAGMENT_CHANGE_PASSWORD_ETEXT_NEWPASS2);
        this.btnUpdate = view.findViewById(R.id.FRAGMENT_CHANGE_PASSWORD_BTN_CHANGE);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validate();

                SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String user = pref.getString("USERNAME","");


                ThuThuDao thuThuDao = new ThuThuDao(getActivity());
                thuThu = thuThuDao.getID(user);
                thuThu.setMkTT(newpass.getText().toString());
                long check = thuThuDao.update(thuThu);
                if(check < 1){
                    Toast.makeText(getActivity(), "Update không thành công", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getActivity(), "Update thành công", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    private int validate(){
        int check =1;
        if(oldpass.getText().length() <=0 || newpass.getText().length() <=0 || renewpass.getText().length() <=0) {
            Toast.makeText(getActivity(), "Không được để chống", Toast.LENGTH_SHORT).show();
        }else{
            SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE",Context.MODE_PRIVATE);
            String passold = pref.getString("PASSWORD","");
            String pass = newpass.getText().toString();
            String repass = renewpass.getText().toString();

            if(!passold.equals(oldpass.getText().toString())){
                Toast.makeText(getActivity(), "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if(!pass.equals(repass)){
                Toast.makeText(getActivity(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check =-1;
            }
        }
        return check;
    }
}
