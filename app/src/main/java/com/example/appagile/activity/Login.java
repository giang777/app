package com.example.appagile.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appagile.R;
import com.example.appagile.dao.ThuThuDao;
import com.example.appagile.elements.ThuThu;

public class Login extends AppCompatActivity {
    private EditText editText1,editText2;
    private Button button;
    private ThuThuDao dao;
    private CheckBox chkrem;
    @Override
    //tk: admin ( hoa hay thường đều đc )
    //mk : 0343729937
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        anh_xa();

        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        String user = pref.getString("USERNAME","");
        String pass = pref.getString("PASSWORD","");
        Boolean rem = pref.getBoolean("REMEMBER",false);

        editText1.setText(user);
        editText2.setText(pass);
        chkrem.setSelected(rem);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tk = editText1.getText().toString();
                String mk = editText2.getText().toString();
                int ckeck = dao.checkLogin(tk,mk);

                if(ckeck == 1){
                    Toast.makeText(Login.this, "Thành công", Toast.LENGTH_SHORT).show();
                    rememberUser(tk,mk,chkrem.isChecked());
                    Intent intent = new Intent(Login.this,MainActivity.class);
                    ThuThu thuThu = dao.getID(tk);
                    intent.putExtra("thuthuLogin",thuThu);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(Login.this, "Thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void rememberUser(String u,String p,boolean status){
        SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if(!status){
            edit.clear();
        }else{
            edit.putString("USERNAME",u);
            edit.putString("PASSWORD",p);
            edit.putBoolean("REMEMBER",status);
        }
        edit.commit();
    }


    private void anh_xa(){
        editText1 = findViewById(R.id.avtivity_login_TK);
        editText2 = findViewById(R.id.avtivity_login_MK);
        chkrem = findViewById(R.id.avtivity_login_CKB);
        button = findViewById(R.id.avtivity_login_BTN);
        dao = new ThuThuDao(this);
    }
}