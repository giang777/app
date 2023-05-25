package com.example.appagile.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appagile.R;
import com.example.appagile.activity.Load_list;
import com.example.appagile.dao.PhieuMuonDao;
import com.example.appagile.dao.SachDao;
import com.example.appagile.dao.ThanhVienDao;
import com.example.appagile.dialog.EditPhieuMuon;
import com.example.appagile.elements.PhieuMuon;
import com.example.appagile.elements.Sach;
import com.example.appagile.elements.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class ItemPhieuMuon extends RecyclerView.Adapter<ItemPhieuMuon.PhieuMuonHoler> {

    private Context context;
    private List<PhieuMuon> list = new ArrayList<>();

    public void setList(List<PhieuMuon> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public ItemPhieuMuon(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public PhieuMuonHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_phieu_muon,parent,false);

        return new PhieuMuonHoler(view);
    }

    @SuppressLint("Range")
    @Override
    public void onBindViewHolder(@NonNull PhieuMuonHoler holder, int position) {
        PhieuMuon phieuMuon = list.get(position);
        if(phieuMuon == null){
            return;
        }

        ThanhVienDao thanhVienDao = new ThanhVienDao(context);
        ThanhVien thanhVien = thanhVienDao.getID(String.valueOf(phieuMuon.getMaTV()));
        holder.name.setText("Tên : " + thanhVien.getName());

        SachDao sachDao = new SachDao(context);
        Sach sach = sachDao.getID(String.valueOf(phieuMuon.getMaSach()));
        holder.book.setText("Sách : " + sach.getName());
        holder.price.setText("Giá : " + String.valueOf(phieuMuon.getPrice()));
        holder.date.setText("Ngày mượn : " + phieuMuon.getDate());
        if(phieuMuon.getTrangThai() == 0){
            holder.status.setTextColor(Color.parseColor("#625DB9"));
            holder.status.setText("Trạng thái : " + "Chưa trả");
            holder.check.setVisibility(View.VISIBLE);
        }else{
            holder.status.setTextColor(Color.parseColor("#7CFC00"));
            holder.status.setText("Trạng thái : " + "Đã trả");
            holder.check.setVisibility(View.INVISIBLE);
        }

        holder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phieuMuon.setTrangThai(1);
                PhieuMuonDao phieuMuonDao = new PhieuMuonDao(context);
                int check = phieuMuonDao.update(phieuMuon);
                if(check == 1){
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
                }
                list.clear();
                list = phieuMuonDao.getAll();
                setList(list);
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditPhieuMuon editPhieuMuon = new EditPhieuMuon(context, phieuMuon, new Load_list() {
                    @Override
                    public void load_ds() {
                        PhieuMuonDao phieuMuonDao = new PhieuMuonDao(context);
                        list.clear();
                        list = phieuMuonDao.getAll();
                        setList(list);
                    }
                });
                editPhieuMuon.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list.isEmpty()){
            return 0;
        }

        return list.size();
    }

    public class PhieuMuonHoler extends RecyclerView.ViewHolder{
        private TextView name,book,price,status,date;
        private ImageView edit,check;
        public PhieuMuonHoler(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_phieuMuon_name);
            book = itemView.findViewById(R.id.item_phieuMuon_book);
            price = itemView.findViewById(R.id.item_phieuMuon_price);
            status = itemView.findViewById(R.id.item_phieuMuon_status);
            date = itemView.findViewById(R.id.item_phieuMuon_date);
            edit = itemView.findViewById(R.id.item_phieuMuon_edit);
            check = itemView.findViewById(R.id.item_phieuMuon_tich);

        }
    }
}
