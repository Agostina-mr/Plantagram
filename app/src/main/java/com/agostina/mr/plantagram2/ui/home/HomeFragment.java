package com.agostina.mr.plantagram2.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.agostina.mr.plantagram2.R;
import com.agostina.mr.plantagram2.databinding.FragmentHomeBinding;
import com.agostina.mr.plantagram2.model.plants.Plant;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;
    private PlantAdapter plantAdapter;
    private ArrayList<Plant> plantList = new ArrayList<>();
    private RecyclerView plantsRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        plantsRecyclerView = root.findViewById(R.id.recycler_view);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        homeViewModel.getAllPlants();
        homeViewModel.getAllPlants().observe(getViewLifecycleOwner(), plants -> {
            plantList.addAll(plants);
            plantAdapter.updatePlantList(plantList);
        });
        setUpRV(view);
    }

    private void setUpRV(View view){
        plantsRecyclerView.hasFixedSize();
        plantsRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        plantAdapter = new PlantAdapter(plantList);
        Observer<List<Plant>> update = plantAdapter::updatePlantList;
        homeViewModel.getAllPlants().observe(getViewLifecycleOwner(), update);
        plantsRecyclerView.setAdapter(plantAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}