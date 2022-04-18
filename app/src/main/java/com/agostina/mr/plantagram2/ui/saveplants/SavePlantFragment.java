package com.agostina.mr.plantagram2.ui.saveplants;

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
import com.agostina.mr.plantagram2.databinding.FragmentSavePlantBinding;
import com.agostina.mr.plantagram2.model.plants.Images;
import com.agostina.mr.plantagram2.model.plants.Suggestions;

import java.util.ArrayList;

public class SavePlantFragment extends Fragment {

    private SavePlantViewModel viewModel;
    private FragmentSavePlantBinding binding;
    private RecyclerView imagesRV;
    private RecyclerView suggestionsRV;
    private ArrayList<Images> images = new ArrayList<>();
    private ArrayList<Suggestions> suggestions = new ArrayList<>();


    private SavePlantImagesAdapter imagesAdapter;
    private SavePlantSuggestionsAdapter suggestionsAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(SavePlantViewModel.class);
        binding = FragmentSavePlantBinding.inflate(inflater,container,false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
       imagesRV = view.findViewById(R.id.plant_images_rv);
       suggestionsRV = view.findViewById(R.id.plant_suggestions_rv);
       viewModel.getIdentifiedPlant().observe(getViewLifecycleOwner(), plant ->{
            images = plant.getImages();
            suggestions = plant.getSuggestions();
        });
        setUpImagesRecyclerView(view);
        setUpSuggestionsRecyclerView(view);
    }
    private void setUpImagesRecyclerView(View view)
    {
        imagesRV.hasFixedSize();
        imagesRV.setLayoutManager(new LinearLayoutManager(view.getContext()));
        imagesAdapter = new SavePlantImagesAdapter(images);
        imagesRV.setAdapter(imagesAdapter);
        viewModel.getIdentifiedPlant().observe(getViewLifecycleOwner(), plant ->{
            imagesAdapter.updatePlantImages(images);
        });
    }

    private void setUpSuggestionsRecyclerView(View view){
        suggestionsRV.hasFixedSize();
        suggestionsRV.setLayoutManager(new LinearLayoutManager(view.getContext()));
        suggestionsAdapter = new SavePlantSuggestionsAdapter(suggestions);
        suggestionsRV.setAdapter(suggestionsAdapter);
        viewModel.getIdentifiedPlant().observe(getViewLifecycleOwner(), plant -> {
            suggestionsAdapter.updatePlantSuggestions(suggestions);
        });
    }
}
