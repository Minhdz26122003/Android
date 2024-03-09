package com.example.baitaplonandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import com.example.baitaplonandroid.Adapter.AccoundAdapter;
import com.example.baitaplonandroid.DTO.Taikhoan;
import com.example.baitaplonandroid.database.database;

import java.util.ArrayList;

public class Quanlytaikhoan extends AppCompatActivity {
    database database;
    RecyclerView recycleview;
    SQLiteDatabase sqLiteDatabase;
    TextView tentk, pass;
    SearchView searchView;
    Toolbar toolbar;
    ArrayList<Taikhoan> tklist;
    AccoundAdapter adaptertr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtaikhoan);
        // Khởi tạo sqlHelper
        database = new database(this);
        tklist = new ArrayList<>();
        Findid();
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);//nut return
        dislayData();
    }
    private void Findid() {
        tentk = findViewById(R.id.texttentk);
        pass = findViewById(R.id.password);
        recycleview = findViewById(R.id.recycletk);
        toolbar = findViewById(R.id.toolbar_tk);
    }
    public void dislayData() {
        database = new database(this);
        sqLiteDatabase = database.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from users", null);
        tklist = new ArrayList<>();
        while (cursor.moveToNext()) {
            String ten = cursor.getString(0);
            String passs=cursor.getString(1);
            tklist.add(new Taikhoan(ten, passs));
        }
        cursor.close();
        adaptertr = new AccoundAdapter(this, R.layout.item_tk,tklist, sqLiteDatabase);
        recycleview.setAdapter(adaptertr);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_bar, menu);
        //khoi tao tim kiem
        searchView=(SearchView) menu.findItem(R.id.timkiem).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adaptertr.getFilter().filter(s);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                adaptertr.getFilter().filter(s);
                if (s.isEmpty()) {
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
            item.setVisible(false);
        }
        else if(item.getItemId() ==R.id.tool_capnhat){
            dislayData();
        }
        return super.onOptionsItemSelected(item);
    }
    public void refreshData() {
        dislayData();
    }
}