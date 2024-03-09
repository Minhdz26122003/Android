package com.example.baitaplonandroid;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baitaplonandroid.database.database;

public class Suatk extends AppCompatActivity {
    Button sua;
    EditText tentk,password;
    database database;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sua_tk);

        database = new database(this);
        Findid();
        getdata();

        EditTruyen();
    }

    private void EditTruyen() {
        sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues=new ContentValues();
                contentValues.put("username", tentk.getText().toString());
                contentValues.put("password", password.getText().toString());

                SQLiteDatabase sqLiteDatabase=database.getWritableDatabase();
                long result=sqLiteDatabase.update("users",contentValues, "username=?", new String[]{tentk.getText().toString()});
                if(result != -1){
                    Toast.makeText(Suatk.this,"Sửa thành công ",Toast.LENGTH_SHORT).show();
                    tentk.setText("");
                    password.setText("");
                }
                else{
                    Toast.makeText(Suatk.this,"Sửa không thành công",Toast.LENGTH_SHORT).show();
                }
                //laydulieu();
            }
        });
    }

    private void Findid() {
        tentk=findViewById(R.id.edttk);
        password=findViewById(R.id.edpass);
        sua=findViewById(R.id.suatk);
    }
    private void getdata() {
        tentk.setEnabled(false);
        if(getIntent().getBundleExtra("data")!= null){
            Bundle bundle=getIntent().getBundleExtra("data");
            tentk.setText(bundle.getString("username"));
            password.setText(bundle.getString("password"));

        }
    }

}
