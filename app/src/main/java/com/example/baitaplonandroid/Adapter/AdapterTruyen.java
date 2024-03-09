package com.example.baitaplonandroid.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.widget.Filter;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.baitaplonandroid.DTO.Truyen;
import com.example.baitaplonandroid.R;
import com.example.baitaplonandroid.Suatruyen;
import com.example.baitaplonandroid.database.database;
import java.util.ArrayList;
import java.util.List;

public class AdapterTruyen extends RecyclerView.Adapter<AdapterTruyen.ViewHolder> implements Filterable {
    private Context context;
    private int layout;
    private ArrayList<Truyen> truyenlist;
    private ArrayList<Truyen> truyenlist1;
    SQLiteDatabase sqLiteDatabase;

    public AdapterTruyen(Context context, int layout, ArrayList<Truyen> truyenlist, SQLiteDatabase sqLiteDatabase) {
        this.context = context;
        this.layout = layout;
        this.truyenlist = truyenlist;
        this.sqLiteDatabase = sqLiteDatabase;
        this.truyenlist1=truyenlist;
    }

    @NonNull
    @Override
    public AdapterTruyen.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.item_chaptr,null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTruyen.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Truyen tr=truyenlist.get(position);//lấy vị trí truyện
        byte[] image=tr.getImagetruyen();
        Bitmap bitmap= BitmapFactory.decodeByteArray(image,0,image.length);// Chuyển đổi mảng byte thành đối tượng Bitmap
        holder.img.setImageBitmap(bitmap);
        holder.idtr.setText(String.valueOf(tr.getId()));
        holder.nametr.setText(tr.getTentruyen());
        holder.noidungtr.setText(tr.getNoidung());

        holder.flowmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context,holder.flowmenu);
                popupMenu.inflate(R.menu.popup_tr);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int itemId = item.getItemId();
                        if (itemId == R.id.menu_edit) {
                            Bundle bundle =new Bundle();
                            bundle.putInt("idtruyen",tr.getId());
                            bundle.putString("tentruyen",tr.getTentruyen());
                            bundle.putString("noidung",tr.getNoidung());
                            bundle.putByteArray("imagetruyen",tr.getImagetruyen());
                            Intent intent=new Intent(context, Suatruyen.class);
                            intent.putExtra("data",bundle);
                            context.startActivity(intent);
                        } else if (itemId == R.id.menu_delete) {
                            database sql=new database(context);
                            sqLiteDatabase=sql.getReadableDatabase();
                            long delete=sqLiteDatabase.delete("Truyen","idtruyen="+tr.getId(),null);
                            if(delete !=-1){
                                Toast.makeText(context,"Xóa thành công",Toast.LENGTH_SHORT).show();
                                truyenlist.remove(position);
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
        return truyenlist.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView idtr,nametr,noidungtr;
        ImageView img;
        ImageButton flowmenu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idtr=itemView.findViewById(R.id.iditem);
            nametr=itemView.findViewById(R.id.nameitem);
            noidungtr=itemView.findViewById(R.id.noidungitem);
            img=itemView.findViewById(R.id.imgitem);
            flowmenu=itemView.findViewById(R.id.flowmenu);
        }
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String timkiem = charSequence.toString();//chuỗi tk
                FilterResults filterResults = new FilterResults();

                if (timkiem.isEmpty()) {
                    truyenlist = new ArrayList<>(truyenlist1);
                } else {
                    List<Truyen> tktr = new ArrayList<>();//tạo list chứa các dt
                    for (Truyen p : truyenlist1) {//duyệt list nếu tên = chuỗi ,add vào list
                        if (p.getTentruyen().toLowerCase().contains(timkiem.toLowerCase())) {
                            tktr.add(p);
                        }
                    }
                    truyenlist = new ArrayList<>(tktr);
                }
                filterResults.values = truyenlist;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                truyenlist = (ArrayList<Truyen>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}
