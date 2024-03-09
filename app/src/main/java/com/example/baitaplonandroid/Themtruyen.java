package com.example.baitaplonandroid;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.baitaplonandroid.database.database;
//import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;

public class Themtruyen extends AppCompatActivity {
    Button them;
    ImageView imgis;
    EditText idis,nameis,ndis;
    database database;
    private static final int PICK_IMAGE_REQUEST=100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nhap_truyen);

        database=new database(this);
        Findid();

        Selectimg();
        InsertTruyen();
    }
    private void Findid() {
        idis=findViewById(R.id.idinsert);
        nameis=findViewById(R.id.nameinsert);
        ndis=findViewById(R.id.noidunginsert);
        imgis=findViewById(R.id.imginsert);
        them=findViewById(R.id.them);
    }
    private void Selectimg() {
        imgis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK, Uri.parse("content://media/internal/images/media"));
                startActivityForResult(intent,PICK_IMAGE_REQUEST);
            }
        });
    }
    public static byte[] imagetoByte(ImageView img) { //chuyển Imageview sang Byte
        Bitmap bm=((BitmapDrawable)img.getDrawable()).getBitmap();//(ma trận điểm ảnh) của một hình ảnh.
        ByteArrayOutputStream stream=new ByteArrayOutputStream();//ByteArrayOutputStream để chứa dữ liệu dạng mảng byte
        bm.compress(Bitmap.CompressFormat.PNG,40,stream);//Nén hình ảnh Bitmap thành định dạng và ghi vào ByteArrayOutputStream.
        byte[] bytes=stream.toByteArray();//Chuyển dữ liệu từ ByteArrayOutputStream thành mảng byte.
        return bytes;
    }
    private void InsertTruyen() {
        them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sqlh.ghidata("delete from truyen");
                ContentValues contentValues=new ContentValues();
                contentValues.put("idtruyen", idis.getText().toString());
                contentValues.put("tentruyen", nameis.getText().toString());
                contentValues.put("noidung", ndis.getText().toString());
                contentValues.put("imagetruyen",imagetoByte(imgis));

                SQLiteDatabase sqLiteDatabase=database.getWritableDatabase();
                Long result=sqLiteDatabase.insert("Truyen",null,contentValues);
                if ( kt(String.valueOf(idis), String.valueOf(nameis))) {
                    Toast.makeText(getApplicationContext(),"Không được để trống",Toast.LENGTH_SHORT).show();
                }else{
                    if(result != -1){
                        Toast.makeText(getApplicationContext(),"Thêm thành công",Toast.LENGTH_SHORT).show();
                        idis.setText("");
                        nameis.setText("");
                        ndis.setText("");
                        imgis.setImageResource(R.drawable.ic_launcher_background);

                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Thêm không thành công",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE_REQUEST){
            Uri uri = data.getData();// Lấy đối tượng Uri từ dữ liệu trả về.
            imgis.setImageURI(uri);// Đặt đối tượng Uri vào ImageView để hiển thị hình ảnh đã chọn
        }
    }
    public boolean kt(String ma, String name) {
        boolean check = true;
        String k = "";
        Cursor doc = database.docdata("select idtruyen from Truyen where idtruyen= '" + ma + "'");
        while (doc.moveToNext()) {
            k = doc.getString(0);
        }
        if (k.isEmpty() || name.isEmpty()) {
            check = false;
        }
        return check;
    }
}
