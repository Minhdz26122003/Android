package com.example.baitaplonandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Theloai extends AppCompatActivity {
    Button hd,manga,chuyensinh,xuyenkhhong,manhwa,romance,mecha,harem;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theloai);
        hd=findViewById(R.id.hanhdong);
        manga=findViewById(R.id.manga);
        chuyensinh=findViewById(R.id.Chuyensinh);
        xuyenkhhong=findViewById(R.id.Xuyenkhong);
        manhwa=findViewById(R.id.manhwa);
        harem=findViewById(R.id.harem);
        mecha=findViewById(R.id.mecha);
        romance=findViewById(R.id.romance);

        hd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Theloai.this,"Thể loại: Hành động",Toast.LENGTH_SHORT).show();
            }
        });
        manhwa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Theloai.this,"Thể loại: Manhwa",Toast.LENGTH_SHORT).show();
            }
        });
        manga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Theloai.this,"Thể loại: Manga",Toast.LENGTH_SHORT).show();
            }
        });
        chuyensinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Theloai.this,"Thể loại: Chuyển sinh",Toast.LENGTH_SHORT).show();
            }
        });
        xuyenkhhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Theloai.this,"Thể loại: Xuyên không",Toast.LENGTH_SHORT).show();
            }
        });
        harem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Theloai.this,"Thể loại: Harem",Toast.LENGTH_SHORT).show();
            }
        });
        mecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Theloai.this,"Thể loại: Mecha",Toast.LENGTH_SHORT).show();
            }
        });
        romance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Theloai.this,"Thể loại: Romance",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
