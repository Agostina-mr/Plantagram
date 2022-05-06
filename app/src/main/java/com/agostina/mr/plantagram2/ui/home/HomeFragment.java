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
import com.agostina.mr.plantagram2.model.plants.Plants;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;
    private PlantAdapter plantAdapter;
     private ArrayList<Plants> plantsList = new ArrayList<>();
    private RecyclerView plantsRecyclerView;

    private FirebaseRecyclerOptions<Plants> options;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        plantsRecyclerView = root.findViewById(R.id.recycler_view);

        setUpRV(root);
        return root;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
      //  homeViewModel.getAllPlants();
        homeViewModel.getAllPlants().observe(getViewLifecycleOwner(), plants -> {
            plantsList.addAll(plants);
            plantAdapter.updatePlantList(plantsList);
        });
        setUpRV(view);
    }

    private void setUpRV(View view) {
        plantsRecyclerView.hasFixedSize();
        plantsRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
         plantAdapter = new PlantAdapter(plantsList);
         Observer<List<Plants>> update = plantAdapter::updatePlantList;
         homeViewModel.getAllPlants().observe(getViewLifecycleOwner(), update);
        plantsRecyclerView.setAdapter(plantAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}