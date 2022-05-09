package com.agostina.mr.plantagram2.ui.community;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.agostina.mr.plantagram2.R;
import com.agostina.mr.plantagram2.databinding.FragmentHomeBinding;
import com.agostina.mr.plantagram2.model.plants.PlantPost;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;

public class CommunityFragment extends Fragment {

    private FragmentHomeBinding binding;
    private CommunityViewModel viewModel;
     private CommunityPlantAdapter plantAdapter;
    private RecyclerView plantsRecyclerView;
    private FirebaseRecyclerOptions<PlantPost> options;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(CommunityViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        plantsRecyclerView = root.findViewById(R.id.recycler_view);
        options =
                new FirebaseRecyclerOptions.Builder<PlantPost>()
                        .setQuery(viewModel.getAllPostsQuery(), snapshot -> {
                            PlantPost plantPost = new PlantPost();
                            for (DataSnapshot ds : snapshot.getChildren()) {
                                plantPost = ds.getValue(PlantPost.class);
                                }
                            return plantPost;
                        }).setLifecycleOwner(this)
                        .build();
        plantAdapter = new CommunityPlantAdapter(options);
        plantAdapter.startListening();
        setUpRV(root);
        return root;
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