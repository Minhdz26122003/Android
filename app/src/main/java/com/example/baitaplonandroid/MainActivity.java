package com.example.baitaplonandroid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import androidx.viewpager2.widget.ViewPager2;

import com.example.baitaplonandroid.Adapter.AdapterTruyen;
import com.example.baitaplonandroid.Adapter.ViewPager2Adapter;
import com.example.baitaplonandroid.Bottom.HistroryFragment;
import com.example.baitaplonandroid.DTO.Truyen;
import com.example.baitaplonandroid.NNMT.Chap1;
import com.example.baitaplonandroid.database.database;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private MenuItem dangNhapMenuItem, dangXuatMenuItem,qlt,qltk;
     boolean isLoggedIn;
     boolean isLoggedOut;
    DrawerLayout drawerLayout;
    FragmentManager fragmentManager;
    NavigationView navview;
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    ViewPager2 vp2;
    static String username;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar(); // Lấy ActionBar
        if (actionBar != null) {
            actionBar.hide();
        }
        findid();

        dn();
        NanoMachineAT.setLoggedInStatus(isLoggedIn);
        ViewPager2Adapter viewPager2Adapter = new ViewPager2Adapter(this);
        vp2.setAdapter(viewPager2Adapter);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigaation_drawer_open, R.string.navigaation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navview.setNavigationItemSelectedListener(this);
        bottomNavigationView.setBackground(null);

        vp2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.bottom_home).setChecked(true);
                        break;
                    case 1:
                        if (isLoggedIn==false){
                            Toast.makeText(MainActivity.this, "Phải đăng nhâp để thực hiên chức năng này!", Toast.LENGTH_SHORT).show();
                            bottomNavigationView.getMenu().findItem(R.id.bottom_yeuthich).setChecked(false);
                        }else{
                            bottomNavigationView.getMenu().findItem(R.id.bottom_yeuthich).setChecked(true);
                        }
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.bottom_Thongbao).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.bottom_Thongtin).setChecked(true);
                        break;
                }
            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.bottom_home) {
                    vp2.setCurrentItem(0);
                } else if (itemId == R.id.bottom_yeuthich) {
                    vp2.setCurrentItem(1);
                } else if (itemId == R.id.bottom_Thongbao) {
                    vp2.setCurrentItem(2);
                } else if (itemId == R.id.bottom_Thongtin) {
                    vp2.setCurrentItem(3);
                }
                return true;
            }
        });
        fragmentManager = getSupportFragmentManager();
    }
    private void findid() {
        vp2 = findViewById(R.id.vp2);
        navview = findViewById(R.id.navview);
        drawerLayout = findViewById(R.id.drawerlaout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navview = findViewById(R.id.navview);
        bottomNavigationView = findViewById(R.id.bottomnavgationview);

        dangNhapMenuItem = navview.getMenu().findItem(R.id.dnhap);
        dangXuatMenuItem = navview.getMenu().findItem(R.id.dxuat);
        qlt = navview.getMenu().findItem(R.id.qlt);
        qltk = navview.getMenu().findItem(R.id.qltk);
    }
    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        int itemId = item.getItemId();

         if (itemId == R.id.qltk) {
             if (isLoggedIn && username != null && username.contains("admin")) {
                 Intent i = new Intent(MainActivity.this,Quanlytaikhoan.class);
                 startActivity(i);
             }
        } else if (itemId == R.id.qlt) {
            // Nếu đăng nhập và tên người dùng chứa "admin", thực hiện các thao tác cần thiết
            if (isLoggedIn && username != null && username.contains("admin")) {
                Intent i = new Intent(MainActivity.this, MainactivityTruyen.class);
                startActivity(i);
            }
        } else if (itemId == R.id.type) {
            intent = new Intent(MainActivity.this, Theloai.class);
            startActivity(intent);
        } else if (itemId == R.id.rank) {
            intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        } else if (itemId == R.id.dnhap) {
                intent = new Intent(MainActivity.this, DangnhapActivity.class);
                startActivity(intent);

        } else if (itemId == R.id.dxuat) {
            isLoggedOut = true;

            Toast.makeText(MainActivity.this, "Đã đăng xuất thành công!", Toast.LENGTH_SHORT).show();
            intent = new Intent(MainActivity.this, DangnhapActivity.class);
            startActivity(intent);
            dn();
            finish();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void dn() {
        DangnhapActivity dn = new DangnhapActivity();
        isLoggedIn = dn.checkLogin();

        if (isLoggedIn) {
            dangNhapMenuItem.setVisible(false);
            dangXuatMenuItem.setVisible(true);
            // Khi đăng nhập


            if (username != null && username.contains("admin")) {
                // Hiển thị nút qlt

                qlt.setVisible(true);
                qltk.setVisible(true);
            } else {
                // Ẩn nút qlt
                qlt.setVisible(false);
                qltk.setVisible(false);
            }
        } else {
            dangNhapMenuItem.setVisible(true);
            dangXuatMenuItem.setVisible(false);
            qlt.setVisible(false);
            qltk.setVisible(false);

            // Khi đăng xuất
        }
    }
    public boolean checkLogOut() {
        return isLoggedOut;
    }
    public static void setUsername(String newUsername) {
        username = newUsername;
    }
}