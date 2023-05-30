package com.example.appagile.fragment.tai_khoan;

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

import java.util.List;



public class TaoTaiKhoan extends Fragment {
    private EditText name,tk,mk,mk2;
    private Button btn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tao_tai_khoan,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.name = view.findViewById(R.id.FRAGMENT_ADDTT_Name);
        this.tk = view.findViewById(R.id.FRAGMENT_ADDTT_TK);
        this.mk = view.findViewById(R.id.FRAGMENT_ADDTT_MK);
        this.mk2 = view.findViewById(R.id.FRAGMENT_ADDTT_MK2);
        this.btn = view.findViewById(R.id.FRAGMENT_ADDTT_BTN);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname = name.getText().toString();
                String username = tk.getText().toString();
                String passwrd = mk.getText().toString();
                String passwrd2 = mk2.getText().toString();

                if(fullname.length() <=0 || username.length() <=0 || passwrd.length() <=0 || passwrd2.length() <=0){
                    Toast.makeText(getActivity(), "Không được để chống", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!passwrd2.equals(passwrd)){
                    Toast.makeText(getActivity(), "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                    return;
                }

                ThuThuDao thuThuDao = new ThuThuDao(getActivity());
                ThuThu thuThu = new ThuThu(fullname,username,passwrd);
                long check = thuThuDao.insert(thuThu);
                if(check < 1){
                    Toast.makeText(getActivity(), "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();

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
}
