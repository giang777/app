package com.example.appagile.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appagile.R;
import com.example.appagile.elements.Top;

import java.util.List;

public class ItemTop extends RecyclerView.Adapter<ItemTop.TopHoler> {

    private Context context;
    private List<Top> list;

    public ItemTop(Context context) {
        this.context = context;
    }

    public void setList(List<Top> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TopHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_top,parent,false);

        return new TopHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopHoler holder, int position) {
        Top top = list.get(position);
        if(top == null){
            return;
        }
        holder.name.setText("Tên sách : "+ top.getTen_sach());
        holder.luot.setText("Lượt mượn : " + String.valueOf(top.getSo_luong()));
    }

    @Override
    public int getItemCount() {
        if(list != null){
            return list.size();
        }
        return 0;
    }

    public class TopHoler extends RecyclerView.ViewHolder{
        private TextView name,luot;
        public TopHoler(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_top_name);
            luot = itemView.findViewById(R.id.item_top_luot);
        }
    }
}
