package com.example.appagile.fragment.quan_ly;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appagile.R;
import com.example.appagile.activity.Load_list;
import com.example.appagile.adapter.ItemPhieuMuon;
import com.example.appagile.dao.PhieuMuonDao;
import com.example.appagile.dialog.AddPhieuMuon;
import com.example.appagile.elements.PhieuMuon;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;



public class QuanLyPhieuMuon extends Fragment {
    private RecyclerView recyclerView;
    private FloatingActionButton button;
    private PhieuMuonDao phieuMuonDao;
    private List<PhieuMuon> list;
    private ItemPhieuMuon itemPhieuMuon;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_phieu_muon,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.Fragment_phieuMuonTrue_RecycelView);
        button = view.findViewById(R.id.Fragment_PhieuMuon_FloatBTN);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddPhieuMuon addPhieuMuon = new AddPhieuMuon(getActivity(), new Load_list() {
                    @Override
                    public void load_ds() {
                        onStart();
                    }
                });
                addPhieuMuon.show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        phieuMuonDao = new PhieuMuonDao(getActivity());
        list = phieuMuonDao.getAll();
        itemPhieuMuon = new ItemPhieuMuon(getActivity());

        itemPhieuMuon.setList(list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(itemPhieuMuon);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
