package com.example.appagile.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.appagile.R;
import com.example.appagile.activity.Load_list;
import com.example.appagile.activity.MainActivity;
import com.example.appagile.adapter.SpinnerLoaiSach;
import com.example.appagile.adapter.SpinnerSach;
import com.example.appagile.adapter.SpinnerThanhVien;
import com.example.appagile.dao.PhieuMuonDao;
import com.example.appagile.dao.SachDao;
import com.example.appagile.dao.ThanhVienDao;
import com.example.appagile.elements.PhieuMuon;
import com.example.appagile.elements.Sach;
import com.example.appagile.elements.ThanhVien;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddPhieuMuon extends Dialog {

    private Context context;
    private Load_list listPhieuMuon;
    private EditText date;
    private Spinner spinnerSach,spinnerThanhVien;
    private Button btnPhieuMuon;

    private SpinnerSach spinnerSachItem;
    private SpinnerThanhVien spinnerThanhVienItem;

    private List<Sach> sachList;
    private List<ThanhVien> thanhVienList;

    private SachDao sachDao;
    private ThanhVienDao thanhVienDao;

    private int maSach,maTv;

    public AddPhieuMuon(@NonNull Context context,Load_list loadList) {
        super(context);
        this.context = context;
        this.listPhieuMuon = loadList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_phieu_muon);
        anh_xa();
        sachList = sachDao.getAll();
        thanhVienList = thanhVienDao.getAll();
        spinnerSachItem = new SpinnerSach(context, (ArrayList<Sach>) sachList);
        spinnerThanhVienItem = new SpinnerThanhVien(context, (ArrayList<ThanhVien>) thanhVienList);

        spinnerSach.setAdapter(spinnerSachItem);
        spinnerThanhVien.setAdapter(spinnerThanhVienItem);

        spinnerSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maSach = sachList.get(i).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinnerThanhVien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maTv = thanhVienList.get(i).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Date dateNow = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        date.setText(format.format(dateNow));

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        date.setText(i+"-"+(i1+1)+"-"+i2);
                    }
                }, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH),Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });

        btnPhieuMuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhieuMuonDao phieuMuonDao = new PhieuMuonDao(context);
                PhieuMuon phieuMuon = new PhieuMuon();
                Sach sach = sachDao.getID(String.valueOf(maSach));
                phieuMuon.setTrangThai(0);
                phieuMuon.setDate(date.getText().toString());
                phieuMuon.setMaTT(MainActivity.thuThuLogin.getTkTT());
                phieuMuon.setMaTV(maTv);
                phieuMuon.setMaSach(maSach);
                phieuMuon.setPrice(sach.getPrice());

                long check = phieuMuonDao.insert(phieuMuon);
                if(check > 1){
                    Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    listPhieuMuon.load_ds();
                }else{
                    Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
                }
                dismiss();
            }
        });


    }

    private void anh_xa(){
        date = findViewById(R.id.editText_date);
        spinnerSach = findViewById(R.id.spinner_sach);
        spinnerThanhVien = findViewById(R.id.spinner_thanhvien);
        btnPhieuMuon = findViewById(R.id.btn_addPM);
        sachDao = new SachDao(context);
        thanhVienDao = new ThanhVienDao(context);

    }
}
