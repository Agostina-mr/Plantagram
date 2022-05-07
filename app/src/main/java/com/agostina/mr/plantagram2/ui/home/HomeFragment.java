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
import com.agostina.mr.plantagram2.model.plants.PlantPost;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;
     private PlantAdapter plantAdapter;
    private RecyclerView plantsRecyclerView;
    private FirebaseRecyclerOptions<PlantPost> options;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        plantsRecyclerView = root.findViewById(R.id.recycler_view);
        options =
                new FirebaseRecyclerOptions.Builder<PlantPost>()
                        .setQuery(homeViewModel.getQuery(), PlantPost.class).setLifecycleOwner(this)
                        .build();
        plantAdapter = new PlantAdapter(options);
        plantAdapter.startListening();
        setUpRV(root);
        return root;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        setUpRV(view);
        plantAdapter.startListening();

    }

    private void setUpRV(View view) {
        plantsRecyclerView.hasFixedSize();
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        plantsRecyclerView.setLayoutManager(layoutManager);
        plantsRecyclerView.setAdapter(plantAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}