package com.example.baitaplonandroid.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baitaplonandroid.DTO.Taikhoan;
import com.example.baitaplonandroid.DTO.Truyen;
import com.example.baitaplonandroid.R;
import com.example.baitaplonandroid.Suatk;
import com.example.baitaplonandroid.Suatruyen;
import com.example.baitaplonandroid.database.database;

import java.util.ArrayList;
import java.util.List;

public class AccoundAdapter extends RecyclerView.Adapter<AccoundAdapter.ViewHolder> implements Filterable {
    private Context context;
    private int layout;
    private ArrayList<Taikhoan> tklist;
    private ArrayList<Taikhoan> tklist1;
    SQLiteDatabase sqLiteDatabase;

    public AccoundAdapter( Context context, int layout, ArrayList<Taikhoan> tklist,SQLiteDatabase sqLiteDatabase) {
        this.context = context;
        this.layout = layout;
        this.tklist = tklist;
        this.sqLiteDatabase = sqLiteDatabase;
        this.tklist1=tklist;
    }

    @NonNull
    @Override
    public AccoundAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.item_tk,null);

        return new AccoundAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AccoundAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Taikhoan tk=tklist.get(position);
        holder.tentk.setText(tk.getUername());
        holder.password.setText(tk.getPassword());
        holder.flowmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.flowmenu);
                popupMenu.inflate(R.menu.popup_tr);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int itemId = item.getItemId();
                        if (itemId == R.id.menu_edit) {
                            Toast.makeText(context, "Chỉ cho phép sửa mật khẩu", Toast.LENGTH_SHORT).show();
                            Bundle bundle = new Bundle();
                            bundle.putString("username", tk.getUername());
                            bundle.putString("password", tk.getPassword());
                            Intent intent = new Intent(context, Suatk.class);
                            intent.putExtra("data", bundle);
                            context.startActivity(intent);
                        } else if (itemId == R.id.menu_delete) {
                            database sql = new database(context);
                            sqLiteDatabase = sql.getWritableDatabase(); // Mở database để có thể xóa dữ liệu
                            long delete = sqLiteDatabase.delete("users", "username=?", new String[]{tk.getUername()});
                            if (delete != -1) {
                                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                tklist.remove(position);
                                notifyDataSetChanged();
                            }
                        } else {
                            return false;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return tklist.size();
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String timkiem = charSequence.toString();
                FilterResults filterResults = new FilterResults();

                if (timkiem.isEmpty()) {
                    tklist = new ArrayList<>(tklist1);
                } else {
                    List<Taikhoan> tktk = new ArrayList<>();
                    for (Taikhoan p : tklist1) {
                        if (p.getUername().toLowerCase().contains(timkiem.toLowerCase())) {
                            tktk.add(p);
                        }
                    }
                    tklist = new ArrayList<>(tktk);
                }

                filterResults.values = tklist;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                tklist = (ArrayList<Taikhoan>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tentk,password;
        ImageButton flowmenu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tentk=itemView.findViewById(R.id.texttentk);
            password=itemView.findViewById(R.id.password);
            flowmenu=itemView.findViewById(R.id.flowmenutk);
        }
    }
}
