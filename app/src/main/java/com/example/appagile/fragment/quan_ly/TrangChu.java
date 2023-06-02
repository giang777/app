package com.example.appagile.fragment.quan_ly;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.appagile.R;
import com.example.appagile.dao.LoaiSachDao;
import com.example.appagile.dao.PhieuMuonDao;
import com.example.appagile.dao.SachDao;
import com.example.appagile.dao.ThanhVienDao;
import com.example.appagile.dao.ThongKeDao;

import java.text.DecimalFormat;
import java.util.Date;

public class TrangChu extends Fragment {
    private ThongKeDao thongKeDao;

    private TextView tv,ls,s,pm,dt,dtText;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trang_chu,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.thongKeDao = new ThongKeDao(getActivity());
        this.tv = view.findViewById(R.id.FRAGMENT_TRANGCHU_SLTV);
        this.ls = view.findViewById(R.id.FRAGMENT_TRANGCHU_LOAISACH);
        this.s = view.findViewById(R.id.FRAGMENT_TRANGCHU_SACH);
        this.pm = view.findViewById(R.id.FRAGMENT_TRANGCHU_PHIEUMUON);
        this.dtText = view.findViewById(R.id.FRAGMENT_TRANGCHU_THONGKE_TEXT);
        this.dt = view.findViewById(R.id.FRAGMENT_TRANGCHU_THONGKE);

        int tk = thongKeDao.getDoanhThuTheoThang();
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        String tien = decimalFormat.format(tk);




        tv.setText(String.valueOf(new ThanhVienDao(getActivity()).getAll().size()));
        ls.setText(String.valueOf(new LoaiSachDao(getActivity()).getAll().size()));
        s.setText(String.valueOf(new SachDao(getActivity()).getAll().size()));
        pm.setText(String.valueOf(new PhieuMuonDao(getActivity()).getAll().size()));
        dtText.setText("Doanh thu tháng "+(new Date().getMonth()+1)+"/" + ""+new Date().getYear()+"");
        dt.setText(tien + "đồng");
    }
}
