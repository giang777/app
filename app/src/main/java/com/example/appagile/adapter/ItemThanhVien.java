package com.example.appagile.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.example.appagile.dao.ThanhVienDao;
import com.example.appagile.dialog.EditUser;
import com.example.appagile.elements.PhieuMuon;
import com.example.appagile.elements.ThanhVien;

import java.util.ArrayList;
import java.util.List;


public class ItemThanhVien extends RecyclerView.Adapter<ItemThanhVien.ThanhVienHoler> {
    private Context context;
    private List<ThanhVien> list = new ArrayList<>();


    public ItemThanhVien(Context context){
        this.context = context;
    }

    public void setList(List<ThanhVien> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ThanhVienHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_thanh_vien,parent,false);

        return new ThanhVienHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThanhVienHoler holder, int position) {
        ThanhVien thanhVien = list.get(position);
        if(thanhVien == null){
            return;
        }
        holder.name.setText("Tên : " + thanhVien.getName());
        holder.numberPhone.setText("Số điện thoại : " + thanhVien.getSđt());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn xóa ko ?");
                builder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ThanhVienDao dao = new ThanhVienDao(context);
                        PhieuMuonDao phieuMuonDao = new PhieuMuonDao(context);
                        List<PhieuMuon> thanhVienList = phieuMuonDao.getAll();
                        boolean xet = true;

                        for(PhieuMuon item : thanhVienList){
                            if(thanhVien.getId() == item.getMaTV()){
                                xet = false;
                            }
                        }

                        if(xet){
                            dao.delete(String.valueOf(thanhVien.getId()));
                            list.clear();
                            list = dao.getAll();
                            setList(list);
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Không được xóa , nó chưa chả tiền !", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("No", null);
                builder.show();
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditUser vien = new EditUser(context, thanhVien, new Load_list() {
                    @Override
                    public void load_ds() {
                        ThanhVienDao dao = new ThanhVienDao(context);
                        list.clear();
                        list = dao.getAll();
                        setList(list);
                    }
                });
                vien.show();
                vien.setCancelable(true);
            }

        });
    }

    @Override
    public int getItemCount() {
        if(list != null){
            return list.size();
        }
        return 0;
    }

    public class ThanhVienHoler extends RecyclerView.ViewHolder{
        private TextView name,numberPhone;
        private ImageView delete,edit;
        public ThanhVienHoler(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_thanh_vien_name);
            numberPhone = itemView.findViewById(R.id.item_thanh_vien_numberPhone);
            delete = itemView.findViewById(R.id.item_thanh_vien_delete);
            edit = itemView.findViewById(R.id.item_thanh_vien_edit);
        }
    }
}
