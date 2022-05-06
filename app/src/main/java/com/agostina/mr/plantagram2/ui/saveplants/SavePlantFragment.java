package com.agostina.mr.plantagram2.ui.saveplants;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.agostina.mr.plantagram2.R;
import com.agostina.mr.plantagram2.databinding.FragmentSavePlantBinding;
import com.agostina.mr.plantagram2.model.plants.Images;
import com.agostina.mr.plantagram2.model.plants.Plants;
import com.agostina.mr.plantagram2.model.plants.Suggestions;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SavePlantFragment extends Fragment {

    private SavePlantViewModel viewModel;
    private RecyclerView imagesRV;
    private RecyclerView suggestionsRV;
    private List<Images> images = new ArrayList<>();
    private List<Suggestions> suggestions = new ArrayList<>();
    private TextInputEditText userInput;


    private SavePlantImagesAdapter imagesAdapter;
    private SavePlantSuggestionsAdapter suggestionsAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(SavePlantViewModel.class);
        com.agostina.mr.plantagram2.databinding.FragmentSavePlantBinding binding = FragmentSavePlantBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        imagesRV = view.findViewById(R.id.plant_images_rv);
        suggestionsRV = view.findViewById(R.id.plant_suggestions_rv);
        viewModel.getIdentifiedPlant().observe(getViewLifecycleOwner(), plant -> {
            images = plant.getImages();
            suggestions = plant.getSuggestions();
        });
        userInput = view.findViewById(R.id.plant_comment_input);
        setUpImagesRecyclerView(view);
        setUpSuggestionsRecyclerView(view);

        Button saveButton = view.findViewById(R.id.save_plant_b);
        saveButton.setOnClickListener(b -> savePlant(view));
    }

    private void setUpImagesRecyclerView(View view) {
        imagesRV.hasFixedSize();
        imagesRV.setLayoutManager(new LinearLayoutManager(view.getContext()));
        imagesAdapter = new SavePlantImagesAdapter(images);
        imagesRV.setAdapter(imagesAdapter);
        viewModel.getIdentifiedPlant().observe(getViewLifecycleOwner(), plant -> {
            imagesAdapter.updatePlantImages(images);
        });
    }

    private void setUpSuggestionsRecyclerView(View view) {
        suggestionsRV.hasFixedSize();
        suggestionsRV.setLayoutManager(new LinearLayoutManager(view.getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        suggestionsAdapter = new SavePlantSuggestionsAdapter(suggestions);
        suggestionsRV.setAdapter(suggestionsAdapter);
        viewModel.getIdentifiedPlant().observe(getViewLifecycleOwner(), plant -> {
            suggestionsAdapter.updatePlantSuggestions(suggestions);
        });
    }

    private void savePlant(View view) {
        Plants plants = viewModel.getIdentifiedPlant().getValue();
        if (!Objects.requireNonNull(userInput.getText()).toString().equals("")) {
            String comments = userInput.getText().toString();
            assert plants != null;
            plants.setUserComment(comments);
        } else {
            userInput.getText().toString();
        }
        viewModel.savePlant(plants);
        Navigation.findNavController(view).navigate(R.id.plantagram_main_fragment);
    }
}
