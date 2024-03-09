package com.example.baitaplonandroid.Bottom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baitaplonandroid.DangnhapActivity;
import com.example.baitaplonandroid.MainActivity;
import com.example.baitaplonandroid.R;


public class ThongtincanhanFragment extends Fragment {
    private TextView tendn, vaitro;
    private boolean isLoggedOut = false;
    private static String username;
    Button ht;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongtincanhan, container, false);
        tendn = view.findViewById(R.id.tendncn);
        vaitro = view.findViewById(R.id.vaitro);

        // Sử dụng getActivity() để truy cập MainActivity
        MainActivity dn = (MainActivity) getActivity();

        isLoggedOut = dn.checkLogOut();
//        Toast.makeText(getContext(), String.valueOf(isLoggedOut), Toast.LENGTH_SHORT).show();
        if (isLoggedOut) {
            tendn.setText("");
            vaitro.setText("");
        } else {
            if (username != null) {
                tendn.setText(username);
                if (username.equals("admin")) {
                    vaitro.setText("admin");
                } else {
                    vaitro.setText("người dùng");
                }
            }
        }

        return view;
    }

    public static void setUsername(String newUsername) {
        username = newUsername;
    }
}
