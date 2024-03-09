package com.example.baitaplonandroid.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {

    public static final String DBNAME="Appdoctruyen1.db";
    public database(Context context) {
        super(context, DBNAME, null, 1);
    }
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(username TEXT primary key, password TEXT)");
        db.execSQL("create table Truyen(idtruyen integer primary key ,tentruyen TEXT ,noidung TEXT,imagetruyen blob not null)");
        db.execSQL("create table TruyenYeuThich(idtruyen integer, username TEXT, foreign key (idtruyen) references Truyen(idtruyen), foreign key (username) references users(username), primary key (idtruyen, username))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists users");
        db.execSQL("drop table if exists Truyen");
        db.execSQL("drop table if exists TruyenYeuThich");
    }
    public void ghidata(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor docdata(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        return database.rawQuery(sql, null);
    }
    public Boolean SignUp(String username, String pass){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",pass);
        long result=db.insert("users",null,contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }
    public Boolean Checkusername(String username){
        SQLiteDatabase db=getWritableDatabase();
        Cursor c=db.rawQuery("select * from users where username=?",new String[] {username});
        if (c.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean Checkuserpass(String username,String pass){
        SQLiteDatabase db=getWritableDatabase();
        Cursor c=db.rawQuery("select * from users where username=? and password=?",new String[] {username,pass});
        if (c.getCount()>0)
            return true;
        else
            return false;


    }

}
