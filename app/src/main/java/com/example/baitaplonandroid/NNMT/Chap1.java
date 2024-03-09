package com.example.baitaplonandroid.NNMT;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.baitaplonandroid.R;
import com.github.barteksc.pdfviewer.PDFView;

public class Chap1 extends AppCompatActivity {
    PDFView view;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitiettruyen);
        view=findViewById(R.id.pdf);
        next=findViewById(R.id.next);

        hienthi();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chuyenchap();
            }
        });
    }
    public void hienthi(){
        Intent it=getIntent();
        String ten=it.getStringExtra("textContent");

        if(ten.equals("1")){
            view.fromAsset("TGHT-1.pdf").load();
        }else if(ten.equals("2")){
            view.fromAsset("NLMT-1.pdf").load();
        }else if(ten.equals("3")){
            view.fromAsset("solo-1.1.pdf").load();
        }else if(ten.equals("4")){
            view.fromAsset("dldl-1.pdf").load();
        }
    }
    public void chuyenchap(){
        Intent it=getIntent();
        String ten=it.getStringExtra("textContent");

        if(ten.equals("1")){
            it=new Intent(Chap1.this,Chap2.class);
            it.putExtra("textContent",ten);
            startActivity(it);
        }else if(ten.equals("2")){
            it=new Intent(Chap1.this,Chap2.class);
            it.putExtra("textContent",ten);
            startActivity(it);
        }else if(ten.equals("3")){
            it=new Intent(Chap1.this,Chap2.class);
            it.putExtra("textContent",ten);
            startActivity(it);
        }
        else if(ten.equals("4")){
            it=new Intent(Chap1.this,Chap2.class);
            it.putExtra("textContent",ten);
            startActivity(it);
        }
    }
}
