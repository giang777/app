package com.example.appagile.fragment.thong_ke;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appagile.R;
import com.example.appagile.dao.ThongKeDao;

import java.text.DecimalFormat;
import java.util.Calendar;



public class DoanhThu extends Fragment {
    private EditText frist_date,last_date;
    private ImageView lich_frist_date,lich_last_date;
    private Button btn_doah_thu;
    private TextView text_doanh_thu;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_doanh_thu,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.frist_date = view.findViewById(R.id.Fragment_DoanhThu_FristDate);
        this.last_date = view.findViewById(R.id.Fragment_DoanhThu_LastDate);
        this.lich_frist_date = view.findViewById(R.id.imageView_doanh_thu_1);
        this.lich_last_date = view.findViewById(R.id.imageView_doanh_thu_2);
        this.btn_doah_thu = view.findViewById(R.id.Fragment_DoanhThu_BtnDoanhThu);
        this.text_doanh_thu = view.findViewById(R.id.Fragment_DoanhThu_TextDoanhThu);

        lich_frist_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        int thg = i1 + 1;
                        if(thg < 10 ){
                            if(i2 < 10){
                                frist_date.setText(""+i+"-0"+thg+"-0"+i2+"");
                            }else{
                                frist_date.setText(""+i+"-0"+thg+"-"+i2+"");
                            }
                        }else{
                            if(i2 < 10){
                                frist_date.setText(""+i+"-"+thg+"-0"+i2+"");
                            }else{
                                frist_date.setText(""+i+"-"+thg+"-"+i2+"");
                            }
                        }

                    }
                },year,month,day);
                dialog.show();
            }
        });

        lich_last_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        int thg = i1 + 1;
                        if(thg < 10 ){
                            if(i2 < 10){
                                last_date.setText(""+i+"-0"+thg+"-0"+i2+"");
                            }else{
                                last_date.setText(""+i+"-0"+thg+"-"+i2+"");
                            }
                        }else{
                            if(i2 < 10){
                                last_date.setText(""+i+"-"+thg+"-0"+i2+"");
                            }else{
                                last_date.setText(""+i+"-"+thg+"-"+i2+"");
                            }
                        }

                    }
                },year,month,day);
                dialog.show();
            }
        });

        btn_doah_thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String day1 = frist_date.getText().toString();
                String day2 = last_date.getText().toString();

                if(day1.length() <=0 || day2.length() <=0){
                    Toast.makeText(getActivity(), "Không được để chống", Toast.LENGTH_SHORT).show();
                }else{
                    String x[] = day1.split("-");
                    String y[] = day2.split("-");

                    String count1 = "";
                    String count2 = "";
                    int tuNgay = Integer.valueOf(count1.concat(x[0]).concat(x[1]).concat(x[2]));
                    int denNgay = Integer.valueOf(count2.concat(y[0]).concat(y[1]).concat(y[2]));

                    if(tuNgay > denNgay){
                        Toast.makeText(getActivity(), "Từ ngày phải nhỏ hơn đến ngày", Toast.LENGTH_SHORT).show();
                    }else{
                        ThongKeDao thongKeDao = new ThongKeDao(getActivity());
                        int doanh_thu = thongKeDao.getDoanhThu(day1,day2);
                        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                        String tien = decimalFormat.format(doanh_thu);
                        text_doanh_thu.setText("Doanh thu : " + tien + " Đ");
                    }
                }
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
