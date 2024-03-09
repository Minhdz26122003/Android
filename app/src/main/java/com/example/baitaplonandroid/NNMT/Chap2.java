package com.example.baitaplonandroid.NNMT;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baitaplonandroid.R;
import com.github.barteksc.pdfviewer.PDFView;

public class Chap2 extends AppCompatActivity {
    PDFView view;
    Button backch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitiettruyen2);
        view=findViewById(R.id.pdf);
        backch=findViewById(R.id.before);
        Intent it=getIntent();
        String ten=it.getStringExtra("textContent");

        if(ten.equals("1")){
            view.fromAsset("TGHT-2.pdf").load();
        }else if(ten.equals("2")){
            view.fromAsset("nlmt-2.pdf").load();
        }else if(ten.equals("3")){
            view.fromAsset("solo-2.pdf").load();
        }
        else if(ten.equals("4")){
            view.fromAsset("dldl-2.pdf").load();
        }
        backch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

    }
    public void back(){
        Intent intent=getIntent();
        String id=intent.getStringExtra("name");
        if(id.equals("1")){
            intent=new Intent(Chap2.this,Chap1.class);
            intent.putExtra("name",id);
            startActivity(intent);
        }else if(id.equals("2")){
            intent=new Intent(Chap2.this,Chap1.class);
            intent.putExtra("name",id);
            startActivity(intent);
        }else if(id.equals("3")){
            intent=new Intent(Chap2.this,Chap1.class);
            intent.putExtra("name",id);
            startActivity(intent);
        }
        else if(id.equals("4")){
            intent=new Intent(Chap2.this,Chap1.class);
            intent.putExtra("name",id);
            startActivity(intent);
        }
    }
}
