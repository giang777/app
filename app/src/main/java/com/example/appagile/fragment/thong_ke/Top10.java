package com.example.appagile.fragment.thong_ke;

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
import com.example.appagile.adapter.ItemTop;
import com.example.appagile.dao.ThongKeDao;
import com.example.appagile.elements.ThanhVien;
import com.example.appagile.elements.Top;

import java.util.ArrayList;
import java.util.List;



public class Top10 extends Fragment {

    private RecyclerView recyclerView;
    private List<Top> list;
    private ItemTop itemTop;
    private ThongKeDao thongKeDao;
    private SearchView searchView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_top10,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.recyclerView = view.findViewById(R.id.Fragment_Top_RecycelView);
        this.list = new ArrayList<>();
        this.searchView = view.findViewById(R.id.Fragment_Top_SearchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                List<Top> topList = new ArrayList<>();

                if(s.equals("")){
                    itemTop.setList(list);
                }else{
                    for(Top item : list){
                        if(item.getTen_sach().toLowerCase().contains(s.toLowerCase())){
                            topList.add(item);
                        }
                    }
                    itemTop.setList(topList);
                }


                return true;
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        this.itemTop = new ItemTop(getActivity());
        this.thongKeDao = new ThongKeDao(getActivity());

        list = thongKeDao.getTop();
        itemTop.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(itemTop);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
