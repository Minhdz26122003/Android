package com.example.baitaplonandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.baitaplonandroid.Adapter.AccoundAdapter;
import com.example.baitaplonandroid.database.database;

public class DangkyActivity extends AppCompatActivity {

    EditText username,password,repass;
    Button dn,dk;
    database database;
    AccoundAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        password=findViewById(R.id.mk);
        username=findViewById(R.id.tk);
        repass=findViewById(R.id.lmk);
        dn=findViewById(R.id.dangnhaptk);
        database=new database(this);

        dn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(),DangnhapActivity.class);
                startActivity(i);
            }
        });
        dk=findViewById(R.id.dangky);
        dk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=username.getText().toString();
                String pass=password.getText().toString();
                String repas=repass.getText().toString();
                if(TextUtils.isEmpty(name) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repas) && kt(name,pass)==false){
                    Toast.makeText(DangkyActivity.this,"Username, password không được để trống",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pass.equals(repas)){
                        Boolean check= database.Checkusername(name);
                        if(check==false){
                            Boolean insert=database.SignUp(name,pass);
                            if(insert==true){
                                    Toast.makeText(DangkyActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(getApplicationContext(), DangnhapActivity.class);
                                    username.setText("");
                                    password.setText("");
                                    repass.setText("");
                                    startActivity(i);
                                    finish();

                            }else{
                                Toast.makeText(DangkyActivity.this,"Đăng ký không thành công",Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(DangkyActivity.this,"Tài khoản đã tồn tại",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(DangkyActivity.this,"Mật khẩu không khớp nhau",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public boolean kt(String user,String pass){
        if( user.length() > 5 && pass.length() > 5){
            return true;
        }
        Toast.makeText(DangkyActivity.this,"Tài khoản và mật khẩu phải nhiều hơn 5 kí tự",Toast.LENGTH_SHORT).show();
        return false;
    }
}