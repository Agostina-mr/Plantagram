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
import com.agostina.mr.plantagram2.model.plants.Plant;
import com.agostina.mr.plantagram2.model.post.PlantPost;
import com.agostina.mr.plantagram2.model.plants.Suggestions;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

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
        Plant plant = viewModel.getIdentifiedPlant().getValue();
        assert plant != null;
        PlantPost plantPost = new PlantPost();
        plantPost.setUserName(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getDisplayName());
        if (FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl()!=null)
        {
            plantPost.setUserPicture(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl()).toString());
        }
        String toAdd = plant.getImages().get(0).getUrl();
        plantPost.setPicture(toAdd);
        String nameToAdd = "";
        for (Suggestions suggestions: plant.getSuggestions()
             ) {
       nameToAdd = (nameToAdd.concat(suggestions.getPlant_name()).concat(" | "));

        }
        plantPost.setPlantName(nameToAdd);
        plantPost.setPlantDescription(plant.getSuggestions().get(0).getPlant_details().getWiki_description().getValue());
        if (!Objects.requireNonNull(userInput.getText()).toString().equals("")) {
            String comment = userInput.getText().toString();
            assert plant != null;
            plantPost.setAuthorComment(comment);
        } else {
            userInput.getText();
        }
        viewModel.savePlant(plantPost);
        Navigation.findNavController(view).navigate(R.id.plantagram_main_fragment);
    }
}
