package com.agostina.mr.plantagram2.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.agostina.mr.plantagram2.R;
import com.agostina.mr.plantagram2.databinding.FragmentHomeBinding;
import com.agostina.mr.plantagram2.model.plants.Plant;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;
    // private PlantAdapter plantAdapter;
    // private ArrayList<Plant> plantList = new ArrayList<>();
    private RecyclerView plantsRecyclerView;
    private HomeFirebaseAdapter homeFirebaseAdapter;
    private FirebaseRecyclerOptions<Plant> options;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        plantsRecyclerView = root.findViewById(R.id.recycler_view);
        options =
                new FirebaseRecyclerOptions.Builder<Plant>()
                        .setQuery(homeViewModel.getPlantsQuery(), Plant.class).setLifecycleOwner(getViewLifecycleOwner())
                        .build();
        homeFirebaseAdapter = new HomeFirebaseAdapter(options);
        setUpRV(root);
        return root;
    }

    /*@Override
    public void onStart() {
        super.onStart();
        homeFirebaseAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        homeFirebaseAdapter.stopListening();
    }*/

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
     /* //  homeViewModel.getAllPlants();
        homeViewModel.getAllPlants().observe(getViewLifecycleOwner(), plants -> {
            plantList.addAll(plants);
            plantAdapter.updatePlantList(plantList);
        });*/
        //setUpRV(view);
    }

    private void setUpRV(View view) {
        plantsRecyclerView.hasFixedSize();
        plantsRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        // plantAdapter = new PlantAdapter(plantList);
        // Observer<List<Plant>> update = plantAdapter::updatePlantList;
        // homeViewModel.getAllPlants().observe(getViewLifecycleOwner(), update);
        plantsRecyclerView.setAdapter(homeFirebaseAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}