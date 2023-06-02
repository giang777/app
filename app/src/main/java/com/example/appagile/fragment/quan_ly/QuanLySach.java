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
import com.example.appagile.adapter.ItemSach;
import com.example.appagile.dao.LoaiSachDao;
import com.example.appagile.dao.SachDao;
import com.example.appagile.dialog.AddLoaiSach;
import com.example.appagile.dialog.AddSach;
import com.example.appagile.elements.LoaiSach;
import com.example.appagile.elements.Sach;
import com.example.appagile.elements.ThanhVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class QuanLySach extends Fragment {

    private FloatingActionButton button;
    private RecyclerView listView;

    private ItemSach itemSach;
    private SachDao sachDao;
    private SearchView searchView;
    private List<Sach> list;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sach,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button = view.findViewById((R.id.Fragment_Sach_FloatBTN));
        listView = view.findViewById(R.id.Fragment_Sach_RecycelView);
        searchView = view.findViewById(R.id.Fragment_Sach_SearchView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddSach addSach = new AddSach(getActivity(), new Load_list() {
                    @Override
                    public void load_ds() {
                        onStart();
                    }
                });
                addSach.show();
                addSach.setCancelable(true);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                List<Sach> sachList = new ArrayList<>();

                if(s.equals("")){
                    itemSach.setList(list);
                }else{
                    for(Sach item : list){
                        if(item.getName().toLowerCase().contains(s.toLowerCase())){
                            sachList.add(item);
                        }
                    }
                    itemSach.setList(sachList);
                }


                return true;
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        itemSach = new ItemSach(getActivity());
        sachDao = new SachDao(getActivity());
        list = sachDao.getAll();
        itemSach.setList(list);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        listView.setLayoutManager(manager);
        listView.setAdapter(itemSach);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
