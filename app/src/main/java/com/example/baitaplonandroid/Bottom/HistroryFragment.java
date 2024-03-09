package com.example.baitaplonandroid.Bottom;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.example.baitaplonandroid.Adapter.AdapterTruyenlv;
import com.example.baitaplonandroid.DTO.Truyenlv;
import com.example.baitaplonandroid.DangnhapActivity;
import com.example.baitaplonandroid.R;
import java.util.ArrayList;
import com.example.baitaplonandroid.database.database;


public class HistroryFragment extends Fragment {
SQLiteDatabase sqLiteDatabase;
database database;
Toolbar toolbar;
    AdapterTruyenlv adaptertr;
    RecyclerView recyclerView;
    ArrayList<Truyenlv> arlisttr;
    private static String username;
    private static boolean checklogin;

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.tool_bar, menu);
        super.onCreateOptionsMenu(menu, inflater);;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_histrory, container, false);
        Findid(view);
        database=new database(getContext());
        DangnhapActivity dn=new DangnhapActivity();
        checklogin=dn.checkLogin();
        onResume();
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        if(checklogin==true)
        {
            dislayData();
        }

    }


    private void Findid(View view){
        recyclerView=view.findViewById(R.id.recyclelove);
        toolbar=view.findViewById(R.id.toolbar_truyenyt);
    }
    public void dislayData() {
        database=new database(getContext());
        sqLiteDatabase = database.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT tr.idtruyen, tr.tentruyen, tr.imagetruyen FROM Truyen as tr " +
                "INNER JOIN TruyenYeuThich as trl ON tr.idtruyen = trl.idtruyen" +
                " WHERE trl.username = ?", new String[]{username});

        arlisttr = new ArrayList<>();

        while (cursor.moveToNext()) {
            int idtr=cursor.getInt(0);
            String tentr = cursor.getString(1);
            byte[] img = cursor.getBlob(2);
            arlisttr.add(new Truyenlv(idtr,username,tentr, img));
        }
        cursor.close();
        adaptertr = new AdapterTruyenlv(getContext(), R.layout.item_love, arlisttr, sqLiteDatabase);
        recyclerView.setAdapter(adaptertr);
        database.close();
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
    public static void setUsername(String newUsername) {
        username = newUsername;
    }

}