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
import com.example.appagile.dao.LoaiSachDao;
import com.example.appagile.dao.PhieuMuonDao;
import com.example.appagile.dao.ThanhVienDao;
import com.example.appagile.dialog.EditLoaiSach;
import com.example.appagile.dialog.EditUser;
import com.example.appagile.elements.LoaiSach;
import com.example.appagile.elements.PhieuMuon;
import com.example.appagile.elements.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class ItemLoaiSach extends RecyclerView.Adapter<ItemLoaiSach.LoaiSachHoler> {
    private Context context;
    private List<LoaiSach> list = new ArrayList<>();
    public ItemLoaiSach(Context context){
        this.context = context;
    }
    public void setList(List<LoaiSach> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LoaiSachHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_loai_sach,parent,false);

        return new LoaiSachHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiSachHoler holder, int position) {
        LoaiSach loaiSach = list.get(position);
        if(loaiSach == null){
            return;
        }
        holder.nameLs.setText("Loại : "+ loaiSach.getNameLs());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn xóa ko ?");
                builder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LoaiSachDao dao = new LoaiSachDao(context);
                        PhieuMuonDao phieuMuonDao = new PhieuMuonDao(context);
                        List<PhieuMuon> thanhVienList = phieuMuonDao.getAll();
                        boolean xet = true;

                        for(PhieuMuon item : thanhVienList){
                            if(loaiSach.getId() == item.getMaSach()){
                                xet = false;
                            }
                        }

                        if(xet){
                            dao.delete(String.valueOf(loaiSach.getId()));
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
                EditLoaiSach editLoaiSach = new EditLoaiSach(context, loaiSach, new Load_list() {
                    @Override
                    public void load_ds() {
                        LoaiSachDao dao = new LoaiSachDao(context);
                        list.clear();
                        list = dao.getAll();
                        setList(list);
                    }
                });
                editLoaiSach.show();
                editLoaiSach.setCancelable(true);
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

    public class LoaiSachHoler extends RecyclerView.ViewHolder{
        private TextView nameLs;
        private ImageView delete,edit;
        public LoaiSachHoler(@NonNull View itemView) {
            super(itemView);
            nameLs = itemView.findViewById(R.id.item_loai_sach_name);
            delete = itemView.findViewById(R.id.item_loai_sach_delete);
            edit = itemView.findViewById(R.id.item_loai_sach_edit);
        }
    }

}
