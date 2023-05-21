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
import com.example.appagile.adapter.ItemThanhVien;
import com.example.appagile.dao.ThanhVienDao;
import com.example.appagile.dialog.AddUser;
import com.example.appagile.elements.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class QuanLyThanhVien extends Fragment {
    private FloatingActionButton button;
    private RecyclerView listView;
    private ItemThanhVien itemThanhVien;
    private ThanhVienDao thanhVienDao;
    private SearchView searchView;
    private List<ThanhVien> list;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_thanh_vien,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button = view.findViewById(R.id.Fragment_ThanhVien_FloatBTN);
        listView = view.findViewById(R.id.Fragment_ThanhVien_RecycelView);
        searchView = view.findViewById(R.id.Fragment_ThanhVien_SearchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                List<ThanhVien> thanhVienList = new ArrayList<>();

                if(s.equals("")){
                    itemThanhVien.setList(list);
                }else{
                    for(ThanhVien item : list){
                        if(item.getName().toLowerCase().contains(s.toLowerCase())){
                            thanhVienList.add(item);
                        }
                    }
                    itemThanhVien.setList(thanhVienList);
                }


                return true;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddUser addUser = new AddUser(getActivity(), new Load_list() {
                    @Override
                    public void load_ds() {
                        onStart();
                    }
                });
                addUser.show();
                addUser.setCancelable(true);
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        itemThanhVien = new ItemThanhVien(getActivity());
        thanhVienDao = new ThanhVienDao(getActivity());
         list = thanhVienDao.getAll();
        itemThanhVien.setList(list);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        listView.setLayoutManager(manager);
        listView.setAdapter(itemThanhVien);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
