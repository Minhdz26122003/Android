package com.example.baitaplonandroid.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.baitaplonandroid.Bottom.HistroryFragment;
import com.example.baitaplonandroid.Bottom.HomeFragment;
import com.example.baitaplonandroid.Bottom.ThongbaoFragment;
import com.example.baitaplonandroid.Bottom.ThongtincanhanFragment;

public class ViewPager2Adapter extends FragmentStateAdapter {
    public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new HistroryFragment();
            case 2:
                return new ThongbaoFragment();
            case 3:
                return new ThongtincanhanFragment();

            default:
                return new HomeFragment();
        }

    }


    @Override
    public int getItemCount() {
        return 4;
    }
}
