package com.agostina.mr.plantagram2.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.agostina.mr.plantagram2.R;
import com.agostina.mr.plantagram2.databinding.FragmentPlantagramMainBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class PlantagramMainFragment extends Fragment {

    private FragmentPlantagramMainBinding binding;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private PlantagramPagerAdapter adapter;
    private final String[] titles = new String[] {"My garden", "New Plant"};
    public static final int PAGE_NUM = 2;
    public PlantagramMainFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPlantagramMainBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        tabLayout = root.findViewById(R.id.plantagram_tab_layout);
        viewPager = root.findViewById(R.id.plantagram_view_pager);
        adapter = new PlantagramPagerAdapter(this);
        viewPager.setAdapter(adapter);
        new TabLayoutMediator(tabLayout,viewPager,(tab, position) -> tab.setText(titles[position])).attach();

        return root;
    }
    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }
}