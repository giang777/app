package com.example.appagile.fragment.quan_ly;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appagile.R;
import com.example.appagile.activity.Load_list;
import com.example.appagile.adapter.ItemLoaiSach;
import com.example.appagile.adapter.ItemThanhVien;
import com.example.appagile.dao.LoaiSachDao;
import com.example.appagile.dao.ThanhVienDao;
import com.example.appagile.dialog.AddLoaiSach;
import com.example.appagile.elements.LoaiSach;
import com.example.appagile.elements.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;



public class QuanLyLoaiSach extends Fragment {

    private FloatingActionButton button;
    private RecyclerView listView;
    private ItemLoaiSach itemLoaiSach;
    private LoaiSachDao loaiSachDao;
    private SearchView searchView;
    private List<LoaiSach> list;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_loai_sach,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button = view.findViewById((R.id.Fragment_LoaiSach_FloatBTN));
        listView = view.findViewById(R.id.Fragment_LoaiSach_RecycelView);
        searchView = view.findViewById(R.id.Fragment_LoaiSach_SearchView);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddLoaiSach addLoaiSach = new AddLoaiSach(getActivity(), new Load_list() {
                    @Override
                    public void load_ds() {
                        onStart();
                    }
                });
                addLoaiSach.show();
                addLoaiSach.setCancelable(true);
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                List<LoaiSach> loaiSachList = new ArrayList<>();

                if(s.equals("")){
                    itemLoaiSach.setList(list);
                }else{
                    for(LoaiSach item : list){
                        if(item.getNameLs().toLowerCase().contains(s.toLowerCase())){
                            loaiSachList.add(item);
                        }
                    }
                    itemLoaiSach.setList(loaiSachList);
                }

                return true;
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        itemLoaiSach = new ItemLoaiSach(getActivity());
        loaiSachDao = new LoaiSachDao(getActivity());
        list = loaiSachDao.getAll();
        itemLoaiSach.setList(list);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        listView.setLayoutManager(manager);
        listView.setAdapter(itemLoaiSach);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
