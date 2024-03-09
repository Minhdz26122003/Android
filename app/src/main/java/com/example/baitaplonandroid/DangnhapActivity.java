package com.example.baitaplonandroid;

import static java.util.Collections.replaceAll;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.baitaplonandroid.Adapter.AccoundAdapter;
import com.example.baitaplonandroid.Bottom.HistroryFragment;
import com.example.baitaplonandroid.Bottom.HomeFragment;
import com.example.baitaplonandroid.Bottom.ThongtincanhanFragment;
import com.example.baitaplonandroid.database.database;
import com.example.baitaplonandroid.database.database;
import com.google.android.material.navigation.NavigationView;

public class DangnhapActivity extends AppCompatActivity {
    EditText username,password;
    Button dn,dk;
    database database;
    private static boolean isLoggedIn;
    private MenuItem dangNhapMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        findid();
        database=new database(this);


        dn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString();
                String pass = password.getText().toString();
                //pass.replaceAll("\\s", "");
                //database.ghidata("create table TruyenYeuThich(idtruyen integer, username TEXT, foreign key (idtruyen) references Truyen(idtruyen), foreign key (username) references users(username), primary key (idtruyen, username))");
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(DangnhapActivity.this, "Username, password không được để trống", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean check = database.Checkuserpass(name, pass);

                    if (check) {
                        isLoggedIn = true;
                        Toast.makeText(DangnhapActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);

                        ThongtincanhanFragment.setUsername(name);
                        HistroryFragment.setUsername(name);
                        MainActivity.setUsername(name);

                        NanoMachineAT nn=new NanoMachineAT();
                        nn.setUsername(name);

                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(DangnhapActivity.this, "Đăng nhập không thành công", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(DangnhapActivity.this,DangkyActivity.class);
                startActivity(i);
            }
        });
    }
    public boolean checkLogin(){
        if(!isLoggedIn){
            return false;
        }
        return true;
    }
    private void findid(){
        username=findViewById(R.id.tkdn);
        password=findViewById(R.id.mkdn);
        dn=findViewById(R.id.dangnhap);
        dk=findViewById(R.id.chcotk);

    }

}