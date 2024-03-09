package com.example.baitaplonandroid.Bottom;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.baitaplonandroid.MainActivity;
import com.example.baitaplonandroid.NNMT.Chap1;
import com.example.baitaplonandroid.NanoMachineAT;
import com.example.baitaplonandroid.R;

public class HomeFragment extends Fragment {
    ImageView tghtnoi,nnmtnoi,slnoi,dldl,tltd,ttdg,thah;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        view= inflater.inflate(R.layout.fragment_home, container, false);

        //image
        slnoi =view.findViewById(R.id.imageViewmoi4);
        tghtnoi =view.findViewById(R.id.imageViewmoi2);
        nnmtnoi=view.findViewById(R.id.imageViewmoi3);
        dldl=view.findViewById(R.id.imageViewmoi1);
        tltd=view.findViewById(R.id.imageViewfull1);
        ttdg=view.findViewById(R.id.imageViewfull4);
        thah=view.findViewById(R.id.imageViewfull2);

        dldl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v != null) {
                    String idd="4";
                    Intent intent = new Intent(getContext(), NanoMachineAT.class);
                    intent.putExtra("textContent", idd);
                    startActivity(intent);
                }
               
            }
        });

        slnoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v != null) {
                    String idd="3";
                    Intent intent = new Intent(getContext(), NanoMachineAT.class);
                    intent.putExtra("textContent", idd);
                    startActivity(intent);
                }
            }
        });

        tghtnoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v != null) {
                    String idd="1";
                    Intent intent = new Intent(getContext(), NanoMachineAT.class);
                    intent.putExtra("textContent", idd);
                    startActivity(intent);
                }
            }
        });

        nnmtnoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v != null) {
                    String idd="2";
                    Intent intent = new Intent(getContext(), NanoMachineAT.class);
                    intent.putExtra("textContent", idd);
                    startActivity(intent);
                }
            }
        });

        tltd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v != null) {
                    String idd="5";
                    Intent intent = new Intent(getContext(), NanoMachineAT.class);
                    intent.putExtra("textContent", idd);
                    startActivity(intent);
                }
            }
        });

        ttdg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v != null) {
                    String idd="6";
                    Intent intent = new Intent(getContext(), NanoMachineAT.class);
                    intent.putExtra("textContent", idd);
                    startActivity(intent);
                }
            }
        });
        thah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (v != null) {
//                    String idd="7";
//                    Intent intent = new Intent(getContext(), NanoMachineAT.class);
//                    intent.putExtra("textContent", idd);
//                    startActivity(intent);
//                }
            }
        });
        return view;
    }

}