package com.agostina.mr.plantagram2.ui.garden;

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
import com.agostina.mr.plantagram2.databinding.FragmentGardenBinding;
import com.agostina.mr.plantagram2.model.post.PlantPost;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class GardenFragment extends Fragment {
    private FragmentGardenBinding binding;
    private GardenViewModel viewModel;
    private GardenPlantAdapter adapter;
    private RecyclerView recyclerView;
    private FirebaseRecyclerOptions<PlantPost> options;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(GardenViewModel.class);
        binding = FragmentGardenBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        recyclerView = root.findViewById(R.id.recycler_view);
        options = new FirebaseRecyclerOptions.Builder<PlantPost>().setQuery(viewModel.getSpecificQuery(), PlantPost.class).setLifecycleOwner(this).build();
        adapter = new GardenPlantAdapter(options);
        setUpRV(root);
        return root;
    }

    private void setUpRV(View view) {
        recyclerView.hasFixedSize();
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
       recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}

