package com.example.baitaplonandroid;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitaplonandroid.Adapter.AdapterTruyen;
import com.example.baitaplonandroid.DTO.Truyen;
import com.example.baitaplonandroid.database.database;

import java.util.ArrayList;

public class MainactivityTruyen extends AppCompatActivity {
    database database;
    RecyclerView recycleview;
    SQLiteDatabase sqLiteDatabase;
    SearchView searchView;
    TextView idtr, namtr,noidung;
    ArrayList<Truyen> arlisttr;
    ImageView imgtr;
    Toolbar toolbar;
    AdapterTruyen adaptertr;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhsachtruyen);

        database = new database(this);
        arlisttr = new ArrayList<>();
        Findid();//        recycleview.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(false);//nut return
        dislayData();

    }
    private void Findid(){
        idtr = findViewById(R.id.iditem);
        namtr = findViewById(R.id.nameitem);
        noidung=findViewById(R.id.noidungitem);
        imgtr = findViewById(R.id.imgitem);
        toolbar = findViewById(R.id.toolbar_truyen);
        recycleview = findViewById(R.id.recycle);
    }

    public void dislayData() {
        sqLiteDatabase = database.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from Truyen", null);
        arlisttr = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String noidung=cursor.getString(2);
            byte[] img = cursor.getBlob(3);

            arlisttr.add(new Truyen(id, name,noidung, img));
        }
        cursor.close();
        adaptertr = new AdapterTruyen(this, R.layout.item_chaptr, arlisttr, sqLiteDatabase);
        recycleview.setAdapter(adaptertr);
        adaptertr.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_bar, menu);
        //khoi tao tim kiem
        searchView=(SearchView) menu.findItem(R.id.timkiem).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adaptertr.getFilter().filter(s);//tìm theo chuỗi s
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                adaptertr.getFilter().filter(s);
                if (s.isEmpty()) {
                    // Nếu từ khóa tìm kiếm trống, hiển thị lại toàn bộ danh sách
                    dislayData();
                }
                return false;
            }

        });

        return super.onCreateOptionsMenu(menu);
    }

    //toolbar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.tool_them) {
            Intent intentthem = new Intent(MainactivityTruyen.this, Themtruyen.class);
            startActivity(intentthem);
        }
        else if(item.getItemId() ==R.id.tool_capnhat){
            dislayData();
        }
        return super.onOptionsItemSelected(item);
    }
}
