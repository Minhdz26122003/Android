package com.example.baitaplonandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baitaplonandroid.Bottom.HistroryFragment;
import com.example.baitaplonandroid.NNMT.Chap1;
import com.example.baitaplonandroid.NNMT.Chap2;
import com.example.baitaplonandroid.database.database;

public class NanoMachineAT extends AppCompatActivity {
    TabHost th;
    TextView tentr, noidung;
    ImageView imgv;
    database database;
    Button chap1, chap2,addbm;
    public static String username;

    private static boolean isLoggedIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nano_machine_at);
        Findid();
        database = new database(this);
        getdata();
        setuptab();

        updateAddBmButton(isLoggedIn);

        //
        chap1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chap1();
            }
        });
        chap2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chap2();
            }
        });
        addbm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                themTruyenYeuThich();
            }
        });


    }

    public void Chap1() {
        Intent intent = getIntent();
        String textContent = intent.getStringExtra("textContent");
        if (textContent != null && (textContent.equals("1") || textContent.equals("2") || textContent.equals("3"))) {
            Intent it = new Intent(getApplicationContext(), Chap1.class);

            it.putExtra("textContent", textContent); // Sửa key thành "textContent"
            startActivity(it);
        }else if (textContent != null && textContent.equals("2")) {
            Intent it = new Intent(getApplicationContext(), Chap1.class);
            it.putExtra("textContent", textContent);
            startActivity(it);
        }else if (textContent != null && textContent.equals("3")) {
            Intent it = new Intent(getApplicationContext(), Chap1.class);

            it.putExtra("textContent", textContent);
            startActivity(it);
        }else if (textContent != null && textContent.equals("4")) {
            Intent it = new Intent(getApplicationContext(), Chap1.class);

            it.putExtra("textContent", textContent);
            startActivity(it);
        }else if (textContent != null && textContent.equals("5")) {
            Intent it = new Intent(getApplicationContext(), Chap1.class);

            it.putExtra("textContent", textContent);
            startActivity(it);
        }

    }
    public void Chap2() {
        Intent intent = getIntent();
        String textContent2 = intent.getStringExtra("textContent");
        if (textContent2 != null && textContent2.equals("1")) {
            Intent it = new Intent(getApplicationContext(), Chap2.class);

            it.putExtra("textContent", textContent2);
            startActivity(it);
        }else if (textContent2 != null && textContent2.equals("2")) {
            Intent it = new Intent(getApplicationContext(), Chap2.class);

            it.putExtra("textContent", textContent2);
            startActivity(it);
        }else if (textContent2 != null && textContent2.equals("3")) {
            Intent it = new Intent(getApplicationContext(), Chap2.class);

            it.putExtra("textContent", textContent2);
            startActivity(it);
        }else if (textContent2 != null && textContent2.equals("4")) {
            Intent it = new Intent(getApplicationContext(), Chap2.class);

            it.putExtra("textContent", textContent2);
            startActivity(it);
        }else if (textContent2 != null && textContent2.equals("5")) {
            Intent it = new Intent(getApplicationContext(), Chap2.class);

            it.putExtra("textContent", textContent2);
            startActivity(it);
        }

    }
    public void setuptab(){
        th.setup();
        TabHost.TabSpec tspec;
        tspec = th.newTabSpec("t1");
        tspec.setContent(R.id.tabgiothieu);
        tspec.setIndicator("Giới Thiệu");
        th.addTab(tspec);

        tspec = th.newTabSpec("t2");
        tspec.setContent(R.id.tabchapter);
        tspec.setIndicator("Chapter");
        th.addTab(tspec);
        th.setCurrentTab(0);
    }

    private void Findid() {
        th = findViewById(R.id.tabhostnano);
        tentr = findViewById(R.id.tentruyenct);
        noidung = findViewById(R.id.ndchitiet);
        imgv = findViewById(R.id.imgbg);
        chap1 = findViewById(R.id.Chapter1);
        chap2 = findViewById(R.id.Chapter2);
        addbm=findViewById(R.id.addbm);
    }
    public void themTruyenYeuThich() {
        database = new database(this);
        SQLiteDatabase sql =database.getWritableDatabase();
        Intent intent = getIntent();
        String textContent = intent.getStringExtra("textContent");
        ContentValues values = new ContentValues();
        values.put("idtruyen",Integer.valueOf(textContent) );
        values.put("username", username);

        // Chèn dữ liệu vào bảng truyện yêu thích
        long result = sql.insert("TruyenYeuThich", null, values);
        if (result == -1) {
            Toast.makeText(NanoMachineAT.this, "Thêm truyện yêu thích không thành công", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(NanoMachineAT.this, "Thêm truyện yêu thích thành công", Toast.LENGTH_SHORT).show();
        }
        database.close();
    }
    private void getdata() {
        Intent intent = getIntent();
        String textContent = intent.getStringExtra("textContent");

        String tentruyen = "";
        String noidungg = "";
        byte[] imgData;
        Cursor doc = database.docdata("SELECT * FROM Truyen WHERE idtruyen = '" +textContent.toString()+ "'");
        while (doc.moveToNext()) {

            tentruyen = doc.getString(1);
            noidungg = doc.getString(2);
            imgData = doc.getBlob(3);

            tentr.setText(tentruyen);
            noidung.setText(noidungg);
            Bitmap bitmap = BitmapFactory.decodeByteArray(imgData, 0, imgData.length);
            imgv.setImageBitmap(bitmap);

        }
        doc.close();

    }
    public void setUsername(String newUsername) {
        username = newUsername;
    }
    public static void setLoggedInStatus(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
    public void updateAddBmButton(boolean isLoggedIn) {
//        Toast.makeText(NanoMachineAT.this, String.valueOf(isLoggedIn), Toast.LENGTH_SHORT).show();
            if (isLoggedIn == true) {
                addbm.setEnabled(true);
            } else {
                addbm.setEnabled(false);
            }

    }
}