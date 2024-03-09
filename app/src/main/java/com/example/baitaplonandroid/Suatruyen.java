package com.example.baitaplonandroid;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.baitaplonandroid.database.database;

import java.io.ByteArrayOutputStream;

public class Suatruyen extends AppCompatActivity {
    Button sua;
    ImageView imged;
    EditText ided,nameed,nded;
    database database;
    private static final int PICK_IMAGE_REQUEST=100;
    public static final int STORAGE_REQUEST=101;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sua_truyen);

        database = new database(this);
        Findid();
        getdata();
        Selectimg();
        EditTruyen();
    }


    private void Findid() {
        ided=findViewById(R.id.idedit);
        nameed=findViewById(R.id.nameedit);
        nded=findViewById(R.id.noidungedit);
        imged=findViewById(R.id.imgedit);
        sua=findViewById(R.id.sua);
    }
    private void Selectimg() {
        imged.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK, Uri.parse("content://media/internal/images/media"));
                startActivityForResult(intent,PICK_IMAGE_REQUEST);

            }
        });
    }
    private void getdata() {
        ided.setEnabled(false);
        ided.setFocusable(false);
        if(getIntent().getBundleExtra("data")!= null){
            Bundle bundle=getIntent().getBundleExtra("data");
            byte[] bytes=bundle.getByteArray("imagetruyen");
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
            imged.setImageBitmap(bitmap);
            //set
            ided.setText(String.valueOf(bundle.getInt("idtruyen")));
            nameed.setText(bundle.getString("tentruyen"));
            nded.setText(bundle.getString("noidung"));
        }
    }
    private void EditTruyen() {
        sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues contentValues=new ContentValues();
                contentValues.put("idtruyen", ided.getText().toString());
                contentValues.put("tentruyen", nameed.getText().toString());
                contentValues.put("noidung", nded.getText().toString());
                contentValues.put("imagetruyen",imagetoByte(imged));

                SQLiteDatabase sqLiteDatabase=database.getWritableDatabase();
                long result=sqLiteDatabase.update("Truyen",contentValues, "idtruyen=?", new String[]{ided.getText().toString()});
                if(result != -1){
                    Toast.makeText(Suatruyen.this,"Sửa thành công ",Toast.LENGTH_SHORT).show();
                    ided.setText("");
                    nameed.setText("");
                    nded.setText("");
                    imged.setImageResource(R.drawable.ic_launcher_background);
                }
                else{
                    Toast.makeText(Suatruyen.this,"Sửa không thành công",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public static byte[] imagetoByte(ImageView img) {
        Bitmap bm=((BitmapDrawable)img.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG,50,stream);
        byte[] bytes=stream.toByteArray();
        return bytes;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE_REQUEST){
            Uri uri = data.getData();
            imged.setImageURI(uri);
        }

    }
}
