package com.example.appagile.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appagile.R;
import com.example.appagile.activity.Load_list;
import com.example.appagile.dao.LoaiSachDao;
import com.example.appagile.dao.PhieuMuonDao;
import com.example.appagile.dao.SachDao;
import com.example.appagile.dialog.EditLoaiSach;
import com.example.appagile.dialog.EditSach;
import com.example.appagile.elements.LoaiSach;
import com.example.appagile.elements.PhieuMuon;
import com.example.appagile.elements.Sach;

import java.util.ArrayList;
import java.util.List;

public class ItemSach extends RecyclerView.Adapter<ItemSach.ViewHolder> {

    private Context context;
    private List<Sach> list;

    public ItemSach(Context context) {
        this.context = context;
    }
    public void setList(List<Sach> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_sach,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Sach sach = list.get(position);
        if(sach == null){
            return;
        }
        LoaiSachDao loaiSachDao = new LoaiSachDao(context);
        LoaiSach loaiSach = loaiSachDao.getID(String.valueOf(sach.getMaloai()));
        holder.tv_namesach.setText("Tên sách : "+sach.getName());
        holder.tv_pricesach.setText("Giá : "+sach.getPrice()+"");
        holder.tv_loaisach.setText(("Loại sách : "+loaiSach.getNameLs()+""));

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có muốn xóa ko ?");
                builder.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SachDao dao = new SachDao(context);
                        PhieuMuonDao phieuMuonDao = new PhieuMuonDao(context);
                        List<PhieuMuon> thanhVienList = phieuMuonDao.getAll();
                        boolean xet = true;

                        for(PhieuMuon item : thanhVienList){
                            if(sach.getId() == item.getMaSach()){
                                xet = false;
                            }
                        }

                        if(xet){
                            dao.delete(String.valueOf(sach.getId()));
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
                EditSach editSach = new EditSach(context, sach,new Load_list() {
                    @Override
                    public void load_ds() {
                        SachDao dao = new SachDao(context);
                        list.clear();
                        list = dao.getAll();
                        setList(list);
                    }
                });
                editSach.show();
                editSach.setCancelable(true);
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_namesach, tv_pricesach,tv_loaisach;
        private ImageView edit,delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_namesach = itemView.findViewById(R.id.item_sach_name);
            tv_pricesach = itemView.findViewById(R.id.item_sach_price);
            tv_loaisach = itemView.findViewById(R.id.item_sach_loaisach);
            edit = itemView.findViewById(R.id.item_sach_edit);
            delete = itemView.findViewById(R.id.item_sach_delete);
        }
    }
}
