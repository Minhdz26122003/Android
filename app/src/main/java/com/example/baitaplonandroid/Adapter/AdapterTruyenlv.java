package com.example.baitaplonandroid.Adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitaplonandroid.DTO.Truyenlv;
import com.example.baitaplonandroid.R;
import com.example.baitaplonandroid.Suatruyen;
import com.example.baitaplonandroid.database.database;

import java.util.ArrayList;

public class AdapterTruyenlv extends RecyclerView.Adapter<AdapterTruyenlv.ViewHolder> {
    private Context context;
    private int layout;
    private ArrayList<Truyenlv> truyenlist;
    SQLiteDatabase sqLiteDatabase;

    public AdapterTruyenlv(Context context,int layout, ArrayList<Truyenlv> truyenlist, SQLiteDatabase sqLiteDatabase) {
        this.context = context;
        this.layout = layout;
        this.truyenlist = truyenlist;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_love, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Truyenlv truyen = truyenlist.get(position);
        holder.nametrlv.setText(truyen.getNameTr());

        // Load hình ảnh từ byte array và hiển thị trong ImageView
        byte[] image = truyen.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
        holder.img.setImageBitmap(bitmap);

        holder.flowmenulv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gọi hàm xử lý khi click vào flowmenulv
                showPopupMenu(holder.flowmenulv, truyen);
            }
        });
    }

    @Override
    public int getItemCount() {
        return truyenlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nametrlv;
        ImageView img;
        ImageButton flowmenulv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nametrlv = itemView.findViewById(R.id.nameitemlv);
            img = itemView.findViewById(R.id.imgitemlv);
            flowmenulv = itemView.findViewById(R.id.flowmenulv);
        }
    }

    // Hàm xử lý hiển thị PopupMenu
    private void showPopupMenu(View view, Truyenlv truyen) {
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.inflate(R.menu.popup_trlv);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.menu_deletelv) {

                    deleteTruyenFromDatabase(truyen);
                } else {
                    return false;
                }
                return false;
            }
        });
        popupMenu.show();
    }
    private void deleteTruyenFromDatabase(Truyenlv truyen) {
        database sql = new database(context);
        sqLiteDatabase = sql.getReadableDatabase();
        long delete = sqLiteDatabase.delete("TruyenYeuThich", "idtruyen=" + truyen.getIdTruyen(), null);
        if (delete != -1) {
            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
            truyenlist.remove(truyen);
            notifyDataSetChanged();
        }
    }
}
