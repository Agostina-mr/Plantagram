package com.agostina.mr.plantagram2.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.agostina.mr.plantagram2.ui.home.HomeFragment;
import com.agostina.mr.plantagram2.ui.request.CameraFragment;

public class PlantagramPagerAdapter extends FragmentStateAdapter {

    public PlantagramPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new HomeFragment();
        if (position == 0)
        {
            fragment = new HomeFragment();
        }
        if (position == 2){
            fragment = new CameraFragment();
        }
        return fragment;

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
