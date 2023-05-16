package com.example.appagile.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.appagile.R;
import com.example.appagile.fragment.quan_ly.QuanLyLoaiSach;
import com.example.appagile.fragment.quan_ly.QuanLyPhieuMuon;
import com.example.appagile.fragment.quan_ly.QuanLySach;
import com.example.appagile.fragment.quan_ly.QuanLyThanhVien;
import com.example.appagile.fragment.tai_khoan.DoiMatKhau;
import com.example.appagile.fragment.tai_khoan.TaoTaiKhoan;
import com.example.appagile.fragment.thong_ke.DoanhThu;
import com.example.appagile.fragment.thong_ke.Top10;
import com.google.android.material.navigation.NavigationView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private View view;
    private LayoutInflater inflater;
    @Override
    //Xin chào ae :))
    //XIn chào 2
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.drawerLayout = findViewById(R.id.drawerLayout);
        this.toolbar = findViewById(R.id.toolBar);
        this.navigationView = findViewById(R.id.NavigationDrawer);

        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,toolbar,R.string.Open,R.string.Close);
        drawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_titel1_trangChu:
                setTitle("Trang chủ");
                drawerLayout.closeDrawer(GravityCompat.START);
                load_fragment(new QuanLyThanhVien());
                Toast.makeText(this, "Trang chủ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_titel1_thanhVien:
                setTitle("Quản lý thành viên");
                drawerLayout.closeDrawer(GravityCompat.START);
                load_fragment(new QuanLyThanhVien());
                Toast.makeText(this, "Quản lý thành viên", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_titel1_sach:
                setTitle("Quản lý sách");
                drawerLayout.closeDrawer(GravityCompat.START);
                load_fragment(new QuanLySach());
                Toast.makeText(this, "Quản lý sách", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_titel1_loaiSach:
                load_fragment(new QuanLyLoaiSach());
                setTitle("Quản lý loại sách");
                drawerLayout.closeDrawer(GravityCompat.START);
                Toast.makeText(this, "Quản lý loại sách", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_titel1_phieuMuon:
                setTitle("Quản lý phiếu mượn");
                load_fragment(new QuanLyPhieuMuon());
                drawerLayout.closeDrawer(GravityCompat.START);
                Toast.makeText(this, "Quản lý phiếu mượn", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_titel2_top:
                setTitle("Top 10");
                drawerLayout.closeDrawer(GravityCompat.START);
                load_fragment(new Top10());
                Toast.makeText(this, "Top 10", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_titel2_doanhThu:
                setTitle("Doanh thu");
                load_fragment(new DoanhThu());
                drawerLayout.closeDrawer(GravityCompat.START);
                Toast.makeText(this, "Doanh thu", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_titel3_newUSer:
                load_fragment(new TaoTaiKhoan());
//                if(thuThu.getTkTT().equals("Admin")){
//                    setTitle("Tạo tài khoản");
//                    load_fragment(new TaoTaiKhoan());
//                    drawerLayout.closeDrawer(GravityCompat.START);
//                    Toast.makeText(this, "Tạo tài khoản", Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(this, "Chức năng này chỉ dành cho Admin", Toast.LENGTH_SHORT).show();
//                }
                break;
            case R.id.menu_titel3_change_Password:
                setTitle("Đổi mật khẩu");
                load_fragment(new DoiMatKhau());
                drawerLayout.closeDrawer(GravityCompat.START);
                Toast.makeText(this, "Đổi mật khẩu", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_titel3_log_out:
                Toast.makeText(this, "Đăng xuất", Toast.LENGTH_SHORT).show();
                ProgressDialog dialog = new ProgressDialog(MainActivity.this);
                dialog.setTitle("Vui lòng chờ");
                dialog.setMessage("Đang đăng xuất");
                dialog.show();
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        startActivity(new Intent(MainActivity.this, Login.class));
                    }
                },3000);
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
        }

        return true;
    }

    private void load_fragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_activity,fragment);
        fragmentTransaction.commit();
    }
}